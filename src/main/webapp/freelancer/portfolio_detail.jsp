<%--
  Author: 오상경
  Description: 구직자 포트폴리오 목록 페이지
  Read Data :
  *  'Id가 'portfolioId인 포트폴리오 조회 : List(Portfolio) / selectPortfolioListByPage(pageInfo, userId);
  Send Data :
  *  포트폴리오 아이디 : Integer /  portfolioId
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8"/>
    <title>포트폴리오 조회</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/common/headerSt.css'/>">
<%--   <link rel="stylesheet" href="${contextPath}/css/headerSt.css"/>--%>
<%--   <link rel="stylesheet" href="${contextPath}/css/freelancer_my_page.css'/>">--%>
<%--    <link rel="stylesheet" href="${contextPath}/css/freelancer_main_portfolio_detail.css'/>"/>--%>
   <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_main_portfolio_detail.css'/>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="header">
    <!-- 헤더 인클루드 영역 -->
<%--    <jsp:include page="/home/header.jsp"/>--%>
</div>
<div class="container">
    <script>console.log(${err})</script>
    <!-- 사이드바 -->
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <!-- 메인 콘텐츠 -->
    <main class="content">
        <section class="section">
            <h2>${portfolio.title}</h2>
            <div class="portfolio-info">
                <div class="portfolio-thumbnail">
                    <img src="image?filename=${portfolio.thumbnail}" alt='포트폴리오 썸네일 이미지' style="width: 100%; height: 100%; border-radius: 8px;"/>
                </div>
                <div class="portfolio-meta">
                    <p><strong>프로젝트 정보</strong></p>
                    <p><strong>프로젝트 기간</strong>
                        <span>
                            <c:if test="${not empty portfolio.portProjStart}">
                                <fmt:formatDate value="${portfolio.portProjStart}" pattern="yyyy-MM"/>
                            </c:if>
                            ~
                            <c:if test="${not empty portfolio.portProjEnd}">
                                <fmt:formatDate value="${portfolio.portProjEnd}" pattern="yyyy-MM"/>
                            </c:if>
                        </span>
                    </p>
                    <strong>팀 구성 및 역할</strong> <span> ${portfolio.teamRole }</span></p>
                </div>
            </div>
        </section>

        <section class="section">
            <h3>포트폴리오 소개</h3>
            <div class="description-text">
                ${portfolio.introduce }
            </div>
        </section>

        <section class="section">
            <h3>스킬</h3>
            <div class="skills">
                <c:forEach var="skill" items="${portfolio.skillList }">
                    <span>${skill}</span>
                </c:forEach>
            </div>
        </section>

        <section class="section">
            <h3>첨부파일</h3>
            <table>
                <tbody>
                <c:if test="${portfolio.projectId ne null}">
                    <tr>
                        <td><label>링크업을 통해 참여했던 프로젝트</label>
                        </td>
                        <td>
                            <a href="/projectDetail?id=${portfolio.projectId }">${portfolio.projectName }</a>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${portfolio.externalUrlList ne null}">
                    <c:forEach var="externalUrl" items="${portfolio.externalUrlList }">
                        <tr>
                            <td><label>외부 링크</label></td>
                            <td>
                                <a href="${externalUrl }">${externalUrl }</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${portfolio.attachmentList ne null}">
                    <c:forEach var="attachment" items="${portfolio.attachmentList }">
                        <tr>
                            <td><label>첨부파일</label></td>
                            <td>
                                <a href="fileDown?filename=${attachment }">${attachment }</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </section>
        <script>console.log(${portfolio.portfolioId})</script>
        <div class="action-buttons">
            <button class="edit-btn" onclick="location.href='${contextPath}/my-page/portfolio-modify?id=${portfolio.portfolioId }'">
                포트폴리오 수정
            </button>
            <button id="list-btn" type="button" class="list-btn" onclick="location.href='${contextPath}/my-page/portfolio-list'">목록</button>
            <button class="delete-btn" onclick="location.href='${contextPath}/my-page/portfolio-delete?id=${portfolio.portfolioId}'">
                포트폴리오 삭제
            </button>
        </div>
    </main>
</div>
</body>
</html>