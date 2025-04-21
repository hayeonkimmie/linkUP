<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
  <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
  <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page_project_review.css'/>">
  <script src="../js/freelancer_my_page_project_review.js"></script>
  <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
<div class="header">
  <!-- 헤더 인클루드 영역 -->
  <jsp:include page="/common/header.jsp"/>
</div>
<div class="container">
  <!-- 사이드바 -->
  <jsp:include page="/freelancer/sidebar.jsp"/>
  <!-- 메인 콘텐츠 -->
  <div class="content">
    <!-- 페이지 제목 -->
    <div class="topbar">
      <h1>프로젝트 후기</h1>
    </div>

    <!-- 탭 메뉴 -->
    <div class="tabs">
      <button class="tab-btn" data-tab="write">후기 작성하기</button>
    </div>

    <!-- 탭 콘텐츠 -->
    <div class="tab-content-container">
      <!-- 후기 작성하기 (form + 아코디언) -->
      <div class="tab-content" id="write">
        <div class="accordion-review">
          <!-- 항목 1 -->
          <form class="accordion-item" action="" method="post">
            <div class="accordion-header">
              <span class="user">구인자3</span>
              <input type="text" value="" style="display: none;" name=""/>
              <span class="project">브랜딩 웹사이트 제작</span>
              <input type="text" value="" style="display: none;" name="project_id"/>
            </div>
            <div class="accordion-body review-form">
              <div class="form-row">
                <label>별점</label>
                <div class="rating-select">
                  <span class="star" data-value="1">★</span>
                  <span class="star" data-value="2">★</span>
                  <span class="star" data-value="3">★</span>
                  <span class="star" data-value="4">★</span>
                  <span class="star" data-value="5">★</span>
                  <input type="number" value="" style="display: none;" name="">
                </div>
              </div>
              <div class="form-row">
                <label>후기 내용</label>
                <textarea rows="5" name="" placeholder="협업 경험에 대해 자유롭게 작성해 주세요."></textarea>
              </div>
              <div class="btn-group right">
                <button type="submit" class="btn light">등록</button>
                <button type="button" class="btn">삭제</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div> <!-- tab-content-container -->
</div> <!-- content -->
</div> <!-- container -->
</body>
</html>