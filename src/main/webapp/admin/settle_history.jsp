<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>정산 내역 조회</title>
    <link rel="stylesheet" href="../css/admin/admin_header.css">
    <link rel="stylesheet" href="../css/admin/settlement.css">
    <script> const defaultOpenMenuId = "projectMenu"; </script>
    <style>
        .search-input {
            width: 100%;
            font-size: 15px;
        }

        .filter-row {
            display: flex;
            flex-wrap: wrap;
            gap: 24px;
            align-items: flex-end;
        }

        .filter-item {
            display: flex;
            flex-direction: column;
            gap: 6px;
            flex: 1;
            min-width: 200px;
        }

        .filter-item input[type="date"] {
            min-width: 120px !important;
            font-size: 14px;
            padding: 6px 10px;
        }

        .filter-item input[type="text"] {
            font-size: 15px;
            padding: 8px 12px;
        }

        .filter-action {
            margin-left: auto;
        }

        .pagination {
            margin-top: 40px; /* ✅ 테이블과의 간격 */
            text-align: center;
            padding-bottom: 30px; /* ✅ 페이지 하단 여백 */
        }

        .card.history-section {
            min-width: 1400px;
            min-height: calc(100vh - 400px); /* ✅ 컨텐츠 높이 보장 */
            display: flex;
            flex-direction: column;
            justify-content: space-between; /* ✅ 내부 요소 하단 정렬 */
        }

        .settlement-table {
            margin-bottom: auto; /* ✅ 위 요소는 위로 밀고 */
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

        <div class="card history-section">
            <div class="total-count">
                <h2>총 <c:out value="${totalCount}" />건의 정산</h2>
            </div>

            <table class="settlement-table">
                <thead>
                <tr>
                    <th>정산 회차</th>
                    <th>프로젝트명</th>
                    <th>정산 금액</th>
                    <th>수수료</th>
                    <th>정산 총액</th>
                    <th>정산일</th>
                    <th>상태</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="h" items="${settlementList}">
                    <tr class="settlement-row"
                        data-slistid="${h.slistId}"
                        data-cnt="${h.cnt}"
                        data-projectname="${h.projectName}"
                        data-pay="${h.pay}"
                        data-fee="${h.fee}"
                        data-totalamount="${h.totalAmount}"
                        data-settledate="${h.settleDate}"
                        data-status="${h.status}"
                        style="cursor: pointer;">
                        <td>${h.cnt}회차</td>
                        <td>${h.projectName}</td>
                        <td><fmt:formatNumber value="${h.pay}" pattern="#,##0원"/></td>
                        <td><fmt:formatNumber value="${h.fee}" pattern="#,##0원"/></td>
                        <td><fmt:formatNumber value="${h.totalAmount}" pattern="#,##0원"/></td>
                        <td>${h.settleDate}</td>
                        <td><span class="badge ${h.status}">${h.status}</span></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <div class="pagination">
                <button <c:if test="${pageInfo.curPage == 1}">disabled</c:if>
                        onclick="location.href='?page=${pageInfo.curPage - 1}'">이전</button>

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

                <button <c:if test="${pageInfo.curPage == pageInfo.allPage}">disabled</c:if>
                        onclick="location.href='?page=${pageInfo.curPage + 1}'">다음</button>
            </div>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const rows = document.querySelectorAll(".settlement-row");

        rows.forEach(row => {
            row.addEventListener("click", function () {
                const data = {
                    slistId: this.dataset.slistid,
                    cnt: this.dataset.cnt,
                    projectName: this.dataset.projectname,
                    pay: this.dataset.pay,
                    fee: this.dataset.fee,
                    totalAmount: this.dataset.totalamount,
                    settleDate: this.dataset.settledate,
                    status: this.dataset.status
                };

                // ✅ slistId는 쿼리스트링으로, 나머지는 JSON으로 전송
                const query = new URLSearchParams({ slistid: data.slistId, format: 'json' }).toString();

                fetch(`/admin/settlement?${query}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                })
                    .then(res => res.json())
                    .then(result => {
                        console.log("서버 응답:", result);
                        // 필요하다면 이후 로직 추가
                    })
                    .catch(err => console.error("전송 오류:", err));
            });
        });
    });
</script>

<script src="../js/include_common.js"></script>
</body>
</html>