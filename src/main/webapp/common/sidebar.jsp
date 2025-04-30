<!-- /views/includes/sidebar.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="sidebar">
  <div class="sidebar-title">마이페이지</div>
  <div class="sidebar-divider"></div>

  <ul class="sidebar-menu">
    <li><a href="${contextPath}/clientProfile">프로필 설정</a></li>
    <li><a href="${contextPath}/clientRecruitMgt">내 프로젝트</a></li>
    <li><a href="${contextPath}/clientFavorites">찜한 구인자</a></li>
    <li><a href="${contextPath}/clientQnA">문의 내역</a></li>
  </ul>
</div>
