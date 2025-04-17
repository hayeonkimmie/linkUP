<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>문의 내역</title>
  <link rel="stylesheet" href="../css/style.css" />
  <link rel="stylesheet" href="../css/headerSt.css" />
</head>
<body>

<!-- 공통 헤더 -->
<jsp:include page="/common/header.jsp" />

<div class="layout">
  <!-- 공통 사이드바 -->
<jsp:include page="/common/sidebar.jsp" />

  <main class="main">
    <div class="top-bar">
      <h2>문의 내역</h2>
      <button type="button" onclick="toggleModal(true)">+ 문의하기</button>
    </div>

    <!-- 알림 모달 -->
    <div class="alert-modal" id="successModal">
      <div class="alert-content">
        <p>문의가 성공적으로 등록되었습니다.</p>
        <button onclick="closeSuccessModal()">OK</button>
      </div>
    </div>

    <!-- 검색 바 & 필터 -->
    <div class="search-filter-bar">
      <input type="text" placeholder="문의 제목 검색" />
      <div class="filters">
        <select>
          <option>모든 상태</option>
          <option>답변 대기</option>
          <option>답변 완료</option>
        </select>
        <select>
          <option>최신순</option>
          <option>오래된순</option>
        </select>
      </div>
    </div>

    <!-- 문의 카드 목록: 예시 데이터 -->
    <c:forEach var="inquiry" items="${inquiryList}">
      <section class="inquiry-card">
        <div class="inquiry-title">
          ${inquiry.title}
          <span class="badge ${inquiry.status eq '답변 완료' ? 'complete' : 'waiting'}">
            ${inquiry.status}
          </span>
        </div>
        <div class="inquiry-meta">${inquiry.date}</div>
        <div class="inquiry-body">${inquiry.content}</div>

        <c:if test="${inquiry.reply ne null}">
          <div class="admin-reply">
            <div class="reply-title">
              <span>👤 관리자 답변</span>
              <time>${inquiry.replyDate}</time>
            </div>
            ${inquiry.reply}
          </div>
        </c:if>
      </section>
    </c:forEach>

  </main>
</div>

<!-- 문의 등록 모달 -->
<div class="modal" id="inquiryModal">
  <div class="modal-content">
    <button class="modal-close" onclick="toggleModal(false)">&times;</button>
    <h3>문의하기</h3>
    <form action="/submitInquiry" method="post" enctype="multipart/form-data">
      <input type="text" name="title" placeholder="문의 제목을 입력하세요" required>
      <textarea name="content" rows="5" placeholder="문의 내용을 자세히 적어주세요" required></textarea>

      <div class="file-box">
        <img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" width="40" alt="파일 아이콘">
        <p><b>파일 업로드</b> 또는 여기에 파일을 끌어다 놓으세요</p>
        <p>PNG, JPG, PDF 최대 10MB</p>
        <input type="file" name="file" accept=".jpg,.png,.pdf" />
      </div>

      <div class="notice">
        <ul>
          <li>문의 내용은 관리자만 확인할 수 있습니다.</li>
          <li>답변은 보통 1-2일 내에 처리됩니다.</li>
        </ul>
      </div>

      <div class="button-group">
        <button type="button" class="btn-cancel" onclick="toggleModal(false)">취소</button>
        <button type="submit" class="btn-submit">문의 등록하기</button>
      </div>
    </form>
  </div>
</div>

<!-- 스크립트 -->
<script src="../js/inquiry.js"></script>
<script>
  function toggleModal(show) {
    const modal = document.getElementById("inquiryModal");
    modal.style.display = show ? "flex" : "none";
  }

  function closeSuccessModal() {
    document.getElementById("successModal").style.display = "none";
  }
</script>
</body>
</html>
