<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Link up Header</title>
  <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css">

</head>
<body>
<header class="header-header">
  <div class="header-header-container">
    <div class="header-logo-search">
      <a href="${contextPath}/home/main.jsp" class="header-logo">
        <img src="${contextPath}/img/링크업 로고.png" alt="Link up 로고">
      </a>
      <input type="text" class="header-search-bar" placeholder="어떤 전문가를 찾고 계신가요" />
    </div>
  </div>

  <div class="header-nav-auth-wrapper">
    <nav class="header-category-nav">
      <ul>
        <li class="header-dropdown">
          <a href="#" class="header-dropdown-toggle">웹제작</a>
          <ul class="header-dropdown-menu">
            <li><a href="${contextPath}/home/catalogWebProduction.jsp">홈페이지 신규 제작</a></li>
            <li><a href="#">쇼핑몰 신규 제작</a></li>
            <li><a href="#">랜딩페이지</a></li>
          </ul>
        </li>
        <li class="header-dropdown">
          <a href="#" class="header-dropdown-toggle">웹유지보수</a>
          <ul class="header-dropdown-menu">
            <li><a href="#">홈페이지 수정·유지보수</a></li>
            <li><a href="#">쇼핑몰 수정·유지보수</a></li>
            <li><a href="#">퍼블리싱</a></li>
            <li><a href="#">검색최적화·SEO</a></li>
            <li><a href="#">애널리틱스</a></li>
          </ul>
        </li>
        <li class="header-dropdown">
          <a href="#" class="header-dropdown-toggle">프로그램</a>
          <ul class="header-dropdown-menu">
            <li><a href="#">프로그램 스토어</a></li>
            <li><a href="#">수익 자동화</a></li>
            <li><a href="#">업무 자동화</a></li>
            <li><a href="#">크롤링·스크래핑</a></li>
            <li><a href="#">일반 프로그램</a></li>
            <li><a href="#">프로그램 수정·유지보수</a></li>
            <li><a href="#">서버·클라우드</a></li>
            <li><a href="#">엑셀·스프레드시트</a></li>
            <li><a href="#">봇·챗봇</a></li>
          </ul>
        </li>
        <li class="header-dropdown">
          <a href="#" class="header-dropdown-toggle">모바일</a>
          <ul class="header-dropdown-menu">
            <li><a href="#">앱</a></li>
            <li><a href="#">앱 패키징</a></li>
            <li><a href="#">앱 수정·유지보수</a></li>
          </ul>
        </li>
        <li class="header-dropdown">
          <a href="#" class="header-dropdown-toggle">AI</a>
          <ul class="header-dropdown-menu">
            <li><a href="#">AI·GPT 서비스 개발</a></li>
            <li><a href="#">AI·GPT 챗봇</a></li>
            <li><a href="#">AI·GPT 자동화 프로그램</a></li>
            <li><a href="#">프롬프트 엔지니어링</a></li>
            <li><a href="#">머신러닝·딥러닝</a></li>
            <li><a href="#">컴퓨터 비전·자연어 처리</a></li>
          </ul>
        </li>
        <li class="header-dropdown">
          <a href="#" class="header-dropdown-toggle">데이터</a>
          <ul class="header-dropdown-menu">
            <li><a href="#">데이터 구매·구축</a></li>
            <li><a href="#">데이터 라벨링</a></li>
            <li><a href="#">데이터 전처리·분석·시각화</a></li>
            <li><a href="#">데이터베이스</a></li>
          </ul>
        </li>
        <li class="header-dropdown">
          <a href="#" class="header-dropdown-toggle">트렌드</a>
          <ul class="header-dropdown-menu">
            <li><a href="#">게임∙AR∙VR</a></li>
            <li><a href="#">메타버스</a></li>
            <li><a href="#">블록체인·NFT</a></li>
          </ul>
        </li>
        <li class="header-dropdown">
          <a href="#" class="header-dropdown-toggle">직무직군</a>
          <ul class="header-dropdown-menu">
            <li><a href="#">UI·UX 기획</a></li>
            <li><a href="#">프론트엔드</a></li>
            <li><a href="#">백엔드</a></li>
            <li><a href="#">풀스택</a></li>
            <li><a href="#">데브옵스·인프라</a></li>
            <li><a href="#">데이터·ML·DL</a></li>
          </ul>
        </li>
      </ul>
    </nav>
    <div class="header-auth-buttons">
      <a href="login.jsp" class="header-login">로그인</a>
      <a href="createAcc.jsp" class="header-signup">회원가입</a>
    </div>
  </div>
</header>
</div>

<script src="../js/header.js"></script>
</body>
</html>
