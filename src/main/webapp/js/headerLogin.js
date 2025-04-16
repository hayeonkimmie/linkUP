document.addEventListener('DOMContentLoaded', () => {
  // 전체보기 드롭다운
  const dropdown = document.querySelector('.dropdown');
  const toggle = dropdown?.querySelector('.dropdown-toggle');
  const menu = dropdown?.querySelector('.dropdown-menu');

  toggle?.addEventListener('click', function (e) {
    e.preventDefault();
    dropdown.classList.toggle('open');
  });

  document.addEventListener('click', function (e) {
    if (dropdown && !dropdown.contains(e.target)) {
      dropdown.classList.remove('open');
    }
  });

  // 프로필 메뉴 토글
  const profileToggle = document.querySelector('.profile-toggle');
  const profileMenu = document.querySelector('.profile-menu');

  profileToggle?.addEventListener('click', (e) => {
    e.stopPropagation();
    profileMenu.style.display = profileMenu.style.display === 'block' ? 'none' : 'block';
  });

  document.addEventListener('click', () => {
    if (profileMenu) profileMenu.style.display = 'none';
  });
});
fetch("headerLogin.html")
.then(res => res.text())
.then(data => {
  document.getElementById("header-login-placeholder").innerHTML = data;

  // header.html 안의 스크립트 다시 실행해주기
  const scripts = document.querySelectorAll('#header-login-placeholder script');
  scripts.forEach((script) => {
    const newScript = document.createElement('script');
    newScript.src = script.src;
    document.body.appendChild(newScript);
  });
});