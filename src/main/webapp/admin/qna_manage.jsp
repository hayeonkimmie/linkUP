<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>문의사항 관리</title>
  <link rel="stylesheet" href="../css/admin/admin_header.css" />
  <link rel="stylesheet" href="../css/admin/qna_manage.css" />
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
    <h2 class="page-title">문의사항 관리</h2>

    <!-- ✅ 통계 카드 -->
    <div class="card-stats-row">
      <div class="stat-card">
        <div class="stat-label">전체 문의</div>
        <div class="stat-value">${totalCount}</div>
      </div>
      <div class="stat-card">
        <div class="stat-label">답변 완료</div>
        <div class="stat-value answered">
          <c:set var="answeredCount" value="0" />
          <c:forEach var="q" items="${qnaList}">
            <c:if test="${q.answerContent != null && q.answerContent != '(답변대기 중)'}">
              <c:set var="answeredCount" value="${answeredCount + 1}" />
            </c:if>
          </c:forEach>
          ${answeredCount}
        </div>
      </div>
      <div class="stat-card">
        <div class="stat-label">미답변 문의</div>
        <div class="stat-value danger">
          <c:set var="notAnsweredCount" value="${totalCount - answeredCount}" />
          ${notAnsweredCount}
        </div>
      </div>
    </div>

    <!-- ✅ 검색/필터 영역 -->
    <div class="qna-filter card">
      <form method="get" action="<c:url value='/admin/qna'/>" class="filter-row">
        <input type="date" name="startDate" value="${param.startDate}">
        <span>~</span>
        <input type="date" name="endDate" value="${param.endDate}">
        <select name="category">
          <option value="">전체</option>
          <option value="채용문의" ${param.category == '채용문의' ? 'selected' : ''}>채용문의</option>
          <option value="기술문의" ${param.category == '기술문의' ? 'selected' : ''}>기술문의</option>
        </select>
        <select name="answerStatus">
          <option value="">전체</option>
          <option value="답변완료" ${param.answerStatus == '답변완료' ? 'selected' : ''}>답변완료</option>
          <option value="미답변" ${param.answerStatus == '미답변' ? 'selected' : ''}>미답변</option>
        </select>
        <div class="search-box">
          <input type="text" name="keyword" placeholder="검색어를 입력하세요" value="${param.keyword}">
          <button type="submit">🔍</button>
        </div>
      </form>
    </div>

    <!-- ✅ 삭제용 form (테이블까지만 감싼다) -->
    <form method="post" action="<c:url value='/admin/qna/delete'/>" id="deleteForm">
      <div class="qna-table card">
        <div class="table-header">
<%--          <button type="submit" class="delete-btn">선택 삭제</button>--%>
          <span class="total-count">총 ${totalCount}건</span>
        </div>
        <table>
          <thead>
          <tr>
            <th><input type="checkbox" id="selectAll" /></th>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>상태</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="q" items="${qnaList}">
            <tr>
              <td><input type="checkbox" name="qnaIds" value="${q.qnaId}" /></td>
              <td>${q.qnaId}</td>
              <td class="q-title">
                <a href="<c:url value='/admin/qnadetail'/>?qnaId=${q.qnaId}">
                    ${q.questionTitle}
                </a>
              </td>
              <td>${q.userId}</td>
              <td><fmt:formatDate value="${q.questionDate}" pattern="yyyy-MM-dd" /></td>
              <td>
                <c:choose>
                  <c:when test="${q.answerContent != null && q.answerContent != '(답변대기 중)'}">
                    <span class="badge answered">답변완료</span>
                  </c:when>
                  <c:otherwise>
                    <span class="badge danger">미답변</span>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </form> <!-- ❗ form 닫기 -->

    <!-- ✅ 페이지네이션은 form 밖에서 독립 -->
    <div class="pagination">
      <c:if test="${pageInfo.curPage > 1}">
        <c:url var="prevUrl" value="/admin/qna">
          <c:param name="page" value="${pageInfo.curPage - 1}" />
          <c:if test="${param.keyword != null}"><c:param name="keyword" value="${param.keyword}" /></c:if>
          <c:if test="${param.startDate != null}"><c:param name="startDate" value="${param.startDate}" /></c:if>
          <c:if test="${param.endDate != null}"><c:param name="endDate" value="${param.endDate}" /></c:if>
          <c:if test="${param.category != null}"><c:param name="category" value="${param.category}" /></c:if>
          <c:if test="${param.answerStatus != null}"><c:param name="answerStatus" value="${param.answerStatus}" /></c:if>
        </c:url>
        <button onclick="location.href='${prevUrl}'">이전</button>
      </c:if>

      <c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
        <c:url var="pageUrl" value="/admin/qna">
          <c:param name="page" value="${i}" />
          <c:if test="${param.keyword != null}"><c:param name="keyword" value="${param.keyword}" /></c:if>
          <c:if test="${param.startDate != null}"><c:param name="startDate" value="${param.startDate}" /></c:if>
          <c:if test="${param.endDate != null}"><c:param name="endDate" value="${param.endDate}" /></c:if>
          <c:if test="${param.category != null}"><c:param name="category" value="${param.category}" /></c:if>
          <c:if test="${param.answerStatus != null}"><c:param name="answerStatus" value="${param.answerStatus}" /></c:if>
        </c:url>
        <c:choose>
          <c:when test="${i == pageInfo.curPage}">
            <button class="curpage" disabled>${i}</button>
          </c:when>
          <c:otherwise>
            <button onclick="location.href='${pageUrl}'">${i}</button>
          </c:otherwise>
        </c:choose>
      </c:forEach>

      <c:if test="${pageInfo.curPage < pageInfo.allPage}">
        <c:url var="nextUrl" value="/admin/qna">
          <c:param name="page" value="${pageInfo.curPage + 1}" />
          <c:if test="${param.keyword != null}"><c:param name="keyword" value="${param.keyword}" /></c:if>
          <c:if test="${param.startDate != null}"><c:param name="startDate" value="${param.startDate}" /></c:if>
          <c:if test="${param.endDate != null}"><c:param name="endDate" value="${param.endDate}" /></c:if>
          <c:if test="${param.category != null}"><c:param name="category" value="${param.category}" /></c:if>
          <c:if test="${param.answerStatus != null}"><c:param name="answerStatus" value="${param.answerStatus}" /></c:if>
        </c:url>
        <button onclick="location.href='${nextUrl}'">다음</button>
      </c:if>
    </div>

  </div>
</div>

<script>
  document.addEventListener("DOMContentLoaded", function() {
    const selectAll = document.getElementById("selectAll");
    const checkboxes = document.querySelectorAll("input[name='qnaIds']");

    selectAll.addEventListener("change", function() {
      checkboxes.forEach(cb => cb.checked = selectAll.checked);
    });
  });
</script>

</body>
</html>
