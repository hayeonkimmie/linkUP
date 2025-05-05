document.addEventListener('DOMContentLoaded', () => {
    const contextPath = document.getElementById('contextPath')?.value || '';
    const projectId = new URLSearchParams(window.location.search).get('projectId');

    // 수락 버튼
    document.querySelectorAll('.accept-btn').forEach(button => {
        button.addEventListener('click', () => {
            updateApplyStatus(button.closest('tr'), 1); // 합격
        });
    });

    // 거절 버튼
    document.querySelectorAll('.reject-btn').forEach(button => {
        button.addEventListener('click', () => {
            updateApplyStatus(button.closest('tr'), 0); // 불합격
        });
    });

    function updateApplyStatus(rowElement, newStatus) {
        const freelancerId = rowElement.getAttribute('data-freelancer-id');
        const applyId = rowElement.querySelector('.apply-id')?.value;
        let settleDay = rowElement.querySelector('.settle-day')?.value;
        const projectId = new URLSearchParams(window.location.search).get('projectId');

        // settleDay가 null이거나 undefined일 경우 빈 문자열로 처리
        if (!settleDay || settleDay === "null" || settleDay === null) {
            settleDay = "";
        }

        if (!applyId || !projectId || !freelancerId) {
            alert('필요한 정보가 누락되었습니다.');
            return;
        }

        fetch(`${contextPath}/candidateAction`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `applyId=${applyId}&projectId=${projectId}&freelancerId=${freelancerId}&applyStatus=${newStatus}&settleDay=${settleDay}`
        })
            .then(response => {
                if (!response.ok) throw new Error('상태 업데이트 실패');
                return response.text();
            })
            .then(data => {
                rowElement.querySelector('.apply-status').innerHTML = newStatus === 1 ? '합격' : '불합격';
                rowElement.querySelector('.action-buttons').innerHTML = '';
                alert(newStatus === 1 ? '수락 처리 완료!' : '거절 처리 완료!');
                window.location.reload();
            })
            .catch(error => {
                console.error('에러 발생:', error);
                alert('처리 중 오류가 발생했습니다: ' + error.message);
            });
    }

});