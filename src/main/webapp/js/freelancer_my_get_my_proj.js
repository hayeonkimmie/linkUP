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

    // ✅ 모달 열기
    document.querySelectorAll('.settlement-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            document.getElementById('settlementModal').style.display = 'flex';
            calculateCompletedSettlementTotal(); // 총합 계산
        });
    });

    // ✅ 모달 닫기
    const closeBtn = document.querySelector('.close-btn');
    const confirmBtn = document.querySelector('.confirm-btn');
    const modal = document.getElementById('settlementModal');

    if (closeBtn && confirmBtn && modal) {
        closeBtn.addEventListener('click', () => {
            modal.style.display = 'none';
        });
        confirmBtn.addEventListener('click', () => {
            modal.style.display = 'none';
        });
    }
};

// ✅ 탭 전환 함수
function switchTab(tabId) {
    document.querySelectorAll('.tab').forEach(tab => tab.classList.remove('active'));
    document.querySelectorAll('.tab-content').forEach(content => content.style.display = 'none');
    document.querySelector('.tab[onclick*="' + tabId + '"]')?.classList.add('active');
    document.getElementById(tabId).style.display = 'block';
}

// ✅ 정산 완료 금액만 합산
function calculateCompletedSettlementTotal() {
    let total = 0;
    document.querySelectorAll('.settlement-table tbody tr').forEach(row => {
        const statusCell = row.querySelector('.status');
        const isCompleted = statusCell && statusCell.classList.contains('complete');

        if (isCompleted) {
            const amountText = row.children[1]?.textContent?.replace(/[₩,]/g, '');
            const amount = parseInt(amountText);
            if (!isNaN(amount)) total += amount;
        }
    });

    const totalCell = document.querySelector('.settlement-table tfoot .total strong');
    if (totalCell) {
        totalCell.textContent = `₩${total.toLocaleString()}`;
    }
}
