<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Link up Header</title>
  <link rel="stylesheet" href="../css/headerSt.css">

</head>
<body>
<header class="header">
  <div class="header-container">
    <div class="logo-search">
      <a href="main.jsp" class="logo">
        <img src="../img/링크업 로고.png" alt="Link up 로고">
      </a>
      <input type="text" class="search-bar" placeholder="어떤 전문가를 찾고 계신가요" />
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
    <div class="auth-buttons">
      <a href="login.jsp" class="login">로그인</a>
      <a href="createAcc.jsp" class="signup">회원가입</a>
    </div>
  </div>
</header>
</div>

<script src="../js/header.js"></script>
</body>
</html>

