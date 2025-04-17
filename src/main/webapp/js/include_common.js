function includeHTML(id, file, callback) {
  fetch(file)
    .then(res => res.text())
    .then(data => {
      document.getElementById(id).innerHTML = data;
      if (typeof callback === "function") callback();
    })
    .catch(err => {
      console.error(`Error loading ${file}:`, err);
    });
}

// 메뉴 열기/닫기 토글 함수 (상태 저장 포함)
function toggleMenu(id) {
  const submenu = document.getElementById(id);
  if (!submenu) return;

  const isOpen = submenu.style.display === 'flex';
  submenu.style.display = isOpen ? 'none' : 'flex';

  // localStorage 상태 업데이트
  const openMenus = JSON.parse(localStorage.getItem("openMenus") || "[]");
  if (isOpen) {
    // 닫힌 경우 → 제거
    const updated = openMenus.filter(menuId => menuId !== id);
    localStorage.setItem("openMenus", JSON.stringify(updated));
  } else {
    // 열린 경우 → 추가
    if (!openMenus.includes(id)) {
      openMenus.push(id);
      localStorage.setItem("openMenus", JSON.stringify(openMenus));
    }
  }
}

document.addEventListener("DOMContentLoaded", () => {
  includeHTML("header-include", "admin_header.jsp");
  includeHTML("menu-include", "menutap.jsp", () => {
    const currentPage = window.location.pathname.split("/").pop();
    const links = document.querySelectorAll(".submenu a");

    // 🔹 저장된 열림 상태 불러오기
    const openMenus = JSON.parse(localStorage.getItem("openMenus") || "[]");
    openMenus.forEach(id => {
      const submenu = document.getElementById(id);
      if (submenu) submenu.style.display = "flex";
    });

    // 현재 페이지에 해당하는 링크 활성화
    links.forEach(link => {
      const href = link.getAttribute("href");
      if (href === currentPage) {
        link.classList.add("active");

        const submenu = link.closest(".submenu");
        if (submenu) {
          submenu.style.display = "flex";

          // 메뉴 ID를 localStorage에 저장 (중복 방지)
          const parentId = submenu.getAttribute("id");
          if (parentId && !openMenus.includes(parentId)) {
            openMenus.push(parentId);
            localStorage.setItem("openMenus", JSON.stringify(openMenus));
          }
        }
      }
    });

    // 아무 링크도 매칭되지 않으면 기본 메뉴 열기
    if (!links.length || !links.some(link => link.classList.contains("active"))) {
      const defaultMenu = document.getElementById("userMenu");
      if (defaultMenu) defaultMenu.style.display = "flex";
    }
  });
});
