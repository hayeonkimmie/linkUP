<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up 마이페이지</title>
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css' />">
</head>
<body>
<div class="header">
    <jsp:include page="/common/header.jsp" />
   <%-- <%@ include file="<c:url value='/common/header.jsp'/>" %>--%>
</div>
<div class="container">
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <main class="content">
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>찜한 프로젝트</h3>
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <th>프로젝트명</th>
                    <th>분야</th>
                    <th>기간</th>
                    <th>근무 환경 | 근무 시간</th>
                    <th>지원 자격</th>
                    <th colspan="2">모집 마감일</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty jjimProjList} && ${jjimProjList eq null}">
                        <tr>
                            <td colspan="8" style="height: 39px;"><b>찜한 프로젝트가 없습니다.</b></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="jp" items="${jjimProjList}">
                            <tr>
                                <td><a href="${jp.projectId}">${jp.projectName}</a></td>
                                <td>${jp.category}</td>
                                <td>${jp.duration}</td>
                                <td>${jp.workingEnvironment}
                                    <c:if test="${jp.workingEnvironment ne '재택'}">(${jp.workingMethod})</c:if> | ${jp.workingHours}
                                </td>
                                <td>${jp.qualification}</td>
                                <td>${jp.deadlineDate}</td>
                                <td class="d-day">${jp.dDay}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </section>
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>진행중 프로젝트</h3>
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <th>프로젝트 정보</th>
                    <th>분야</th>
                    <th>기간</th>
                    <th>예산</th>
                    <th>요구사항</th>
                    <th>작업방식</th>
                    <th colspan="2">마감일</th>
                </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty onGoingProjList} && ${onGoingProjList eq null}">
                            <tr>
                                <td colspan="8" style="height: 39px;"><b>찜한 프로젝트가 없습니다.</b></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="project" items="${onGoingProjList}">
                                <tr>
                                    <td><a href="${project.projectId}">${project.projectName}</a><hr/>${project.clientName}</td>
                                    <td>${project.categories}</td>
                                    <td>${project.projectDuration}</td>
                                    <td>${project.totalBudget}</td>
                                    <td>
                                        <c:if test="${project.reqSkills ne null}">${project.reqSkills}, </c:if>
                                            ${project.qualification}</td>
                                    <td>${project.workingEnvironment}
                                        <c:if test="${project.workingEnvironment ne '재택'}">(${project.workingMethod})</c:if>| ${project.workingHours}
                                    </td>
                                    <td>${project.deadlineDate}</td>
                                    <td><span class="d-day">${project.dDay}</span></td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </section>
    </main>
</div>
</body>
</html>