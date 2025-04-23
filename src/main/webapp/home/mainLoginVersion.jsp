<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Link Up</title>
    <link rel="stylesheet" href="<c:url value='/css/home/mainLoginVersion.css'/>">
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
                <img src="${contextPath}/img/알람벨.png" alt="알림" class="icon" />
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
        <a href="${contextPath}/catalog?category=웹 제작">
            <img src="${contextPath}/img/webDesign.png" alt="웹 제작" />
            <div class="category-label">웹제작</div>
        </a>
    </div>
    <div class="category-item">
        <a href="${contextPath}/catalog?category=웹 유지보수">
            <img src="${contextPath}/img/maintenance.png" alt="웹 유지보수" />
            <div class="category-label">웹 유지보수</div>
        </a>
    </div>
    <div class="category-item">
        <a href="${contextPath}/catalog?category=프로그램">
            <img src="${contextPath}/img/program.png" alt="프로그램" />
            <div class="category-label">프로그램</div>
        </a>
    </div>
    <div class="category-item">
        <a href="${contextPath}/catalog?category=모바일">
            <img src="${contextPath}/img/mobile.png" alt="모바일" />
            <div class="category-label">모바일</div>
        </a>
    </div>
    <div class="category-item">
        <a href="${contextPath}/catalog?category=Ai">
            <img src="${contextPath}/img/ai.png" alt="Ai" />
            <div class="category-label">Ai</div>
        </a>
    </div>
    <div class="category-item">
        <a href="${contextPath}/catalog?category=데이터">
            <img src="${contextPath}/img/data.png" alt="데이터" />
            <div class="category-label">데이터</div>
        </a>
    </div>
    <div class="category-item">
        <a href="${contextPath}/catalog?category=트렌드">
            <img src="${contextPath}/img/trend.png" alt="트렌드" />
            <div class="category-label">트렌드</div>
        </a>
    </div>
    <div class="category-item">
        <a href="${contextPath}/catalog?category=직무직군">
            <img src="${contextPath}/img/developer.png" alt="직무직군" />
            <div class="category-label">직무직군</div>
        </a>
    </div>
</div>
<br/><br/><br/><br/><br/><br/>

<div class="section-wrapper">
    <div class="section">

        <!-- 개발 섹션 -->
        <div class="section-row">
            <div class="section-title">
                <div class="title-text">웹 제작</div>
            </div>

            <div class="cards">
                <c:forEach var="project" items="${devProjects}">
                    <div class="card">
                        <img src="${contextPath}/img/${project.profileImg}" alt="프로필 이미지" class="profile-img"/>
                        <div class="card-text">
                            <strong>${project.projectName}</strong><br/>
                            ★ <fmt:formatNumber value="${project.avgStar}" pattern="#.##" /><br/>
                                ${project.clientId}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- 디자인 섹션 -->
        <div class="section-row">
            <div class="section-title">
                <div class="title-text">웹 유지보수</div>
            </div>

            <div class="cards">
                <c:forEach var="project" items="${designProjects}">
                    <div class="card">
                        <img src="${contextPath}/img/${project.profileImg}" alt="프로필 이미지" class="profile-img"/>
                        <div class="card-text">
                            <strong>${project.projectName}</strong><br/>
                            ★ <fmt:formatNumber value="${project.avgStar}" pattern="#.##" /><br/>
                                ${project.clientId}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- 기획 섹션 -->
        <div class="section-row">
            <div class="section-title">
                <div class="title-text">프로그램</div>
            </div>

            <div class="cards">
                <c:forEach var="project" items="${planProjects}">
                    <div class="card">
                        <img src="${contextPath}/img/${project.profileImg}" alt="프로필 이미지" class="profile-img"/>
                        <div class="card-text">
                            <strong>${project.projectName}</strong><br/>
                            ★ <fmt:formatNumber value="${project.avgStar}" pattern="#.##" /><br/>
                                ${project.clientId}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<script>
    const contextPath = "${contextPath}";

    function handleSearch() {
        const searchType = document.getElementById('search-type').value;
        const keyword = document.getElementById('search-keyword').value.trim();

        if (!keyword) {
            alert("검색어를 입력하세요!");
            return;
        }

        if (searchType === "company") {
            const url = `${contextPath}/SearchCompany?keyword=${keyword}` + keyword;
            console.log("넘어가는 url: " + url); // ✅ 이거 찍어서 URL 제대로 찍히는지 확인
            location.href = url;
        } else {
            alert("해당 검색 유형은 아직 준비 중입니다!");
        }
    }
</script>

<script src="${contextPath}/js/headerLogin.js"></script>
</body>
</html>
