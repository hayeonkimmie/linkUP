/*
document.querySelectorAll('.accordion-header').forEach(header => {
    header.addEventListener('click', () => {
      const body = header.nextElementSibling;
      const isVisible = body.style.display === 'block';
      document.querySelectorAll('.accordion-body').forEach(b => b.style.display = 'none');
      body.style.display = isVisible ? 'none' : 'block';
    });
  });
document.querySelectorAll('.accordion-toggle').forEach((row, index) => {
    row.addEventListener('click', () => {
        const contentRow = row.nextElementSibling;
        contentRow.style.display = contentRow.style.display === 'table-row' ? 'none' : 'table-row';
    });
});*/
/*
// 기존 이벤트에 중복 방지 로직 추가
document.querySelectorAll('.accordion-toggle').forEach((toggleRow) => {
    toggleRow.addEventListener('click', () => {
        const contentRow = toggleRow.nextElementSibling;

        // 다른 열 닫기
        document.querySelectorAll('.accordion-content').forEach(row => {
            if (row !== contentRow) row.style.display = 'none';
        });

        // 현재 행 토글
        if (contentRow.style.display === 'table-row') {
            contentRow.style.display = 'none';
        } else {
            contentRow.style.display = 'table-row';
        }
    });
});
*/window.onload = function () {
    const toggleRows = document.querySelectorAll('.accordion-toggle');

    toggleRows.forEach((row) => {
        row.addEventListener('click', function () {
            const contentRow = this.nextElementSibling;
            const isOpen = contentRow.style.display === 'table-row';

            // 모든 아코디언 내용 닫기
            document.querySelectorAll('.accordion-content').forEach((r) => {
                r.style.display = 'none';
            });

            // 현재 클릭한 항목만 토글
            if (!isOpen) {
                contentRow.style.display = 'table-row';
            }
        });
    });
};
