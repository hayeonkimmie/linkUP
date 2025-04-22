<%--
  Author: 이원영
  Date: 25. 4. 14.
  Time: 오후 2:10
  Description: 특정한 Client, 업체가 LinkUp에서 진행중이거나 완료된 프로젝트 목록 조회
  Read Data :
  * List<AdminProject> ongoingProjectList
  사용 서블릿 :
  ClientListController.java // admin/client-list
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>업체 프로젝트 목록</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css" />
  <link rel="stylesheet" href="../css/table_common.css" />
  <link rel="stylesheet" href="../css/admin/company_project_list.css" />
  <script>
    const defaultOpenMenuId = "projectMenu";
  </script>
  <script src="../js/include_common.js"></script>

</head>
<body>
  <div id="header-include"></div>
  <div class="layout-wrapper">
    <div id="menu-include"></div>
    <div class="content">
      <div class="page-header">
        <h2>㈜테크놀로지 주식회사<br><span style="font-size:14px; font-weight:normal;">프로젝트 목록</span></h2>
      </div>

      <!-- 검색 필터 바 -->
      <form class="search-bar" onsubmit="event.preventDefault(); /* TODO: 검색 동작 구현 예정 */">
        <input type="text" placeholder="🔍 프로젝트명 검색">
        <select>
          <option>전체 상태</option>
          <option>진행중</option>
          <option>완료</option>
          <option>대기</option>
        </select>
        <button type="submit">검색</button>
      </form>

      <div class="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>프로젝트명</th>
              <th>담당자</th>
              <th>기간</th>
              <th>상태</th>
              <th>참여인원</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="p" items="${ongoingProjectList}">
            <tr>
              <td>${p.projectName}</td>
              <td>${p.projectManager}</td>
              <td>${p.projectDuration}</td>
              <td><span class="status-badge">${p.settleStatus}</span></td>
              <td>${p.participant}명</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>

        <!-- 페이지네이션 -->
      <div class="pagination">
        <button disabled>이전</button>
        <button class="curpage">1</button>
        <button>다음</button>
      </div>
    </div>
  </div>
</body>
</html>
