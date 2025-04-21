document.addEventListener("DOMContentLoaded", function () {
    const selectAll = document.getElementById("select_all");
    const checkboxes = document.querySelectorAll(".row-check");

    // [1] 전체 선택/해제
    selectAll.addEventListener("change", function () {
        checkboxes.forEach(cb => {
            cb.checked = selectAll.checked;
        });
    });

    // [2] 개별 체크 해제 시 전체 체크박스 해제
    checkboxes.forEach(cb => {
        cb.addEventListener("change", function () {
            if (!this.checked) {
                selectAll.checked = false;
            } else {
                // 모두 체크된 경우만 전체선택 체크박스도 체크
                const allChecked = Array.from(checkboxes).every(cb => cb.checked);
                selectAll.checked = allChecked;
            }
        });
    });
});