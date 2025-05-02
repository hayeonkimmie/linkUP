<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up 마이페이지</title>
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css" />
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css' />">
</head>
<body>
<!-- 헤더 인클루드 영역 -->
<%--    <jsp:include page="../home/header.jsp" />--%>
<div id="header-placeholder"></div>
<%-- <%@ include file="<c:url value='/common/header.jsp'/>" %>--%>
<div class="container">
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <main class="content" style="margin-top: 9px;">
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>찜한 프로젝트</h3>
                </div>
            </div>
            <div class="table-container" style="overflow-x: auto;">
                <table>
                    <thead>
                    <tr>
                        <th>프로젝트명</th>
                        <th>분야</th>
                        <th class="duration" style="width: 80px;">기간</th>
                        <th>지원 자격</th>
                        <th>근무환경 | 근무방식</th>
                        <th class="deadline" style="width:180px" colspan="2">모집 마감일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${empty jjimProjList}">
                            <tr>
                                <td colspan="7" class="main-empty"><b>찜한 프로젝트가 없습니다.</b></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="jp" items="${jjimProjList}">
                                <tr>
                                    <td><a href="${jp.projectId}">${jp.projectName}</a></td>
                                    <td>${jp.category}</td>
                                    <td>${jp.duration}</td>
                                    <td>${jp.qualification}</td>
                                    <td>${jp.workingEnvironment}
                                        <c:if test="${jp.workingEnvironment ne '재택'}" > | ${jp.workingMethod}</c:if>
                                    </td>
                                    <td>${jp.deadlineDate}</td>
                                    <td class="d-day">${jp.dDay}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </section>
        <section class="main-section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>진행중인 프로젝트</h3>
                </div>
            </div>
            <div class="table-container" style="overflow-x: auto;">
                <table>
                    <thead>
                    <tr>
                        <th>프로젝트 정보</th>
                        <th>분야</th>
                        <th class="duration" style="width: 80px;">기간</th>
                        <th>예산</th>
                        <th>요구사항</th>
                        <th>근무환경 | 근무방식</th>
                        <th class="deadline" style="width:180px" colspan="2">마감일</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty onGoingProjList}">
                                <tr>
                                    <td colspan="8" class="main-empty"><b>진행중인 프로젝트가 없습니다.</b></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="project" items="${onGoingProjList}">
                                    <tr>
                                        <td><a href="${project.projectId}">${project.projectName}</a></td>
                                        <td>${project.categories}</td>
                                        <td>${project.projectDuration}</td>
                                        <td>${project.totalBudget}</td>
                                        <td>
                                            <c:if test="${project.reqSkills ne null}"> ${project.reqSkills}, </c:if>
                                                ${project.qualification}</td>
                                        <td>${project.workingEnvironment}
                                            <c:if test="${project.workingEnvironment ne '재택'}"> | ${project.workingMethod}</c:if>
                                        </td>
                                        <td>${project.deadlineDate}</td>
                                        <td><span class="d-day">${project.dDay}</span></td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
</div>

<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/catalog.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
</body>
</html>