document.addEventListener('DOMContentLoaded', () => {
    const tabs = document.querySelectorAll('.filter-tab');

    // 탭 활성화 + 필터링 처리
    function activateTab(tabElement) {
        tabs.forEach(t => t.classList.remove('active'));
        tabElement.classList.add('active');

        const status = tabElement.getAttribute('data-status');
        document.querySelectorAll('.job-card').forEach(card => {
            const cardStatus = card.getAttribute('data-status');
            card.style.display = (status === 'all' || cardStatus === status) ? 'block' : 'none';
        });
    }

    // 카드에 이벤트 바인딩 (초기 + 변경 후)
    function bindCardEvents() {
        // 모집확정
        document.querySelectorAll('.btn-secondary').forEach(button => {
            button.addEventListener('click', () => {
                const projectCard = button.closest('.job-card');
                const projectId = projectCard.getAttribute('data-project-id');

                fetch(`${contextPath}/confirmRecruitment`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: `projectId=${projectId}`
                })
                    .then(res => res.json())
                    .then(data => {
                        if (data.success) {
                            alert('모집이 확정되었습니다!');

                            // data-status 변경 → 필터링으로 탭 이동처럼 보이게 처리
                            if (data.projectProgress === '시작전') {
                                projectCard.setAttribute('data-status', 'done-start');
                                activateTab(document.querySelector('.filter-tab[data-status="done-start"]'));
                            } else if (data.projectProgress === '진행중') {
                                projectCard.setAttribute('data-status', 'done-progress');
                                activateTab(document.querySelector('.filter-tab[data-status="done-progress"]'));
                            } else {
                                location.reload();
                            }
                        } else {
                            alert('모집 확정 실패!');
                        }
                    })
                    .catch(() => alert('서버 오류 발생'));
            });
        });

        // 삭제
        document.querySelectorAll('.btn-delete').forEach(button => {
            button.addEventListener('click', () => {
                const projectCard = button.closest('.job-card');
                const projectId = projectCard.getAttribute('data-project-id');

                if (confirm("정말 삭제하시겠습니까?")) {
                    fetch(`${contextPath}/deleteProject`, {
                        method: 'POST',
                        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                        body: `projectId=${projectId}`
                    })
                        .then(response => response.text())
                        .then(result => {
                            if (result === "success") {
                                alert("삭제되었습니다.");
                                projectCard.remove();
                            } else {
                                alert("삭제 실패!");
                            }
                        });
                }
            });
        });
    }

    // 탭 클릭 이벤트 바인딩
    tabs.forEach(tab => {
        tab.addEventListener('click', () => activateTab(tab));
    });

    // 첫 진입시 '전체보기' 활성화
    const defaultTab = document.querySelector('.filter-tab.active') || document.querySelector('.filter-tab[data-status="all"]');
    if (defaultTab) {
        activateTab(defaultTab);
    }

    // 초기에 이벤트 바인딩
    bindCardEvents();
});
