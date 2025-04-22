<%--
  Author: 이원영
  Date: 25. 4. 14.
  Time: 오후 2:10
  Description: 관리자 헤더
  Read Data :
  *  Admin : {String id, String password}
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
  <div class="header-left">
    <a href="<c:url value='/admin/dashboard'/>" class="logo" style="font-size:24px; font-weight:bold;">
      Link Up
    </a>
  </div>
  <div class="header-right">
    <button class="header-button" onclick="location.href='<c:url value='/logout' />'">로그아웃</button>
    <div class="profile">
      <img src="https://via.placeholder.com/28" alt="프로필 이미지">
      <span class="username">admin</span>
    </div>
  </div>
</header>
