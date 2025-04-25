<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Link up Header</title>
  <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css">

</head>
<body>
<header class="header-header">
  <div class="header-header-container">
    <div class="header-logo-search">
      <a href="${contextPath}/mainPage" class="header-logo">
        <img src="${contextPath}/img/링크업 로고.png" alt="Link up 로고">
      </a>
    </div>
  </div>

  <div class="header-nav-auth-wrapper">
    <nav class="header-category-nav">
      <ul>
        <c:forEach var="c" items="${categoryList}">
          <li class="header-dropdown">
            <a href="${contextPath}/catalog?category=${c.categoryName}" class="header-dropdown-toggle">
                ${c.categoryName}
            </a>
            <ul class="header-dropdown-menu">
              <c:forEach var="sc" items="${c.subCategories}">
                <li>
                  <a href="${contextPath}/catalog?category=${c.categoryName}&subCategory=${sc.subCategoryName}">
                      ${sc.subCategoryName}
                  </a>
                </li>
              </c:forEach>
            </ul>
          </li>
        </c:forEach>
      </ul>

    </nav>
    <div class="header-auth-buttons">
      <a href="${contextPath}/login" class="header-login">로그인</a>
      <a href="${contextPath}/createAcc" class="header-signup">회원가입</a>
    </div>
  </div>
</header>
<script>
  const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/header.js"></script>
</body>
</html>