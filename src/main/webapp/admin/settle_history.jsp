<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>정산 내역 조회</title>
    <link rel="stylesheet" href="../css/admin/admin_header.css">
    <link rel="stylesheet" href="../css/settlement.css">
    <link rel="stylesheet" href="../css/settlement_history.css">
    <script> const defaultOpenMenuId = "projectMenu"; </script>
    <style>
        .search-input {
            width: 1000px !important;
        }
        .filter-item input[type="date"] {
            min-width: 150px !important;
            font-size: 14px;
            margin-left: 12px;
        }
        .filter-action {
            margin-left: auto;
        }
        .pagination {
            text-align: center;
        }
        .card.history-section {
            min-width: 1400px;
        }
    </style>
</head>

<body>
<div id="header-include"></div>

<div class="layout-wrapper">
    <div id="menu-include"></div>
    <div class="content">
        <div class="card filter-box">
            <h1>정산 내역 조회</h1>
            <div class="filter-row">
                <form method="get" action="<c:url value='/admin/settlement-history'/>" class="search-form" style="width: 100%; display: flex; gap: 20px; flex-wrap: wrap; align-items: flex-end;">
                    <div class="filter-item" style="flex: 2;">
                        <label>검색어</label>
                        <input type="text" name="keyword" placeholder="🔍 프로젝트명 또는 프리랜서명" value="${param.keyword}" class="search-input">
                    </div>
                    <div class="filter-item">
                        <label>시작일</label>
                        <input type="date" name="startDate" value="${param.startDate}">
                    </div>
                    <div class="filter-item">
                        <label>종료일</label>
                        <input type="date" name="endDate" value="${param.endDate}">
                    </div>
                    <div class="filter-action">
                        <button type="submit" class="search-btn">검색</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="card history-section" style="min-height: calc(100vh - 300px);">
            <div class="total-count">
             <h2>
                 총 <c:out value="${totalCount}" />건의 정산
             </h2>
            </div>

            <table class="settlement-table">
                <thead>
                <tr>
                    <th>정산 회차</th>
                    <th>프로젝트명</th>
                    <th>프리랜서</th>
                    <th>직무</th>
                    <th>정산 금액</th>
                    <th>계좌번호</th>
                    <th>정산 기간</th>
                    <th>정산일</th>
                    <th>상태</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="s" items="${settlementList}">
                    <tr>
                        <td>${s.cnt}회차</td>
                        <td>${s.projectName}</td>
                        <td>${s.freelancerName}</td>
                        <td>${s.position}</td>
                        <td><fmt:formatNumber value="${s.amount}" type="currency" currencySymbol="₩"/></td>
                        <td>${s.account}</td>
                        <td>${s.startDate} ~ ${s.endDate}</td>
                        <td>${s.settleDate}</td>
                        <td>
                            <span class="badge ${s.status}">${s.status}</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="pagination" style="margin-top: 32px; text-align: center;">
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

<script src="../js/include_common.js"></script>
</body>
</html>