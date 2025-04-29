<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>프로젝트 조회</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css">
  <link rel="stylesheet" href="../css/admin/admin_project_list.css">
  <link rel="stylesheet" href="../css/table_common.css">
  <style>
    table th:nth-child(3),
    table td:nth-child(3) {
      width: 90px !important;
      white-space: nowrap !important;
      overflow: hidden !important;
      text-overflow: ellipsis !important;
    }

    table th:nth-child(4),
    table td:nth-child(4) {
      width: 200px !important;
      white-space: nowrap !important;
    }
  </style>
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

      <!-- ✅ 검색/필터 영역 -->
      <div class="search-bar">
        <form method="get" action="<c:url value='/admin/project'/>" class="search-form" style="display: flex; align-items: center; max-width: 1600px;">
          <input type="text" name="keyword" placeholder="🔍 프로젝트명, 회사명으로 검색"
                 value="${param.keyword}" id="searchInput" class="search-input"
                 style="width: 800px; height: 40px; padding: 0 10px; font-size: 16px;">

          <div style="display: flex; align-items: center; gap: 10px; margin-left: 30px;">
            <div class="filter-item" style="display: flex; align-items: center;">
              <label for="startDate" style="margin-right: 5px;">시작일:</label>
              <input type="date" name="startDate" id="startDate" value="${param.startDate}" style="width: 160px; height: 32px;">
            </div>
            <div class="filter-item" style="display: flex; align-items: center;">
              <label for="endDate" style="margin-right: 5px;">종료일:</label>
              <input type="date" name="endDate" id="endDate" value="${param.endDate}" style="width: 160px; height: 32px;">
            </div>
            <div class="filter-item" style="display: flex; align-items: center;">
              <label for="settleStatus" style="margin-right: 5px;">상태:</label>
              <select name="settleStatus" id="settleStatus" style="width: 120px; height: 36px;">
                <option value="">전체</option>
                <option value="진행중" ${param.settleStatus == '진행중' ? 'selected' : ''}>진행중</option>
                <option value="정산완료" ${param.settleStatus == '정산완료' ? 'selected' : ''}>정산완료</option>
              </select>
            </div>
            <button type="submit" class="search-btn" style="width: 80px; height: 40px;">검색</button>
          </div>
        </form>
      </div>

      <div class="total-count">총 <c:out value="${totalCount}" />개의 프로젝트</div>

      <!-- ✅ 프로젝트 테이블 -->
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
            <td>
              <c:choose>
                <c:when test="${p.totalSettlement == 0}">
                  <span class="no-settlement">정산 예정 없음</span>
                </c:when>
                <c:otherwise>
                  <fmt:formatNumber value="${p.totalSettlement}" type="currency" currencySymbol="₩"/>
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>

      <!-- ✅ 페이지네이션 (검색 조건 유지) -->
      <div class="pagination" style="margin-top: 30px; text-align: center;">
        <!-- 이전 버튼 -->
        <c:if test="${pageInfo.curPage > 1}">
          <c:url var="prevUrl" value="/admin/project">
            <c:param name="page" value="${pageInfo.curPage - 1}"/>
            <c:if test="${param.keyword != null}"><c:param name="keyword" value="${param.keyword}"/></c:if>
            <c:if test="${param.startDate != null}"><c:param name="startDate" value="${param.startDate}"/></c:if>
            <c:if test="${param.endDate != null}"><c:param name="endDate" value="${param.endDate}"/></c:if>
            <c:if test="${param.settleStatus != null}"><c:param name="settleStatus" value="${param.settleStatus}"/></c:if>
          </c:url>
          <button onclick="location.href='${prevUrl}'">이전</button>
        </c:if>

        <!-- 페이지 번호 -->
        <c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
          <c:url var="pageUrl" value="/admin/project">
            <c:param name="page" value="${i}"/>
            <c:if test="${param.keyword != null}"><c:param name="keyword" value="${param.keyword}"/></c:if>
            <c:if test="${param.startDate != null}"><c:param name="startDate" value="${param.startDate}"/></c:if>
            <c:if test="${param.endDate != null}"><c:param name="endDate" value="${param.endDate}"/></c:if>
            <c:if test="${param.settleStatus != null}"><c:param name="settleStatus" value="${param.settleStatus}"/></c:if>
          </c:url>
          <c:choose>
            <c:when test="${i == pageInfo.curPage}">
              <button class="page-button selected" disabled>${i}</button>
            </c:when>
            <c:otherwise>
              <button class="page-button" onclick="location.href='${pageUrl}'">${i}</button>
            </c:otherwise>
          </c:choose>
        </c:forEach>

        <!-- 다음 버튼 -->
        <c:if test="${pageInfo.curPage < pageInfo.allPage}">
          <c:url var="nextUrl" value="/admin/project">
            <c:param name="page" value="${pageInfo.curPage + 1}"/>
            <c:if test="${param.keyword != null}"><c:param name="keyword" value="${param.keyword}"/></c:if>
            <c:if test="${param.startDate != null}"><c:param name="startDate" value="${param.startDate}"/></c:if>
            <c:if test="${param.endDate != null}"><c:param name="endDate" value="${param.endDate}"/></c:if>
            <c:if test="${param.settleStatus != null}"><c:param name="settleStatus" value="${param.settleStatus}"/></c:if>
          </c:url>
          <button onclick="location.href='${nextUrl}'">다음</button>
        </c:if>
      </div>

    </div>
  </div>
</div>

<script src="../js/include_common.js"></script>
</body>
</html>
