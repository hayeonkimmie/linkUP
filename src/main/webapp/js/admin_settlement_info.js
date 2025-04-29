document.addEventListener("DOMContentLoaded", () => {
    const data = window.projectJson;
    console.log(data);

    document.getElementById("projectName").textContent = data.projectName;
    document.getElementById("projectPeriod").textContent = `${data.startDate} ~ ${data.endDate}`;
    const settleDate = new Date(data.settleDate);
    const settleDay = settleDate.getDate();
    document.getElementById("settleDay").textContent = `매월 ${settleDay}일`;

    document.getElementById("monthFilter").addEventListener("change", function() {
        const selectedCnt = this.value; // ✅ value는 cnt다
        const projectId = data.projectId;

        fetch(`/linkup/admin/settlementMonth?projectId=${projectId}&cnt=${selectedCnt}`)
            .then(response => response.json())
            .then(json => {
                console.log("변경된 데이터:", json);
                renderSettlementTable(json);
            })
            .catch(error => {
                console.error("월별 정산 데이터 가져오기 실패", error);
            });
    });
});

function renderSettlementTable(json) {
    const doneBody = document.querySelector("#doneTable tbody");
    doneBody.innerHTML = "";
    json.doneList.forEach(item => {
        doneBody.innerHTML += `
        <tr>
          <td>${item.freelancerName}</td>
          <td>${item.position}</td>
          <td>${item.startDate ?? '-'} ~ ${item.endDate ?? '-'}</td>
          <td>${item.cnt}회차</td>
          <td>${formatNumber(item.settleAmount)}</td>
          <td><span class="status complete">${item.status}</span></td>
        </tr>
      `;
    });

    const waitBody = document.querySelector("#waitTable tbody");
    waitBody.innerHTML = "";
    json.waitList.forEach(item => {
        waitBody.innerHTML += `
        <tr>
          <td>${item.freelancerName ?? '-'}</td>
          <td>${item.position}</td>
          <td>-</td>
          <td>${item.cnt}회차</td>
          <td>${formatNumber(item.settleAmount)}</td>
          <td><span class="status pending">${item.status}</span></td>
        </tr>
      `;
    });

    document.getElementById("totalAmount").textContent = formatNumber(json.totalAmount);
}

function formatNumber(num) {
    return new Intl.NumberFormat('ko-KR').format(num) + '원';
}
