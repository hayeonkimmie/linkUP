<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up 마이페이지</title>
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css' />">
</head>
<body>
<div class="header">
    <jsp:include page="/common/header.jsp" />
   <%-- <%@ include file="<c:url value='/common/header.jsp'/>" %>--%>
</div>
<div class="container">
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <main class="content">
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>찜한 프로젝트</h3>
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <th>프로젝트 명</th>
                    <th>분야</th>
                    <th>기간</th>
                    <th>예산</th>
                    <th>고객사</th>
                    <th>요구 스킬/경력</th>
                    <th>모집 마감일</th>
                    <th>D-Day</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>웹 쇼핑몰 리뉴얼</td>
                    <td>웹 개발</td>
                    <td>3개월</td>
                    <td>2,500만원</td>
                    <td>패션스토어(주)</td>
                    <td>React, TypeScript, 3년 이상</td>
                    <td>25. 04. 07</td>
                    <td>
                        <span class="d-day">D-6</span>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>진행중 프로젝트</h3>
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <th>진행여부</th>
                    <th>프로젝트 명</th>
                    <th>분야</th>
                    <th>기간</th>
                    <th>예산</th>
                    <th>요구사항</th>
                    <th>프로젝트 마감일</th>
                    <th>D-Day</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><span class="status">진행중</span></td>
                    <td>데이터 대시보드 구축</td>
                    <td>데이터시각화</td>
                    <td>2개월</td>
                    <td>1,800만원</td>
                    <td>D3.js, React, 2년 이상</td>
                    <td>25. 04. 07</td>
                    <td><span class="d-day">D-6</span>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="height: 39px;"><b>진행중인 프로젝트가 없습니다.</b></td>
                </tr>
                </tbody>
            </table>
        </section>
    </main>
</div>
</body>
</html>