<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>문의 내역</title>
  <link rel="stylesheet" href="${contextPath}/css/style.css" />
  <link rel="stylesheet" href="${contextPath}/css/headerSt.css" />
  <link rel="stylesheet" href="${contextPath}/css/inquiryHistory.css" />
</head>
<body>
<jsp:include page="../home/header.jsp" />

<div class="layout">
  <!-- 사이드바 -->
  <aside class="sidebar">
    <div class="profile">
      <img src="https://via.placeholder.com/80" alt="프로필 이미지" />
      <p>${nickname}</p>
      <p><a href="${contextPath}/client/profileSetting.jsp">마이페이지</a></p>
    </div>
    <h3>프로필 설정</h3>
    <h3>프로젝트</h3>
    <ul>
      <li><a href="${contextPath}/client/register.jsp">내 프로젝트 조회</a></li>
      <li><a href="${contextPath}/applicant/manage.jsp">지원자 관리</a></li>
    </ul>
    <h3><a href="${contextPath}/bookmark.jsp">찜한 구인자</a></h3>
    <h3><a href="${contextPath}/review/history.jsp">리뷰 내역</a></h3>
    <h3><a href="${contextPath}/clientQnA" class="active">문의 내역</a></h3>
    <h3><a href="${contextPath}/alarm/setting.jsp">알림 설정</a></h3>
  </aside>

  <!-- 본문 -->
  <main class="main">
    <div class="top-bar">
      <h2>문의 내역</h2>
      <a href="${contextPath}/home/Q&A.jsp" class="btn-submit">+ 문의하기</a>
    </div>

    <!-- 검색 & 필터 바 -->
    <form method="get" action="clientQnA" id="filterForm" class="search-filter-bar">
      <input
              type="text"
              name="keyword"
              value="${keyword}"
              placeholder="문의 제목 검색"
              aria-label="문의 제목 검색"
              style="margin-right: 10px;"
      />

      <div class="filters" style="display: flex; gap: 10px;">
        <select name="status" onchange="document.getElementById('filterForm').submit()" aria-label="답변 상태 필터">
          <option value="" ${empty status ? 'selected' : ''}>모든 상태</option>
          <option value="waiting" ${status == 'waiting' ? 'selected' : ''}>답변 대기</option>
          <option value="complete" ${status == 'complete' ? 'selected' : ''}>답변 완료</option>
        </select>

        <select name="sort" onchange="document.getElementById('filterForm').submit()" aria-label="정렬 기준">
          <option value="desc" ${sort == 'desc' || empty sort ? 'selected' : ''}>최신순</option>
          <option value="asc" ${sort == 'asc' ? 'selected' : ''}>오래된순</option>
        </select>
      </div>
    </form>

    <!-- QnA 카드 목록 -->
    <c:forEach var="qna" items="${qnaList}">
      <section class="inquiry-card">
        <div class="inquiry-title">
            ${qna.questionTitle}
          <c:choose>
            <c:when test="${not empty qna.answerContent}">
              <span class="badge complete">답변 완료</span>
            </c:when>
            <c:otherwise>
              <span class="badge waiting">답변 대기</span>
            </c:otherwise>
          </c:choose>
        </div>
        <div class="inquiry-meta"><fmt:formatDate value="${qna.questionDate}" pattern="yyyy-MM-dd" /></div>
        <div class="inquiry-body">${qna.questionContent}</div>

        <c:if test="${not empty qna.answerContent}">
          <div class="admin-reply">
            <div class="reply-title">
              <span>👤 관리자 답변</span>
              <time><fmt:formatDate value="${qna.answerDate}" pattern="yyyy-MM-dd" /></time>
            </div>
              ${qna.answerContent}
          </div>
        </c:if>
      </section>
    </c:forEach>

    <!-- 페이징 -->
    <div class="pagination">
      <c:choose>
        <c:when test="${pageInfo.curPage > 1}">
          <a href="clientQnA?page=${pageInfo.curPage - 1}&status=${status}&sort=${sort}&keyword=${keyword}">&lt;</a>
        </c:when>
        <c:otherwise>
          <a class="disabled">&lt;</a>
        </c:otherwise>
      </c:choose>

      <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="page">
        <c:choose>
          <c:when test="${page eq pageInfo.curPage}">
            <a href="clientQnA?page=${page}&status=${status}&sort=${sort}&keyword=${keyword}" class="select">${page}</a>
          </c:when>
          <c:otherwise>
            <a href="clientQnA?page=${page}&status=${status}&sort=${sort}&keyword=${keyword}">${page}</a>
          </c:otherwise>
        </c:choose>
      </c:forEach>

      <c:choose>
        <c:when test="${pageInfo.curPage < pageInfo.allPage}">
          <a href="clientQnA?page=${pageInfo.curPage + 1}&status=${status}&sort=${sort}&keyword=${keyword}">&gt;</a>
        </c:when>
        <c:otherwise>
          <a class="disabled">&gt;</a>
        </c:otherwise>
      </c:choose>
    </div>
  </main>
</div>
</body>
</html>
