<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>개인 프로젝트 지원 현황</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%--    <link rel="stylesheet" href="<c:url value='/css/common/headerSt.css'/>">--%>
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_apply_proj_list.css'/>">
    <script src="${contextPath}/js/freelancer_apply_proj_list.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css" />
</head>
<body>
<div class="header">
    <!-- 헤더 인클루드 영역 -->
    <jsp:include page="../home/header.jsp" />
</div>
<div class="container">
    <!-- 사이드바 -->
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <main class="content">
        <section class="section">
            <h3>내 프로젝트 지원 현황</h3>
            <p style="color: #888; font-size: 0.9rem; margin-bottom: 1rem;">지원한 프로젝트 목록을 확인하세요</p>
            <table id="apply-table">
                <thead>
                <tr>
                    <th>상태</th>
                    <th>지원일</th>
                    <th colspan="2">프로젝트 정보</th>
                    <th colspan="2">분야</th>
                    <th>기간 | 예산</th>
                    <th>모집 마감일<br>D-Day</th>
                    <th>승인여부</th>
                    <th>지원취소</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${ApplyProjList eq null}">
                        <tr>
                            <td colspan="10" style="text-align: center; padding: 20px;">
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
                                            <span class="status completed" style="background: #e05a5a;">지원취소</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <fmt:formatDate value='${apply.applyDate}' pattern='yyyy-MM-dd'/>
                                    <c:if test="${apply.cancelDate ne null}">
                                        <br><span style="color:red; font-size:0.75rem;">취소 <fmt:formatDate
                                            value='${apply.applyDate}' pattern='yyyy-MM-dd'/></span>
                                    </c:if>
                                </td>
                                <td></td>
                                <td>
                                    <div class="project-info">
                                        <div class="project-text">
                                                ${apply.advertisementTitle}<br>
                                                ${apply.projectName}
                                        </div>
                                    </div>
                                </td>
                                <td colspan="2">
                                    <span>${apply.category}</span>
                                </td>
                                <td>
                                        ${apply.duration}<br>${apply.projectFee}
                                </td>
                                <td><fmt:formatDate value='${apply.deadlineDate}' pattern='yyyy-MM-dd'/> <br><span
                                        class="d-day">${apply.dDay}</span></td>
                                <td style="font-weight: bold;">${apply.approved}</td>
                                <td>
                                    <c:if test="${apply.cancelDate eq null}">
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
            <div class="pagination" id="paging">
                <c:choose>
                    <c:when test="${pageInfo.curPage > 1}">
                        <a href="?page=${pageInfo.curPage-1 }">&lt;</a>
                    </c:when>
                    <c:otherwise>
                        <a>&lt;</a>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1"
                           var="page">
                    <c:choose>
                        <c:when test="${page eq pageInfo.curPage }">
                            <a href="?page=${page }" class="select">${page }</a>
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${page }" class="btn">${page }</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${pageInfo.curPage<pageInfo.allPage }">
                        <a href="?page=${pageInfo.curPage+1 }">&gt;</a>
                    </c:when>
                    <c:otherwise>
                        <a>&gt;</a>
                    </c:otherwise>
                </c:choose>
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
</body>
</html>