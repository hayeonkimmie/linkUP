<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>새 소식 목록 - LinkUp</title>
  <link rel="stylesheet" href="${contextPath}/css/home/News.css">
</head>
<body>
<div class="container">
  <div class="path"><a href="${contextPath}/gogakCenter" style="text-decoration:none; color:inherit;">고객센터</a> &gt; 새소식</div>
  <h2>➕ 새 소식</h2>
  <ul class="news-list">
    <c:forEach var="news" items="${newsList}">
      <li><a href="${contextPath}/newsPage?id=${news.newsId}"> &nbsp;&nbsp; ${news.title}</a> ${news.createdAt}</li>
    </c:forEach>
  </ul>
  <%--    <li><a href="newsPage.jsp">링크업에서 주최하는 취업박람회 소개</a><span>2025.04.01</span></li>--%>
  <%--    <li><a href="news-detail.html">링크업 홈페이지 리뉴얼</a><span>2025.03.26</span></li>--%>
  <%--    <li><a href="news-detail.html">창업을 위한 링크업 이벤트(3/2~)</a><span>2025.03.04</span></li>--%>
  <%--    <li><a href="news-detail.html">2월 우리는 오늘도 프로젝트</a><span>2025.02.26</span></li>--%>
  <%--    <li><a href="news-detail.html">2월 링크업이 아메리카노 쏜다!</a><span>2025.04.01</span></li>--%>
</div>
</body>
</html>
