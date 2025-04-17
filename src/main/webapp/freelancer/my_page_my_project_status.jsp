<%--
  Created by IntelliJ IDEA.
  User: sangk
  Date: 25. 4. 9.
  Time: 오전 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_get_my_proj.css'/>">
    <script src="/../js/freelancer_my_get_my_proj.js"></script>
</head>
<body>
<!-- header include -->
<!--#include virtual="/header.html" -->
<div class="sidebar">
    <div class="profile">
        <img src="https://via.placeholder.com/100" alt="프로필 이미지">
        <div class="profile-name">닉네임</div>
        <div class="my-page">마이페이지</div>
    </div>
    <ul>
        <li>프로필 설정</li>
        <li>포트폴리오</li>
        <li>찜한 프로젝트</li>
        <li>지원한 프로젝트 내역</li>
        <li class="active">진행중인 / 완료된 프로젝트</li>
        <li>프로젝트 후기</li>
        <li>문의내역</li>
    </ul>
</div>

<div class="main-content">
    <div>
        <h2>내 프로젝트 현황</h2>
        <p>지원한 프로젝트 목록을 확인하세요</p>
    </div>

    <div class="tabs">
        <div class="tab active" onclick="switchTab('ongoing')">진행중인 프로젝트 <span>(2)</span></div>
        <div class="tab" onclick="switchTab('completed')">완료된 프로젝트 <span>(2)</span></div>
    </div>

    <!--진행중인 프로젝트-->
    <div id="ongoing" class="tab-content">
        <table>
            <thead>
            <tr>
                <th>프로젝트 정보</th>
                <th>분야</th>
                <th>기간</th>
                <th>예산</th>
                <th>요구사항</th>
                <th>마감일</th>
            </tr>
            </thead>
            <tbody>
            <tr class="clickable" onclick="toggleDetails(this)">
                <td>반응형 웹사이트 리디자인</td>
                <td>UI/UX 디자인</td>
                <td>2개월</td>
                <td>800만원</td>
                <td>React, TypeScript, 3년 이상</td>
                <td>2024년 12월 31일</td>
            </tr>
            <tr class="accordion-row">
                <td colspan="6">프로젝트 소개<br>신규 모바일 앱을 위한 RESTful API 개발 프로젝트입니다.</td>
            </tr>
            <tr class="clickable" onclick="toggleDetails(this)">
                <td>반응형 웹사이트 리디자인</td>
                <td>UI/UX 디자인</td>
                <td>2개월</td>
                <td>800만원</td>
                <td>React, TypeScript, 3년 이상</td>
                <td>2024년 12월 31일</td>
            </tr>
            <tr class="accordion-row">
                <td colspan="6">프로젝트 소개<br>신규 모바일 앱을 위한 RESTful API 개발 프로젝트입니다.</td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--완료된 프로젝트-->
    <div id="completed" class="tab-content" style="display: none;">
        <table>
            <thead>
            <tr>
                <th>프로젝트 정보</th>
                <th>분야</th>
                <th>기간</th>
                <th>예산</th>
                <th>요구사항</th>
                <th>마감일</th>
            </tr>
            </thead>
            <tbody>
            <tr class="clickable" onclick="toggleDetails(this)">
                <td>이커머스 플랫폼 구축</td>
                <td>풀스택 개발</td>
                <td>6개월</td>
                <td>5,000만원</td>
                <td>React, TypeScript, 3년 이상</td>
                <td>2024년 12월 31일</td>
            </tr>
            <tr class="accordion-row">
                <td colspan="6">프로젝트 소개<br> 온라인 쇼핑몰 시스템 전반 구축 프로젝트입니다.</td>
            </tr>
            <tr class="clickable" onclick="toggleDetails(this)">
                <td>반응형 웹사이트 리디자인</td>
                <td>UI/UX 디자인</td>
                <td>2개월</td>
                <td>800만원</td>
                <td>React, TypeScript, 3년 이상</td>
                <td>2024년 12월 31일</td>
            </tr>
            <tr class="accordion-row">
                <td colspan="6">프로젝트 소개<br>기존 기업 홈페이지 반응형 리디자인 프로젝트입니다.</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
