<%--
  Created by IntelliJ IDEA.
  User: sangk
  Date: 25. 4. 17.
  Time: 오후 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="sidebar">
    <div class="profile">
        <%--            <img src="<c:url value='/img/${user.profile_img}' />" alt="${user.nickname }님의 프로필 이미지" />
                    <p>${user.nickname }</p>--%>
        <%--<img src="image?filename=${user.profileImg}" alt="프로필 이미지"/>--%>
            <img src="<c:url value='/img/basic_profile_img.png' />" alt="프로필 이미지"/>
        <%--<img src="${pageContext.request.contextPath}/img/basic_profile_img.png" alt="프로필 이미지" />--%>
        <p>닉네임</p>
        <p>마이페이지</p>
    </div>
    <ul>
        <li class="profile-settings">
            <h3>프로필 설정</h3>
            <a href="my-page/edit-info?type=basic">기본 정보 설정</a>
            <a href="my-page/edit-info?type=expert">전문가 정보 설정</a>
        </li>
        <li><a href="my-page/portfolio-list"><h3>포트폴리오</h3></a></li>
        <li><h3><a href="my-page/jjim-projs-list">찜한 프로젝트</a></h3></li>
        <li><h3><a href="my-page/apply-proj-list">지원한 프로젝트 내역</a></h3></li>
        <li><h3><a href="my-page/project-status/">진행중인 / 완료된 프로젝트</a></h3></li>
        <li><h3><a href="my-page/project-review-list">프로젝트 후기</a></h3></li>
        <li><h3><a href="my-page/qna-list">문의내역</a></h3></li>
    </ul>
</aside>