<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>테크놀로지 주식회사</title>
  <link rel="stylesheet" href="../css/CompanySearch.css">
</head>
<body>

<div id="header-placeholder"></div>


<div class="container">
  <nav class="breadcrumb">테크놀로지 주식회사 검색결과 &gt; 테크놀로지 주식회사</nav>

  <header class="company-header">
    <div>
      <h1>테크놀로지 주식회사</h1>
      <p>업종 : 소프트웨어 개발<br>
        업태 : 서비스 업<br>
        대표자명 : 김민석 | 설립일자 2020년 1월 1일<br>
        사업자등록 번호 : 123-12-12345</p>
    </div>
    <img src="../img/회사사진.png" alt="회사 로고">
  </header>

  <section class="company-intro">
    <h2>회사 소개</h2>
    <p>우리 회사는 혁신적인 IT 솔루션을 제공하는 기업입니다. 최신 기술과 창의적인 아이디어로 고객의 비즈니스를 성공으로 이끌어 드립니다.</p>
  </section>

  <section class="contact-info">
    <h2>연락처 정보</h2>
    <div class="contact-grid">
      <div>
        <p><strong>본사 주소 :</strong> 서울특별시 강남구 테헤란로 123 테크빌딩 15층</p>
        <p><strong>대표번호 :</strong> 02-1234-5577</p>
        <p><strong>인사담당 전화번호 :</strong> 02-1231-1231</p>
      </div>
      <div>
        <p><strong>이메일 :</strong> avsad@gamil.com</p>
        <p><strong>FAX :</strong> 02-1235-1235</p>
        <p><strong>회사페이지 :</strong> https://www.company.com</p>
      </div>
    </div>
  </section>

  <section class="projects">
    <h2>프로젝트</h2>
    <div class="search-bar">
      <input type="text" placeholder="프로젝트명 검색">
      <select>
        <option>전체 상태</option>
        <option>진행중</option>
        <option>완료</option>
      </select>
    </div>

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
        <td class="status active">진행중</td>
        <td>3명</td>
      </tr>
      <tr>
        <td>모바일 앱 개발</td>
        <td>이성철</td>
        <td>2023-06-01 ~ 2023-12-31</td>
        <td class="status active">진행중</td>
        <td>5명</td>
      </tr>
      <tr>
        <td>서비스 리뉴얼</td>
        <td>김민수</td>
        <td>2023-05-01 ~ 2023-08-31</td>
        <td class="status ended">완료</td>
        <td>3명</td>
      </tr>
      <tr>
        <td>모바일 앱 개발</td>
        <td>이성철</td>
        <td>2023-06-01 ~ 2023-12-31</td>
        <td class="status ended">완료</td>
        <td>5명</td>
      </tr>
      </tbody>
    </table>
  </section>
</div>
<script>
  document.addEventListener('DOMContentLoaded', function () {
    const statusFilter = document.querySelectorAll('.search-bar select')[0];
    const rows = document.querySelectorAll('table tbody tr');

    statusFilter.addEventListener('change', function () {
      const selectedStatus = statusFilter.value;

      rows.forEach(row => {
        const statusText = row.querySelector('.status').textContent.trim();

        if (selectedStatus === '전체 상태' || statusText === selectedStatus) {
          row.style.display = '';
        } else {
          row.style.display = 'none';
        }
      });
    });
  });
</script>

<script src="../js/header.js"></script>
</body>
</html>

