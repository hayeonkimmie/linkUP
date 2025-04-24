<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>고객센터</title>
  <link rel="stylesheet" href="${contextPath}/css/home/gogakCenter.css" />
</head>
<body>

<div id="header-placeholder"></div>
<div class="container">
  <!-- 고객센터 타이틀 -->
  <div class="title-box">
    <h2>고객센터</h2>
  </div>

  <!-- 공지사항 (100%) -->
  <div class="full-box">
      <h3><a href="${contextPath}/notice">공지사항</a></h3>
    <ul class="list">
      <c:forEach var="notice" items="${noticeList}">
        <li><a href="${contextPath}/noticePage?id=${notice.noticeId}">${notice.title}</a> ${notice.createdAt}</li>
      </c:forEach>

<%--      <li><span>4월 고객센터 운영시간 안내</span><span>2025.04.01</span></li>--%>
<%--      <li><span>3월 27일(목) 링크업 서버 점검 안내 및 공지</span><span>2025.03.26</span></li>--%>
<%--      <li><span>[필독] 창업사업화 지원사업 모집 기간, 불법 컨설팅에 주의해 주세요!</span><span>2025.03.04</span></li>--%>
<%--      <li><span>3월 1일 공휴일 운영시간 안내</span><span>2025.02.26</span></li>--%>

    </ul>
  </div>

  <!-- 문의 + 새 소식 (좌우 50%) -->
  <div class="row-2col">
    <!-- 문의하기 -->
    <div class="QAHalf-box inquiry">
      <div class="section-title">문의관리</div>
      <div class="btn-wrap">
        <a href="${contextPath}/QnA" class="btn">문의하기 <span>&#8250;</span></a>
        <a href="#" class="btn">문의내역 <span>&#8250;</span></a>
      </div>
    </div>

    <!-- 새 소식 -->
    <div class="newHalf-box">
      <h3> <a href="${contextPath}/news"> ❤ 새 소식 </a></h3>
      <ul class="list">
        <c:forEach var="news" items="${newsList}">
          <li><a href="${contextPath}/newsPage?id=${news.newsId}">${news.title}</a> ${news.createdAt}</li>
        </c:forEach>
      </ul>
<%--        <table border="0">--%>
<%--          <tr>--%>
<%--            <th>새소식번호</th>--%>
<%--            <th>제목</th>--%>
<%--            <th>내용</th>--%>
<%--            <th>날짜</th>--%>
<%--          </tr>--%>
<%--          <c:forEach var="news" items="${newsList}">--%>
<%--            <tr>--%>
<%--              <td>${news.news_id}</td>--%>
<%--              <td>${news.title}</td>--%>
<%--              <td>${news.content}</td>--%>
<%--              <td>${news.created_at}</td>--%>
<%--            </tr>--%>
<%--          </c:forEach>--%>

<%--        </table>--%>
<%--        <li>--%>
<%--          <span><a href="newsPage.jsp">링크업에서 주최하는 취업박람회 소개</a></span><span>2025.04.01</span>--%>
<%--        </li>--%>
<%--        <li><span>링크업 홈페이지 리뉴얼</span><span>2025.03.26</span></li>--%>
<%--        <li><span>창업을 위한 링크업 이벤트 (3/2~)</span><span>2025.03.04</span></li>--%>
<%--        <li><span>2월 우리는 오늘도 프로젝트</span><span>2025.02.26</span></li>--%>


    </div>
  </div>
</div>
<script>
  const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/catalog.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
</body>
</html>
