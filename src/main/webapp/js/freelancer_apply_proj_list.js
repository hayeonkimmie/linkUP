window.onload = function () {
    const cancelButtons = document.querySelectorAll('button');
    cancelButtons.forEach((btn) => {
        btn.addEventListener('click', () => {
            if (confirm('정말로 지원을 취소하시겠습니까?')) {
                btn.closest('tr').querySelector('.status').textContent = '취소됨';
                btn.closest('tr').querySelector('.status').style.backgroundColor = '#e05a5a';
                btn.remove();
            }
        });
    });
};