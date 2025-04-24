// 별점 선택 기능
document.addEventListener("DOMContentLoaded", function () {
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