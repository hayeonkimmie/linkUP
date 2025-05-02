<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- if, forEach, c:set사용 위함 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 함수사용 - -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 숫자 포맷팅 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    // 세션에서 플래그 확인 - 페이지 상단으로 이동
    Boolean projectUpdated = (Boolean) session.getAttribute("projectUpdated");
    // URL에 timestamp 파라미터가 있는지 확인
    String timestamp = request.getParameter("timestamp");

    if (projectUpdated != null && projectUpdated) {
        // 플래그 제거
        session.removeAttribute("projectUpdated");

        // timestamp 파라미터가 없으면 리다이렉트
        if (timestamp == null) {
            String redirectURL = request.getRequestURI() + "?timestamp=" + System.currentTimeMillis();
            // 기존 파라미터 유지
            String queryString = request.getQueryString();
            if (queryString != null && !queryString.isEmpty()) {
                redirectURL += "&" + queryString;
            }
            response.sendRedirect(redirectURL);
            return; // 페이지 처리 중단
        }
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- 캐시 방지 메타 태그 추가 -->
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>프로젝트 관리</title>
    <link rel="stylesheet" href="${contextPath}/css/client/style.css"/>
    <link rel="stylesheet" href="${contextPath}/css/common/headerLoginSt.css"/>
    <link rel="stylesheet" href="${contextPath}/css/client/recruitmentList.css"/>
    <link rel="stylesheet" href="${contextPath}/css/client/sideBar.css"/>
</head>

<script>
    window.contextPath = '${contextPath}';
</script>

<body>
<input type="hidden" id="contextPath" value="${contextPath}"/>
<div id="header-placeholder"></div>

<div class="layout">
    <!-- 구인자 공통 사이드바 include -->
    <jsp:include page="../common/sidebar.jsp"/>

    <main class="main">
        <h2 class="section-title">프로젝트 관리</h2>

        <!-- 필터 탭 -->
        <div class="filter-tabs">
            <div class="filter-tab active" data-status="all">전체보기</div>
            <div class="filter-tab" data-status="open">구인중</div>
            <div class="filter-tab" data-status="done-start">시작전</div>
            <div class="filter-tab" data-status="done-progress">진행중</div>
            <div class="filter-tab" data-status="done-end">종료됨</div>
        </div>

        <!-- 카드 출력 -->
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
                                    <c:when test="${project.projectProgress eq '시작전'}">
                                        <button class="btn btn-edit">수정하기</button>
                                    </c:when>
                                    <c:when test="${project.projectProgress eq '진행중'}">
                                        <button class="btn btn-settle">월별 정산하기</button>
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
    </main>
</div>

<%--<!-- 페이징 -->--%>
<%--<div class="pagination">--%>
<%--    <c:choose>--%>
<%--        <c:when test="${pageInfo.curPage > 1}">--%>
<%--            <a href="${contextPath}/clientRecruitMgt?page=${pageInfo.curPage - 1}&sort=${param.sort}">&lt;</a>--%>
<%--        </c:when>--%>
<%--        <c:otherwise><a>&lt;</a></c:otherwise>--%>
<%--    </c:choose>--%>

<%--    <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">--%>
<%--        <c:choose>--%>
<%--            <c:when test="${page eq pageInfo.curPage}">--%>
<%--                <a href="${contextPath}/clientRecruitMgt?page=${page}&sort=${param.sort}" class="select">${page}</a>--%>
<%--            </c:when>--%>
<%--            <c:otherwise>--%>
<%--                <a href="${contextPath}/clientRecruitMgt?page=${page}&sort=${param.sort}" class="btn">${page}</a>--%>
<%--            </c:otherwise>--%>
<%--        </c:choose>--%>
<%--    </c:forEach>--%>

<%--    <c:choose>--%>
<%--        <c:when test="${pageInfo.curPage < pageInfo.allPage}">--%>
<%--            <a href="${contextPath}/clientRecruitMgt?page=${pageInfo.curPage + 1}&sort=${param.sort}">&gt;</a>--%>
<%--        </c:when>--%>
<%--        <c:otherwise><a>&gt</a></c:otherwise>--%>
<%--    </c:choose>--%>
<%--</div>--%>


<!--  recruitmentList.js 로딩 -->
<script>
    const contextPath = '${pageContext.request.contextPath}';

    // DOMContentLoaded 이벤트에 새로고침 코드 추가
    document.addEventListener('DOMContentLoaded', function() {
        // URL에서 timestamp 파라미터 확인
        const urlParams = new URLSearchParams(window.location.search);
        const timestamp = urlParams.get('timestamp');

        // 현재 시간과 timestamp 비교 (5초 이내면 새로고침하지 않음)
        const now = new Date().getTime();
        if (timestamp && (now - timestamp < 5000)) {
            console.log('페이지 최신화 완료');
        }
    });
</script>
<script src="${contextPath}/js/catalog.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
<script src="${contextPath}/js/recruitmentList.js"></script>
</body>
</html>