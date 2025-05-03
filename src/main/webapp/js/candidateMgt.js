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

        fetch(`${contextPath}/candidateAction`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `projectId=${projectId}&freelancerId=${freelancerId}&applyStatus=${newStatus}`
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('상태 업데이트 실패');
                }
                return response.text();
            })
            .then(data => {
                // UI 업데이트
                const statusCell = rowElement.querySelector('.apply-status');
                statusCell.innerHTML = (newStatus === 1) ? '합격' : '불합격';

                // 버튼 없애기
                const actionCell = rowElement.querySelector('.action-buttons');
                actionCell.innerHTML = '';

                // 알림창
                alert(newStatus === 1 ? '수락 처리 완료!' : '거절 처리 완료!');

                // 페이지 새로고침 (변경사항을 즉시 반영)
                window.location.reload();
            })
            .catch(error => {
                console.error('에러 발생:', error);
                alert('처리 중 오류가 발생했습니다: ' + error.message);
            });
    }
});