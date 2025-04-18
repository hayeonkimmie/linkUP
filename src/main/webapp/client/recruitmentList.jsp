<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>구인 관리</title>
  <link rel="stylesheet" href="${contextPath}/css/style.css" />
  <link rel="stylesheet" href="${contextPath}/css/headerSt.css" />
</head>
<body>

<!--  공통 헤더  -->
<jsp:include page="../common/header.jsp" />

<div class="layout">

  <!-- 공통 사이드바 포함-->
  <jsp:include page="../common/sidebar.jsp" />

  <main class="main">
    <h2 class="section-title">구인 관리</h2>

    <!-- 필터 탭 -->
    <div class="filter-tabs">
      <div class="filter-tab active" data-status="all">전체보기</div>
      <div class="filter-tab" data-status="open">구인중</div>
      <div class="filter-tab" data-status="temp">임시저장</div>
      <div class="filter-tab" data-status="done">구인완료</div>
    </div>

    <!-- 구인 목록 반복 -->
    <c:forEach var="job" items="${recruitmentList}">
      <div class="job-card" data-status="${job.status}">
        <div class="job-card-badge">
          <c:if test="${job.status == 'open'}">
            <span class="badge">지원자 ${job.applicantCount}명</span>
            <span class="badge">구인중</span>
          </c:if>
          <c:if test="${job.status == 'temp'}">
            <span class="badge">임시저장</span>
          </c:if>
          <c:if test="${job.status == 'done'}">
            <span class="badge">구인완료</span>
          </c:if>
          <br><br />
        </div>

        <div class="job-card-header">
          <div class="job-title">${job.title}</div>
          <div class="tags">
            <c:forEach var="tag" items="${job.tags}">
              <span>${tag}</span>
            </c:forEach>
          </div><br />

          <div class="job-meta">${job.category} / ${job.createdAt}</div><br />
          <strong>프로젝트 기간</strong><br />
          ${job.startDate} ~ ${job.endDate}

          <div class="job-card-status">
            <div class="total-price">총 프로젝트 금액 ${job.totalAmount}원</div>

            <c:if test="${job.status == 'done'}">
              <div class="settlement-status">
                <span class="status-label">정산 상태</span>
                <span class="status-value">${job.settlementStatus}</span>
              </div>
            </c:if>
          </div>

          <div class="highlight">구인 인원 및 페이</div>
          <div class="pay-section">
            <c:forEach var="pay" items="${job.payList}">
              <div class="pay-box">
                <h4>${pay.level}</h4>
                <p>${pay.pricePerPerson}원/인</p>
                <p>${pay.peopleCount}명</p>
              </div>
            </c:forEach>
          </div>

          <div class="buttons">
            <c:if test="${job.status == 'done'}">
              <button class="btn btn-settle">정산하기</button>
              <button class="btn btn-review">리뷰작성</button>
            </c:if>
            <c:if test="${job.status != 'done'}">
              <button class="btn btn-delete">삭제하기</button>
              <button class="btn btn-edit">구인인원 및 상태 수정</button>
              <button class="btn btn-edit">수정하기</button>
              <c:if test="${job.status == 'open'}">
                <button class="btn btn-manage">지원자 관리</button>
              </c:if>
            </c:if>
          </div>
        </div>
      </div>
    </c:forEach>
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
