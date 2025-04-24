document.addEventListener('DOMContentLoaded', () => {
    const tabs = document.querySelectorAll('.filter-tab');
    const cards = document.querySelectorAll('.job-card');

    // 필터 탭 클릭 시
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');

            const status = tab.getAttribute('data-status');
            cards.forEach(card => {
                card.style.display =
                    status === 'all' || card.getAttribute('data-status') === status
                        ? 'block'
                        : 'none';
            });
        });
    });

    // 모집확정 버튼 클릭 시 AJAX 호출
    const confirmButtons = document.querySelectorAll('.btn-secondary');
    confirmButtons.forEach(button => {
        button.addEventListener('click', () => {
            const projectCard = button.closest('.job-card');
            const projectId = projectCard.getAttribute('data-project-id');

            fetch(`${contextPath}/confirmRecruitment`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `projectId=${projectId}`
            })
                .then(response => {
                    if (response.ok) {
                        alert('모집이 확정되었습니다!');
                        location.reload();
                    } else {
                        alert('모집 확정 실패!');
                    }
                });
        });
    });
});
