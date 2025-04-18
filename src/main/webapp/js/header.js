document.addEventListener('DOMContentLoaded', () => {
  fetch('header.jsp')
      .then(res => res.text())
      .then(data => {
        document.getElementById('header-placeholder').innerHTML = data;

        // ✅ 헤더 내용이 로드된 후 드롭다운 이벤트 등록
        const dropdownToggles = document.querySelectorAll('.header-category-nav .header-dropdown-toggle');

        dropdownToggles.forEach(toggle => {
          toggle.addEventListener('click', function (e) {
            e.preventDefault();
            const dropdown = this.parentElement;

            document.querySelectorAll('.header-category-nav .header-dropdown').forEach(d => {
              if (d !== dropdown) d.classList.remove('open');
            });

            dropdown.classList.toggle('open');
          });
        });

        // ✅ 바깥 클릭 시 드롭다운 닫기
        document.addEventListener('click', function (e) {
          const isDropdown = e.target.closest('.header-category-nav .header-dropdown');
          if (!isDropdown) {
            document.querySelectorAll('.header-category-nav .header-dropdown').forEach(dropdown => {
              dropdown.classList.remove('open');
            });
          }
        });
      })
      .catch(err => console.error('헤더 불러오기 실패:', err));
});
