document.addEventListener('DOMContentLoaded', () => {
    fetch(contextPath +'/header')
        .then(res => res.text())
        .then(data => {
            document.getElementById('header-placeholder').innerHTML = data;

            // ✅ 드롭다운 이벤트 등록
            const dropdownToggles = document.querySelectorAll('.header-category-nav .header-dropdown-toggle');

            dropdownToggles.forEach(toggle => {
                const dropdown = toggle.parentElement;

                // 마우스 갖다댔을 때 드롭다운 열기
                toggle.addEventListener('mouseenter', () => {
                    document.querySelectorAll('.header-category-nav .header-dropdown').forEach(d => {
                        d.classList.remove('open');
                    });
                    dropdown.classList.add('open');
                });

                // 메뉴 밖으로 나가면 드롭다운 닫기
                dropdown.addEventListener('mouseleave', () => {
                    dropdown.classList.remove('open');
                });
            });

            // ✅ 클릭 시 원래 링크로 이동
            document.querySelectorAll('.header-category-nav .header-dropdown-toggle').forEach(link => {
                link.addEventListener('click', function (e) {
                    // 링크가 없는 경우 (href="#")만 드롭다운용
                    if (this.getAttribute('href') === '#') {
                        e.preventDefault(); // 링크 이동 막음
                    }
                    // href가 실제 경로면 이동하도록 그냥 놔둠 (e.preventDefault 안 함)
                });
            });
        })
        .catch(err => console.error('헤더 불러오기 실패:', err));
});
