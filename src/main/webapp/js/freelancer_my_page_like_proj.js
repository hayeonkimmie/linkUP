function deleteSelectedRows() {
    const table = document.getElementById("projectTable").getElementsByTagName("tbody")[0];
    const rows = table.getElementsByTagName("tr");
    for (let i = rows.length - 1; i >= 0; i--) {
      const checkbox = rows[i].getElementsByTagName("input")[0];
      if (checkbox && checkbox.checked) {
        table.deleteRow(i);
      }
    }
  }

function deleteAllRows() {
    const table = document.getElementById("projectTable").getElementsByTagName("tbody")[0];
    table.innerHTML = "";
}

  function updateEmptyMessageVisibility() {
    const tableBody = document.getElementById("projectTable").getElementsByTagName("tbody")[0];
    const emptyMessage = document.getElementById("emptyMessage");
    if (tableBody.rows.length === 0) {
      emptyMessage.style.display = "block";
    } else {
      emptyMessage.style.display = "none";
    }
  }

  function deleteSelectedRows() {
    const table = document.getElementById("projectTable").getElementsByTagName("tbody")[0];
    const rows = table.getElementsByTagName("tr");
    for (let i = rows.length - 1; i >= 0; i--) {
      const checkbox = rows[i].getElementsByTagName("input")[0];
      if (checkbox && checkbox.checked) {
        table.deleteRow(i);
      }
    }
    updateEmptyMessageVisibility();
  }

  function deleteAllRows() {
    const table = document.getElementById("projectTable").getElementsByTagName("tbody")[0];
    table.innerHTML = "";
    updateEmptyMessageVisibility();
  }

  // 페이지 로딩 시 초기 상태 확인
  window.onload = updateEmptyMessageVisibility;