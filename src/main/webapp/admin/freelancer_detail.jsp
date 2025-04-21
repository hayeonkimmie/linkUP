<%--
  Author: 이원영
  Date: 25. 4. 14.
  Time: 오후 2:10
  Description: 프리랜서(개인, 구직자)의 상세 페이지
  Read Data :
    - AdminFreelancer freelancer (프리랜서 정보)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>구직자 상세 정보</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css" />
  <link rel="stylesheet" href="../css/admin/company_detail.css" />
  <script>
    const defaultOpenMenuId = "userMenu";
  </script>
</head>
<body>
<jsp:include page="/admin/admin_header.jsp" />
<div class="layout-wrapper">
  <jsp:include page="/admin/menutap.jsp" />

  <div class="content">
    <div class="page-header">
      <h2>${freelancer.name} (${freelancer.nickname})</h2>
      <a href="<c:url value='/admin/user-list'/>" class="back-link">
        전체 구직자 목록 보기 →
      </a>
    </div>

    <!-- ✅ 기본 정보 -->
    <div class="card">
      <h3>기본 정보</h3>
      <div class="info-grid">
        <div><strong>이름</strong><p>${freelancer.name}</p></div>
        <div><strong>닉네임</strong><p>${freelancer.nickname}</p></div>
        <div><strong>등록일</strong><p>${freelancer.registrationDate}</p></div>
        <div><strong>학력</strong><p>${freelancer.academic}</p></div>
        <div><strong>기술 스택</strong><p>${freelancer.skill}</p></div>
        <div><strong>희망 연봉</strong><p>${freelancer.desiredSalary}</p></div>
        <div><strong>희망 근무지</strong><p>${freelancer.desiredLocation}</p></div>
        <div><strong>거주 여부</strong><p><c:choose>
          <c:when test="${freelancer.resident}">거주자</c:when>
          <c:otherwise>비거주자</c:otherwise>
        </c:choose></p></div>
      </div>
    </div>

    <!-- ✅ 연락처 정보 -->
    <div class="card">
      <h3>연락처 정보</h3>
      <ul class="contact-list">
        <li><strong>전화번호:</strong> ${freelancer.phoneNum}</li>
        <li><strong>이메일:</strong> ${freelancer.email}</li>
        <li><strong>주소:</strong> ${freelancer.address}</li>
        <li><strong>포트폴리오 링크:</strong> <a href="${freelancer.externalUrl}" target="_blank">${freelancer.externalUrl}</a></li>
      </ul>
    </div>

    <!-- ✅ 기타 정보 -->
    <div class="card">
      <h3>기타 정보</h3>
      <ul class="contact-list">
        <li><strong>자기소개:</strong> ${freelancer.introduction}</li>
        <li><strong>자격증:</strong> ${freelancer.license}</li>
        <li><strong>첨부파일:</strong> ${freelancer.attachment}</li>
        <li><strong>기타 요청사항:</strong> ${freelancer.otherRequests}</li>
      </ul>
    </div>
  </div>
</div>
</body>
</html>
