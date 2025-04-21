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
</head>

<body>
<div id="header-include"></div>
<div class="layout-wrapper">
  <div id="menu-include"></div>
  <div class="content">
    <div class="card" style="min-height: calc(100vh - 100px);">
      <h1>프로젝트 조회</h1>
      <h2>진행되었던 프로젝트 리스트입니다.</h2>

      <div class="search-bar">
        <form method="get" action="<c:url value='/admin/project'/>" class="search-form">
          <input type="text" name="keyword" placeholder="🔍 프로젝트명, 회사명으로 검색" value="${param.keyword}" id="searchInput" class="search-input" style="width: 400px;">

          <button type="button" id="toggleFilterBtn" class="filter-toggle-btn">필터 ▾</button>
          <button type="submit" class="search-btn">검색</button>

          <!-- 필터 드롭다운 (form 안에 있지만 input은 hidden 아님) -->
          <div id="filterDropdown" class="filter-dropdown">
            <h4>프로젝트 필터</h4>
            <div class="filter-item">
              <label for="startDate">시작일:</label>
              <input type="date" name="startDate" id="startDate" value="" />
            </div>
            <div class="filter-item">
              <label for="endDate">종료일:</label>
              <input type="date" name="endDate" id="endDate" value="" />
            </div>
            <div class="filter-item">
              <label for="settleStatus">상태:</label>
              <select name="settleStatus" id="settleStatus">
                <option value="">전체</option>
                <option value="진행중">진행중</option>
                <option value="정산완료">정산완료</option>
              </select>
            </div>
          </div>
        </form>
      </div>

      <div class="total-count">총 <c:out value="${totalCount}" />개의 프로젝트</div>

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
            <td><a href="<c:url value='/admin/project'/>?id=${p.projectId}" class="project-link">${p.projectName}</a></td>
            <td><a href="client?clientid=${p.clientId}" class="company-link">${p.clientName}</a></td>
            <td>${p.projectManager}</td>
            <td>${p.projectDuration}</td>
            <td>${p.managerPhone}</td>
            <td><span class="badge ${p.settleStatus}">${p.settleStatus}</span></td>
            <td><fmt:formatNumber value="${p.totalSettlement}" type="currency" currencySymbol="₩"/></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>

      <div class="pagination">
        <button <c:if test="${pageInfo.curPage == 1}">disabled</c:if> onclick="location.href='?page=${pageInfo.curPage - 1}'">이전</button>
        <c:forEach var="i" begin="1" end="${pageInfo.allPage}">
          <c:choose>
            <c:when test="${i == pageInfo.curPage}">
              <button class="page-button selected" disabled>${i}</button>
            </c:when>
            <c:otherwise>
              <button class="page-button" onclick="location.href='?page=${i}'">${i}</button>
            </c:otherwise>
          </c:choose>
        </c:forEach>
        <button <c:if test="${pageInfo.curPage == pageInfo.allPage}">disabled</c:if> onclick="location.href='?page=${pageInfo.curPage + 1}'">다음</button>
      </div>
    </div>
  </div>
</div>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const toggleBtn = document.getElementById("toggleFilterBtn");
    const dropdown = document.getElementById("filterDropdown");

    // 새로고침 시 필터 초기화
    document.getElementById("startDate").value = "";
    document.getElementById("endDate").value = "";
    document.getElementById("settleStatus").selectedIndex = 0;

    dropdown.style.display = "none";

    toggleBtn.addEventListener("click", function (e) {
      e.stopPropagation();
      const isVisible = dropdown.style.display === "block";
      if (isVisible) {
        dropdown.style.display = "none";
      } else {
        const rect = toggleBtn.getBoundingClientRect();
        dropdown.style.position = "absolute";
        dropdown.style.top = `${rect.bottom + window.scrollY + 60}px`;
        dropdown.style.left = `${rect.left + window.scrollX + 1250}px`;
        dropdown.style.display = "block";
      }
    });

    document.addEventListener("click", function (e) {
      if (!dropdown.contains(e.target) && !toggleBtn.contains(e.target)) {
        dropdown.style.display = "none";
      }
    });
  });
</script>
<script src="../js/include_common.js"></script>
</body>
</html>