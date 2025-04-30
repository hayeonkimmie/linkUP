<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 숫자 포맷팅 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>지원자 관리</title>
<%--  <link rel="stylesheet" href="${contextPath}/css/client/style.css" />--%>
  <link rel="stylesheet" href="${contextPath}/css/common/headerLoginSt.css"/>
  <link rel="stylesheet" href="${contextPath}/css/client/sideBar.css" />
  <link rel="stylesheet" href="${contextPath}/css/client/candidateMgt.css" />
</head>
<body>
<!-- 공통 헤더 include -->
<div id="header-placeholder"></div>

<!-- contextPath 숨김 input 추가 (JS에서 사용) -->
<input type="hidden" id="contextPath" value="${contextPath}" />

<div class="layout">

  <!--  공통 사이드바 include -->
  <jsp:include page="../common/sidebar.jsp" />

  <!-- 메인 콘텐츠 -->
  <main class="main">

    <!-- 프로젝트 정보 카드 -->
    <div class="project-card">
      <h2>${project.title}
        <span class="status-badge">${project.status}</span>
      </h2>
      <p>등록일: ${project.regDate}</p>
      <p>${project.projectDescription}</p>
      <p>
        <strong>프로젝트 금액:</strong><fmt:formatNumber value="${project.totalAmount}" type="number" groupingUsed="true" />원 |
        <strong>기간:</strong> ${project.duration}개월 |
        <strong>지원자:</strong><fmt:formatNumber value="${project.applyCount}" type="number" groupingUsed="true" />명 |
        <strong>마감일:</strong> ${project.deadlineDate}
      </p>
      <p>
        <strong>프로젝트 기간:</strong> ${project.startDate} ~ ${project.endDate}
      </p>
      <div class="tags">
        <c:forEach var="tag" items="${fn:split(project.skills, ',')}">
          <span class="tag">${tag}</span>
        </c:forEach>
      </div>
    </div>

    <!-- 지원자 목록 테이블 -->
    <div class="table-wrapper">
      <table>
        <thead>
        <tr>
          <th>지원자</th>
          <th>지원 레벨</th>
          <th>경력(년)</th>
          <th>평점</th>
          <th>상태</th>
          <th>액션</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="applicant" items="${applicants}">
          <tr data-freelancer-id="${applicant.freelancerId}">
              <%-- 경력 (년), 평점 다른 필드로 교체해야할 듯 --%>
            <td><strong>${applicant.name}</strong></td> <%-- 지원자 이름 --%>
            <td>${applicant.applyPosition}</td> <%-- 지원한 레벨 (초급, 중급, 고급) --%>
            <td>${applicant.careerYear}</td> <%-- 경력 (년) --%>
            <td>⭐ ${applicant.star}</td> <%-- 평점 --%>
            <td class="apply-status">
              <c:choose>
                <c:when test="${applicant.applyStatus == 1}">합격</c:when>
                <c:when test="${applicant.applyStatus == 0}">불합격</c:when>
                <c:otherwise><%-- 수락/거절 둘 다 안한 경우 --%>-</c:otherwise>
              </c:choose>
            </td>
            <td class="action-buttons">
                <%-- 상태가 아직 미정일 때만 버튼 보여주기 --%>
              <c:if test="${applicant.applyStatus == null}">
                <button class="action-btn accept-btn">수락</button> <%-- accept-btn 추가 --%>
                <button class="action-btn reject-btn">거절</button> <%-- reject-btn 추가 --%>
              </c:if>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </main>
</div>

<!-- js 파일 분리 -->
<script>
  const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/catalog.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
<script src="${contextPath}/js/candidateMgt.js"></script>
</body>
</html>
