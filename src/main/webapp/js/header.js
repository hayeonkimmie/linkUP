document.addEventListener('DOMContentLoaded', () => {
    // ✅ 로그인 여부에 따라 자동 분기되는 컨트롤러 경로
    fetch(contextPath + '/loadHeader')
        .then(res => res.text())
        .then(data => {
            document.getElementById('header-placeholder').innerHTML = data;

            // ✅ 드롭다운 메뉴 토글 등록
            const dropdownToggles = document.querySelectorAll('#header-placeholder .header-dropdown-toggle');

            dropdownToggles.forEach(toggle => {
                const dropdown = toggle.parentElement;

                // 마우스 올리면 드롭다운 열기
                toggle.addEventListener('mouseenter', () => {
                    document.querySelectorAll('#header-placeholder .header-dropdown').forEach(d => {
                        d.classList.remove('open');
                    });
                    dropdown.classList.add('open');
                });

                // 마우스 벗어나면 드롭다운 닫기
                dropdown.addEventListener('mouseleave', () => {
                    dropdown.classList.remove('open');
                });

                // 클릭 시 href가 "#"이면 링크 막기
                toggle.addEventListener('click', function (e) {
                    if (this.getAttribute('href') === '#') {
                        e.preventDefault();
                    }
                });
            });
        })
        .catch(err => {
            console.error('헤더 로딩 실패:', err);
        });
});
