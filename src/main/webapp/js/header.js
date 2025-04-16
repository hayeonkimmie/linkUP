document.addEventListener('DOMContentLoaded', () => {
  const dropdown = document.querySelector('.dropdown');
  const toggle = dropdown.querySelector('.dropdown-toggle');
  const menu = dropdown.querySelector('.dropdown-menu');

  toggle.addEventListener('click', function (e) {
    e.preventDefault();
    dropdown.classList.toggle('open');
  });

  document.addEventListener('click', function (e) {
    if (!dropdown.contains(e.target)) {
      dropdown.classList.remove('open');
    }
  });
});

document.addEventListener("DOMContentLoaded", () => {
    includeHTML("header-include", "admin_header.html");
    includeHTML("menu-include", "menutap.html", () => {
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
  
  
fetch('header.html')
.then(res => res.text())
.then(data => {
  document.getElementById('header-placeholder').innerHTML = data;

  // ✅ 여기서 드롭다운 이벤트 등록
  const dropdown = document.querySelector('.dropdown');
  if (dropdown) {
    const toggle = dropdown.querySelector('.dropdown-toggle');
    const menu = dropdown.querySelector('.dropdown-menu');

    toggle.addEventListener('click', function (e) {
      e.preventDefault();
      dropdown.classList.toggle('open');
    });

    document.addEventListener('click', function (e) {
      if (!dropdown.contains(e.target)) {
        dropdown.classList.remove('open');
      }
    });
  }
})
.catch(err => console.error('헤더 불러오기 실패:', err));