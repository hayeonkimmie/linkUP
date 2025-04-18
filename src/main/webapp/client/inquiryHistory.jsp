<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>문의 내역</title>
  <link rel="stylesheet" href="${contextPath}/css/headerSt.css" />
  <link rel="stylesheet" href="${contextPath}/css/inquiryHistory.css" />
</head>
<body>
<div class="layout">
  <aside class="sidebar">
    <div class="profile">
      <img src="https://via.placeholder.com/80" alt="프로필 이미지" />
      <p>${nickname}</p>
      <p><a href="/mypage">마이페이지</a></p>
    </div>
    <h3>프로필 설정</h3>
    <h3>프로젝트</h3>
    <ul>
      <li><a href="#">구인 등록</a></li>
      <li><a href="#">프로젝트 조회</a></li>
      <li><a href="#">지원자 관리</a></li>
    </ul>
    <h3><a href="#">찜한 구인자</a></h3>
    <h3><a href="#">리뷰 내역</a></h3>
    <h3><a href="#" class="active">문의 내역</a></h3>
    <h3><a href="#">알림 설정</a></h3>
  </aside>

  <main class="main">
    <div class="top-bar">
      <h2>문의 내역</h2>
      <button type="button" onclick="toggleModal(true)">+ 문의하기</button>

      <!-- 문의 모달 -->
      <div class="modal" id="inquiryModal">
        <div class="modal-content">
          <button class="modal-close" onclick="toggleModal(false)">&times;</button>
          <h3>문의하기</h3>
          <input type="text" id="titleInput" placeholder="문의 제목을 입력하세요">
          <textarea id="contentInput" rows="5" placeholder="문의 내용을 자세히 적어주세요"></textarea>
          <div class="file-box">
            <img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" width="40" alt="파일 아이콘">
            <p><b>파일 업로드</b> 또는 여기에 파일을 끌어다 놓으세요</p>
            <p>PNG, JPG, PDF 최대 10MB</p>
          </div>
          <div class="notice">
            <ul>
              <li>문의 내용은 관리자만 확인할 수 있습니다.</li>
              <li>답변은 보통 1-2일 내에 처리됩니다.</li>
              <li>급한 문의는 고객센터(1234-5678)로 연락해 주세요.</li>
            </ul>
          </div>
          <div class="button-group">
            <button class="btn-cancel" onclick="toggleModal(false)">취소</button>
            <button class="btn-submit" onclick="submitInquiry()">문의 등록하기</button>
          </div>
        </div>
      </div>
    </div>

    <div class="alert-modal" id="successModal">
      <div class="alert-content">
        <p>문의가 성공적으로 등록되었습니다.</p>
        <button onclick="closeSuccessModal()">OK</button>
      </div>
    </div>

    <div class="search-filter-bar">
      <input type="text" placeholder="문의 제목 검색" aria-label="문의 제목 검색" />
      <div class="filters">
        <select aria-label="답변 상태 필터">
          <option>모든 상태</option>
          <option>답변 대기</option>
          <option>답변 완료</option>
        </select>
        <select aria-label="정렬 기준">
          <option>최신순</option>
          <option>오래된순</option>
        </select>
      </div>
    </div>

    <!-- 예시: 서버에서 받아오는 문의 리스트 -->
    <c:forEach var="inquiry" items="${inquiryList}">
      <section class="inquiry-card">
        <div class="inquiry-title">
            ${inquiry.title}
          <span class="badge ${inquiry.status == '답변 완료' ? 'complete' : 'waiting'}">
              ${inquiry.status}
          </span>
        </div>
        <div class="inquiry-meta">${inquiry.date}</div>
        <div class="inquiry-body">${inquiry.content}</div>

        <c:if test="${not empty inquiry.reply}">
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

<script>
  const modal = document.getElementById("inquiryModal");
  const successModal = document.getElementById("successModal");

  function toggleModal(show) {
    modal.style.display = show ? "flex" : "none";
  }

  function submitInquiry() {
    const title = document.getElementById("titleInput").value;
    const content = document.getElementById("contentInput").value;
    if (title.trim() && content.trim()) {
      toggleModal(false);
      successModal.style.display = "flex";
    } else {
      alert("제목과 내용을 입력해주세요.");
    }
  }

  function closeSuccessModal() {
    successModal.style.display = "none";
  }
</script>
</body>
</html>
