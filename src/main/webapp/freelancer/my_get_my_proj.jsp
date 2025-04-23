<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_get_my_proj.css'/>">
    <script src="../js/freelancer_my_get_my_proj.js"></script>
</head>
<body>
<div class="header" style="height: 100px;">
    <!-- 헤더 인클루드 영역 -->
</div>
<div class="container">
    <aside class="sidebar">
        <div class="profile">
            <img src="./../img/basic_profile_img.png" alt="profile"/>
            <p>닉네임</p>
            <p>마이페이지</p>
        </div>
        <ul>
            <li class="profile-settings">
                <h3>프로필 설정</h3>
                <a href="#">기본 정보 설정</a>
                <a href="#">전문가 정보 설정</a>
            </li>
            <li><h3><a href="#">포트폴리오</a></h3></li>
            <li><h3><a href="#">찜한 프로젝트</a></h3></li>
            <li><h3><a href="#">지원한 프로젝트 내역</a></h3></li>
            <li><h3><a href="#">진행중인 / 완료된 프로젝트</a></h3></li>
            <li><h3><a href="#">프로젝트 후기</a></h3></li>
            <li><h3><a href="#">문의내역</a></h3></li>
        </ul>
    </aside>
    <main class="content">
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h2>내 프로젝트 현황</h2>
                    <p>지원한 프로젝트 목록을 확인하세요</p>
                </div>
            </div>

            <div class="tabs">
                <div class="tab active" onclick="switchTab('ongoing')">진행중인 프로젝트 <span>(2)</span></div>
                <div class="tab" onclick="switchTab('completed')">완료된 프로젝트 <span>(2)</span></div>
            </div>

            <!--진행중인 프로젝트-->
            <div id="ongoing" class="tab-content">
                <table>
                    <thead>
                    <tr>
                        <th>프로젝트 정보</th>
                        <th>분야</th>
                        <th>기간</th>
                        <th>예산</th>
                        <th>요구사항</th>
                        <th>마감일</th>
                        <th>정산내역 확인</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="clickable" onclick="toggleDetails(this)">
                        <td>반응형 웹사이트 리디자인</td>
                        <td>UI/UX 디자인</td>
                        <td>2개월</td>
                        <td>800만원</td>
                        <td>React, TypeScript, 3년 이상</td>
                        <td>2024년 12월 31일</td>
                        <td>
                            <button class="settlement-btn">정산내역 확인</button>
                        </td>
                    </tr>
                    <tr class="accordion-row">
                        <td colspan="7">프로젝트 소개<br>신규 모바일 앱을 위한 RESTful API 개발 프로젝트입니다.</td>
                    </tr>
                    <tr class="clickable" onclick="toggleDetails(this)">
                        <td>반응형 웹사이트 리디자인</td>
                        <td>UI/UX 디자인</td>
                        <td>2개월</td>
                        <td>800만원</td>
                        <td>React, TypeScript, 3년 이상</td>
                        <td>2024년 12월 31일</td>
                        <td>
                            <button class="settlement-btn">정산내역 확인</button>
                        </td>
                    </tr>
                    <tr class="accordion-row">
                        <td colspan="7">프로젝트 소개<br>신규 모바일 앱을 위한 RESTful API 개발 프로젝트입니다.</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--완료된 프로젝트-->
            <div id="completed" class="tab-content" style="display: none;">
                <table>
                    <thead>
                    <tr>
                        <th>프로젝트 정보</th>
                        <th>분야</th>
                        <th>기간</th>
                        <th>예산</th>
                        <th>요구사항</th>
                        <th>마감일</th>
                        <th>정산내역 확인</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="clickable" onclick="toggleDetails(this)">
                        <td>이커머스 플랫폼 구축</td>
                        <td>풀스택 개발</td>
                        <td>6개월</td>
                        <td>5,000만원</td>
                        <td>React, TypeScript, 3년 이상</td>
                        <td>2024년 12월 31일</td>
                        <td>
                            <button class="settlement-btn">정산내역 확인</button>
                        </td>
                    </tr>
                    <tr class="accordion-row">
                        <td colspan="7">프로젝트 소개<br> 온라인 쇼핑몰 시스템 전반 구축 프로젝트입니다.</td>
                    </tr>
                    <tr class="clickable" onclick="toggleDetails(this)">
                        <td>반응형 웹사이트 리디자인</td>
                        <td>UI/UX 디자인</td>
                        <td>2개월</td>
                        <td>800만원</td>
                        <td>React, TypeScript, 3년 이상</td>
                        <td>2024년 12월 31일</td>
                        <td>
                            <button class="settlement-btn">정산내역 확인</button>
                        </td>
                    </tr>
                    <tr class="accordion-row">
                        <td colspan="7">프로젝트 소개<br>기존 기업 홈페이지 반응형 리디자인 프로젝트입니다.</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
    <div id="settlementModal" class="modal-overlay" style="display: none;">
        <div class="modal">
            <button class="close-btn">&times;</button>
            <h3 class="modal-title">[프로젝트 명] 정산 내역</h3>
            <table class="settlement-table">
                <thead>
                <tr>
                    <th>회차</th>
                    <th>금액</th>
                    <th>상태</th>
                    <th>정산일</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1회차</td>
                    <td>₩3,600,000</td>
                    <td><span class="status complete">정산 완료</span></td>
                    <td>2025-01-01</td>
                </tr>
                <tr>
                    <td>2회차</td>
                    <td>₩3,600,000</td>
                    <td><span class="status complete">정산 완료</span></td>
                    <td>2025-01-01</td>
                </tr>
                <tr>
                    <td>3회차</td>
                    <td>₩3,600,000</td>
                    <td><span class="status complete">정산 완료</span></td>
                    <td>2025-01-01</td>
                </tr>
                <tr>
                    <td>4회차</td>
                    <td>₩3,600,000</td>
                    <td><span class="status complete">정산 완료</span></td>
                    <td>2025-01-01</td>
                </tr>
                <tr>
                    <td>5회차</td>
                    <td>₩3,600,000</td>
                    <td><span class="status pending">대기중</span></td>
                    <td>2025-01-01</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="4" class="total">총 정산액 <strong>₩14,400,000</strong></td>
                </tr>
                </tfoot>
            </table>
            <button class="confirm-btn">확인</button>
        </div>
    </div>
</div>
</body>
</html>