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
    <link rel="stylesheet" href="${contextPath}/css/style.css" />
    <link rel="stylesheet" href="${contextPath}/css/headerSt.css" />
    <link rel="stylesheet" href="${contextPath}/css/client_favorites.css" />
</head>
<body>

<jsp:include page="../home/header.jsp" />

<div class="layout">
    <!-- 사이드바 -->
    <aside class="sidebar">
        <div class="profile">
            <img src="https://via.placeholder.com/80" alt="프로필 이미지" />
            <p>${nickname}</p>
            <p><a href="${contextPath}/profileSetting.jsp">마이페이지</a></p>
        </div>
        <h3>프로필 설정</h3>
        <h3>프로젝트</h3>
        <ul>
            <li><a href="${contextPath}/recruit/register.jsp">내 프로젝트 조회</a></li>
            <li><a href="${contextPath}/applicant/manage.jsp">지원자 관리</a></li>
        </ul>
        <h3><a href="${contextPath}/bookmark.jsp">찜한 구인자</a></h3>
        <h3><a href="${contextPath}/review/history.jsp">리뷰 내역</a></h3>
        <h3><a href="${contextPath}/clientQnA" class="active">문의 내역</a></h3>
        <h3><a href="${contextPath}/alarm/setting.jsp">알림 설정</a></h3>
    </aside>

    <main class="main">
        <h2 class="section-title">찜한 프리랜서</h2>

        <!-- 🔽 정렬 드롭다운 -->
        <form method="get" action="${contextPath}/clientFavorites" style="margin-bottom: 20px;">
            <select name="sort" onchange="this.form.submit()">
                <option value="recent" ${param.sort == 'recent' ? 'selected' : ''}>최신순</option>
                <option value="score" ${param.sort == 'score' ? 'selected' : ''}>평점순</option>
                <option value="project" ${param.sort == 'project' ? 'selected' : ''}>프로젝트 개수 순</option>
            </select>
        </form>

        <!-- 🧑‍💻 찜한 프리랜서 카드 (이미지 없음 버전) -->
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

        <!-- 🔢 페이징 -->
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
<script>
    function toggleFavorite(element) {
        const freelancerId = element.dataset.freelancerId;

        fetch('${contextPath}/toggleFavorite', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `freelancerId=${freelancerId}`
        })
            .then(response => response.text())
            .then(result => {
                if (result === 'added') {
                    alert("찜 등록 완료!");
                    element.innerText = '❤️';
                } else if (result === 'removed') {
                    alert("찜 해제 완료!");
                    element.innerText = '🤍';
                } else {
                    alert("처리 실패! 로그인 여부 또는 서버 에러를 확인하세요.");
                }
            })
            .catch(err => {
                console.error("에러 발생:", err);
                alert("요청 중 오류 발생!");
            });
    }
</script>

</body>
</html>
