<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>찜한 구인자</title>
    <link rel="stylesheet" href="${contextPath}/css/common/headerLoginSt.css"/>
    <link rel="stylesheet" href="${contextPath}/css/client/client_favorites.css" />
    <link rel="stylesheet" href="${contextPath}/css/client/sideBar.css" />
</head>

<body data-context-path="${contextPath}">
<jsp:include page="../home/headerLogin.jsp" />

<div class="layout">
    <!-- 공통 사이드바 include -->
    <jsp:include page="../common/sidebar.jsp" />

    <!-- 본문 -->
    <main class="main">
        <!-- 제목 + 필터 드롭다운 한 줄 -->
        <div class="section-header">
            <h2 class="section-title">찜한 구인자</h2>
            <form method="get" action="${contextPath}/clientFavorites">
                <select name="sort" class="filter-select" onchange="this.form.submit()">
                    <option value="recent" ${sort == 'recent' ? 'selected' : ''}>최신순</option>
                    <option value="score" ${sort == 'score' ? 'selected' : ''}>평점순</option>
                    <option value="project" ${sort == 'project' ? 'selected' : ''}>프로젝트 개수 순</option>
                </select>
            </form>
        </div>

        <!-- 찜한 프리랜서 카드 목록 -->
        <div class="container">
            <c:forEach var="freelancer" items="${clientFavoritesList}">
                <div class="card">
                    <!-- 이름 + 하트 같은 줄 -->
                    <div class="name-heart">
                        <span class="name">👤 ${freelancer.name}</span>
                        <span class="heart" data-freelancer-id="${freelancer.freelancerId}" onclick="toggleFavorite(this)">❤️</span>
                    </div>

                    <!-- 직무, 지역 -->
                    <div class="info">
                        <div class="info-text no-image">
                            <div class="job">${freelancer.job}</div>
                            <div class="location">📍 ${freelancer.location}</div>
                        </div>
                    </div>

                    <div class="rating-project">
                        <div>⭐ ${freelancer.rating}/5.0</div>
                        <div>📁 프로젝트 ${freelancer.projectCount}개</div>
                    </div>

                    <div class="tags">
                        <c:forEach var="tag" items="${freelancer.tags}">
                            <span class="tag">${tag}</span>
                        </c:forEach>
                    </div>

                    <a href="${contextPath}/freelancer/profile?id=${freelancer.freelancerId}" class="profile-button">프로필 보기</a>
                </div>
            </c:forEach>
        </div>

        <!-- 페이징 -->
        <div class="pagination">
            <c:choose>
                <c:when test="${pageInfo.curPage > 1}">
                    <a href="${contextPath}/clientFavorites?page=${pageInfo.curPage - 1}&sort=${param.sort}">&lt;</a>
                </c:when>
                <c:otherwise><a>&lt;</a></c:otherwise>
            </c:choose>

            <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
                <c:choose>
                    <c:when test="${page eq pageInfo.curPage}">
                        <a href="${contextPath}/clientFavorites?page=${page}&sort=${param.sort}" class="select">${page}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${contextPath}/clientFavorites?page=${page}&sort=${param.sort}" class="btn">${page}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${pageInfo.curPage < pageInfo.allPage}">
                    <a href="${contextPath}/clientFavorites?page=${pageInfo.curPage + 1}&sort=${param.sort}">&gt;</a>
                </c:when>
                <c:otherwise><a>&gt;</a></c:otherwise>
            </c:choose>
        </div>

    </main>
</div>

<script src="${contextPath}/js/toggleFavorite.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
</body>
</html>
