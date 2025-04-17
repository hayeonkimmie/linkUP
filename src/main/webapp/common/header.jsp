<!-- /views/includes/header.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header">
  <div class="header-container">
    <div class="logo-search">
      <a href="/" class="logo">
        <img src="/images/logo.png" alt="Link up 로고">
      </a>
      <input type="text" class="search-bar" placeholder="어떤 전문가를 찾고 계신가요" />
    </div>
    <div class="auth-buttons">
      <a href="/login.jsp" class="login">로그인</a>
      <a href="/signup.jsp" class="signup">회원가입</a>
    </div>
  </div>

  <nav class="category-nav">
    <ul>
      <li><a href="#">디자인</a></li>
      <li><a href="#">IT·프로그래밍</a></li>
      <li><a href="#">마케팅</a></li>
      <!-- ... -->
    </ul>
  </nav>
</header>
