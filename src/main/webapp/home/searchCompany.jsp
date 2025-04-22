<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회사 검색 결과</title>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css" />
</head>
<body>

<div class="container">
  <p>${keyword} 검색</p>
  <div class="tab-container">
    <div class="tab active">회사</div>
  </div>
  <div id="company" class="content active">
    <c:choose>
      <c:when test="${not empty companies}">
        <c:forEach var="company" items="${companies}">
          <a href="#">
            <div class="company-card">
              <div class="info">
                <h2>● ${company.companyName} <span class="rating">⭐⭐⭐⭐⭐ 4.0 (125)</span></h2>
                <p>업종 : ${company.businessType}</p>
                <p>회사소개 : ${company.companyDescription}</p>
                <p>대표자명 : ${company.ceo} | 설립일자 : ${company.foundedDate}</p>
              </div>
              <img src="../img/회사사진.png" alt="logo">
            </div>
          </a>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <p>검색 결과가 없습니다.</p>
      </c:otherwise>
    </c:choose>
  </div>
</div>

</body>
</html>
