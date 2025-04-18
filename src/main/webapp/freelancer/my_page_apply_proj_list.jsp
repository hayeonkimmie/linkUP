<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>개인 프로젝트 지원 현황</title>
  <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
  <link rel="stylesheet" href="<c:url value='/css/headerSt.css'/>">
  <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
  <link rel="stylesheet" href="<c:url value='/css/freelancer_my_apply_proj_list.css'/>">
  <script src="../js/freelancer_apply_proj_list.js"></script>
</head>

<body>
<div class="header">
  <!-- 헤더 인클루드 영역 -->
  <jsp:include page="/common/header.jsp"/>
</div>
<div class="container">
  <!-- 사이드바 -->
  <jsp:include page="/freelancer/sidebar.jsp"/>
      <main class="content">
        <section class="section">
          <h3>내 프로젝트 지원 현황</h3>
          <p style="color: #888; font-size: 0.9rem; margin-bottom: 1rem;">지원한 프로젝트 목록을 확인하세요</p>

          <table>
            <thead>
            <tr>
              <th>상태</th>
              <th>지원일</th>
              <th colspan="2">프로젝트 정보</th>
              <th>분야</th>
              <th>기간<br><hr/>예산</th>
              <th>요구사항</th>
              <th>모집 마감일<br>D-Day</th>
              <th>승인여부</th>
              <th>지원취소</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td><span class="status ongoing">지원완료</span></td>
              <td>2023년 11월 20일</td>
              <td colspan="2">
                <div class="project-info">
                  <img src="https://images.unsplash.com/photo-1461749280684-dccba630e2f6?w=800&auto=format&fit=crop&q=60" alt="프로젝트 이미지">
                  <div class="project-text">
                    앱 쇼핑몰 리뉴얼<br>
                    <span>퍼블리셔</span>
                  </div>
                </div>
              </td>
              <td>웹 개발</td>
              <td>3개월<br><hr/>2,500만원</td>
              <td>React, TypeScript, 3년 이상</td>
              <td>2024년 5월 15일<br><span class="d-day">D-30</span></td>
              <td style="color:#999;">-</td>
              <td><button class="cancel-btn">지원 취소</button></td>
            </tr>

            <tr>
              <td><span class="status completed" style="background: #e05a5a;">취소</span></td>
              <td>2023년 11월 15일<br><span style="color:red; font-size:0.75rem;">취소: 2023년 11월 25일</span></td>
              <td colspan="2">
                <div class="project-info">
                  <img src="https://images.unsplash.com/photo-1461749280684-dccba630e2f6?w=800&auto=format&fit=crop&q=60" alt="프로젝트 이미지">
                  <div class="project-text">
                    모바일 주문 API 개발<br>
                    <span>백엔드 개발</span>
                  </div>
                </div>
              </td>
              <td>백엔드 개발</td>
              <td>4개월<br><hr/>3,200만원</td>
              <td>Node.js, MongoDB, 5년 이상</td>
              <td>2023년 12월 20일<br><span class="d-day">마감</span></td>
              <td style="color:#999;">-</td>
              <td></td>
            </tr>

            <tr>
              <td><span class="status ongoing">지원완료</span></td>
              <td>2023년 11월 18일</td>
              <td colspan="2">
                <div class="project-info">
                  <img src="https://images.unsplash.com/photo-1461749280684-dccba630e2f6?w=800&auto=format&fit=crop&q=60" alt="프로젝트 이미지">
                  <div class="project-text">
                    데이터 대시보드 구축<br>
                    <span>데이터시각화</span>
                  </div>
                </div>
              </td>
              <td>데이터 시각화</td>
              <td>2개월<br><hr/>1,800만원</td>
              <td>D3.js, React, 2년 이상</td>
              <td>2023년 12월 10일<br><span class="d-day">마감</span></td>
              <td style="color: green; font-weight: bold;">승인 완료</td>
              <td></td>
            </tr>
            </tbody>
          </table>
        </section>
      </main>
    </div>
</body>
</html>