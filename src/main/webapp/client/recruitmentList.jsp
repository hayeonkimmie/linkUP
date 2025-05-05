<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>프로젝트 관리</title>
    <link rel="stylesheet" href="${contextPath}/css/client/style.css"/>
    <link rel="stylesheet" href="${contextPath}/css/common/headerLoginSt.css"/>
    <link rel="stylesheet" href="${contextPath}/css/client/recruitmentList.css"/>
    <link rel="stylesheet" href="${contextPath}/css/client/sideBar.css"/>
</head>
<body>
<input type="hidden" id="contextPath" value="${contextPath}"/>
<div id="header-placeholder"></div>

<div class="layout">
    <jsp:include page="../common/sidebar.jsp"/>

    <main class="main">
        <h2 class="section-title">프로젝트 관리</h2>

        <!-- 필터 탭 -->
        <div class="filter-tabs">
            <div class="filter-tab ${status eq 'all' ? 'active' : ''}" data-status="all">전체보기</div>
            <div class="filter-tab ${status eq 'open' ? 'active' : ''}" data-status="open">구인중</div>
            <div class="filter-tab ${status eq 'done-start' ? 'active' : ''}" data-status="done-start">시작전</div>
            <div class="filter-tab ${status eq 'done-progress' ? 'active' : ''}" data-status="done-progress">진행중</div>
            <div class="filter-tab ${status eq 'done-end' ? 'active' : ''}" data-status="done-end">종료됨</div>
        </div>

        <c:forEach var="project" items="${projectList}">
            <c:set var="statusClass" value="none"/>
            <c:choose>
                <c:when test="${project.status eq '구인중'}">
                    <c:set var="statusClass" value="open"/>
                </c:when>
                <c:when test="${project.status eq '구인완료'}">
                    <c:choose>
                        <c:when test="${project.projectProgress eq '시작전'}">
                            <c:set var="statusClass" value="done-start"/>
                        </c:when>
                        <c:when test="${project.projectProgress eq '진행중'}">
                            <c:set var="statusClass" value="done-progress"/>
                        </c:when>
                        <c:when test="${project.projectProgress eq '종료됨'}">
                            <c:set var="statusClass" value="done-end"/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>

            <c:if test="${statusClass ne 'none'}">

                <%--  구인중 => 구인중, 구인완료의 경우 3가지 경우의 수: 구인완료(시작전), 구인완료(진행중), 구인완료(종료됨)--%>
                <div class="job-card" data-status="${statusClass}" data-project-id="${project.projectId}">
                    <div class="job-card-badge">
                <span class="badge">
                    <c:choose>
                        <%-- 구인중이면 '구인중' 출력 --%>
                        <c:when test="${project.status eq '구인중'}">
                            ${project.status}
                        </c:when>
                        <%-- 구인완료 상태 + (진행상태) 출력 --%>
                        <c:when test="${project.status eq '구인완료'}">
                            ${project.status} (${project.projectProgress})
                        </c:when>
                    </c:choose>
                </span>
                    </div>

                    <div class="job-card-header">
                        <div class="job-title">${project.title}</div>
                        <div class="tags">
                            <c:forEach var="skill" items="${fn:split(project.skills, ',')}">
                                <span>${skill}</span>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="job-meta">
                            ${project.category} / 지원자 ${project.applyCount}명 / ${project.regDate} 등록
                    </div>

                    <div class="project-period">
                        <strong>프로젝트 기간</strong><br/>
                            ${project.startDate} ~ ${project.endDate}
                    </div>

                    <div class="job-card-status">
                        <div class="total-price">총 프로젝트 금액 <fmt:formatNumber value="${project.totalAmount}" type="number"
                                                                             groupingUsed="true"/>원
                        </div>
                    </div>

                    <div class="highlight">구인 인원 및 페이</div>
                    <div class="pay-section">
                        <c:forEach var="pay" items="${project.payList}">
                            <div class="pay-box">
                                <h4>${pay.level}</h4>
                                <p><fmt:formatNumber value="${pay.fee}" type="number" groupingUsed="true"/>원/인</p>
                                <p><fmt:formatNumber value="${pay.count}" type="number" groupingUsed="true"/>명</p>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="buttons">
                        <c:choose>
                            <c:when test="${project.status eq '구인중'}">
                                <button class="btn btn-secondary">모집확정하기</button>
                                <button class="btn btn-edit" onclick="location.href='${contextPath}/editProject?projectId=${project.projectId}'">수정하기</button>
                                <button class="btn btn-delete">삭제하기</button>
                                <a href="${contextPath}/candidateMgt?projectId=${project.projectId}" class="btn btn-manage">지원자
                                    관리</a>
                            </c:when>
                            <c:when test="${project.status eq '구인완료'}">
                                <c:choose>
                                    <%--                                    시작전일 경우 '수정'불가능 하게 변경--%>
                                    <%--                                    <c:when test="${project.projectProgress eq '시작전'}">--%>
                                    <%--                                        <button class="btn btn-edit">수정하기</button>--%>
                                    <%--                                    </c:when>--%>
                                    <c:when test="${project.projectProgress eq '진행중'}">
                                        <a href="<c:url value='/request-settlement'>
                                                <c:param name='projectId' value='${project.projectId}'/>
                                             </c:url>" class="btn btn-settle">
                                            월별 정산하기
                                        </a>
                                    </c:when>

                                    <c:when test="${project.projectProgress eq '종료됨' and project.settleStatus eq '완료'}">
                                        <button class="btn btn-review">리뷰작성</button>
                                    </c:when>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </div>
                </div>

            </c:if>
        </c:forEach>

        <!-- 페이징 영역 -->
        <div class="pagination">
            <c:if test="${pageInfo.curPage > pageInfo.startPage}">
                <a href="${contextPath}/clientRecruitMgt?page=${pageInfo.curPage - 1}&status=${status}">&lt;</a>
            </c:if>

            <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
                <c:choose>
                    <c:when test="${page eq pageInfo.curPage}">
                        <a href="${contextPath}/clientRecruitMgt?page=${page}&status=${status}" class="select">${page}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${contextPath}/clientRecruitMgt?page=${page}&status=${status}" class="btn">${page}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${pageInfo.curPage < pageInfo.allPage}">
                <a href="${contextPath}/clientRecruitMgt?page=${pageInfo.curPage + 1}&status=${status}">&gt;</a>
            </c:if>
        </div>
    </main>
</div>

<script>
    const contextPath = '${pageContext.request.contextPath}';
    document.addEventListener('DOMContentLoaded', function () {
        const tabs = document.querySelectorAll('.filter-tab');
        tabs.forEach(tab => {
            tab.addEventListener('click', function () {
                const selectedStatus = this.dataset.status;
                const url = new URL(window.location.href);
                url.searchParams.set("status", selectedStatus);
                url.searchParams.set("page", 1);
                window.location.href = url.toString();
            });
        });
    });
</script>

<script src="${contextPath}/js/catalog.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
<script src="${contextPath}/js/recruitmentList.js"></script>
</body>
</html>
