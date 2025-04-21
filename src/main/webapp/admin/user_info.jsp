<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>사용자 정보 관리</title>
  <link rel="stylesheet" href="../css/admin_header.css">
  <link rel="stylesheet" href="../css/user_info.css">
  <style>

  </style>


</head>
<body>

<jsp:include page="/admin/admin_header.jsp" />
<div class="layout">
  <jsp:include page="/admin/menutap.jsp" />
  <div class="content">
    <!-- ✅ 검색 바 (form 추가) -->
    <!-- ✅ 검색 바 (form 추가) -->
    <div class="filter-container">
      <form class="search-bar" method="get" action="<c:url value='/admin/users'/>">
        <select name="usertype">
          <option value="all" ${param.usertype == 'all' ? 'selected' : ''}>전체</option>
          <option value="freelancer" ${param.usertype == 'freelancer' ? 'selected' : ''}>구직자</option>
          <option value="client" ${param.usertype == 'client' ? 'selected' : ''}>구인자</option>
        </select>
        <input type="text" name="keyword" placeholder="회원명, 아이디 또는 이메일로 검색" value="${param.keyword}" />
        <button type="submit">검색</button>
      </form>
    </div>



    <!-- ✅ 사용자 목록 테이블 -->
    <div class="card">
      <h2>사용자 목록</h2>

      <div class="segment-tabs">
        <div id="구직자Tab" class="segment-tab" data-type="구직자">구직자</div>
        <div id="구인자Tab" class="segment-tab" data-type="구인자">구인자</div>
      </div>


      <table>
        <thead>
        <tr>
          <th class="col-id">아이디</th>
          <th class="col-name">회원명</th>
          <th class="col-email">이메일</th>
          <th class="col-date">가입일</th>
          <th class="col-phone">연락처</th>
          <th class="col-type">구분</th>
        </tr>
        </thead>
        <tbody>
        <!-- 구직자 출력 -->
        <c:forEach var="f" items="${freelancerList}">
          <tr data-type="구직자">
            <td>${f.freelancerId}</td>
            <td>
              <a href="<c:url value='/admin/freelancer'/>?freelancerid=${f.freelancerId}" class="project-link">${f.name}</a>
            </td>
            <td>${f.email}</td>
            <td>${f.registrationDate}</td>
            <td>${f.phoneNum}</td>
            <td><span class="badge 구직자">${f.type}</span></td>
          </tr>
        </c:forEach>

        <!-- 구인자 출력 -->
        <c:forEach var="c" items="${clientList}">
          <tr data-type="구인자">
            <td>${c.userId}</td>
            <td>
              <a href="<c:url value='/admin/client'/>?clientid=${c.userId}" class="project-link">${c.name}</a>
            </td>
            <td>${c.email}</td>
            <td>${c.registrationDate}</td>
            <td>${c.phoneNumber}</td>
            <td><span class="badge 구인자">${c.type}</span></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>



  </div>
</div>
<script>
  document.addEventListener("DOMContentLoaded", function () {
    const tabs = document.querySelectorAll(".segment-tab");
    const rows = document.querySelectorAll("tbody tr");

    // ✅ URL 파라미터에서 usertype 추출
    const params = new URLSearchParams(window.location.search);
    const usertypeParam = params.get("usertype"); // "freelancer", "client", "all" 등

    // ✅ 탭 클릭 이벤트 처리
    tabs.forEach(tab => {
      tab.addEventListener("click", () => {
        const selectedType = tab.dataset.type;

        // UI 처리
        tabs.forEach(t => t.classList.remove("active"));
        tab.classList.add("active");

        // 테이블 필터링
        rows.forEach(row => {
          const rowType = row.dataset.type;
          row.classList.toggle("hidden-row", rowType !== selectedType);
        });
      });
    });

    // ✅ 페이지 로드시 usertype 값에 따라 탭 선택
    if (usertypeParam === "client") {
      document.getElementById("구인자Tab")?.click();
    } else {
      document.getElementById("구직자Tab")?.click(); // 기본값
    }
  });
</script>




</body>
</html>
