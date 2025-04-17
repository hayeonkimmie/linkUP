// user_info.js
function includeHTML(id, file, callback) {
  fetch(file)
    .then(res => res.text())
    .then(data => {
      document.getElementById(id).innerHTML = data;
      if (typeof callback === "function") callback();
    })
    .catch(err => console.error(`Error loading ${file}:`, err));
}

includeHTML("header-include", "admin_header.jsp");
includeHTML("menu-include", "menutap.jsp", () => {
  const defaultOpen = document.getElementById("userMenu");
  if (defaultOpen) defaultOpen.style.display = "flex";

  // 메뉴 드롭다운 토글 기능
  const buttons = document.querySelectorAll(".menu-button");
  buttons.forEach(button => {
    button.addEventListener("click", () => {
      const submenu = button.nextElementSibling;
      const isOpen = submenu && submenu.style.display === "flex";
      submenu.style.display = isOpen ? "none" : "flex";
    });
  });
});
