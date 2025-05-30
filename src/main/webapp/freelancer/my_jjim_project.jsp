<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Link Up - 찜한 프로젝트 목록</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/css/headerSt.css">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_like.css'/>">
   <script src="${contextPath}/js/freelancer_my_page_like_proj.js"></script>
</head>
<body>
<div id="header-placeholder"></div>
<div class="container">
<!-- 사이드바 -->
<jsp:include page="/freelancer/sidebar.jsp"/>
<!-- 메인 콘텐츠 -->
    <main class="content" style="margin: 0" >
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h2>찜한 프로젝트</h2>
                    <p class="subtext">찜한 프로젝트 목록을 확인하세요</p>
                </div>
            </div>
            <c:choose>
                <c:when test="${empty jjimProjList}">
                    <div class="qna-empty empty">
                        <table>
                            <thead>
                                <tr>
                                    <th class="center"><input type="checkbox"/></th>
                                    <th>프로젝트명</th>
                                    <th>분야</th>
                                    <th class="duration center">기간</th>
                                    <th style="width:16.81%;">근무 환경 | 근무 방식</th>
                                        <%--<th>클라이언트</th>--%>
                                    <th>지원 자격</th>
                                    <th class="deadline center" colspan="2">모집 마감일</th>
                                </tr>
                            </thead>
                            <tbody>
                            <tr><td class="empty" colspan="8">찜한 프로젝트 내역이 없습니다.</td></tr>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="project-table">
                        <div class="project-table-header">
                            <h3>찜한 프로젝트 목록 <span>총 ${jjimProjCnt} 건</span></h3>
                        <form action="${contextPath}/my-page/project-jjim-list" method="post">
                            <div class="delete-button-wrapper">
                                <button class="delete-button" type="submit">삭제</button>
                            </div>
                            <table>
                                <thead>
                                <tr>
                                    <th class="center"><input type="checkbox" id="select_all"/></th>
                                    <th>프로젝트명</th>
                                    <th>분야</th>
                                    <th class="duration center">기간</th>
                                    <th style="width:16.81%;">근무 환경 | 근무 방식</th>
                                    <%--<th>클라이언트</th>--%>
                                    <th>지원 자격</th>
                                    <th class="deadline center" colspan="2">모집 마감일</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="jp" items="${jjimProjList}">
                                    <tr>
                                        <td><input class="row-check"  type="checkbox" name="jjimId" value="${jp.jjimId}"/></td>
                                        <td><a href="${contextPath}/project?projectid=${jp.projectId}">${jp.projectName}</a></td>
                                        <td>${jp.category}</td>
                                        <td class="center">${jp.duration}</td>
                                        <td>${jp.workingEnvironment}
                                            <c:if test="${jp.workingEnvironment ne '재택'}"> | ${jp.workingMethod}</c:if>
                                        </td>
                                        <%--<td>${jp.companyName}</td>--%>
                                        <td>${jp.qualification}</td>
                                        <td class="center">${jp.deadlineDate}</td>
                                        <td class="d-day center">${jp.dDay}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </form>
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
                                        <a href="?page=${pageInfo.curPage + 1}">1&gt;</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a>&gt;</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div> <!-- end of pagination -->
                    </div> <!-- end of project-table -->
                </c:otherwise>
            </c:choose>
        </section>
    </main>
</div>
</body>
<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
</html>
