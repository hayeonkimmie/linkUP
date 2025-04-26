<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>프로젝트 정산 현황</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css">
  <link rel="stylesheet" href="../css/admin/settlement_info.css">
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
    <h2 class="page-title">프로젝트 정산 현황</h2>

    <div class="filter-bar">
      <select>
        <option selected>2024년 5월</option>
        <option>2024년 4월</option>
        <option>2024년 3월</option>
      </select>
    </div>

    <div class="card summary-box">
      <div class="summary-item">
        <span class="label">프로젝트명</span>
        <span class="value" id="projectName">-</span>
      </div>
      <div class="summary-item">
        <span class="label">전체 계약금액</span>
        <span class="value"><fmt:formatNumber value="${totalAmount}" pattern="#,##0원"/></span>

<%--        <span class="value" id="totalAmount">-</span>--%>
      </div>
      <div class="summary-item">
        <span class="label">프로젝트 기간</span>
        <span class="value" id="projectPeriod">-</span>
      </div>
      <div class="summary-item">
        <span class="label">총 참여 인원</span>
        <span class="value">
      <c:set var="totalPeople" value="${fn:length(doneList) + fn:length(waitList)}"/>
      ${totalPeople}명
    </span>
      </div>

      <div class="summary-item">
        <span class="label">프로젝트 정산일</span>
        <span class="value" id="settleDay">-</span>
      </div>

    </div>

    <div class="card">
      <h3>정산 완료 인원</h3>
      <div class="fixed-table-wrapper">
        <c:choose>
          <c:when test="${empty doneList}">
            <div class="no-data">정산 완료 인원이 존재하지 않습니다</div>
          </c:when>
          <c:otherwise>
            <table>
              <thead>
              <tr>
                <th>참여자명</th>
                <th>구분</th>
                <th>참여 기간</th>
                <th>정산 회차</th>
                <th>이번달 정산 금액</th>
                <th>상태</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="item" items="${doneList}">
                <tr>
                  <td>${item.freelancerName}</td>
                  <td>${item.position}</td>
                  <td>${item.startDate} ~ ${item.endDate}</td>
                  <td>${item.duration}개월</td>
                  <td><fmt:formatNumber value="${item.settleAmount}" pattern="#,##0원"/></td>
                  <td><span class="status complete">${item.status}</span></td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </c:otherwise>
        </c:choose>
      </div>
    </div>

    <div class="card">
      <h3>정산 대기 인원</h3>
      <div class="fixed-table-wrapper">
        <c:choose>
          <c:when test="${empty waitList}">
            <div class="no-data">정산 대기 인원이 존재하지 않습니다</div>
          </c:when>
          <c:otherwise>
            <table>
              <thead>
              <tr>
                <th>참여자명</th>
                <th>구분</th>
                <th>참여 기간</th>
                <th>이번달 정산 금액</th>
                <th>상태</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="item" items="${waitList}">
                <tr>
                  <td><c:out value="${item.freelancerName != null ? item.freelancerName : '-'}"/></td>
                  <td>${item.position}</td>
                  <td>${item.duration}개월</td>
                  <td><fmt:formatNumber value="${item.settleAmount}" pattern="#,##0원"/></td>
                  <td><span class="status pending">${item.status}</span></td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </c:otherwise>
        </c:choose>
      </div>
    </div>

  </div>
<script>
  document.addEventListener("DOMContentLoaded", () => {
    const data = JSON.parse('${projectJson}'); // 💥 문자열을 JSON 객체로 변환

    console.log(data)
    document.getElementById("projectName").textContent = data.projectName;
    document.getElementById("projectPeriod").textContent = `\${data.startDate} ~ \${data.endDate}`;
    const settleDate = new Date(data.settleDate);
    const settleDay = settleDate.getDate();
    document.getElementById("settleDay").textContent = `매월 \${settleDay}일`;
    const formatter = new Intl.NumberFormat('ko-KR');

    // 여기서 totalAmount 제대로 출력 가능
    document.getElementById("totalAmount").textContent = formatter.format(data.totalAmount) + '원';
  });

</script>

</body>
</html>
