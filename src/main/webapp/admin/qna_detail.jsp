<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>문의 상세 내역</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css" />
  <link rel="stylesheet" href="../css/admin/qna_detail.css" />
  <script src="../js/include_common.js"></script>
  <script>
  const defaultOpenMenuId = "qaMenu";
  </script>
</head>

<body>
<div id="header-include"></div>

<div class="layout-wrapper">
  <div id="menu-include"></div>

  <div class="content">

    <!-- ✅ card 안에 타이틀 + 전체 내용 포함 -->
    <div class="card detail-page">

      <!-- 타이틀 -->
      <h2 class="page-title">문의 상세 내역</h2>

      <!-- 문의 정보 -->
      <div class="detail-info">
        <div><strong>작성자:</strong> <span>${qna.userId}</span></div>
        <div><strong>작성일:</strong> <span><fmt:formatDate value="${qna.questionDate}" pattern="yyyy-MM-dd" /></span></div>
        <div><strong>상태:</strong>
          <span class="status-label">
            <c:choose>
              <c:when test="${qna.answerContent != null && qna.answerContent != '(답변대기 중)'}">답변완료</c:when>
              <c:otherwise>미답변</c:otherwise>
            </c:choose>
          </span>
        </div>
      </div>

      <!-- 질문 제목 + 내용 -->
      <div class="question-section">
<%--        <h3 class="question-title">${qna.questionTitle}</h3>--%>
        <h3 class="question-title">질문 제목</h3>
        <pre class="question-content">${qna.questionContent}</pre>
      </div>

      <!-- 답변 작성 폼 -->
      <div class="answer-section">
        <form method="post" action="<c:url value='/admin/qna-answer'/>">
          <input type="hidden" name="qnaId" value="${qna.qnaId}" />
          <label for="answer" class="answer-label">답변 작성</label>
          <textarea id="answer" name="answerContent" placeholder="답변을 입력하세요..." rows="8" class="answer-textarea">
<c:if test="${qna.answerContent != '(답변대기 중)'}">${qna.answerContent}</c:if>
          </textarea>

          <div class="actions">
            <button type="button" class="btn cancel" onclick="location.href='<c:url value='/admin/qna'/>'">취소</button>
            <button type="submit" class="btn confirm">답변 등록</button>
          </div>
        </form>
      </div>

    </div> <!-- card 끝 -->

  </div> <!-- content 끝 -->
</div> <!-- layout-wrapper 끝 -->


</body>
</html>