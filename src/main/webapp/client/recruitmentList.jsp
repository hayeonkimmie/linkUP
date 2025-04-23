<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>구인 관리</title>
  <link rel="stylesheet" href="${contextPath}/css/client/style.css" />
  <link rel="stylesheet" href="${contextPath}/css/client/headerSt.css" />
</head>
<body>

<!-- 공통 헤더 include -->
<jsp:include page="../home/header.jsp" />

<div class="layout">
  <!-- 공통 사이드바 include -->
  <jsp:include page="../common/sidebar.jsp" />

  <!-- 메인 컨텐츠 -->
  <main class="main">
    <h2 class="section-title">구인 관리</h2>

    <div class="filter-tabs">
      <div class="filter-tab active" data-status="all">전체보기</div>
      <div class="filter-tab" data-status="open">구인중</div>
      <div class="filter-tab" data-status="done">구인완료</div>
      <div class="filter-tab" data-status="temp">임시저장</div>
    </div>

    <!-- ========== 더미 카드 1: 구인중 ========== -->
    <div class="job-card" data-status="open">
      <div class="job-card-badge">
        <span class="badge">지원자 8명</span>
        <span class="badge">구인중</span><br><br />
      </div>
      <div class="job-card-header">
        <div class="job-title">웹사이트 유지보수</div>
        <div class="tags">
          <span>HTML</span>
          <span>CSS</span>
          <span>JavaScript</span>
        </div><br />
        <div class="job-meta">웹 개발 / 2024.10.01 등록</div><br />
        <strong>프로젝트 기간</strong><br />
        2023.10.15 ~ 2023.12.15
        <div class="job-card-status">
          <div class="total-price">총 프로젝트 금액 2,000,000원</div>
        </div>

        <div class="highlight">구인 인원 및 페이</div>
        <div class="pay-section">
          <div class="pay-box">
            <h4>초급</h4>
            <p>1,000,000원/인</p>
            <p>1명</p>
          </div>
          <div class="pay-box">
            <h4>중급</h4>
            <p>1,500,000원/인</p>
            <p>2명</p>
          </div>
          <div class="pay-box">
            <h4>고급</h4>
            <p>2,000,000원/인</p>
            <p>1명</p>
          </div>
        </div>
        <div class="buttons">
          <button class="btn btn-delete">삭제하기</button>
          <button class="btn btn-edit">구인인원 및 상태 수정</button>
          <button class="btn btn-edit">수정하기</button>
          <button class="btn btn-manage">지원자 관리</button>
        </div>
      </div>
    </div>

    <!-- ========== 더미 카드 2: 임시저장 ========== -->
    <div class="job-card" data-status="temp">
      <div class="job-card-badge">
        <span class="badge">임시저장</span><br><br />
      </div>
      <div class="job-card-header">
        <div class="job-title">백엔드 API 개발</div>
        <div class="tags">
          <span>Python</span>
          <span>Java</span>
        </div><br />
        <div class="job-meta">백엔드 개발 / 2024.10.05 저장</div><br />
        <strong>프로젝트 기간</strong><br />
        2024.10.15 ~ 2024.12.15
        <div class="job-card-status">
          <div class="total-price">총 프로젝트 금액 3,000,000원</div>
        </div>

        <div class="highlight">구인 인원 및 페이</div>
        <div class="pay-section">
          <div class="pay-box">
            <h4>중급</h4>
            <p>1,500,000원/인</p>
            <p>2명</p>
          </div>
        </div>
        <div class="buttons">
          <button class="btn btn-edit">수정하기</button>
          <button class="btn btn-delete">삭제하기</button>
        </div>
      </div>
    </div>

    <!-- ========== 더미 카드 3: 구인완료 ========== -->
    <div class="job-card" data-status="done">
      <div class="job-card-badge">
        <span class="badge">구인완료</span><br><br />
      </div>
      <div class="job-card-header">
        <div class="job-title">웹사이트 유지보수</div>
        <div class="tags">
          <span>HTML</span>
          <span>CSS</span>
        </div><br />
        <div class="job-meta">웹 개발 · 지원자 3명 · 2023.07.20 등록</div>
        <strong>프로젝트 기간</strong><br />
        2023.08.01 ~ 2023.09.01
        <div class="job-card-status">
          <div class="total-price">총 프로젝트 금액 1,000,000원</div>
          <div class="settlement-status">
            <span class="status-label">정산 상태</span>
            <span class="status-value">정산 완료</span>
          </div>
        </div>

        <div class="highlight">구인 인원 및 페이</div>
        <div class="pay-section">
          <div class="pay-box">
            <h4>초급</h4>
            <p>500,000원/인</p>
            <p>2명</p>
          </div>
          <div class="pay-box">
            <h4>중급</h4>
            <p>1,000,000원/인</p>
            <p>1명</p>
          </div>
          <div class="pay-box">
            <h4>고급</h4>
            <p>1,500,000원/인</p>
            <p>1명</p>
          </div>
        </div>
        <div class="buttons">
          <button class="btn btn-settle">정산하기</button>
          <button class="btn btn-review">리뷰작성</button>
        </div>
      </div>
    </div>

  </main>
</div>

<script>
  const tabs = document.querySelectorAll('.filter-tab');
  const cards = document.querySelectorAll('.job-card');

  tabs.forEach(tab => {
    tab.addEventListener('click', () => {
      tabs.forEach(t => t.classList.remove('active'));
      tab.classList.add('active');

      const status = tab.getAttribute('data-status');
      cards.forEach(card => {
        if (status === 'all') {
          card.style.display = 'block';
        } else {
          card.style.display = card.getAttribute('data-status') === status ? 'block' : 'none';
        }
      });
    });
  });
</script>

</body>
</html>
