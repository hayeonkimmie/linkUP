<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>개인 프로젝트 지원 현황</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%--    <link rel="stylesheet" href="<c:url value='/css/common/headerSt.css'/>">--%>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css" />
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_apply_proj_list.css'/>">
</head>
<body>

<div id="header-placeholder"></div>
<div class="container">
    <!-- 사이드바 -->
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <main class="content">
        <section class="section">
            <h3>내 프로젝트 지원 현황 총 ${applyProjCount} 건</h3>
            <p style="color: #888; font-size: 0.9rem; margin-bottom: 1rem;">지원한 프로젝트 목록을 확인하세요</p>
            <table class="apply-table">
                <thead>
                <tr>
                    <th>상태</th>
                    <th style="width:10%;">지원일</th>
                    <th>프로젝트 정보</th>
                    <th>분야</th>
                    <th style="width: 10%;">기간 | 예산</th>
                    <th style="width: 9%;">모집 마감일<br>D-Day</th>
                    <th style="width: 8%;">승인여부</th>
                    <th style="width: 9%">지원취소</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty ApplyProjList}">
                        <tr>
                            <td colspan="8" class="empty">
                                지원한 프로젝트가 없습니다.
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="apply" items="${ApplyProjList}">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${apply.cancelDate eq null}">
                                            <span class="status ongoing">지원완료</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="status canceled" style="">지원취소</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <fmt:formatDate value='${apply.applyDate}' pattern='yyyy-MM-dd'/>
                                    <c:if test="${apply.cancelDate ne null}">
                                        <br><span style="color:red; font-size:0.75rem;">취소 <fmt:formatDate
                                            value='${apply.cancelDate}' pattern='yyyy-MM-dd'/></span>
                                    </c:if>
                                </td>
                                <td>
                                    <div class="project-info">
                                        <div class="project-text">
                                                <%--${apply.advertisementTitle}<br>--%>
                                            <a href="${contextPath}/project?projectid=${apply.projectId}">${apply.projectName}</a>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <span>${apply.category}</span>
                                </td>
                                <td>
                                    ${apply.duration}<br>
                                        <c:if test="${not empty apply.projectFee}">
                                            <fmt:formatNumber value="${apply.projectFee}" /> 원
                                        </c:if>
                                </td>
                                <td><fmt:formatDate value='${apply.deadlineDate}' pattern='yyyy-MM-dd'/> <br><span
                                        class="d-day">${apply.dDay}</span></td>
                                <td style="font-weight: bold;">${apply.approved}</td>
                                <td>
                                    <c:if test="${empty apply.cancelDate
                                         and fn:trim(apply.approved) ne '승인거절'
                                         and fn:trim(apply.approved) ne '거절'
                                         and fn:trim(apply.dDay) ne '마감'}">
                                    <button type="button"
                                                class="cancel-btn"
                                                data-apply-id="${apply.applyId}"
                                                data-context-path="${contextPath}">
                                            지원취소
                                        </button>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>

                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
            <!-- 페이지네이션 -->
            <div class="center">
                <div class="pagination" id="paging">
                    <c:choose>
                        <c:when test="${pageInfo.curPage > 1}">
                            <a href="?page=${pageInfo.curPage - 1}">&lt;</a>
                        </c:when>
                        <c:otherwise>
                            <a>&lt;</a>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" var="page">
                        <c:choose>
                            <c:when test="${page eq pageInfo.curPage}">
                                <a href="?page=${page}" class="select">${page}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="?page=${page}" class="btn">${page}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${pageInfo.curPage < pageInfo.allPage}">
                            <a href="?page=${pageInfo.curPage + 1}">&gt;</a>
                        </c:when>
                        <c:otherwise>
                            <a>&gt;</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </section>
    </main>

    <form id="cancelForm" method="get" action="${contextPath}/my-page/apply-cancel" style="display:none;">
        <input type="hidden" name="applyId" id="cancelApplyId">
    </form>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const cancelButtons = document.querySelectorAll(".cancel-btn");
        const cancelForm = document.getElementById("cancelForm");
        const cancelApplyIdInput = document.getElementById("cancelApplyId");

        cancelButtons.forEach(button => {
            button.addEventListener("click", function () {
                const applyId = this.dataset.applyId;

                if (!applyId) {
                    alert("지원 ID가 유효하지 않습니다.");
                    return;
                }

                const confirmCancel = confirm("정말로 지원을 취소하시겠습니까?");
                if (confirmCancel) {
                    cancelApplyIdInput.value = applyId;
                    cancelForm.submit();
                }
            });
        });
    });
</script>
<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/freelancer_apply_proj_list.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
</body>
</html>