<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Link Up - 포트폴리오 목록</title>
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_like.css'/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../js/freelancer_my_page_like_proj.js"></script>
</head>

<body>
<div class="container">
    <!-- 사이드바 -->
    <aside class="sidebar">
        <div class="profile">
            <img src="../img/basic_profile_img.png" alt=""/>
            <p>닉네임</p>
            <p>마이페이지</p>
        </div>
        <ul>
            <li class="profile-settings">
                <h3>프로필 설정</h3>
                <a href="#">기본 정보 설정</a>
                <a href="#">전문가 정보 설정</a>
            </li>
            <a href="#">포트폴리오</a>
            <a href="#">찜한 프로젝트</a>
            <a href="#">지원한 프로젝트 내역</a>
            <a href="#" class="active">진행중인 / 완료된 프로젝트</a>
            <a href="#">프로젝트 후기</a>
            <a href="#">문의내역</a>
        </ul>
    </aside>
        <!-- 메인 콘텐츠 -->
        <main class="main-content">
            <h2>찜한 프로젝트</h2>
            <p class="subtext">찜한 프로젝트 목록을 확인하세요</p>
            <div>
                <span>정렬</span>
                <ul>
                    <li class="active">찜한 날짜순</li>
                    <li>마감 임박순</li>
                </ul>
            </div>
            <div class="project-table">
                <table>
                    <thead>
                    <tr>
                        <th><input type="checkbox" /></th>
                        <th>프로젝트명</th>
                        <th>분야</th>
                        <th>기간</th>
                        <th>금액</th>
                        <th>클라이언트</th>
                        <th>요구 스킬/경력</th>
                        <th>모집 마감일</th>
                        <th>모집 마감 D-Day</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input type="checkbox" /></td>
                        <td>웹 쇼핑몰 리뉴얼</td>
                        <td>웹 개발</td>
                        <td>3개월</td>
                        <td>협의 후 결정</td>
                        <td>페스토이(주)</td>
                        <td>React, TypeScript, 3년 이상</td>
                        <td>2023.12.15</td>
                        <td class="d-day">D-6</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" /></td>
                        <td class="disabled">모바일 앱 API 개발</td>
                        <td>백엔드 개발</td>
                        <td>4개월</td>
                        <td>3,200만원</td>
                        <td>테크솔루션</td>
                        <td>Node.js, MongoDB, 5년 이상</td>
                        <td>2023.12.20</td>
                        <td class="closed">마감</td>
                    </tr>
                    <!-- 나머지 항목들도 동일하게 반복 -->
                    </tbody>
                </table>
            </div>

            <!-- 삭제 버튼 -->
            <div class="delete-button-wrapper">
                <button class="delete-button">삭제</button>
            </div>

            <!-- 페이지네이션 -->
            <div class="pagination">
                <button class="active">1</button>
                <button>2</button>
                <button>3</button>
                <span>다음</span>
                <span>끝</span>
            </div>
        </main>
        </div>
</body>
</html>
