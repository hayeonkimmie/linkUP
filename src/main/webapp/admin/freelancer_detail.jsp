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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>구직자 상세 정보</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css" />
  <link rel="stylesheet" href="../css/admin/user_detail.css" />
  <script>
    const defaultOpenMenuId = "userMenu";
  </script>

</head>
<body>
<jsp:include page="/admin/admin_header.jsp" />
<!-- 생략된 헤더는 동일 -->

<div class="layout-wrapper">
  <jsp:include page="/admin/menutap.jsp" />

  <div class="content">
    <!-- ✅ 닉네임 / 대표 정보 박스 -->
    <div class="profile-banner">
      <img class="profile-image" src="${contextPath}/img/${freelancer.profileImg}" alt="프로필 이미지"/>

      <div class="profile-info">
        <h1 class="name">${freelancer.name} <span>(${freelancer.nickname})</span></h1>

        <div class="skill-badges">
          <c:forEach var="tag" items="${fn:split(freelancer.skill, '^')}">
            <span class="badge">${tag}</span>
          </c:forEach>
        </div>
      </div>
    </div>

    <hr class="section-divider" />

    <!-- ✅ 자기소개 -->
    <div class="card">
      <h2>자기소개</h2>
      <p class="introduction-text">${freelancer.introduction}</p>
    </div>

    <!-- ✅ 경력사항 -->
    <div class="card">
      <h2>경력사항</h2>
      <ul class="info-list">
        <li><strong>등록일:</strong> ${freelancer.registrationDate}</li>
        <li><strong>학력:</strong> ${freelancer.academic}</li>
        <li><strong>거주 여부:</strong>
          <c:choose>
            <c:when test="${freelancer.resident}">거주자</c:when>
            <c:otherwise>비거주자</c:otherwise>
          </c:choose>
        </li>
        <li><strong>희망 연봉:</strong> ${freelancer.desiredSalary}</li>
        <li><strong>자격증:</strong> ${freelancer.license}</li>
      </ul>
    </div>

    <!-- ✅ 기타 정보 -->
    <div class="card">
      <h2>기타 정보</h2>
      <ul class="info-list">
        <li><strong>이메일:</strong> ${freelancer.email}</li>
        <li><strong>전화번호:</strong> ${freelancer.phoneNum}</li>
        <li><strong>주소:</strong> ${freelancer.address}</li>
        <li><strong>포트폴리오 링크:</strong> <a href="${freelancer.externalUrl}" target="_blank">${freelancer.externalUrl}</a></li>
        <li><strong>첨부파일:</strong> ${freelancer.attachment}</li>
        <li><strong>기타 요청사항:</strong> ${freelancer.otherRequests}</li>
      </ul>
    </div>
  </div>
</div><script>
  // HEX 대비색 구하기
  function getContrastYIQ(hexcolor) {
    hexcolor = hexcolor.replace("#", "");
    const r = parseInt(hexcolor.substr(0, 2), 16);
    const g = parseInt(hexcolor.substr(2, 2), 16);
    const b = parseInt(hexcolor.substr(4, 2), 16);
    const yiq = (r * 299 + g * 587 + b * 114) / 1000;
    return yiq >= 128 ? "#000" : "#fff";
  }

  // 랜덤 HEX 색상 생성기
  function getRandomHexColor() {
    const r = Math.floor(Math.random() * 200);
    const g = Math.floor(Math.random() * 200);
    const b = Math.floor(Math.random() * 200);
    return (
            "#" +
            [r, g, b]
                    .map(x => {
                      const hex = x.toString(16);
                      return hex.length === 1 ? "0" + hex : hex;
                    })
                    .join("")
    );
  }

  // DOM 로드 후 badge 색상 적용
  window.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".skill-badges .badge").forEach(badge => {
      const bgColor = getRandomHexColor();
      badge.style.backgroundColor = bgColor;
      badge.style.color = getContrastYIQ(bgColor);
    });
  });
</script>

</body>
</html>
