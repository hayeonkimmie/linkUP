<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>Link up 회원가입</title>
    <link rel="stylesheet" href="${contextPath}/css/home/createAcc.css">
</head>

<body>
<div class="signup-container">
    <a href="${contextPath}/home/main.jsp" class="logo">
        <img src="../img/링크업 로고.png" alt="Link up 로고">
    </a>
    <p class="description">10초의 간단한 가입으로,<br>Link up의 여러 기능을 사용해보자!</p>

    <button class="btn kakao">카카오톡 간편 가입 하기</button>
    <button class="btn google">구글 간편 가입 하기</button>

    <hr class="divider">
    <!-- divider은 얇은 구분선임 -->
    <a href="${contextPath}/createAccEmail">
        <button class="btn email">
            이메일로 가입하기
        </button></a>

    <a href="login.html" class="login-link">로그인 하러가기</a>
</div>
</body>
</html>