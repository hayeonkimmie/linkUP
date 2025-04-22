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

function setSidebarByPath(pathname) {
  if (pathname.includes("/admin/users")) {
    localStorage.setItem("sidebarTitle", "사용자 관리");
    localStorage.setItem("sidebarIcon", "👥");
  } else if (pathname.includes("/admin/project")) {
    localStorage.setItem("sidebarTitle", "프로젝트 관리");
    localStorage.setItem("sidebarIcon", "🛠️");
  } else if (pathname.includes("/admin/settlement")) {
    localStorage.setItem("sidebarTitle", "정산 관리");
    localStorage.setItem("sidebarIcon", "💰");
  } else if (pathname.includes("/admin/qna")) {
    localStorage.setItem("sidebarTitle", "Q&A 관리");
    localStorage.setItem("sidebarIcon", "❓");
  } else if (
      pathname === "/admin" ||
      pathname === "/admin/" ||
      pathname.includes("/admin/dashboard")
  ) {
    localStorage.setItem("sidebarTitle", "대시보드");
    localStorage.setItem("sidebarIcon", "📊");
  }
}
document.addEventListener("DOMContentLoaded", () => {
  const pathname = window.location.pathname;
  const search = window.location.search;

  // 최초 경로 기반 sidebar 텍스트 설정
  setSidebarByPath(pathname);

  includeHTML("header-include", "admin_header.jsp");
  includeHTML("menu-include", "menutap.jsp", () => {
    const sidebarTitleText = document.getElementById("sidebarTitleText");
    const sidebarIcon = document.getElementById("sidebarIcon");

    const savedTitle = localStorage.getItem("sidebarTitle");
    const savedIcon = localStorage.getItem("sidebarIcon");

    if (sidebarTitleText && sidebarIcon && savedTitle && savedIcon) {
      sidebarTitleText.textContent = savedTitle;
      sidebarIcon.textContent = savedIcon;
    }

    const openMenus = JSON.parse(localStorage.getItem("openMenus") || "[]");
    openMenus.forEach(id => {
      const submenu = document.getElementById(id);
      if (submenu) submenu.style.display = "flex";
    });

    // ✅ 모든 a 태그 중 현재 경로와 정확히 일치하는 것만 active
    const links = document.querySelectorAll(".submenu a");
    const fullURL = pathname + search;

    links.forEach(link => {
      const href = link.getAttribute("href");
      if (href && (href === fullURL || location.href.includes(href))) {
        links.forEach(l => l.classList.remove("active")); // 중복 방지
        link.classList.add("active");
      }

      // 🔄 href 이동 전 sidebarTitle 설정하고 직접 이동 (버튼처럼)
      link.addEventListener("click", function (e) {
        const title = this.textContent.trim();
        const icon = this.textContent.trim().includes("정산") ? "💰"
            : this.textContent.trim().includes("Q&A") ? "❓"
                : this.textContent.trim().includes("사용자") ? "👥"
                    : this.textContent.trim().includes("프로젝트") ? "🛠️"
                        : "📊";

        e.preventDefault();  // 기본 이동 막고
        localStorage.setItem("sidebarTitle", title);
        localStorage.setItem("sidebarIcon", icon);
        window.location.href = this.href;
      });
    });
  });
});
