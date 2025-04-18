<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>프로젝트 정산 현황</title>
  <link rel="stylesheet" href="../css/admin_header.css">
  <link rel="stylesheet" href="../css/settlement_info.css">
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
    <h2 class="page-title">프로젝트 정산 현황</h2>

    <div class="filter-bar">
      <select>
        <option selected>2024년 5월</option>
        <option>2024년 4월</option>
        <option>2024년 3월</option>
      </select>
    </div>

    <div class="card summary-box">
      <div class="summary-item">
        <span class="label">프로젝트명</span>
        <span class="value">블록체인 플랫폼 개발</span>
      </div>
      <div class="summary-item">
        <span class="label">전체 계약금액</span>
        <span class="value">30,000,000원</span>
      </div>
      <div class="summary-item">
        <span class="label">프로젝트 기간</span>
        <span class="value">2024.04.01 ~ 2024.06.30</span>
      </div>
      <div class="summary-item">
        <span class="label">총 참여 인원</span>
        <span class="value">5명</span>
      </div>
      <div class="summary-item">
        <span class="label">프로젝트 정산일</span>
        <span class="value">매월 14일</span>
      </div>
      <div class="summary-item">
        <span class="label">미정산 금액</span>
        <span class="value danger">7,200,000원</span>
      </div>
    </div>

    <div class="card">
      <h3>정산 완료 인원</h3>
      <table>
        <thead>
          <tr>
            <th>참여자명</th>
            <th>구분</th>
            <th>참여 기간</th>
            <th>이번달 정산 금액</th>
            <th>상태</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>김철수</td>
            <td>고급 개발자</td>
            <td>3개월</td>
            <td>12,000,000원</td>
            <td><span class="status complete">완료</span></td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="card">
      <h3>정산 대기 인원</h3>
      <table>
        <thead>
          <tr>
            <th>참여자명</th>
            <th>구분</th>
            <th>참여 기간</th>
            <th>이번달 정산 금액</th>
            <th>상태</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>이영희</td>
            <td>중급 개발자</td>
            <td>3개월</td>
            <td>7,200,000원</td>
            <td><span class="status pending">대기중</span></td>
          </tr>
          <tr>
            <td>서바둑</td>
            <td>초급 개발자</td>
            <td>2개월</td>
            <td>3,600,000원</td>
            <td><span class="status pending">대기중</span></td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="footer-total">
      <p>정산된 금액 (총 1명)</p>
      <h3>22,800,000원</h3>
    </div>

  </div>
</div>
</body>
</html>
