<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Link up Header</title>
  <link rel="stylesheet" href="${contextPath}/css/common/headerLoginSt.css" />
</head>
<body>
<header class="header">
  <div class="header-container">
    <div class="logo-search">
      <a href=${contextPath}/mainPage class="logo">
        <img src="${contextPath}/img/링크업 로고.png" alt="Link up 로고">
      </a>
    </div>
  </div>

  <div class="nav-auth-wrapper">
    <nav class="category-nav">
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

    <div class="user-section">
      <c:if test="${sessionScope.role ne 'jobseeker'}">
        <a href="${contextPath}/makeProject" class="post-job-btn">구인등록</a>
      </c:if>
      <div class="profile-wrapper">
        <p class="username"> <%= session.getAttribute("userId") %>&nbsp;&nbsp;</p>
        <div class="profile-icon"></div>
        <button class="profile-toggle">&#9662;</button>
        <ul class="profile-menu">
          <li><a href="${contextPath}/myPage">마이페이지</a></li>
          <li><a href="${contextPath}/gogakCenter">고객센터</a></li>
          <form action="${contextPath}/logout" method="get" style="margin: 0;">
            <button type="submit" class="profile-menu-link">로그아웃</button>
          </form>
        </ul>
      </div>
    </div>
  </div>
</header>
<script>
  const contextPath = '${pageContext.request.contextPath}';

</script>
<script src="${contextPath}/js/headerLogin.js"></script>
</body>
</html>
