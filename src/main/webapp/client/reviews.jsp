<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>리뷰 내역</title>
  <link rel="stylesheet" href="<c:url value='/css/headerSt.css' />" />
  <link rel="stylesheet" href="<c:url value='/css/style.css' />" />
  <link rel="stylesheet" href="<c:url value='/css/reviewMgt.css' />" />
</head>
<body>
  <%-- 공통 헤더 (선택) --%>
  <jsp:include page="../common/header.jsp" />

  <div class="layout">
    <!-- 사이드바 -->
    <aside class="sidebar">
      <img src="https://via.placeholder.com/80" alt="프로필 사진">
      <h2>닉네임</h2>
      <nav>
        <a href="#">프로필 설정</a>
        <a href="#">프로젝트</a>
        <a href="#">찜한 구인자</a>
        <a href="#" class="active">리뷰 내역</a>
        <a href="#">문의 내역</a>
        <a href="#">알림 설정</a>
      </nav>
    </aside>

    <!-- 본문 -->
    <main class="main">
      <h1>리뷰 내역</h1>

      <!-- 리뷰 통계 -->
      <div class="review-stats">
        <div class="review-box box-received">
            <span class="label">받은 리뷰</span><br>
            <span class="value">2</span>
        </div>
        <div class="review-box box-written">
            <span class="label">작성한 리뷰</span><br>
            <span class="value">2</span>
        </div>
        <div class="review-box box-score">
            <span class="label">평균 평점</span><br>
            <span class="value">4.5/5.0</span>
        </div>
      </div>

      <!-- 탭 메뉴 -->
      <div class="tabs">
        <button class="active" onclick="openTab(event, 'tab1')">받은 리뷰</button>
        <button onclick="openTab(event, 'tab2')">작성한 리뷰</button>
        <button onclick="openTab(event, 'tab3')">리뷰 작성하기</button>
      </div>

      <!-- 탭 내용 -->
      <div id="tab1" class="tab-content active">
        <%-- 받은 리뷰 1 --%>
        <div class="review-card">
          <div class="review-header">
            <div class="left">
              <img class="profile-img" src="https://randomuser.me/api/portraits/men/75.jpg" alt="이개발">
              <div class="profile-info">
                <strong class="reviewer-name">이개발</strong>
                <div class="review-job">반응형 웹사이트 개발</div>
              </div>
            </div>
            <div class="date">2023.09.30</div>
          </div>
          <div class="stars">⭐⭐⭐⭐⭐ <span class="score">5.0</span></div>
          <div class="review-text">
            프로젝트 요구사항을 명확히 설명해주시고 작업 과정에서 필요한 피드백을 적시에 제공해주셔서 원활하게 프로젝트를 진행할 수 있었습니다.
            또한 작업 완료 후 정산도 빠르게 처리해주셔서 감사합니다.
          </div>
        </div>

        <%-- 받은 리뷰 2 --%>
        <div class="review-card">
          <div class="review-header">
            <div class="left">
              <img class="profile-img" src="https://randomuser.me/api/portraits/women/65.jpg" alt="김디자인">
              <div class="profile-info">
                <strong class="reviewer-name">김디자인</strong>
                <div class="review-job">로고 디자인 및 브랜딩</div>
              </div>
            </div>
            <div class="date">2023.08.15</div>
          </div>
          <div class="stars">⭐⭐⭐⭐☆ <span class="score">4.0</span></div>
          <div class="review-text">
            전반적으로 만족스러운 협업이었습니다. 다만 초기 요구사항이 중간에 조금 변경되어 작업 일정이 지연되었던 점이 아쉬웠습니다.
            하지만 원활한 소통과 합리적인 피드백으로 결과물은 만족스럽게 완성되었습니다.
          </div>
        </div>
      </div>

      <!-- 작성한 리뷰 탭 -->
      <div id="tab2" class="tab-content">
        <div class="review-card">
          <div class="review-header">
            <div class="left">
              <img class="profile-img" src="https://randomuser.me/api/portraits/men/30.jpg" alt="박백엔드">
              <div class="profile-info">
                <strong class="reviewer-name">박백엔드</strong>
                <div class="review-job">서버 개발 프로젝트</div>
                <div class="stars">⭐⭐⭐⭐⭐</div>
              </div>
            </div>
            <div class="date">2023-04-10</div>
          </div>
          <div class="review-text">기술력이 뛰어나고 일정 관리도 철저했습니다. 다음에도 함께 일하고 싶습니다.</div>
          <div class="review-buttons">
            <button class="edit">수정</button>
            <button class="delete">삭제</button>
          </div>
        </div>

        <div class="review-card">
          <div class="review-header">
            <div class="left">
              <img class="profile-img" src="https://randomuser.me/api/portraits/women/13.jpg" alt="최풀스택">
              <div class="profile-info">
                <strong class="reviewer-name">최풀스택</strong>
                <div class="review-job">웹 애플리케이션 개발</div>
                <div class="stars">⭐⭐⭐⭐☆</div>
              </div>
            </div>
            <div class="date">2023-02-15</div>
          </div>
          <div class="review-text">빠른 개발 속도와 높은 품질을 보여주셨습니다. 일부 커뮤니케이션 부분이 아쉬웠지만 전반적으로 만족스러웠습니다.</div>
          <div class="review-buttons">
            <button class="edit">수정</button>
            <button class="delete">삭제</button>
          </div>
        </div>
      </div>

      <!-- 리뷰 작성하기 탭 -->
      <div id="tab3" class="tab-content">
        <div class="review-write-area">
          <div class="review-header">
            <div class="left">
              <img class="profile-img" src="https://randomuser.me/api/portraits/men/55.jpg" alt="정기희">
              <div class="profile-info">
                <strong class="reviewer-name">정기희</strong>
                <div class="review-job">마케팅 웹사이트 개발</div>
                <div class="date">완료일: 2023-04-01</div>
              </div>
            </div>
          </div>

          <div style="margin-top: 10px;">
            <label><strong>평점</strong></label><br>
            <span class="stars">☆ ☆ ☆ ☆ ☆</span>
          </div>

          <div style="margin-top: 10px;">
            <label><strong>리뷰 내용</strong></label>
            <textarea placeholder="협업 경험에 대한 리뷰를 작성해주세요..."></textarea>
          </div>

          <div style="text-align: right;">
            <button class="review-submit">리뷰 작성</button>
          </div>
        </div>
      </div>
    </main>
  </div>

  <script>
    function openTab(evt, tabId) {
      document.querySelectorAll('.tab-content').forEach(t => t.classList.remove('active'));
      document.querySelectorAll('.tabs button').forEach(b => b.classList.remove('active'));
      document.getElementById(tabId).classList.add('active');
      evt.currentTarget.classList.add('active');
    }
  </script>
</body>
</html>
