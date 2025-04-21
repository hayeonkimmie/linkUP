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

// 경로에 따라 localStorage에 사이드바 정보 저장
function setSidebarByPath(pathname) {
  if (pathname.includes("/admin/users")) {
    localStorage.setItem("sidebarTitle", "사용자 관리");
    localStorage.setItem("sidebarIcon", "👤");
  } else if (pathname.includes("/admin/project")) {
    localStorage.setItem("sidebarTitle", "프로젝트 관리");
    localStorage.setItem("sidebarIcon", "📁");
  } else if (pathname.includes("/admin/settlement")) {
    localStorage.setItem("sidebarTitle", "정산 관리");
    localStorage.setItem("sidebarIcon", "💰");
  } else if (pathname.includes("/admin/qna")) {
    localStorage.setItem("sidebarTitle", "Q&A 관리");
    localStorage.setItem("sidebarIcon", "❓");
  }
}

document.addEventListener("DOMContentLoaded", () => {
  const pathname = window.location.pathname;

  // 1️⃣ 경로에 따라 localStorage 저장 (단 한 번)
  setSidebarByPath(pathname);

  // 2️⃣ include로 헤더/메뉴 로드
  includeHTML("header-include", "admin_header.jsp");
  includeHTML("menu-include", "menutap.jsp", () => {
    // ⏱ 3️⃣ 약간의 지연 후 localStorage 적용
    setTimeout(() => {
      const sidebarTitleText = document.getElementById("sidebarTitleText");
      const sidebarIcon = document.getElementById("sidebarIcon");

      const savedTitle = localStorage.getItem("sidebarTitle");
      const savedIcon = localStorage.getItem("sidebarIcon");

      if (sidebarTitleText && savedTitle) sidebarTitleText.textContent = savedTitle;
      if (sidebarIcon && savedIcon) sidebarIcon.textContent = savedIcon;
    }, 50); // ← 요게 핵심입니다. 50~100ms면 충분합니다.

    // 4️⃣ 열려 있던 메뉴 복구
    const openMenus = JSON.parse(localStorage.getItem("openMenus") || "[]");
    openMenus.forEach(id => {
      const submenu = document.getElementById(id);
      if (submenu) submenu.style.display = "flex";
    });

    // 5️⃣ 현재 링크에 active 클래스 부여
    const currentPage = pathname.split("/").pop();
    const links = document.querySelectorAll(".submenu a");

    links.forEach(link => {
      const href = link.getAttribute("href");
      if (href && href.endsWith(currentPage)) {
        link.classList.add("active");

        const submenu = link.closest(".submenu");
        if (submenu) submenu.style.display = "flex";
      }
    });
  });
});
