<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Link up Header</title>
  <link rel="stylesheet" href="${contextPath}/css/headerLoginSt.css" />
</head>
<body>
<header class="header">
  <div class="header-container">
    <div class="logo-search">
      <a href="main.jsp" class="logo">
        <img src="${contextPath}/img/링크업 로고.png" alt="Link up 로고">
      </a>
    </div>
  </div>

  <div class="nav-auth-wrapper">
    <nav class="category-nav">
      <ul>
        <li><a href="#">웹제작</a></li>
        <li><a href="#">웹유지보수</a></li>
        <li><a href="#">프로그램</a></li>
        <li><a href="#">모바일</a></li>
        <li><a href="#">Ai</a></li>
        <li><a href="#">데이터</a></li>
        <li><a href="#">트렌드</a></li>
        <li><a href="#">직무직군</a></li>
      </ul>
    </nav>

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
          <li><a href="#">고객센터</a></li>
          <li><a href="#">로그아웃</a></li>
        </ul>
      </div>
    </div>
  </div>
</header>

<script src="../js/headerLogin.js"></script>
</body>
</html>
