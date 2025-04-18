<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>프로젝트 정산하기</title>
  <link rel="stylesheet" href="../css/admin_header.css">
  <link rel="stylesheet" href="../css/settlement_detail.css">
  
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
    <h2 class="page-title">프로젝트 정산하기</h2>

    <div class="summary-box">
      <div class="summary-item">
        <span class="label">프로젝트명</span>
        <span class="value">블록체인 플랫폼 개발</span>
      </div>
      <div class="summary-item">
        <span class="label">이번달 정산 금액</span>
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
    </div>

    <h3 class="section-title">정산 인원 & 금액</h3>
    <div class="table-wrapper">
      <table>
        <thead>
        <tr>
          <th>참여자명</th>
          <th>구분</th>
          <th>참여 기간</th>
          <th>전화번호</th>
          <th>입금 계좌</th>
          <th>이번달 정산 금액</th>
          <th>정산 대상</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>김철수</td>
          <td>고급 개발자</td>
          <td>3개월</td>
          <td>010-1234-5678</td>
          <td>국민은행 123456-78-901234</td>
          <td>12,000,000원</td>
          <td><input type="checkbox" checked></td>
        </tr>
        <tr>
          <td>이영희</td>
          <td>중급 개발자</td>
          <td>3개월</td>
          <td>010-2345-6789</td>
          <td>신한은행 234567-89-012345</td>
          <td>7,200,000원</td>
          <td><input type="checkbox"></td>
        </tr>
        <tr>
          <td>박지민</td>
          <td>중급 디자이너</td>
          <td>3개월</td>
          <td>010-3456-7890</td>
          <td>하나은행 345678-90-123456</td>
          <td>3,600,000원</td>
          <td><input type="checkbox"></td>
        </tr>
        <tr>
          <td>박지민</td>
          <td>고급 디자이너</td>
          <td>3개월</td>
          <td>010-4567-8901</td>
          <td>우리은행 456789-01-234567</td>
          <td>3,600,000원</td>
          <td><input type="checkbox"></td>
        </tr>
        <tr>
          <td>서바둑</td>
          <td>초급 개발자</td>
          <td>2개월</td>
          <td>010-5678-9012</td>
          <td>카카오뱅크 567890-12-345678</td>
          <td>3,600,000원</td>
          <td><input type="checkbox"></td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="total-footer">
      <div class="total-text">총합 <strong>12,000,000원</strong> (총 1명)</div>
      <div class="buttons">
        <button class="btn cancel">취소</button>
        <button class="btn confirm">정산 승인</button>
      </div>
    </div>

  </div>
</div>
</body>
</html>
