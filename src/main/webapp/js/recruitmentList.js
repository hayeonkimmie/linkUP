document.addEventListener('DOMContentLoaded', () => {
    // const contextPath = document.body.getAttribute('data-context-path');
    // const contextPath = ${pageContext.request.contextPath};
    const contextPath = window.contextPath
    console.log(contextPath)
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

                fetch(`/linkup/confirmRecruitment`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: `projectId=${projectId}`
                })
                    .then(res => res.json())
                    .then(data => {
                        console.log('서버 응답:', data); // 디버깅용

                        if (data.success === true) {
                            alert('모집이 확정되었습니다!');

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
                            // 서버가 보내준 message를 그대로 출력
                            if (data.message) {
                                alert(data.message);
                            } else {
                                alert('모집 확정 실패!');
                            }
                        }
                    })
                    .catch((error) => {
                        console.error('서버 통신 실패', error);
                        alert('서버 오류 발생');
                    });
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
