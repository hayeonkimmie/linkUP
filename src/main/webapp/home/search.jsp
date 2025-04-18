<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>검색결과 페이지</title>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${contextPath}/css/search.css" />
</head>
<body>

<div id="header-placeholder"></div>

<br>
<br>


<div class="container">
  <p>테크놀로지 주식회사 검색</p>
  <div class="tab-container">
    <div class="tab active" onclick="showTab('company')">회사</div>
  </div>
  <div id="company" class="content active">
    <a href="companySearch.jsp">
      <div class="company-card">
        <div class="info">
          <h2>● 테크놀로지 주식회사 <span class="rating">⭐⭐⭐⭐⭐ 4.0 (125)</span></h2>
          <p>업종 : 소프트웨어 개발</p>
          <p>업태 : 서비스 업</p>
          <p>대표자명 : 김민수 | 설립일자 2020년 1월 1일</p>
        </div>
        <img src="../img/회사사진.png" alt="logo">
      </div>
    </a>
  </div>
</div>

<script src="../js/header.js"></script>
</body>
</html>
