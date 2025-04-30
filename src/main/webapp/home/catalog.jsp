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
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css">
    <link rel="stylesheet" href="${contextPath}/css/home/catalog.css">
    <style>
        .subcategory-filters button {
            margin-right: 0.5rem;
            padding: 0.5rem 1rem;
            border-radius: 20px;
            border: 1px solid #ddd;
            background: #fff;
            cursor: pointer;
        }
        .subcategory-filters button.active {
            background: #3f51b5;
            color: white;
            border-color: #3f51b5;
        }
    </style>
</head>
<body>

<div id="header-placeholder"></div>
<div class="container">
    <main class="main">
        <!-- 검색창 -->
        <div class="search-bar-container" style="display: flex; justify-content: center; margin-bottom: 1rem;">
            <form action="${contextPath}/catalog" method="get" style="width: 100%; display: flex; justify-content: center;">
                <input type="hidden" name="category" value="${category}">
                <input type="hidden" name="subCategory" value="${subCategory}">
                <input type="text" name="keyword" class="search-input"
                       placeholder="원하는 프로젝트나 전문가를 검색해보세요"
                       value="${param.keyword}"
                       style="width: 60%; padding: 0.8rem 1rem; font-size: 1rem; border: 1px solid #ccc; border-radius: 8px;">
            </form>
        </div>

        <!-- 키워드 검색 결과 문구 -->
        <c:if test="${not empty param.keyword}">
            <h2 class="search-result-title" style="font-size: 1.1rem; color: #888; margin-bottom: 0.2rem;">
                “${param.keyword}” 검색 결과
            </h2>
        </c:if>
        <!-- 카테고리 타이틀 -->
        <c:choose>
            <c:when test="${not empty category && category ne '전체'}">
                <h1 class="breadcrumb">
                    <a href="${contextPath}/catalog?category=${category}">
                            ${category}
                    </a>
                    <c:if test="${not empty subCategory}">
                        &nbsp;&nbsp;&gt;&nbsp;&nbsp;${subCategory}
                    </c:if>
                </h1>
            </c:when>
            <c:otherwise>
                <h1 class="breadcrumb">
                    “${keyword}” 검색 결과
                </h1>
            </c:otherwise>
        </c:choose>


        <!-- 🔹 서브카테고리 필터 버튼 -->
        <div class="subcategory-filters" style="margin-bottom: 1rem;">
            <c:forEach var="sub" items="${subCategoryList}">
                <form method="get" action="${contextPath}/catalog" style="display: inline;">
                    <input type="hidden" name="category" value="${category}" />
                    <input type="hidden" name="subCategory" value="${sub.subCategoryName}" /> <!-- ✅ name 수정 -->
                    <button type="submit"
                            class="${sub.subCategoryName == param.subCategory ? 'active' : ''}">
                            ${sub.subCategoryName}
                    </button>
                </form>
            </c:forEach>
        </div>

        <!-- 탭 영역 -->
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

        <!-- 🔹 프로젝트 리스트 -->
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
                        <div class="job-image"></div>
                        <h3>${p.advertisementTitle}</h3>
                        <p>${p.duration}일</p>
                        <div class="profile">
                            <div class="avatar"></div>
                            <span>${p.manager}</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- 🔹 프리랜서 리스트 -->
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
                        <div class="job-image"></div>
                        <h3>${f.introduction}</h3>
                        <p>희망 급여: ${f.desiredSalary}원/월</p>
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
<script src="${contextPath}/js/catalog.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>

</body>
</html>
