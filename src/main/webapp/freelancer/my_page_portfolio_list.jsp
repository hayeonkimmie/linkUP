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
            <img src="./../img/basic_profile_img.png" alt="profile"/>
            <p>닉네임</p>
            <p>마이페이지</p>
        </div>
        <ul>
            <li class="profile-settings">
                <h3>프로필 설정</h3>
                <a href="#">기본 정보 설정</a>
                <a href="#">전문가 정보 설정</a>
            </li>
            <li><h3><a href="#" class="active">포트폴리오</a></h3></li>
            <li><h3><a href="#">찜한 프로젝트</a></h3>
            <li><h3><a href="#">지원한 프로젝트 내역</a></h3>
            <li><h3><a href="#">진행중인 / 완료된 프로젝트</a></h3>
            <li><h3><a href="#">프로젝트 후기</a></h3>
            <li><h3><a href="#">문의내역</a></h3>
        </ul>
    </aside>
    <!-- 메인 콘텐츠 -->
    <main class="content">
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <span>
                        <h3>포트폴리오 목록</h3>
                        <p>닉네임님의 최고의 프로젝트를 선보이세요.</p>
                    </span>
                    <button class="add-portfolio">+ 포트폴리오 등록</button>
                </div>
            </div>
        </section>

        <div class="portfolio-list">
            <!-- 포트폴리오 카드 1 - 작성중 -->
            <div class="portfolio-card">
                <div class="portfolio-content">
                    <span class="status writing">작성중</span>
                    <div class="portfolio-body">
                        <div class="thumbnail"></div>
                        <div class="portfolio-info">
                            <h3>포트폴리오 제목</h3>
                            <p class="description">등록자가 남긴 소개글</p>
                            <div class="tags">
                                <span>스킬1</span>
                                <span>스킬2</span>
                                <span>스킬3</span>
                                <span>스킬1</span>
                                <span>스킬2</span>
                            </div>
                            <div class="reg-date">등록일 | 25.03.31</div>
                        </div>
                        <div class="actions">
                            <button class="edit-btn">수정</button>
                            <button class="delete-btn">삭제</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 포트폴리오 카드 2 - 작성완료 -->
            <div class="portfolio-card complete">
                <div class="portfolio-content">
                    <span class="status completed">작성완료</span>
                    <div class="portfolio-body">
                        <div class="thumbnail"></div>
                        <div class="portfolio-info">
                            <h3>포트폴리오 제목</h3>
                            <p class="description">등록자가 남긴 소개글</p>
                            <div class="tags">
                                <span>스킬1</span>
                                <span>스킬2</span>
                                <span>스킬3</span>
                                <span>스킬1</span>
                                <span>스킬2</span>
                            </div>
                            <div class="reg-date">등록일 | 25.03.31</div>
                        </div>
                        <div class="actions">
                            <button class="edit-btn">수정</button>
                            <button class="delete-btn">삭제</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 포트폴리오 카드 3 - 작성완료 -->

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
                        <a>&gt;</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
</div>
</body>
</html>