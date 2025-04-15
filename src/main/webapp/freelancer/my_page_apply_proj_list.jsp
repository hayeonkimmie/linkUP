<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>개인 프로젝트 지원 현황</title>
  <link rel="stylesheet" href="./../css/freelancer_my_page.css"/>
  <link rel="stylesheet" href="./../css/freelancer_my_apply_proj_list.css">
  <script src="./../js/freelancer_apply_proj_list.js"></script>

</head>

<body>
<div class="container">
    <aside class="sidebar">
      <div class="profile">
        <img src="./../img/basic_profile_img.png" alt=" " />
        <p>닉네임</p>
        <p>마이페이지</p>
      </div>
      <ul>
        <ul class="profile-settings">
          <h3>프로필 설정</h3>
          <li>
            <a href="#">기본 정보 설정</a>
          </li>
          <li>
            <a href="#">전문가 정보 설정</a>
          </li>
        </ul>
        <li><a href="#">포트폴리오</a></li>
        <li><a href="#">찜한 프로젝트</a></li>
        <li><a href="#">지원한 프로젝트 내역</a></li>
        <li class="active"><a href="#">진행중인 / 완료된 프로젝트</a></li>
        <li><a href="#">프로젝트 후기</a></li>
        <li><a href="#">문의내역</a></li>
      </ul>
    </aside>
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