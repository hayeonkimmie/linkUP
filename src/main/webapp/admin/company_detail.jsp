<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>회사 상세 정보</title>
  <link rel="stylesheet" href="../css/admin_header.css" />
  <link rel="stylesheet" href="../css/company_detail.css" />
  <script>
    const defaultOpenMenuId = "projectMenu";
  </script>
</head>
<body>
<!-- ✅ 헤더/사이드 메뉴 include -->
<jsp:include page="/admin/admin_header.jsp" />
<div class="layout-wrapper">
  <jsp:include page="/admin/menutap.jsp" />

  <div class="content">
    <div class="page-header">
      <h2>${company.name}</h2>
      <a href="<c:url value='/admin/company-list' />?companyid=${company.id}" class="back-link">
        ${company.name}의 프로젝트 목록 보기 →
      </a>

    </div>

    <!-- ✅ 기본 정보 -->
    <div class="card">
      <h3>기본 정보</h3>
      <div class="info-grid">
        <div><strong>회사명</strong><p>${company.name}</p></div>
        <div><strong>설립일자</strong><p>${company.foundedDate}</p></div>
        <div><strong>대표자명</strong><p>${company.ceo}</p></div>
        <div><strong>업종</strong><p>소프트웨어 개발</p></div> <!-- 추후 company.industry로 변경 가능 -->
        <div><strong>사업자등록번호</strong><p>123-45-67890</p></div> <!-- 추후 company.businessNumber -->
        <div><strong>업태</strong><p>서비스업</p></div> <!-- company.businessType -->
      </div>
    </div>

    <!-- ✅ 연락처 정보 -->
    <div class="card">
      <h3>연락처 정보</h3>
      <ul class="contact-list">
        <li><strong>본사 주소:</strong> ${company.address}</li>
        <li><strong>대표 전화번호:</strong> ${company.phone}</li>
        <li><strong>대표 이메일:</strong> ${company.email}</li>
        <li><strong>FAX:</strong> 02-1234-5679</li> <!-- 추후 company.fax 추가 가능 -->
      </ul>
    </div>
  </div>
</div>
</body>
</html>
