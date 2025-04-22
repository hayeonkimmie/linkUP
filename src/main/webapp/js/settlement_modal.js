function openSettlementModal() {
  const checked = document.querySelectorAll(".settle-checkbox:checked");
  const tbody = document.getElementById("modalFreelancerBody");
  const jsonList = [];

  // ✅ 정산일 가져오기
  const settleDate = document.querySelector("#settleDateWrapper").dataset.settleDate;

  tbody.innerHTML = "";
  checked.forEach(cb => {
    const row = cb.closest("tr");
    const amountEl = row.querySelector(".amount");
    const data = {
      id: amountEl.dataset.id,
      fid: amountEl.dataset.fid, // freelancer_id
      name: amountEl.dataset.name,
      category: amountEl.dataset.category,
      amount: amountEl.dataset.amount,
      settleDate: settleDate
    };

    jsonList.push(data);
    const rowHTML =
      "<tr>" +
        "<td>" + data.name + "</td>" +
        "<td>" + data.category + "</td>" +
        "<td>" + parseInt(data.amount).toLocaleString() + "원</td>" +
      "</tr>";
    tbody.insertAdjacentHTML("beforeend", rowHTML);
  });

  document.getElementById("freelancerCount").textContent = jsonList.length;
  document.getElementById("jsonData").value = JSON.stringify(jsonList);
  document.getElementById("settlementModal").style.display = "flex";
}

function closeModal() {
  document.getElementById("settlementModal").style.display = "none";
}

function updateTotalInfo() {
  const checked = document.querySelectorAll(".settle-checkbox:checked");
  let totalAmount = 0;
  checked.forEach(cb => {
    const amount = parseInt(cb.closest("tr").querySelector(".amount").dataset.amount);
    totalAmount += isNaN(amount) ? 0 : amount;
  });

  document.querySelector(".total-text").innerHTML = "총합 <strong>" + totalAmount.toLocaleString() + "원</strong> (선택된 " + checked.length + "명)";
}

document.addEventListener("DOMContentLoaded", function () {
  document.querySelectorAll(".settle-checkbox").forEach(cb => {
    cb.addEventListener("change", updateTotalInfo);
  });
});
