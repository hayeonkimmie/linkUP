document.addEventListener("DOMContentLoaded", function () {
        const contextPath = document.body.getAttribute("data-context-path");

        document.querySelectorAll(".heart").forEach(function (element) {
            element.addEventListener("click", function () {
                const freelancerId = element.dataset.freelancerId;

                fetch(contextPath + "/toggleFavorite", {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: "freelancerId=" + encodeURIComponent(freelancerId)
                })
                    .then(response => response.text())
                    .then(result => {
                        if (result === 'removed') {
                            alert("찜 해제 완료");
                            // 카드 즉시 삭제
                            element.closest(".card").remove();
                        }
                    })
                    .catch(error => {
                        console.error("요청 실패:", error);
                        alert("요청 도중 에러가 발생하였습니다. ");
                    });
            });
        });
});