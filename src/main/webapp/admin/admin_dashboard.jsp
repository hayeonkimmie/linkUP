<%--
  Author: 이원영
  Date: 25. 4. 14.
  Time: 오후 2:10
  Description: 관리자 대시보드 페이지
  Read Data :
  *  이번달 총 거래액 : Integer / totalPayment
  *  이번달 계약이 성사 된 프로젝트의 수 : Integer / totalProjectCount
  *  이번달에 정산된 총 수수료 : Integer / totalPaymentFee
  *  이번달 진행중인 프로젝트의 정보 : List(AdminProject) / ongoingProjects
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="dto.DashboardProject" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="dto.AdminProject" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>관리자 대시보드</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css">
  <link rel="stylesheet" href="../css/admin/admin_dashboard.css">
</head>
<body>
<jsp:include page="/admin/admin_header.jsp" />
<div class="layout">
  <jsp:include page="/admin/menutap.jsp" />
  <div class="content">
    <div class="cards">
      <div class="card">
        <div class="card-title">이번달 총 거래액</div>
<%--        <div class="card-value">₩ <%= formattedPayment %></div>--%>
        <div class="card-value"><fmt:formatNumber value="${totalPayment}" pattern="#,##0원"/></div>
<%--        <td><fmt:formatNumber value="${h.pay}" pattern="#,##0원"/></td>--%>
      </div>
      <div class="card">
        <div class="card-title">진행중인 프로젝트</div>
        <div class="card-value"><%= request.getAttribute("totalProjectCount") %></div>
<%--        <div class="card-sub">전월 대비 8건 증가</div>--%>
      </div>
      <div class="card">
        <div class="card-title">정산된 수수료</div>
        <div class="card-value"><fmt:formatNumber value="${totalPaymentFee}" pattern="#,##0원"/></div>
<%--        <div class="card-value">₩ <%= formattedPaymentFee %></div>--%>
      </div>
    </div>

    <div class="table-box">
      <div class="section-title">
        이번달 프로젝트 결제내역
        <a href="project_list.jsp" class="view-more">전체 보기 →</a>
      </div>
      <table>
        <thead>
        <tr>
          <th>ID</th>
          <th>프로젝트 명</th>
          <th>금액</th>
          <th>수수료</th>
          <th>총 금액</th>
          <th>진행 상태</th>
          <th>담당자</th>
          <th>프로젝트 기간</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${ongoingProjects}">
          <tr>
            <td>${p.projectId}</td>
            <td>${p.projectName}</td>
            <td><fmt:formatNumber value="${p.totalAmount}" type="currency" pattern="#,##0원"/></td>
            <td><fmt:formatNumber value="${p.totalFee}" type="currency" pattern="#,##0원"/></td>
            <td><fmt:formatNumber value="${p.totalSettlement}" type="currency" pattern="#,##0원"/></td>
            <td><span class="status 진행중">${p.settleStatus}</span></td>
            <td>${p.projectManager}</td>
            <td>${p.projectDuration}</td>
          </tr>
        </c:forEach>
        </tbody>

      </table>
    </div>
  </div>
</div>
</body>
</html>
