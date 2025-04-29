<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>문의 상세 내역</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css" />
  <link rel="stylesheet" href="../css/admin/qna_manage.css" />
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
    <h2 class="page-title">문의 상세 내역</h2>

    <!-- ✅ 문의 상세 카드 -->
    <div class="card detail-box">
      <div class="detail-row">
        <div><strong>카테고리:</strong> <span>채용문의</span></div> <%-- (현재 고정) --%>
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

      <div class="question-section">
        <h3>${qna.questionTitle}</h3>
        <p><pre>${qna.questionContent}</pre></p>
      </div>

      <!-- ✅ 답변 영역 -->
      <div class="answer-section">
        <form method="post" action="<c:url value='/admin/qna/answer'/>">
          <input type="hidden" name="qnaId" value="${qna.qnaId}" />
          <label for="answer">답변 작성</label>
          <textarea id="answer" name="answerContent" placeholder="답변을 입력하세요..." rows="8">
<c:if test="${qna.answerContent != '(답변대기 중)'}">${qna.answerContent}</c:if>
          </textarea>

          <div class="actions">
            <button type="button" class="btn cancel" onclick="location.href='<c:url value='/admin/qna'/>'">목록으로</button>
            <button type="submit" class="btn confirm">답변 저장</button>
          </div>
        </form>
      </div>

    </div> <!-- card 끝 -->

  </div> <!-- content 끝 -->
</div> <!-- layout-wrapper 끝 -->

</body>
</html>