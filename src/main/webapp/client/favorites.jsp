<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>찜한 프리랜서</title>
    <link rel="stylesheet" href="${contextPath}/css/client/style.css" />
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css" />
    <link rel="stylesheet" href="${contextPath}/css/client/client_favorites.css" />
</head>
<%--// JS에서 해당 값을 읽어와서 AJAX 경로로 사용 가능 --%>
<body data-context-path="${contextPath}">
<jsp:include page="../home/headerLogin.jsp" />


<div class="layout">
    <!-- 공통 사이드바 include -->
    <jsp:include page="../common/sidebar.jsp" />

    <!-- 본문 -->
    <main class="main">
        <h2 class="section-title">찜한 프리랜서</h2>

        <!--  정렬 드롭다운 -->
        <form method="get" action="${contextPath}/clientFavorites" style="margin-bottom: 20px;">
            <select name="sort" onchange="this.form.submit()">
                <option value="recent" ${sort == 'recent' ? 'selected' : ''}>최신순</option>
                <option value="score" ${sort == 'score' ? 'selected' : ''}>평점순</option>
                <option value="project" ${sort == 'project' ? 'selected' : ''}>프로젝트 개수 순</option>

            </select>
        </form>

        </form>

        <!-- ‍ 찜한 프리랜서 카드 (이미지 없음 버전) -->
        <div class="container">
            <c:forEach var="freelancer" items="${clientFavoritesList}">
                <div class="card">
                    <span class="heart" data-freelancer-id="${freelancer.freelancerId}" onclick="toggleFavorite(this)">❤️</span>

                    <div class="info">
                        <!-- 이미지 추가해야 함 / webapp 에 프로필 사진 넣기 -->
                        <!-- 텍스트 프로필 -->
                        <div class="info-text no-image">
                            <div class="name">👤 ${freelancer.name}</div>
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


<!-- 외부 JS 파일 불러오기 -->
<script src="${contextPath}/js/toggleFavorite.js"></script>
</body>
</html>