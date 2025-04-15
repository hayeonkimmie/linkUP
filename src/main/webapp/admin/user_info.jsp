<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>사용자 정보 관리</title>
  <script>
    const defaultOpenMenuId = "projectMenu";
  </script>
  <link rel="stylesheet" href="../css/admin_header.css">
  <link rel="stylesheet" href="../css/user_info.css">
</head>
<body>

<!-- ✅ 헤더 include -->
<jsp:include page="/admin/admin_header.jsp" />
<div class="layout">
  <!-- ✅ 메뉴탭 include -->
  <jsp:include page="/admin/menutap.jsp" />
  <div class="content">

    <div class="search-bar">
      <input type="text" placeholder="회원명, 아이디 또는 이메일로 검색">
      <button>검색</button>
    </div>

    <!-- ✅ 구직자 목록 -->
    <div class="card">
      <h2>구직자 목록</h2>
      <table>
        <thead>
        <tr>
          <th class="col-no">번호</th>
          <th class="col-name">회원명</th>
          <th class="col-id">아이디</th>
          <th class="col-email">이메일</th>
          <th class="col-date">가입일</th>
          <th class="col-phone">연락처</th>
          <th class="col-type">구분</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${companyList}">
          <c:if test="${c.type eq '구직자'}">
            <tr>
              <td>${c.id}</td>
              <td>
                <a href="<c:url value='/admin/company'/>?companyid=${c.id}" class="project-link">${c.name}</a>
              </td>
              <td>${c.name}</td>
              <td>${c.email}</td>
              <td>${c.joinDate}</td>
              <td>${c.phone}</td>
              <td><span class="badge 구직자">구직자</span></td>
            </tr>
          </c:if>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <!-- ✅ 구인자 목록 -->
    <div class="card">
      <h2>구인자 목록</h2>
      <table>
        <thead>
        <tr>
          <th class="col-no">번호</th>
          <th class="col-name">회원명</th>
          <th class="col-id">아이디</th>
          <th class="col-email">이메일</th>
          <th class="col-date">가입일</th>
          <th class="col-phone">연락처</th>
          <th class="col-type">구분</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${companyList}">
          <c:if test="${c.type eq '구인자'}">
            <tr>
              <td>${c.id}</td>
              <td>
                <a href="<c:url value='/admin/company'/>?companyid=${c.id}" class="project-link">${c.name}</a>
              </td>
              <td>${c.name}</td>
              <td>${c.email}</td>
              <td>${c.joinDate}</td>
              <td>${c.phone}</td>
              <td><span class="badge 구인자">구인자</span></td>
            </tr>
          </c:if>
        </c:forEach>
        </tbody>
      </table>
    </div>

  </div>
</div>

</body>
</html>