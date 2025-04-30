document.addEventListener('DOMContentLoaded', function() {
    // contextPath 가져오기
    const contextPath = document.getElementById('contextPath').value;

    // 정산하기 버튼에 이벤트 리스너 추가
    const settleBtns = document.querySelectorAll('.settle-btn');
    settleBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            const settlementId = this.getAttribute('data-id');
            confirmSettlement(settlementId, this);
        });
    });

    // 정산 확정 함수
    function confirmSettlement(settlementId, button) {
        if (confirm('해당 월의 정산을 진행하시겠습니까?')) {
            // 버튼 비활성화 및 로딩 표시
            button.disabled = true;
            button.textContent = '처리중...';

            // AJAX로 정산 확정 요청
            fetch(`${contextPath}/confirmSettlement`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `id=${settlementId}`
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('서버 응답 오류');
                    }
                    return response.json();
                })
                .then(data => {
                    if (data.success) {
                        alert('정산이 완료되었습니다.');

                        // 버튼 업데이트
                        button.textContent = '정산완료';
                        button.disabled = true;
                        button.classList.remove('settle-btn');

                        // 상태 배지 업데이트
                        const row = button.closest('tr');
                        const statusBadge = row.querySelector('.status');
                        statusBadge.textContent = '완료';
                        statusBadge.classList.remove('pending');
                        statusBadge.classList.add('completed');
                    } else {
                        alert('정산 처리 중 오류가 발생했습니다: ' + (data.message || ''));

                        // 버튼 원래대로 복구
                        button.disabled = false;
                        button.textContent = '정산하기';
                    }
                })
                .catch(error => {
                    console.error('정산 처리 중 오류가 발생했습니다:', error);
                    alert('정산 처리 중 오류가 발생했습니다.');

                    // 버튼 원래대로 복구
                    button.disabled = false;
                    button.textContent = '정산하기';
                });
        }
    }
});