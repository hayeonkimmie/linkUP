<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>문의하기 - LinkUp</title>
  <link rel="stylesheet" href="${contextPath}/css/Q&A.css">
</head>
<body>
<div class="container">
  <div class="path"><a href="${contextPath}/gogakCenter" style="text-decoration:none; color:inherit;">고객센터</a> &gt; 문의</div>
  <h2>❓ 문의하기</h2>
  <form action="${contextPath}/QnA" method="post">
    <input type="text" name="questionTitle" placeholder="제목을 입력해주세요" required />
    <textarea name="questionContent" rows="10" placeholder="문의 내용을 입력해주세요" required></textarea>
    <button type="submit" class="submit-btn">등록하기</button>
  </form>
</div>
</body>
</html>

