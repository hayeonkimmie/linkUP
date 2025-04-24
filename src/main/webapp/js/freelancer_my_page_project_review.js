window.onload = function () {
    const tabs = document.querySelectorAll('.tab-btn');
    const contents = document.querySelectorAll('.tab-content');
    const container = document.querySelector('.tab-content-container');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            const targetId = tab.dataset.tab;
            // 탭 버튼 상태 갱신
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');

            // 탭 콘텐츠 active 클래스 갱신
            contents.forEach(content => {
                if (content.id === targetId) {
                    content.classList.add('active');
                } else {
                    content.classList.remove('active');
                }
            });

            // tab-content-container 내부에서 active 아닌 콘텐츠 숨김
            const allTabContents = container.querySelectorAll('.tab-content');
            allTabContents.forEach(content => {
                if (!content.classList.contains('active')) {
                    content.style.display = 'none';
                } else {
                    content.style.display = 'block';
                }
            });
        });
    });

    // 페이지 로드시 초기 표시 설정
    const allTabContents = document.querySelectorAll('.tab-content-container .tab-content');
    allTabContents.forEach(content => {
        content.style.display = content.classList.contains('active') ? 'block' : 'none';
    });

    // 별점 선택 기능
    const stars = document.querySelectorAll('.rating-select .star');
    stars.forEach(star => {
        star.addEventListener('click', () => {
            const value = parseInt(star.dataset.value);
            stars.forEach(s => s.classList.remove('selected'));
            for (let i = 0; i < value; i++) {
                stars[i].classList.add('selected');
            }
        });
    });
    document.querySelectorAll('.accordion-header').forEach(header => {
        header.addEventListener('click', () => {
            const currentItem = header.parentElement;
            const allItems = document.querySelectorAll('.accordion-item');

            allItems.forEach(item => {
                if (item !== currentItem) {
                    item.classList.remove('active');
                }
            });

            currentItem.classList.toggle('active');
        });
    });
};
document.addEventListener("DOMContentLoaded", function () {
    const headers = document.querySelectorAll(".accordion-header");

    headers.forEach(header => {
        header.addEventListener("click", () => {
            const allBodies = document.querySelectorAll(".accordion-body");

            // 현재 클릭한 header에 연결된 body 요소 가져오기
            const body = header.nextElementSibling;

            // 열려 있는 다른 아코디언 닫기
            allBodies.forEach(b => {
                if (b !== body) {
                    b.classList.remove("active");
                    b.style.display = "none";
                }
            });

            // 현재 클릭한 아코디언 toggle
            if (body.classList.contains("active")) {
                body.classList.remove("active");
                body.style.display = "none";
            } else {
                body.classList.add("active");
                body.style.display = "block";
            }
        });
    });
});
