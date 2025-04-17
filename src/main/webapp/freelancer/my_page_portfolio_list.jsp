<%--
  Created by IntelliJ IDEA.
  User: sangk
  Date: 25. 4. 14.
  Time: 오후 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Link up Profile</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_main_portfolio.css'/>">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>

<body>
<div class="header" style="height: 100px;">
    <!-- 헤더 인클루드 영역 -->
    <!--#include virtual="./../header.html" -->
</div>
<div class="container">
    <aside class="sidebar">
        <div class="profile">
            <%--            <img src="<c:url value='/img/${user.profile_img}' />" alt="프로필 이미지" />
                        <p>${user.nickname }</p>--%>
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
    <!-- 메인 콘텐츠 -->
    <main class="content">
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <span>
                        <h3>포트폴리오 목록</h3>
                        <%--<p>${nickname}님의 최고의 프로젝트를 선보이세요.</p>--%>
                        <p>닉네임님의 최고의 프로젝트를 선보이세요.</p>
                    </span>
                    <button class="add-portfolio" onclick="location.href='my-page/portfolio-write'">+ 포트폴리오 등록</button>
                </div>
            </div>
        </section>

        <div class="portfolio-list">
            <c:choose>
                <!-- 등록된 포트폴리오가 없을 때-->
                <c:when test="${portfolioList eq null}">
                    <div class="portfolio-empty">
                        <p>등록된 포트폴리오가 없습니다.</p>
                        <p>포트폴리오를 등록해보세요.</p>
                        <button class="add-portfolio" onclick="location.href='my-page/portfolio-write'">+ 포트폴리오 등록
                        </button>
                    </div>
                </c:when>

                <c:otherwise>
                    <c:forEach var="portfolio" items="${portfolioList }">
                        <div class="portfolio-card">
                            <div class="portfolio-content">
                                <c:choose>
                                    <c:when test="${portfolio.isTempSaved eq false}">
                                        <span class="status writing">작성중</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status completed">작성완료</span>
                                    </c:otherwise>
                                </c:choose>
                                <div class="portfolio-body">
                                    <div class="thumbnail">${portfolio.thumbnail }</div>
                                    <div class="portfolio-info">
                                        <h3>
                                            <a href="my-page/portfolio-detail?id=${portfolio.portfolioId}">${portfolio.title }</a>
                                        </h3>
                                        <p class="description">등록자가 남긴 소개글 ${portfolio.introduce }</p>
                                        <div class="tags">
                                            <c:forEach var="skill" items="${portfolio.skillList }">
                                                <span>${skill}</span>
                                            </c:forEach>
                                        </div>
                                        <div class="reg-date">등록일 | ${portfolio.createdDate}</div>
                                    </div>
                                    <div class="actions">
                                        <button class="edit-btn">수정</button>
                                        <button class="delete-btn">삭제</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <div class="pagination" id="paging">
                        <c:choose>
                            <c:when test="${pageInfo.curPage > 1}">
                                <a href="my-page/portfolio-list?page=${pageInfo.curPage-1 }">&lt;</a>
                            </c:when>
                            <c:otherwise>
                                <a>&lt;</a>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1" var="page">
                            <c:choose>
                                <c:when test="${page eq pageInfo.curPage }">
                                    <a href="my-page/portfolio-list?page=${page }" class="select">${page }</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="my-page/portfolio-list?page=${page }" class="btn">${page }</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${pageInfo.curPage<pageInfo.allPage }">
                                <a href="my-page/portfolio-list?page=${pageInfo.curPage+1 }">&gt;</a>
                            </c:when>
                            <c:otherwise>
                                <a>&lt;</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</div>
</body>
</html>