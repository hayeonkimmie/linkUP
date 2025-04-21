<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:13
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
  <link rel="stylesheet" href="${contextPath}/css/createAccChoose.css">
</head>
<body>
<div class="container">
  <a href="${contextPath}/home/main.jsp" class="logo">
    <img src="../img/링크업 로고.png" alt="Link up 로고">
  </a>
  <p>링크업에서 어떤 서비스를<br>이용하고 싶으신가요?</p>
  <div class="subtitle">원하는 회원가입 유형을 선택하세요.</div>

  <div class="section-title">의뢰를 맡기고 싶다면?</div>
  <a href="${contextPath}/createAccRecruiter">
    <button class="button">
      <span class="checkmark">✔</span> 사업자로 가입하기
    </button>
  </a>

  <div class="section-title">내 능력을 펼치고 싶다면?</div>
  <a href="${contextPath}/createAccJobseeker">
    <button class="button">
      <span class="checkmark">✔</span> 일반으로 가입하기
    </button>
  </a>
</div>
</body>
</html>
