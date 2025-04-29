document.addEventListener("DOMContentLoaded", function() {


    fetch(contextPath + '/loadHeader')
        .then(res => {
            if (!res.ok) {
                throw new Error('헤더를 불러오지 못했습니다.');
            }
            return res.text();
        })
        .then(data => {
            const placeholder = document.getElementById("header-placeholder");

            if (!placeholder) {
                console.error('🚨 header-placeholder 요소가 없습니다!');
                return;
            }

            placeholder.innerHTML = data;

            // HTML 삽입 완료 후에 이벤트 연결
            const profileToggle = placeholder.querySelector('.profile-toggle');
            const profileMenu = placeholder.querySelector('.profile-menu');

            console.log('profileToggle:', profileToggle);
            console.log('profileMenu:', profileMenu);

            if (profileToggle && profileMenu) {
                profileMenu.style.display = 'none';

                profileToggle.addEventListener('click', (e) => {
                    e.preventDefault();
                    e.stopPropagation();

                    // 여기 수정된 부분
                    const isVisible = window.getComputedStyle(profileMenu).display === 'block';
                    profileMenu.style.display = isVisible ? 'none' : 'block';
                });

                document.addEventListener('click', (e) => {
                    if (!profileToggle.contains(e.target) && !profileMenu.contains(e.target)) {
                        profileMenu.style.display = 'none';
                    }
                });
            } else {
                console.warn('❌ profileToggle 또는 profileMenu를 찾지 못했습니다.');
            }
        })
        .catch(error => {
            console.error('🚨 fetch 실패:', error);
        });
});
