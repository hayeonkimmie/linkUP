<%--
  Created by IntelliJ IDEA.
  User: sangk
  Date: 25. 4. 17.
  Time: 오후 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
<aside class="sidebar">
    <div class="profile">
        <%--<img src="image?filename=${user.profileImg}" alt="프로필 이미지"/>--%>
           <%-- <img src="<c:url value='/img/basic_profile_img.png' />" alt="프로필 이미지"/>--%>
        <%--            <img src="<c:url value='/img/${user.profile_img}' />" alt="${user.nickname }님의 프로필 이미지" />
                    <p>${user.nickname }</p>--%>
        <img src="${contextPath}/img/basic_profile_img2.png" alt="프로필 이미지" />
        <p><%= session.getAttribute("userId") %>&nbsp;</p>
        <p>마이페이지</p>
    </div>
    <ul id="side-menu">
        <li class="profile-settings">
            <h3>프로필 설정</h3>
            <a href="${contextPath}/my-page/edit-basic-info">기본 정보 설정</a>
            <a href="${contextPath}/my-page/edit-expert-info">전문가 정보 설정</a>
        </li>
        <li><h3><a href="${contextPath}/my-page/portfolio-list">포트폴리오</a></h3></li>
        <li><h3><a href="${contextPath}/my-page/project-jjim-list">찜한 프로젝트</a></h3></li>
        <li><h3><a href="${contextPath}/my-page/apply-proj-list">지원한 프로젝트 내역</a></h3></li>
        <li><h3><a href="${contextPath}/my-page/project-status">진행중인 / 완료된 프로젝트</a></h3></li>

        <%--<li class="review-lists">
            <h3>프로젝트 후기</h3>
            <a href="${contextPath}/my-page/project-review">리뷰 조회 및 수정</a>
            <a href="${contextPath}/my-page/project-review-write">리뷰 작성</a>
        </li>--%>
        <li><h3><a href="${contextPath}/my-page/qna-list">문의내역</a></h3></li>
    </ul>
</aside>
<script>
    window.addEventListener('DOMContentLoaded', function () {
        const currentPath = window.location.pathname;
        const pathList = [
            '/edit-basic-info',
            '/edit-expert-info',
            '/portfolio-list',
            '/apply-proj-list',
            '/project-jjim-list',
            '/project-status',
/*            '/project-review',
            '/project-review-write',*/
            '/qna-list'
        ];

        const menuItems = document.querySelectorAll('#side-menu li');

        menuItems.forEach((li) => {
            const anchor = li.querySelector('a');
            const href = anchor.getAttribute('href');

            // 경로 목록 중, 현재 주소가 해당 href로 시작하면 active 적용
            if (currentPath.startsWith(href) && pathList.includes(href)) {
                li.classList.add('active');
            } else {
                li.classList.remove('active');
            }
        });
    });
</script>