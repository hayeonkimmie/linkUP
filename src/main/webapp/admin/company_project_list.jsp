<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>업체 프로젝트 목록</title>
  <link rel="stylesheet" href="../css/admin_header.css" />
  <link rel="stylesheet" href="../css/table_common.css" />
  <link rel="stylesheet" href="../css/company_project_list.css" />
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
            <tr>
              <td>서비스 리뉴얼</td>
              <td>김민수</td>
              <td>2023-05-01 ~ 2023-08-31</td>
              <td><span class="status-badge">진행중</span></td>
              <td>3명</td>
            </tr>
            <tr>
              <td>모바일 앱 개발</td>
              <td>이상철</td>
              <td>2023-06-01 ~ 2023-12-31</td>
              <td><span class="status-badge">진행중</span></td>
              <td>5명</td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="pagination">
        <button disabled>이전</button>
        <button class="curpage">1</button>
        <button>다음</button>
      </div>
    </div>
  </div>
</body>
</html>
