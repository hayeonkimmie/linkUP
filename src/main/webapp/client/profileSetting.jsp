<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>마이페이지</title>
  <link rel="stylesheet" href="${contextPath}/css/style.css" />
  <link rel="stylesheet" href="${contextPath}/css/headerSt.css" />
</head>
<body>
<!-- 공통 헤더 include -->
<jsp:include page="../common/header.jsp" />

<div class="layout">

  <!-- 공통 사이드바 include -->
  <jsp:include page="../common/sidebar.jsp" />

  <!-- 메인 콘텐츠 -->
  <main class="main">
    <h2 class="section-title">프로필 설정</h2>

    <h3>기본 정보</h3>
    <div class="form-grid">
      <div class="form-group">
        <label>이름</label>
        <input type="text" name="name" value="${user.name}" />
      </div>
      <div class="form-group">
        <label>아이디</label>
        <input type="text" name="username" value="${user.username}" />
      </div>
      <div class="form-group">
        <label>인사담당자 이메일</label>
        <input type="email" name="email" value="${user.email}" />
      </div>
      <div class="form-group">
        <label>대표번호</label>
        <input type="text" name="mainPhone" value="${user.mainPhone}" />
      </div>
      <div class="form-group">
        <label>담당자 휴대폰 번호</label>
        <input type="text" name="mobile" value="${user.mobile}" />
      </div>
      <div class="form-group">
        <label>팩스 번호</label>
        <input type="text" name="fax" value="${user.fax}" />
      </div>
    </div>

    <h3>회사/기관 정보</h3>
    <div class="form-grid">
      <div class="form-group">
        <label>회사/기관명</label>
        <input type="text" name="companyName" value="${user.companyName}" />
      </div>
      <div class="form-group">
        <label>회사/기관 홈페이지</label>
        <input type="url" name="companyWebsite" value="${user.companyWebsite}" />
      </div>
      <div class="form-group">
        <label>설립일자</label>
        <input type="text" name="establishedAt" value="${user.establishedAt}" />
      </div>
      <div class="form-group">
        <label>사업자등록번호</label>
        <input type="text" name="bizNum" value="${user.bizNum}" />
      </div>
      <div class="form-group" style="grid-column: 1 / -1">
        <label>회사 소개</label>
        <textarea name="intro">${user.intro}</textarea>
      </div>
    </div>

    <h3>비밀번호 관리</h3>
    <div class="form-grid">
      <div class="form-group" style="grid-column: 1 / -1;">
        <label>현재 비밀번호</label>
        <input type="password" name="currentPw" />
      </div>
      <div class="form-group">
        <label>새 비밀번호</label>
        <input type="password" name="newPw" />
      </div>
      <div class="form-group">
        <label>새 비밀번호 확인</label>
        <input type="password" name="newPwConfirm" />
      </div>
    </div>

    <div class="button-group">
      <button class="btn-primary">비밀번호 변경</button>
      <button class="btn-secondary" type="reset">취소</button>
    </div>

    <br>
    <div class="button-group-right">
      <button class="button-save">저장</button>
    </div>
  </main>
</div>
</body>
</html>
