<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>결제 정보</title>
  <link rel="stylesheet" href="${contextPath}/css/common.css" />
  <link rel="stylesheet" href="${contextPath}/css/payment.css" />
</head>
<body>

<div class="layout">
  <aside class="sidebar">
    <div class="profile">
      <img src="https://via.placeholder.com/80" alt="프로필 이미지">
      <p>${nickname}</p>
      <p><a href="/mypage">마이페이지</a></p>
    </div>
    <h3>프로필 설정</h3>
    <h3>프로젝트</h3>
    <ul>
      <li><a href="#">구인 등록</a></li>
      <li><a href="#">프로젝트 조회</a></li>
      <li><a href="#">지원자 관리</a></li>
    </ul>
    <h3><a href="#">찜한 구인자</a></h3>
    <h3><a href="#">리뷰 내역</a></h3>
    <h3><a href="#">문의 내역</a></h3>
    <h3>설정</h3>
    <ul>
      <li><a href="#">알림 설정</a></li>
      <li><a href="#" class="active">결제 정보</a></li>
    </ul>
  </aside>

  <main class="main">
    <h2 class="section-title">결제 정보</h2>
    <div class="account-box">
      <div class="account-header">
        <span>${account.bank} <span class="account-info">${account.number} (예금주: ${account.holder})</span></span>
        <button class="btn-change" onclick="toggleForm(true)">계좌 변경</button>
      </div>
    </div>

    <div class="form-section" id="accountForm" style="display:none">
      <h4>새 계좌 변경</h4>
      <form method="post" action="updateAccount.jsp">
        <label>은행</label>
        <select name="bank">
          <option>은행 선택</option>
          <option>국민은행</option>
          <option>신한은행</option>
          <option>하나은행</option>
          <option>우리은행</option>
          <option>농협은행</option>
          <option>기업은행</option>
          <option>카카오뱅크</option>
        </select>
        <label>계좌번호</label>
        <input type="text" name="accountNumber" placeholder="'-' 없이 입력해주세요">
        <label>예금주</label>
        <input type="text" name="holder" placeholder="예금주명을 입력해주세요">
        <div class="form-buttons">
          <button class="btn-cancel" type="button" onclick="toggleForm(false)">취소</button>
          <button class="btn-change" type="submit">변경</button>
        </div>
      </form>
    </div>

    <div class="settlement-history">
      <h4>정산 내역</h4>
      <table>
        <thead>
        <tr>
          <th>프로젝트</th>
          <th>정산 금액</th>
          <th>정산일</th>
          <th>상태</th>
          <th>영수증</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="settlement" items="${settlementList}">
          <tr>
            <td>${settlement.projectName}</td>
            <td>
                ${settlement.amount}원<br>
              <span style="font-size: 12px; color: #999;">(수수료 ${settlement.fee}원 차감)</span>
            </td>
            <td>${settlement.date}</td>
            <td style="color: ${settlement.status == '완료' ? 'green' : 'orange'};">${settlement.status}</td>
            <td>
              <c:choose>
                <c:when test="${settlement.status == '완료'}">
                  <a href="${settlement.receiptUrl}">🔽 영수증</a>
                </c:when>
                <c:otherwise>-</c:otherwise>
              </c:choose>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <div class="notice">
      <strong>정산 안내</strong><br>
      정산은 프로젝트 완료 후 영업일 기준 3~5일 이내에 처리되며, 정산 금액에서 서비스 수수료 3%가 차감되며, 세금계산서는 정산 완료 후 이메일로 발송됩니다.
    </div>
  </main>
</div>

<script>
  function toggleForm(show) {
    document.getElementById("accountForm").style.display = show ? 'block' : 'none';
  }
</script>

</body>
</html>
