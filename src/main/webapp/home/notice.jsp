<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:52
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
  <title>공지 사항 목록 - LinkUp</title>
  <link rel="stylesheet" href="${contextPath}/css/News.css">
</head>
<body>
<div class="container">
  <div class="path"><a href="${contextPath}/gogakCenter" style="text-decoration:none; color:inherit;">고객센터</a> &gt; 공지사항</div>
  <h2>➕ 공지사항</h2>
  <ul class="news-list">
<%--    <li><a href="newsPage.jsp">링크업에서 주최하는 취업박람회 소개</a><span>2025.04.01</span></li>--%>
<%--    <li><a href="news-detail.html">링크업 홈페이지 리뉴얼</a><span>2025.03.26</span></li>--%>
<%--    <li><a href="news-detail.html">창업을 위한 링크업 이벤트(3/2~)</a><span>2025.03.04</span></li>--%>
<%--    <li><a href="news-detail.html">2월 우리는 오늘도 프로젝트</a><span>2025.02.26</span></li>--%>
<%--    <li><a href="news-detail.html">2월 링크업이 아메리카노 쏜다!</a><span>2025.04.01</span></li>--%>
    <c:forEach var="notice" items="${noticeList}">
      <li><a href="${contextPath}/noticePage?id=${notice.noticeId}"> &nbsp;&nbsp;${notice.title}</a> ${notice.createdAt}</li>
    </c:forEach>
  </ul>
</div>
</body>
</html>
