window.onload = function () {
    document.querySelector('.toggle-form').addEventListener('submit', function (e) {
        e.preventDefault();
        alert("알림 설정이 저장되었습니다!");
        // 실제로는 서버에 저장 요청이 가야 함
    });
};
