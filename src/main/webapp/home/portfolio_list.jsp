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
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <meta charset="UTF-8">
<%--    <meta content="width=device-width, initial-scale=1.0" name="viewport">--%>
    <title>Link up Profile List</title>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css" />
    <link rel="stylesheet" href="<c:url value='/css/admin/admin_portfolio_list.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_main_portfolio.css'/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>

<body>
<div class="header">
    <div id="header-placeholder"></div>
</div>

<div class="container">
    <main class="content" style="max-width: 1150px; margin: 0 auto;">
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <span>
                        <h3>${name}님의 포트폴리오 목록</h3>
                        <%--<p>${nickname}님의 최고의 프로젝트를 선보이세요.</p>--%>
                    </span>
                </div>
            </div>
        </section>

        <div class="portfolio-list">
            <!-- 등록된 포트폴리오가 없을 때-->
            <c:choose>
                <c:when test="${portfolioList eq null}">
                    <div class="portfolio-empty empty">
                        <p>등록된 포트폴리오가 없습니다.</p>
                    </div>
                </c:when>

                <c:otherwise>
                    <c:forEach var="portfolio" items="${portfolioList }">
                        <div class="portfolio-card">
                            <div class="portfolio-content">
                                <div class="portfolio-body">
                                    <div class="list-thumbnail">
                                        <img src="./img/${portfolio.thumbnail}" alt='포트폴리오 썸네일 이미지'/>
                                    </div>
                                    <div class="portfolio-info">
                                        <h3>
                                            <a href="${contextPath}/portfolio-detail?id=${portfolio.portfolioId}">${portfolio.title }</a>
                                        </h3>
                                        <p class="description">${portfolio.introduce }</p>
                                        <div class="tags">
                                            <c:forEach var="skill" items="${portfolio.skillList }">
                                                <c:if test="${not empty skill }">
                                                    <span class="tag">${skill }</span>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                        <div class="reg-date">등록일 | ${portfolio.createdDate}</div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="pagination" id="paging">
                        <c:choose>
                            <c:when test="${pageInfo.curPage > 1}">
                                <a href="?page=${pageInfo.curPage-1 }">&lt;</a>
                            </c:when>
                            <c:otherwise>
                                <a>&lt;</a>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1"
                                   var="page">
                            <c:choose>
                                <c:when test="${page eq pageInfo.curPage }">
                                    <a href="?page=${page }" class="select">${page }</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="?page=${page }" class="btn">${page }</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${pageInfo.curPage<pageInfo.allPage }">
                                <a href="?page=${pageInfo.curPage+1 }">&gt;</a>
                            </c:when>
                            <c:otherwise>
                                <a>&gt;</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</div>
<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
</body>
