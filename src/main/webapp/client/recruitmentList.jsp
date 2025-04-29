<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<!-- 여기 contextPath 변수만 JSP로 셋팅, js에서는 선언 안 함 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>구인 관리</title>
    <link rel="stylesheet" href="${contextPath}/css/client/style.css" />
    <link rel="stylesheet" href="${contextPath}/css/client/headerSt.css" />
</head>

<body>
<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}" />
<jsp:include page="../home/header.jsp" />

<div class="layout">
    <jsp:include page="../common/sidebar.jsp" />

    <main class="main">
        <h2 class="section-title">구인 관리</h2>

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
            <c:set var="statusClass" value="none" />
            <c:choose>
                <c:when test="${project.status eq '구인중'}">
                    <c:set var="statusClass" value="open" />
                </c:when>
                <c:when test="${project.status eq '구인완료'}">
                    <c:choose>
                        <c:when test="${project.projectProgress eq '시작전'}">
                            <c:set var="statusClass" value="done-start" />
                        </c:when>
                        <c:when test="${project.projectProgress eq '진행중'}">
                            <c:set var="statusClass" value="done-progress" />
                        </c:when>
                        <c:when test="${project.projectProgress eq '종료됨'}">
                            <c:set var="statusClass" value="done-end" />
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>

            <c:if test="${statusClass ne 'none'}">
                <div class="job-card" data-status="${statusClass}" data-project-id="${project.projectId}">
                    <div class="job-card-badge">
                        <span class="badge">${project.status} (${project.projectProgress})</span>
                    </div>

                    <div class="job-card-header">
                        <div class="job-title">${project.title}</div>
                        <div class="tags">
                            <c:forEach var="skill" items="${fn:split(project.skills, ',')}">
                                <span>${skill}</span>
                            </c:forEach>
                        </div><br />

                        <div class="job-meta">${project.category} / 지원자 ${project.applyCount}명 / ${project.regDate} 등록</div><br />
                        <strong>프로젝트 기간</strong><br />
                            ${project.startDate} ~ ${project.endDate}

                        <div class="job-card-status">
                            <div class="total-price">총 프로젝트 금액 ${project.totalAmount}원</div>
                        </div>

                        <div class="highlight">구인 인원 및 페이</div>
                        <div class="pay-section">
                            <c:forEach var="pay" items="${project.payList}">
                                <div class="pay-box">
                                    <h4>${pay.level}</h4>
                                    <p>${pay.fee}원/인</p>
                                    <p>${pay.count}명</p>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="buttons">
                            <c:choose>
                                <c:when test="${project.status eq '구인중'}">
                                    <button class="btn btn-secondary">모집확정하기</button>
                                    <button class="btn btn-edit">수정하기</button>
                                    <button class="btn btn-delete">삭제하기</button>
<%--                                    지원자 관리 클릭하면, 해당 projectId값 전달하면서 새 페이지로 이동 (candidateMgt.jsp)--%>
                                    <a href="${contextPath}/candidateMgt?projectId=${project.projectId}" class="btn btn-manage">지원자 관리</a>
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
                </div>
            </c:if>
        </c:forEach>
    </main>
</div>

<!-- js 이벤트 바인딩 -->
<script src="${contextPath}/js/recruitmentList.js"></script>
<script>
    window.contextPath = ${contextPath}
</script>
</body>
</html>
