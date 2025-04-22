<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>프로젝트 정산하기</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css">
  <link rel="stylesheet" href="../css/admin/settlement_detail.css">
  <script>
    const defaultOpenMenuId = "projectMenu";
  </script>
  <script src="../js/include_common.js"></script>
</head>
<body>
<div id="header-include"></div>
<div class="layout-wrapper">
  <div id="menu-include"></div>
  <div class="content">
    <h2 class="page-title">프로젝트 정산하기</h2>

    <div class="summary-box">
      <div class="summary-item"><span class="label">프로젝트명</span><span class="value">${project.projectName}</span></div>
      <div class="summary-item"><span class="label">이번달 정산 금액</span><span class="value"><fmt:formatNumber value="${totalAmount}" type="number" pattern="#,##0원" /></span></div>
      <div class="summary-item"><span class="label">프로젝트 기간</span><span class="value">${project.projectDuration}</span></div>
      <div class="summary-item"><span class="label">총 참여 인원</span><span class="value">${fn:length(targetList)}명</span></div>
      <div class="summary-item"><span class="label">프로젝트 정산일</span><span class="value">${project.settleDate}일</span></div>
    </div>

    <h3 class="section-title">정산 인원 & 금액</h3>
    <div class="table-wrapper">
      <table id="settlementTable">
        <thead>
        <tr>
          <th>참여자명</th>
          <th>구분</th>
          <th>참여 기간</th>
          <th>전화번호</th>
          <th>입금 계좌</th>
          <th>이번달 정산 금액</th>
          <th>정산 대상</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="t" items="${targetList}">
          <tr>
            <td class="name">${t.freelancerName}</td>
            <td class="category">${t.categoryName}</td>
            <td><fmt:formatDate value="${t.startDate}" pattern="yyyy.MM.dd" /> ~ <fmt:formatDate value="${t.endDate}" pattern="yyyy.MM.dd" /></td>
            <td>${t.fphone}</td>
            <td>${t.account}</td>
            <td class="amount" data-amount="${t.totalPay}"><fmt:formatNumber value="${t.totalPay}" type="number" pattern="#,##0원" /></td>
            <td><input type="checkbox" name="contractIds" value="${t.id}" onchange="updateSelected(this)"></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <div class="total-footer">
      <div class="total-text">총합 <strong>0원</strong> (선택된 0명)</div>
      <div class="buttons">
        <button class="btn cancel">취소</button>
        <button class="btn confirm" onclick="openSettlementModal()">정산 승인</button>
      </div>
    </div>
  </div>

  <div id="settlementModal" class="modal-overlay" style="display:none;">
    <div class="modal">
      <h2>정산을 진행하시겠습니까?</h2>
      <p>아래 <strong id="freelancerCount">0</strong>명의 선택된 프리랜서에게 정산을 진행합니다.</p>
      <div class="modal-table-wrapper">
        <table class="modal-table">
          <thead>
          <tr><th>참여자명</th><th>구분</th><th>정산 금액</th></tr>
          </thead>
          <tbody id="modalFreelancerBody"></tbody>
        </table>
      </div>
      <form id="settlementForm" method="post" action="/admin/settlement">
        <input type="hidden" name="projectId" value="${project.projectId}" />
        <div id="hiddenContractInputs"></div>
        <div class="modal-buttons">
          <button type="submit" class="btn confirm">정산하기</button>
          <button type="button" class="btn cancel" onclick="closeModal()">닫기</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script>
  const selectedMap = new Map();

  function updateSelected(checkbox) {
    const row = checkbox.closest("tr");
    const id = checkbox.value;
    const name = row.querySelector(".name").innerText.trim();
    const category = row.querySelector(".category").innerText.trim();
    const amount = row.querySelector(".amount").dataset.amount;

    if (checkbox.checked) {
      selectedMap.set(id, { name, category, amount });
    } else {
      selectedMap.delete(id);
    }

    updateTotalAmount();

    const modal = document.getElementById("settlementModal");
    if (modal.style.display === "flex") {
      updateModalContent();
    }
  }

  function updateTotalAmount() {
    let totalAmount = 0;
    selectedMap.forEach((info) => {
      totalAmount += parseInt(info.amount);
    });

    const totalText = document.querySelector(".total-text");
    const selectedCount = selectedMap.size;
    totalText.innerHTML = `총합 <strong>\${formatNumberWithCommas(totalAmount)}원</strong> (선택된 \${selectedCount}명)`;
  }

  function formatNumberWithCommas(num) {
    if (isNaN(num)) return "0";
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  }

  function updateModalContent() {
    const tbody = document.getElementById("modalFreelancerBody");
    const inputs = document.getElementById("hiddenContractInputs");
    const count = document.getElementById("freelancerCount");
    const settlementRows = document.querySelectorAll("#settlementTable tbody tr");

    tbody.innerHTML = "";
    inputs.innerHTML = "";

    const checkedCount = selectedMap.size;
    count.textContent = checkedCount;

    selectedMap.forEach((info, id) => {
      inputs.insertAdjacentHTML("beforeend", `<input type="hidden" name="contractIds" value="\${id}" />`);
    });

    settlementRows.forEach((row) => {
      const checkbox = row.querySelector('input[type="checkbox"]');
      if (checkbox && checkbox.checked) {
        const name = row.querySelector(".name")?.innerText.trim() || "N/A";
        const category = row.querySelector(".category")?.innerText.trim() || "N/A";
        const amount = row.querySelector(".amount")?.dataset.amount || "0";
        const amountFormatted = formatNumberWithCommas(parseInt(amount)) + "원";

        const newRow = document.createElement("tr");
        newRow.innerHTML = `<td>\${name}</td><td>\${category}</td><td>\${amountFormatted}</td>`;
        tbody.appendChild(newRow);
      }
    });
  }

  function openSettlementModal() {
    updateModalContent();
    document.getElementById("settlementModal").style.display = "flex";
  }

  function closeModal() {
    document.getElementById("settlementModal").style.display = "none";
  }

  updateTotalAmount();
</script>
</body>
</html>