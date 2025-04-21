<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:51
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
  <title>새 소식 상세 - LinkUp</title>
  <link rel="stylesheet" href="${contextPath}/css/NewDT.css">
</head>
<body>
<div class="container">
  <div class="path"><a href="${contextPath}/gogakCenter" style="text-decoration:none; color:inherit;">고객센터</a> &gt; <a href="${contextPath}/news" style="text-decoration:none; color:inherit;">새소식</a></div>
  <h2>➕<c:out value="${news.title}"/></h2>
  <div class="date"><c:out value="${news.createdAt}"/></div>
  <div class="content">
    <c:out value="${news.content}"/>
<%--    안녕하세요. 링크업입니다.--%>

<%--    점점 초록색의 계절이 오고있는데요.--%>
<%--    링크업의 취업박람회를 5월 중순에 개최하려고 합니다.--%>
<%--    아래 내용을 확인해주시고 많은 참여 해주시면 감사하겠습니다.--%>

<%--    ----------------------------------------------------%>
<%--    제목 : JOBFAIR--%>
<%--    날짜 및 시간 : 5월 15일 10AM - 3PM--%>
<%--    장소 : 123ANYWHERE ST. YOUR CITY--%>
<%--    협력회사 : 미정--%>
<%--    주소 : www.linkup.com--%>
  </div>
</div>
</body>
</html>
