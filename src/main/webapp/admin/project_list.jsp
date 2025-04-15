<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>프로젝트 조회</title>
  <link rel="stylesheet" href="../css/admin_header.css">
  <link rel="stylesheet" href="../css/admin_project_list.css">
  <link rel="stylesheet" href="../css/table_common.css">
  <script> const defaultOpenMenuId = "projectMenu"; </script>
  <script src="../js/include_common.js"></script>
</head>

<body>
  <div id="header-include"></div>
  <div class="layout-wrapper">
    <div id="menu-include"></div>
    <div class="content">
      <div class="card">
        <h1>프로젝트 조회</h1>
        <p class="desc">진행되었던 프로젝트 리스트입니다.</p>
        <div class="search-bar">
          <input type="text" placeholder="🔍  프로젝트명, 회사명으로 검색">
          <div class="filter-group">
            <input type="date">
            <span>~</span>
            <input type="date">
            <button>검색</button>
          </div>
        </div>
        <div class="total-count">총 8개의 프로젝트</div>
        <table>
          <thead>
            <tr>
              <th>프로젝트명</th>
              <th>회사명</th>
              <th>담당자</th>
              <th>기간</th>
              <th>연락처</th>
              <th>상태</th>
              <th>정산 예정 금액</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <a href="project_detail.jsp" class="project-link">웹 서비스 리뉴얼</a>
                <!-- <a href="project_detail.jsp?id=123" class="project-link">웹 서비스 리뉴얼</a> -->
              </td>
              <td>
                <a href="company-list?company=" class="company-link">테크놀로지 주식회사</a>
              </td>
              <td>김민수<br></td>
              <td>2023-05-01 ~ 2023-08-31</td>
              <td>010-1234-5678</td>
              <td><span class="badge 진행중">진행중</span></td>
              <td>50,000,000원</td>
            </tr>
            <tr>
              <td>모바일 앱 개발</td>
              <td>글로벌 솔루션즈</td>
              <td>이지은<br></td>
              <td>2023-06-01 ~ 2023-09-30</td>
              <td>010-2345-6789</td>
              <td><span class="badge 구인중">구인중</span></td>
              <td>35,000,000원</td>
            </tr>
            <tr>
              <td>클라우드 마이그레이션</td>
              <td>스마트 시스템즈</td>
              <td>박준호<br></td>
              <td>2023-04-15 ~ 2023-07-31</td>
              <td>010-3456-7890</td>
              <td><span class="badge 진행중">진행중</span></td>
              <td>85,000,000원</td>
            </tr>
            <tr>
              <td>보안 시스템 구축</td>
              <td>세이프티 테크</td>
              <td>최유진<br></td>
              <td>2023-05-20 ~ 2023-08-15</td>
              <td>010-4567-8901</td>
              <td><span class="badge 지연">지연</span></td>
              <td>45,000,000원</td>
            </tr>
            <tr>
              <td>AI 솔루션 개발</td>
              <td>인텔리전스 앱</td>
              <td>정석훈<br></td>
              <td>2023-06-10 ~ 2023-10-31</td>
              <td>010-5678-9012</td>
              <td><span class="badge 진행중">진행중</span></td>
              <td>120,000,000원</td>
            </tr>
            <tr>
              <td>데이터 분석 플랫폼</td>
              <td>데이터 인사이트</td>
              <td>한지원<br></td>
              <td>2023-03-01 ~ 2023-06-30</td>
              <td>010-6789-0123</td>
              <td><span class="badge 완료">완료</span></td>
              <td>65,000,000원</td>
            </tr>
            <tr>
              <td>UI/UX 개선 프로젝트</td>
              <td>디자인 허브</td>
              <td>임수진<br></td>
              <td>2023-05-15 ~ 2023-08-15</td>
              <td>010-7890-1234</td>
              <td><span class="badge 구인중">구인중</span></td>
              <td>25,000,000원</td>
            </tr>
            <tr>
              <td>블록체인 시스템 통합</td>
              <td>넥스트 테크놀로지</td>
              <td>송인재<br></td>
              <td>2023-07-01 ~ 2023-12-31</td>
              <td>010-8901-2345</td>
              <td><span class="badge 구인중">구인중</span></td>
              <td>95,000,000원</td>
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
