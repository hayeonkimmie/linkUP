<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>프로젝트 조회</title>
  <link rel="stylesheet" href="../css/admin_header.css">
  <link rel="stylesheet" href="../css/admin_project_list.css">
  <link rel="stylesheet" href="../css/table_common.css">
  <script> const defaultOpenMenuId = "projectMenu"; </script>
  <script src="../js/include_common.js"></script>
</head>

<body>
<div id="header-include"></div>
<div class="layout-wrapper">
  <div id="menu-include"></div>
  <div class="content">
    <div class="card">
      <h1>프로젝트 조회</h1>
      <p class="desc">진행되었던 프로젝트 리스트입니다.</p>

      <!-- 검색 바 -->
      <div class="search-bar">
        <input type="text" placeholder="🔍  프로젝트명, 회사명으로 검색">
        <div class="filter-group">
          <input type="date">
          <span>~</span>
          <input type="date">
          <button>검색</button>
        </div>
      </div>

      <!-- 프로젝트 총 개수 표시 -->
      <div class="total-count">총 <c:out value="${fn:length(projectList)}" />개의 프로젝트</div>

      <!-- 프로젝트 테이블 -->
      <table>
        <thead>
        <tr>
          <th>프로젝트명</th>
          <th>회사명</th>
          <th>담당자</th>
          <th>기간</th>
          <th>연락처</th>
          <th>상태</th>
          <th>정산 예정 금액</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${projectList}">
          <tr>
            <td>
              <a href="<c:url value='/admin/project'/>?id=${p.projectId}" class="project-link">${p.projectName}</a>
<%--              <a href="project_detail.jsp?id=${p.projectId}" class="project-link">${p.projectName}</a>--%>
            </td>
            <td>
              <a href="client?clientid=${p.clientId}" class="company-link">${p.clientName}</a>
            </td>
            <td>${p.projectManager}</td>
            <td>${p.projectDuration}</td>
            <td>${p.managerPhone}</td>
            <td><span class="badge ${p.settleStatus}">${p.settleStatus}</span></td>
            <td><fmt:formatNumber value="${p.totalSettlement}" type="currency" currencySymbol="₩"/></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>

      <!-- 페이지네이션 -->
      <div class="pagination">
        <!-- 이전 버튼 -->
        <button
                <c:if test="${pageInfo.curPage == 1}">disabled</c:if>
                onclick="location.href='?page=${pageInfo.curPage - 1}'">
          이전
        </button>

        <!-- 페이지 번호 버튼들 -->
        <c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
          <c:choose>
            <c:when test="${i == pageInfo.curPage}">
              <button class="page-button selected" disabled>${i}</button>
            </c:when>
            <c:otherwise>
              <button class="page-button" onclick="location.href='?page=${i}'">${i}</button>
            </c:otherwise>
          </c:choose>
        </c:forEach>

        <!-- 다음 버튼 -->
        <button
                <c:if test="${pageInfo.curPage == pageInfo.allPage}">disabled</c:if>
                onclick="location.href='?page=${pageInfo.curPage + 1}'">
          다음
        </button>
      </div>

    </div>
  </div>
</div>
</body>
</html>
