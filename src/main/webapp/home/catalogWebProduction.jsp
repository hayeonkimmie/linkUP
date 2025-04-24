<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>구인 · 구직 플랫폼</title>
    <link href="https://fonts.googleapis.com/css2?family=Pretendard&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${contextPath}/css/home/catalog.css">
</head>
<body>

<div id="header-placeholder"></div>
<div class="container">
    <div id="sidebar-placeholder"></div>
    <main class="main">
        <h1 class="breadcrumb"><a href="catalog.html">웹 제작</a> &gt; <a href="catalog1.html">홈페이지 신규 제작</a></h1>
        <div class="tabs">
            <button class="tab active" data-tab="projects">프로젝트</button>
            <button class="tab" data-tab="experts">전문가</button>
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
                <div class="job-card">
                    <div class="job-image"></div>
                    <h3>Blazor 기반 AGV/AMR 통합 관제 및 운용 프론트엔드 개발</h3>
                    <p>★ 4.3 (154)<br/>4개월 / 월 : 500만원</p>
                    <div class="profile">
                        <div class="avatar"></div>
                        <span>홍길동</span>
                    </div>
                </div>
                <div class="job-card">
                    <div class="job-image"></div>
                    <h3>믿고 맡기는 고퀄리티 디자인 프로젝트 진행해요</h3>
                    <p>★ 4.3 (154)<br/>4개월 / 월 : 500만원</p>
                    <div class="profile">
                        <div class="avatar"></div>
                        <span>김길동</span>
                    </div>
                </div>
                <div class="job-card">
                    <div class="job-image"></div>
                    <h3>믿고 맡기는 고퀄리티 디자인 프로젝트 진행해요</h3>
                    <p>★ 4.3 (154)<br/>4개월 / 월 : 500만원</p>
                    <div class="profile">
                        <div class="avatar"></div>
                        <span>김길동</span>
                    </div>
                </div>
                <div class="job-card">
                    <div class="job-image"></div>
                    <h3>믿고 맡기는 고퀄리티 디자인 프로젝트 진행해요</h3>
                    <p>★ 4.3 (154)<br/>4개월 / 월 : 500만원</p>
                    <div class="profile">
                        <div class="avatar"></div>
                        <span>김길동</span>
                    </div>
                </div>
                <div class="job-card">
                    <div class="job-image"></div>
                    <h3>믿고 맡기는 고퀄리티 디자인 프로젝트 진행해요</h3>
                    <p>★ 4.3 (154)<br/>4개월 / 월 : 500만원</p>
                    <div class="profile">
                        <div class="avatar"></div>
                        <span>김길동</span>
                    </div>
                </div>
                <!-- 더 많은 카드가 여기에 반복될 수 있음 -->
            </div>
        </div>

        <div id="experts" class="tab-content">
            <div class="job-list">
                <div class="job-card">
                    <div class="job-image"></div>
                    <h3>믿고 맡기는 고퀄리티 디자인 제작해드립니다</h3>
                    <p>★ 4.3 (154)<br/>33,000원 ~</p>
                    <div class="profile">
                        <div class="avatar"></div>
                        <span>starbbung</span>
                    </div>
                </div>
                <div class="job-card">
                    <div class="job-image"></div>
                    <h3>믿고 맡기는 고퀄리티 디자인 제작해드립니다</h3>
                    <p>★ 4.3 (154)<br/>33,000원 ~</p>
                    <div class="profile">
                        <div class="avatar"></div>
                        <span>starbbung</span>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>



<script src="../js/catalogWebProduction.js"></script>
<script src="../js/header.js"></script>
</body>
</html>
