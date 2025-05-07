<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>사용자 정보 관리</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css">
  <link rel="stylesheet" href="../css/admin/user_info.css">
</head>
<body>

<div id="header-include"></div>
<div class="layout">
  <div id="menu-include"></div>
  <div class="content">
    <div class="card">
      <h1>사용자 목록</h1>
      <h2>모든 유저를 조회하고 검색하는 페이지입니다.</h2>

      <!-- 🔍 검색 필터 -->
      <div class="filter-container">
        <form class="search-bar" method="get" action="<c:url value='/admin/users'/>">
          <label>
            <select name="usertype" class="search-select">
              <option value="all" ${param.usertype == 'all' ? 'selected' : ''}>전체</option>
              <option value="freelancer" ${param.usertype == 'freelancer' ? 'selected' : ''}>구직자</option>
              <option value="client" ${param.usertype == 'client' ? 'selected' : ''}>구인자</option>
            </select>
          </label>
          <label>
            <input type="text" name="keyword" placeholder="회원명, 아이디 또는 이메일로 검색" value="${param.keyword}" class="search-input" />
          </label>
          <button type="submit" class="search-btn">검색</button>
        </form>
      </div>

      <!-- 🔘 세그먼트 탭 -->
      <div class="segment-tabs">
        <div id="freelancerTab" class="segment-tab" data-type="freelancer">구직자</div>
        <div id="clientTab" class="segment-tab" data-type="client">구인자</div>
      </div>

      <!-- 📋 테이블 -->
      <div class="table-section">
        <c:if test="${empty freelancerList and empty clientList}">
          <div class="no-data-message">찾으실 유저를 검색하세요.</div>
        </c:if>

        <c:if test="${not empty freelancerList or not empty clientList}">
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
            <!-- 👤 구직자 -->
            <c:forEach var="f" items="${freelancerList}">
              <tr data-type="freelancer">
                <td>${f.freelancerId}</td>
                <td><a href="<c:url value='/admin/freelancer'/>?freelancerid=${f.freelancerId}" class="project-link">${f.name}</a></td>
                <td>${f.email}</td>
                <td>${f.registrationDate}</td>
                <td>${f.phoneNum}</td>
                <td><span class="badge 구직자">구직자</span></td>
              </tr>
            </c:forEach>

            <!-- 👨‍💼 구인자 -->
            <c:forEach var="c" items="${clientList}">
              <tr data-type="client">
                <td>${c.userId}</td>
                <td><a href="<c:url value='/admin/client'/>?clientid=${c.userId}" class="project-link">${c.name}</a></td>
                <td>${c.email}</td>
                <td>${c.registrationDate}</td>
                <td>${c.phoneNumber}</td>
                <td><span class="badge 구인자">구인자</span></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </c:if>
      </div>
    </div>
  </div>
</div>

<!-- 🔁 탭 스크립트 -->
<script>
  document.addEventListener("DOMContentLoaded", function () {
    const tabs = document.querySelectorAll(".segment-tab");
    const rows = document.querySelectorAll("tbody tr");

    // ✅ usertype 파라미터 기준으로 탭 자동 클릭
    const params = new URLSearchParams(window.location.search);
    const usertypeParam = params.get("usertype");

    tabs.forEach(tab => {
      tab.addEventListener("click", () => {
        const selectedType = tab.dataset.type;

        tabs.forEach(t => t.classList.remove("active"));
        tab.classList.add("active");

        rows.forEach(row => {
          row.classList.toggle("hidden-row", row.dataset.type !== selectedType);
        });
      });
    });

    // 자동 탭 선택
    if (usertypeParam === "client") {
      document.getElementById("clientTab")?.click();
    } else {
      document.getElementById("freelancerTab")?.click(); // 기본값
    }
  });
</script>
<script src="../js/include_common.js"></script>
</body>
</html>
