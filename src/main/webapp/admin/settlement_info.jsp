<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>그리자 프로젝트 정산 현황</title>
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
      <select id="monthFilter">
        <c:forEach var="monthInfo" items="${settlementMonths}">
          <option value="${monthInfo.cnt}">
              ${fn:substring(monthInfo.settle_date, 0, 4)}년 ${fn:substring(monthInfo.settle_date, 5, 7)}월
          </option>
        </c:forEach>
      </select>
    </div>

    <div class="card summary-box">
      <div class="summary-item">
        <span class="label">프로젝트명</span>
        <span class="value" id="projectName">-</span>
      </div>
      <div class="summary-item">
        <span class="label">전체 계약금액</span>
        <span class="value" id="totalAmount">
          <fmt:formatNumber value="${totalAmount}" pattern="#\,##0원"/>
        </span>
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
        <table id="doneTable">
          <thead>
          <tr>
            <th>참여자명</th>
            <th>구리</th>
            <th>정산 기간</th>
            <th>정산 회차</th>
            <th>이보년 정산 금액</th>
            <th>상태</th>
          </tr>
          </thead>
          <tbody>
          <c:choose>
            <c:when test="${empty doneList}">
              <tr><td colspan="6" style="text-align:center;">정산 완료 인원이 존재하지 않습니다</td></tr>
            </c:when>
            <c:otherwise>
              <c:forEach var="item" items="${doneList}">
                <tr>
                  <td>
                    <a href="javascript:void(0);" onclick="openSettlementModal('${item.freelancerName}', '${item.projectId}')">
                        ${item.freelancerName}
                    </a>
                  </td>
                  <td>${item.position}</td>
                  <td>${item.startDate} ~ ${item.endDate}</td>
                  <td>${item.cnt}회차</td>
                  <td><fmt:formatNumber value="${item.settleAmount}" pattern="#,##0원"/></td>
                  <td><span class="status complete">${item.status}</span></td>
                </tr>
              </c:forEach>

            </c:otherwise>
          </c:choose>
          </tbody>
        </table>
      </div>
    </div>

    <div class="card">
      <h3>정산 대기 인원</h3>
      <div class="fixed-table-wrapper">
        <table id="waitTable">
          <thead>
          <tr>
            <th>참여자명</th>
            <th>구리</th>
            <th>정산 기간</th>
            <th>정산 회차</th>
            <th>이보년 정산 금액</th>
            <th>상태</th>
          </tr>
          </thead>
          <tbody>
          <c:choose>
            <c:when test="${empty waitList}">
              <tr><td colspan="6" style="text-align:center;">정산 대기 인원이 존재하지 않습니다</td></tr>
            </c:when>
            <c:otherwise>
              <c:forEach var="item" items="${waitList}">
                <tr>
                  <td>
                    <a href="javascript:void(0);" onclick="openSettlementModal('${item.freelancerName}', '${item.projectId}')">
                      <c:out value="${item.freelancerName != null ? item.freelancerName : '-'}"/>
                    </a>
                  </td>
                  <td>${item.position}</td>
                  <td>${item.startDate} ~ ${item.endDate}</td>
                  <td>${item.cnt}회차</td>
                  <td><fmt:formatNumber value="${item.settleAmount}" pattern="#,##0원"/></td>
                  <td><span class="status pending">${item.status}</span></td>
                </tr>
              </c:forEach>

            </c:otherwise>
          </c:choose>
          </tbody>
        </table>
      </div>
    </div>

  </div>
</div>
<div id="settlementModal"
     style="display:none; position:fixed; top:20%; left:30%; width:40%; background:white; border:1px solid #ccc; padding:20px; z-index:1000;">
  <h3>
    <span id="modalProjectName" style="font-weight:normal;"></span> - 정산 내역
  </h3>
  <table border="1" style="width:100%;" class="settlement-table">
    <thead>
    <tr>
      <th>회차</th>
      <th>금액</th>
      <th>상태</th>
      <th>정산일</th>
    </tr>
    </thead>
    <tbody id="settlementTableBody">
    </tbody>
    <tfoot>
    <tr>
      <td colspan="4" class="total" style="text-align:right; font-weight:bold;">
        총 합계: <strong id="modalTotalAmount">₩0</strong>
      </td>
    </tr>
    </tfoot>
  </table>
  <br/>
  <button onclick="closeSettlementModal()">닫기</button>
</div>

<script>
  window.projectJson = ${projectJson};
</script>
<script>
  const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="../js/admin_settlement_info.js"></script>

</body>
</html>