<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>문의사항 관리</title>
  <link rel="stylesheet" href="../css/admin_header.css" />
  <link rel="stylesheet" href="../css/qna_manage.css" />
  <script>
    const defaultOpenMenuId = "qaMenu";
  </script>
  <script src="../js/include_common.js"></script>
</head>
<body>
  <div id="header-include"></div>
  <div class="layout-wrapper">
    <div id="menu-include"></div>
    <div class="content">
      <h2 class="page-title">문의사항 관리</h2>

      <div class="card-stats-row">
        <div class="stat-card">
          <div class="stat-label">전체 문의</div>
          <div class="stat-value">1</div>
        </div>
        <div class="stat-card">
          <div class="stat-label">답변 완료</div>
          <div class="stat-value answered">1</div>
        </div>
        <div class="stat-card">
          <div class="stat-label">미답변 문의</div>
          <div class="stat-value danger">1</div>
        </div>
      </div>

      <div class="qna-filter card">
        <div class="filter-row">
          <input type="date">
          <span>~</span>
          <input type="date">
          <select>
            <option>전체</option>
            <option>채용문의</option>
            <option>기술문의</option>
          </select>
          <select>
            <option>전체</option>
            <option>답변완료</option>
            <option>미답변</option>
          </select>
          <div class="search-box">
            <input type="text" placeholder="검색어를 입력하세요">
            <button>🔍</button>
          </div>
        </div>
      </div>

      <div class="qna-table card">
        <div class="table-header">
          <button class="delete-btn">선택 삭제</button>
          <span class="total-count">총 2건</span>
        </div>
        <table>
          <thead>
            <tr>
              <th><input type="checkbox" /></th>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>작성일</th>
              <th>상태</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><input type="checkbox" /></td>
              <td>1</td>
              <td class="q-title"><a href=qna_detail.jsp>채용 공고 등록 방법 문의</a></td>
              <td>홍길동</td>
              <td>2024-02-20</td>
              <td><span class="badge danger">미답변</span></td>
            </tr>
          </tbody>
        </table>
        <div class="pagination">
          <button disabled>이전</button>
          <button class="curpage">1</button>
          <button>다음</button>
        </div>
      </div>
    </div>
  </div>
</body>
</html>