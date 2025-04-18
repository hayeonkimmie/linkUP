<%--
  Author: 오상경
  Description: 구직자 포트폴리오 목록 페이지
  Read Data :
  *  'user_id'에 의해 작성되었고, 삭제되지 않은 포트폴리오의 총 갯수 조회 : Integer /  selectPortfolioCnt(userId)
  *  'user_id'에 의해 작성되었고, 삭제되지 않은 포트폴리오 목록 조회 : List(Portfolio) / selectPortfolioListByPage(pageInfo, userId);
  Send Data :
  *  유저 아이디 : String / user_id
  *  현재 페이지 넘버 : Integer /  page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Link up Profile List</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%--    <link rel="stylesheet" href="<c:url value='/css/headerSt.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_main_portfolio.css'/>">
--%>
    <link rel="stylesheet" href="<c:url value='/css/headerSt.css'/>">
    <%--    <link rel="stylesheet" href="${contextPath}/css/headerSt.css">--%>
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_main_portfolio_write_and_modify.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_main_portfolio.css'/>">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>

<body>
<div class="header">
    <!-- 헤더 인클루드 영역 -->
    <jsp:include page="/common/header.jsp"/>
</div>
<div class="container">
    <!-- 사이드바 -->
    <jsp:include page="/freelancer/sidebar.jsp"/>
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
                    <button class="add-portfolio" onclick="location.href='linkup/my-page/portfolio-write'">+ 포트폴리오 등록</button>
                </div>
            </div>
        </section>

        <div class="portfolio-list">
            <!-- 등록된 포트폴리오가 없을 때-->
            <c:choose>
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
                                    <c:when test="${portfolio.isTempSaved == true}">
                                        <span class="status writing">작성중</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status completed">작성완료</span>
                                    </c:otherwise>
                                </c:choose>
                                <div class="portfolio-body">
                                    <div class="thumbnail">
                                        <img src="${portfolio.thumbnail}" alt='프로필 이미지'/></div>
                                    <div class="portfolio-info">
                                        <h3>
                                            <a href="portfolio-detail?id=${portfolio.portfolioId}">${portfolio.title }</a>
                                        </h3>
                                        <p class="description">${portfolio.introduce }</p>
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
                                <a href="page=${pageInfo.curPage-1 }">&lt;</a>
                            </c:when>
                            <c:otherwise>
                                <a>&lt;</a>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1" var="page">
                            <c:choose>
                                <c:when test="${page eq pageInfo.curPage }">
                                    <a href="page=${page }" class="select">${page }</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="page=${page }" class="btn">${page }</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${pageInfo.curPage<pageInfo.allPage }">
                                <a href="page=${pageInfo.curPage+1 }">&gt;</a>
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