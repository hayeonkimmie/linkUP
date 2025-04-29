<!-- /views/includes/sidebar.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<aside class="sidebar">
  <div class="profile">
    <img src="https://via.placeholder.com/80" alt="프로필 이미지" />
    <p>닉네임</p>
    <p>마이페이지</p>
  </div>
  <h3><a href="${contextPath}/clientProfile">프로필 설정</a></h3>
  <h3><a href="${contextPath}/clientRecruitMgt">내 프로젝트</a></h3>
  <h3><a href="${contextPath}/clientFavorites">찜한 구인자</a></h3>
  <h3><a href="${contextPath}/clientQnA">문의 내역</a></h3>
</aside>