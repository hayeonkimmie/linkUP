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
<%-- 공통 헤더 (선택) --%>
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

        <div class="container">
            <!-- 반복 출력 카드 영역 (예시 1~4개) -->
            <c:forEach var="freelancer" items="${clientFavoritesList}">
                <div class="card">
                    <span class="heart">❤️</span>
                    <div class="info">
                        <img src="${freelancer.profileImage}" alt="${freelancer.name}">
                        <div class="info-text">
                            <div class="name">${freelancer.name}</div>
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
                    <a href="${contextPath}/freelancer/profile?id=${freelancer.id}" class="profile-button">프로필 보기</a>
                </div>
            </c:forEach>
        </div>

        <!-- 문의 내역 테이블 -->
        <table border="1">
            <tr>
                <th>찜아이디</th>
                <th>고객아이디</th>
                <th>프리랜서아이디</th>
            </tr>
            <c:forEach var="jjimlist" items="${jjimList}">
                <tr>
                    <td>${jjimlist.jjimId}</td>
                    <td>${jjimlist.clientId}</td>
                    <td>${jjimlist.freelancerId}</td>
                </tr>
            </c:forEach>
        </table>

        <!-- 페이징 영역 -->
        <div class="pagination">
            <c:choose>
                <c:when test="${pageInfo.curPage > 1}">
                    <a href="inquiry?page=${pageInfo.curPage-1}">&lt;</a>
                </c:when>
                <c:otherwise><a>&lt;</a></c:otherwise>
            </c:choose>

            <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
                <c:choose>
                    <c:when test="${page eq pageInfo.curPage}">
                        <a href="inquiry?page=${page}" class="select">${page}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="inquiry?page=${page}" class="btn">${page}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${pageInfo.curPage < pageInfo.allPage}">
                    <a href="inquiry?page=${pageInfo.curPage+1}">&gt;</a>
                </c:when>
                <c:otherwise><a>&gt;</a></c:otherwise>
            </c:choose>
        </div>
    </main>
</div>

</body>
</html>
