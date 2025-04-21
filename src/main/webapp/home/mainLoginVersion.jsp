<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Link Up</title>

    <link rel="stylesheet" href="${contextPath}/css/mainLoginVersion.css">
    <link href="https://fonts.googleapis.com/css2?family=SUIT&display=swap" rel="stylesheet">
</head>
<body>

<header class="header">
    <div class="logo-user-container">
        <div class="logo">
            <img src="${contextPath}/img/링크업 로고.png" alt="Link Up 로고" style="height: 40px;" />
        </div>
        <div class="user-section">
            <a href="makeProject.jsp" class="post-job-btn">구인등록</a>
            <button class="notification-btn">
                <img src="../img/알람벨.png" alt="알림" class="icon" />
            </button>
            <div class="profile-wrapper">
                <div class="profile-icon"></div>
                <button class="profile-toggle">&#9662;</button>
                <ul class="profile-menu">
                    <li><a href="#">마이페이지</a></li>
                    <li><a href="${contextPath}/gogakCenter">고객센터</a></li>
                    <li>
                        <form action="${contextPath}/logout" method="get" style="margin: 0;">
                            <button type="submit" class="profile-menu-link">로그아웃</button>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<br/>

<div class="main-title">사람과 사람을 연결해주는 사이트 <strong>LINK UP!</strong></div>

<br/>

<div class="search-box">
    <select id="search-type" class="search-type">
        <option value="company">회사명</option>
        <option value="project">구인</option>
        <option value="expert">구직</option>
    </select>
    <input class="search-input" type="text" id="search-keyword" placeholder="검색어를 입력하세요" />
    <button class="search-button" onclick="handleSearch()">검색</button>
</div>

<br/><br/><br/>

<div class="categories">
    <div class="category-item">
        <a href="${contextPath}/home/catalog.jsp">
            <img src="${contextPath}/img/웹제작.png" alt="웹제작" />
            <div class="category-label">웹제작</div>
        </a>
    </div>
    <div class="category-item">
        <img src="${contextPath}/img/유지보수.png" alt="유지보수" />
        <div class="category-label">유지보수</div>
    </div>
    <div class="category-item">
        <img src="${contextPath}/img/design-tools_11933683.png" alt="프로그램" />
        <div class="category-label">프로그램</div>
    </div>
    <div class="category-item">
        <img src="${contextPath}/img/design-tools_11933683.png" alt="모바일" />
        <div class="category-label">모바일</div>
    </div>
    <div class="category-item">
        <img src="${contextPath}/img/design-tools_11933683.png" alt="Ai" />
        <div class="category-label">Ai</div>
    </div>
    <div class="category-item">
        <img src="${contextPath}/img/design-tools_11933683.png" alt="데이터" />
        <div class="category-label">데이터</div>
    </div>
    <div class="category-item">
        <img src="${contextPath}/img/design-tools_11933683.png" alt="트렌드" />
        <div class="category-label">트렌드</div>
    </div>
    <div class="category-item">
        <img src="${contextPath}/img/design-tools_11933683.png" alt="직무직군" />
        <div class="category-label">직무직군</div>
    </div>
</div>

<br/><br/><br/><br/><br/><br/>

<div class="section">
    <div class="section-row">
        <div class="section-title">
            <a href="catalog2.html">
                <div class="title-text">웹 제작</div>
                <button class="arrow-button">→</button>
            </a>
        </div>
        <div class="cards">
            <div class="card"><div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div></div>
            <div class="card"><div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div></div>
            <div class="card"><div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div></div>
        </div>
    </div>

    <!-- 추가 섹션들 동일 구조 반복 -->
</div>
<script>
    function handleSearch() {
        const searchType = document.getElementById('search-type').value;
        const keyword = document.getElementById('search-keyword').value.trim();

        if (!keyword) {
            alert("검색어를 입력하세요!");
            return;
        }

        if (searchType === "company") {
            window.location.href = `${contextPath}/home/companySearch.jsp?keyword=${keyword}`;
        } else {
            alert("구현되지 않은 검색 타입입니다!");
        }
    }
</script>

<script src="${contextPath}/js/headerLogin.js"></script>
</body>
</html>
