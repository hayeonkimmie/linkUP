<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>문의 상세 내역</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css">
  <link rel="stylesheet" href="../css/admin/qna_detail.css">
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
    <h1 class="page-title">문의 상세 내역</h1>

    <div class="card detail-box">
      <div class="detail-row">
        <div><strong>카테고리:</strong> 채용문의</div>
        <div><strong>작성자:</strong> 홍길동</div>
        <div><strong>작성일:</strong> 2024-02-20</div>
        <div><strong>상태:</strong> <span class="status-label">미답변</span></div>
      </div>

      <div class="question-section">
        <h3>채용 공고 등록 방법 문의</h3>
        <p>
          안녕하세요. 채용 공고를 등록하고 싶은데 관리자 페이지에서 어떤 메뉴를 통해 등록할 수 있나요?
          자세한 절차가 궁금합니다.
        </p>
      </div>

      <div class="answer-section">
        <label for="answer">답변 작성</label>
        <textarea id="answer" placeholder="답변을 입력하세요..."></textarea>
        <div class="actions">
          <button class="btn cancel">취소</button>
          <button class="btn confirm">답변 등록</button>
        </div>
      </div>
    </div>

  </div>
</div>
</body>
</html>
