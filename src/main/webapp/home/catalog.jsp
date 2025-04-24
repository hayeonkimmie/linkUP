<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 11:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>구인 · 구직 플랫폼</title>
    <link href="https://fonts.googleapis.com/css2?family=Pretendard&display=swap" rel="stylesheet" />
<%--    <link rel="stylesheet" href="../css/catalog.css">--%>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css">
    <link rel="stylesheet" href="${contextPath}/css/home/catalog.css">
</head>
<body>

<div id="header-placeholder"></div>

<div class="container">
    <main class="main">
        <div class="search-bar-container" style="display: flex; justify-content: center; margin-bottom: 1rem;">
            <form action="${contextPath}/catalog" method="get" style="width: 100%; display: flex; justify-content: center;">
                <input type="hidden" name="category" value="${category}">
                <input type="text" name="keyword" class="search-input"
                       placeholder="원하는 프로젝트나 전문가를 검색해보세요"
                       value="${param.keyword}"
                       style="width: 60%; padding: 0.8rem 1rem; font-size: 1rem; border: 1px solid #ccc; border-radius: 8px;">
            </form>
        </div>
        <c:if test="${not empty param.keyword}">
            <h2 class="search-result-title" style="font-size: 1.1rem; color: #888; margin-bottom: 0.2rem;">
                “${param.keyword}” 검색 결과
            </h2>
        </c:if>

        <h1 class="breadcrumb">${category}</h1>

        <div class="tabs">
            <button class="tab active" data-tab="projects">
                프로젝트를 찾으시나요?
                <c:if test="${not empty projectList}">
                    (<c:out value="${fn:length(projectList)}"/>건)
                </c:if>
            </button>
            <button class="tab" data-tab="experts">
                전문가를 찾으시나요?
                <c:if test="${not empty freelancerList}">
                    (<c:out value="${fn:length(freelancerList)}"/>건)
                </c:if>
            </button>
        </div>

        <div id="projects" class="tab-content active">
            <div class="filters">
                <button class="dropdown-toggle">인기순 ▼</button>
                <ul class="dropdown-menu">
                    <li>인기순</li>
                    <li>최신 등록 순</li>
                    <li>작업량 많은 순</li>
                </ul>
            </div>

            <div class="job-list">
                <c:forEach var="p" items="${projectList}">
                    <div class="job-card">
                        <div class="job-image"></div> <!-- 썸네일 이미지 있으면 여기에 추가 가능 -->
                        <h3>${p.advertisementTitle}</h3>
                        <p>${p.duration}일 / ★ <fmt:formatNumber value="${p.avgStar}" type="number" maxFractionDigits="1" /> </p>
                        <div class="profile">
                            <div class="avatar"></div> <!-- 프로필 이미지도 있으면 바꿔줄 수 있음 -->
                            <span>${p.manager}</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div id="experts" class="tab-content">
            <div class="filters">
                <button class="dropdown-toggle">인기순 ▼</button>
                <ul class="dropdown-menu">
                    <li>인기순</li>
                    <li>최신 등록 순</li>
                    <li>작업량 많은 순</li>
                </ul>
            </div>

            <div class="job-list">
                <c:forEach var="f" items="${freelancerList}">
                    <div class="job-card">
                        <div class="job-image"></div> <!-- 썸네일이 있다면 여기에 f.profileImg 써도 됨 -->
                        <h3>${f.introduction}</h3>
                        <p>희망 급여: ${f.desiredSalary}원/월</p>
                        <p>★ <fmt:formatNumber value="${f.avgStar}" type="number" maxFractionDigits="1"/> / 5.0</p>
                        <div class="profile">
                            <div class="avatar"></div>
                            <span>${f.nickname}</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </main>
</div>
<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<!-- 기본 스크립트 -->
<script src="${contextPath}/js/catalog.js"></script>
<script src="${contextPath}/js/header.js"></script>

</body>
</html>

