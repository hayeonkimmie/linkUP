window.onload = function () {

    // ✅ 기본 탭 설정
    switchTab('ongoing');

    // ✅ 탭 전환 이벤트
    document.querySelectorAll('.tab').forEach(tab => {
        tab.addEventListener('click', () => {
            const tabId = tab.textContent.includes('진행중') ? 'ongoing' : 'completed';
            switchTab(tabId);
        });
    });
    window.toggleDetails = function(event) {
        const clickedRow = event.currentTarget;
        const nextRow = clickedRow.nextElementSibling;

        if (nextRow && nextRow.classList.contains('accordion-row')) {
            const isOpen = nextRow.style.display === 'table-row';

            document.querySelectorAll('.accordion-row').forEach(row => {
                row.style.display = 'none';
            });

            if (!isOpen) {
                nextRow.style.display = 'table-row';
            }
        }
    };
    // ✅ 아코디언: 다시 누르면 닫힘 + 하나만 열림
    document.querySelectorAll('.clickable').forEach(row => {
        row.addEventListener('click', () => {
            const next = row.nextElementSibling;

            // 이미 열려있으면 닫기
            if (next && next.classList.contains('accordion-row') && next.style.display === 'table-row') {
                next.style.display = 'none';
                return;
            }

            // 먼저 전부 닫기
            document.querySelectorAll('.accordion-row').forEach(acc => {
                acc.style.display = 'none';
            });

            // 현재 클릭한 행의 다음 요소 열기
            if (next && next.classList.contains('accordion-row')) {
                next.style.display = 'table-row';
            }
        });
    });
};
document.querySelectorAll('.settlement-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        event.stopPropagation();
        const projectId = btn.dataset.projectId;
        let settlementList = null;

        if (window.onGoingProjSettlementMap && window.onGoingProjSettlementMap[projectId]) {
            settlementList = window.onGoingProjSettlementMap[projectId];
        } else if (window.completedProjSettlementMap && window.completedProjSettlementMap[projectId]) {
            settlementList = window.completedProjSettlementMap[projectId];
        }
        openSettlementModal(settlementList); // settlementList를
    });
});

function openSettlementModal(settlementList) {
    const tbody = document.getElementById("settlementTableBody");
    const projectNameSpan = document.getElementById("modalProjectName");

    tbody.innerHTML = ""; // 테이블 초기화

    // 프로젝트 이름 세팅
    if (projectNameSpan && settlementList[0]?.projectName) {
        projectNameSpan.textContent = settlementList[0].projectName;
    }
    let hasCntValue = false;

    // 테이블 데이터 렌더링
    settlementList.forEach(settle => {
        const tr = document.createElement("tr");
        // 명시적으로 확인 - null 체크
        if (settle.cnt == null) {
            tr.innerHTML = `
            <td colspan="4" style="text-align: center; width: 100%; height: auto">조회된 정산내역이 없습니다</td>
            `;
            tbody.appendChild(tr);
        } else {
            hasCntValue = true; // cnt 값이 있는 항목 발견
            tr.innerHTML = `
            <td>${settle.cnt}</td>
            <td>₩${parseInt(settle.ammount, 10).toLocaleString()}</td>
            <td class="status ${settle.status === '정산완료' ? 'complete' : ''}">${settle.status}</td>
            <td>${settle.settleDate}</td>
            `;
            tbody.appendChild(tr);
            calculateCompletedSettlementTotal();
        }
    });
/*
    // 유효한 cnt 값이 있을 때만 총합 계산
    if (hasCntValue) {
        calculateCompletedSettlementTotal();
    }*/

    document.getElementById("settlementModal").style.display = "block"; // 모달 열기
}

function closeSettlementModal() {
    document.getElementById('settlementModal').style.display = 'none';
}
// ✅ 탭 전환 함수
function switchTab(tabId) {
    document.querySelectorAll('.tab').forEach(tab => tab.classList.remove('active'));
    document.querySelectorAll('.tab-content').forEach(content => content.style.display = 'none');
    document.querySelector('.tab[onclick*="' + tabId + '"]')?.classList.add('active');
    document.getElementById(tabId).style.display = 'block';
}

function calculateCompletedSettlementTotal() {
    let total = 0;
    const rows = document.querySelectorAll('.settlement-table tbody tr');
    let statusCellFound = false;

    rows.forEach(row => {
        const statusCell = row.querySelector('.status');
        if (statusCell) {
            statusCellFound = true;
            const isCompleted = statusCell.classList.contains('complete');

            if (isCompleted) {
                const amountText = row.children[1]?.textContent?.replace(/[₩,]/g, '');
                const amount = parseInt(amountText);
                if (!isNaN(amount)) total += amount;
            }
        }
    });

    const totalCell = document.querySelector('.settlement-table tfoot .total strong');
    if (totalCell) {
        if (!statusCellFound) {
            totalCell.textContent = '';
        } else {
            totalCell.textContent = `₩${total.toLocaleString()}`;
        }
    }
}