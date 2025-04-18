<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:53
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
    <title>공지사항 상세 - LinkUp</title>
    <link rel="stylesheet" href="${contextPath}/css/noticeListDT.css">
</head>
<body>
<div class="container">
    <div class="path"><a href="${contextPath}/gogakCenter" style="text-decoration:none; color:inherit;">고객센터</a> &gt; <a href="${contextPath}/notice" style="text-decoration:none; color:inherit;">공지사항</a></div>
    <h2>📄 <c:out value="${notice.title}"/></h2>
    <div class="date"> <c:out value="${notice.createdAt}"/></div>
    <div class="content">
        <c:out value="${notice.content}"/>
    </div>
</div>
</body>
</html>
