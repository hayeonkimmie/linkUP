document.addEventListener("DOMContentLoaded", function () {
    // contextPath 가져오기 (여러 방식 시도)
    const contextPath = document.getElementById('contextPath')?.value ||
        window.contextPath ||
        document.body.getAttribute("data-context-path") ||
        '';

    console.log("contextPath:", contextPath); // 디버깅용

    document.querySelectorAll(".heart").forEach(function (element) {
        element.addEventListener("click", function () {
            const freelancerId = element.dataset.freelancerId;
            console.log("하트 클릭 - freelancerId:", freelancerId); // 디버깅용

            fetch(`${contextPath}/toggleFavorite`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: "freelancerId=" + encodeURIComponent(freelancerId)
            })
                .then(response => {
                    console.log("서버 응답 상태:", response.status); // 디버깅용
                    return response.text();
                })
                .then(result => {
                    console.log("서버 응답 결과:", result); // 디버깅용

                    if (result === 'removed') {
                        alert("찜 해제 완료");
                        // 카드 즉시 삭제
                        element.closest(".card").remove();
                    } else if (result === 'not_found') {
                        alert("이미 찜 목록에서 제거되었습니다.");
                        // 화면 새로고침
                        window.location.reload();
                    } else {
                        alert("요청 처리에 실패했습니다.");
                    }
                })
                .catch(error => {
                    console.error("요청 실패:", error);
                    alert("요청 도중 에러가 발생하였습니다.");
                });
        });
    });
});