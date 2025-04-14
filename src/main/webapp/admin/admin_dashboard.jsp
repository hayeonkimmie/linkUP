<%--
  Author: 이원영
  Date: 25. 4. 14.
  Time: 오후 2:10
  Description: 관리자 대시보드 페이지
  Read Data :
  *  이번달 총 거래액 : Integer / totalPayment
  *  이번달 계약이 성사 된 프로젝트의 수 : Integer / totalProjectCount
  *  이번달에 진행이 완료 된 프로젝트의 수 : Integer / completedProjectCount
  *  이번달 진행중인 프로젝트의 정보 : List(Project) / ongoingProjects
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, dto.Project" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>관리자 대시보드</title>
  <link rel="stylesheet" href="../css/admin_header.css">
  <link rel="stylesheet" href="../css/admin_dashboard.css">
</head>
<body>
<!-- ✅ JSP include 방식 -->
<jsp:include page="/admin/admin_header.jsp" />
<div class="layout">
  <!-- ✅ 메뉴탭도 JSP include 방식으로 -->
  <jsp:include page="/admin/menutap.jsp" />
  <div class="content">
    <div class="cards">
      <div class="card">
        <div class="card-title">이번달 총 거래액</div>
        <%
          Integer totalPayment = (Integer) request.getAttribute("totalPayment");
          String formattedPayment = String.format("%,d", totalPayment);
        %>
        <div class="card-value">₩<%= formattedPayment %></div>
        <div class="card-sub">전월 대비 12% 증가</div>
      </div>
      <div class="card">
        <div class="card-title">진행중인 프로젝트</div>
        <div class="card-value"><%= request.getAttribute("totalProjectCount") %></div>
        <div class="card-sub">전월 대비 8건 증가</div>
      </div>
      <div class="card">
        <div class="card-title">완료된 프로젝트</div>
        <div class="card-value"><%= request.getAttribute("completedProjectCount") %></div>
        <div class="card-sub">전월 대비 15건 증가</div>
      </div>
    </div>

    <div class="table-box">
      <div class="section-title">
        이번달 프로젝트 결제내역
        <a href="project_list.html" class="view-more">전체 보기 →</a>
      </div>
      <table>
        <thead>
        <tr>
          <th>프로젝트명</th>
          <th>설명</th>
          <th>시작일</th>
          <th>종료일</th>
          <th>상태</th>
          <th>담당자</th>
        </tr>
        </thead>
        <tbody>
        <%
          List<Project> list = (List<Project>) request.getAttribute("ongoingProjects");
          for (Project p : list) {
        %>
        <tr>
          <td><%= p.getProjectName() %></td>
          <td><%= p.getProjectDescription() %></td>
          <td><%= p.getProjectStartDate() %></td>
          <td><%= p.getProjectEndDate() %></td>
          <td><span class="status 진행중"><%= p.getProjectStatus() %></span></td>
          <td><%= p.getProjectManager() %></td>
        </tr>
        <% } %>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>
