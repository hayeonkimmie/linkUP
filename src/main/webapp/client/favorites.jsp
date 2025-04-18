<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>찜한 프리랜서</title>
    <link rel="stylesheet" href="${contextPath}/css/style.css" />
    <link rel="stylesheet" href="${contextPath}/css/headerSt.css" />
    <style>
        /* 기존 style 유지 */
        * { box-sizing: border-box; font-family: sans-serif; }
        body { background-color: #f9fafb; margin: 0; padding: 20px; }
        .container {
            flex: 1;
            margin-left: 40px;
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }
        .layout { display: flex; align-items: flex-start; margin-top: 20px; }
        .sidebar { width: 300px; }
        .card {
            background: white; border-radius: 12px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
            padding: 10px; width: 420px; position: relative;
        }
        .card img {
            width: 100px; height: 100px; border-radius: 50%;
            object-fit: cover;
        }
        .heart { position: absolute; top: 20px; right: 20px; color: red; font-size: 30px; }
        .info { display: flex; align-items: center; gap: 10px; }
        .info-text { display: flex; flex-direction: column; }
        .name { font-weight: bold; font-size: 18px; }
        .job, .location { font-size: 14px; color: #666; }
        .rating-project { display: flex; justify-content: space-between; margin: 30px 0; font-size: 14px; }
        .tags { display: flex; flex-wrap: wrap; gap: 5px; margin-bottom: 20px; }
        .tag {
            background-color: #eef2ff; color: #3730a3;
            padding: 5px 10px; border-radius: 8px; font-size: 12px;
        }
        .profile-button {
            display: block; width: 100%; background-color: #2563eb;
            color: white; padding: 15px; text-align: center;
            border-radius: 10px; text-decoration: none; font-weight: bold;
        }
    </style>
</head>
<body>

<header class="header">
    <div class="header-container">
        <div class="logo-search">
            <a href="" class="logo">
                <img src="/Users/hayeon/Documents/devs/workspace/mini-project/링크업 로고.png" alt="Link up 로고">
            </a>
            <input type="text" class="search-bar" placeholder="어떤 전문가를 찾고 계신가요" />
        </div>
        <div class="auth-buttons">
            <a href="#" class="login">로그인</a>
            <a href="#" class="signup">회원가입</a>
        </div>
    </div>

    <nav class="category-nav">
        <ul>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle">■ 전체보기</a>
                <ul class="dropdown-menu">
                    <li><a href="#">비즈니스</a></li>
                    <li><a href="#">디자인</a></li>
                    <li><a href="#">IT·프로그래밍</a></li>
                    <li><a href="#">영상·사진·음향</a></li>
                    <li><a href="#">마케팅</a></li>
                    <li><a href="#">번역·통역</a></li>
                    <li><a href="#">문서·글쓰기</a></li>
                    <li><a href="#">창업·사업</a></li>
                    <li><a href="#">세무·법무·노무</a></li>
                    <li><a href="#">취미 레슨</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</header>

<div class="layout">
    <aside class="sidebar">
        <div class="profile">
            <img src="https://via.placeholder.com/80" alt=" " />
            <label class="camera-icon" for="profile-upload"></label>
            <input type="file" id="profile-upload" accept="image/*" />
            <p>닉네임</p>
            <p>마이페이지</p>
        </div>
        <h3>프로필 설정</h3><br>
        <h3>프로젝트</h3>
        <ul>
            <li><a href="#">구인 등록</a></li>
            <li><a href="#">프로젝트 조회</a></li>
            <li><a href="#">거래내역</a></li>
            <li><a href="#">지원자 관리</a></li>
        </ul>
        <h3>찜한 구인자</h3>
        <h3>리뷰 내역</h3>
        <h3>문의 내역</h3>
        <h3>알림 설정</h3>
    </aside>

    <main class="main">
        <h2 class="section-title">찜한 프리랜서</h2>

<%--        <div class="container">--%>
<%--            <!-- 반복 출력 카드 영역 (예시 1~4개) -->--%>
<%--            <c:forEach var="freelancer" items="${clientFavoritesList}">--%>
<%--                <div class="card">--%>
<%--                    <span class="heart">❤️</span>--%>
<%--                    <div class="info">--%>
<%--                        <img src="${freelancer.profileImage}" alt="${freelancer.name}">--%>
<%--                        <div class="info-text">--%>
<%--                            <div class="name">${freelancer.name}</div>--%>
<%--                            <div class="job">${freelancer.job}</div>--%>
<%--                            <div class="location">📍 ${freelancer.location}</div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="rating-project">--%>
<%--                        <div>⭐ ${freelancer.rating}/5.0</div>--%>
<%--                        <div>📁 프로젝트 ${freelancer.projectCount}개</div>--%>
<%--                    </div>--%>
<%--                    <div class="tags">--%>
<%--                        <c:forEach var="tag" items="${freelancer.tags}">--%>
<%--                            <span class="tag">${tag}</span>--%>
<%--                        </c:forEach>--%>
<%--                    </div>--%>
<%--                    <a href="${contextPath}/freelancer/profile?id=${freelancer.id}" class="profile-button">프로필 보기</a>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>

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
