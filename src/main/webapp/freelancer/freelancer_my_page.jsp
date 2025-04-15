<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/freelancer_my_page.css">
    <!--<title>Link up 마이페이지</title>-->
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <!--<link rel="stylesheet" href="../css/freelancer_my_get_my_proj.css">-->
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
            <li><a href="#"><h3>포트폴리오</h3></a></li>
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
                    <h3>찜한 프로젝트</h3>
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <th>프로젝트 명</th>
                    <th>분야</th>
                    <th>기간</th>
                    <th>예산</th>
                    <th>고객사</th>
                    <th>요구 스킬/경력</th>
                    <th>모집 마감일 <br/>D-Day</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>웹 쇼핑몰 리뉴얼</td>
                    <td>웹 개발</td>
                    <td>3개월</td>
                    <td>2,500만원</td>
                    <td>패션스토어(주)</td>
                    <td>React, TypeScript, 3년 이상</td>
                    <td>25. 04. 07
                        <span class="d-day">D-6</span>
                    </td>
                </tr>
                <tr>
                    <td>모바일 앱 AI기 개발</td>
                    <td>백엔드 개발</td>
                    <td>4개월</td>
                    <td>3,200만원</td>
                    <td>테크솔루션</td>
                    <td>Node.js, MongoDB, 5년 이상</td>
                    <td>25. 04. 07
                        <span class="d-day">D-6</span>
                    </td>
                </tr>
                <tr>
                    <td>데이터 대시보드 구축</td>
                    <td>데이터 시각화</td>
                    <td>2개월</td>
                    <td>1,800만원</td>
                    <td>데이터사이트</td>
                    <td>D3.js, React, 2년 이상</td>
                    <td>25. 04. 07
                        <span class="d-day">D-6</span>
                    </td>
                </tr>
                <tr>
                    <td>결제 시스템 개발</td>
                    <td>웹/앱 개발</td>
                    <td>6개월</td>
                    <td>5,500만원</td>
                    <td>페이테크(주)</td>
                    <td>Java, Spring, 보안 경험 필수</td>
                    <td>25. 04. 07
                        <span class="d-day">D-6</span>
                    </td>
                </tr>
                <tr>
                    <td>SNS 하이브 플랫폼</td>
                    <td>웹/앱 개발</td>
                    <td>8개월</td>
                    <td>4,000만원</td>
                    <td>소셜미디어랩</td>
                    <td>React Native, Redux, 4년 이상</td>
                    <td>25. 04. 07
                        <span class="d-day">D-6</span>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>진행중 프로젝트</h3>
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <th>진행여부</th>
                    <th>프로젝트 명</th>
                    <th>분야</th>
                    <th>기간<br/>예산</th>
                    <th>요구사항</th>
                    <th>프로젝트 마감일<br/>D-Day</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><span class="status">진행중</span></td>
                    <td>데이터 대시보드 구축</td>
                    <td>데이터시각화</td>
                    <td>2개월<br>1,800만원</td>
                    <td>D3.js, React, 2년 이상</td>
                    <td>25. 04. 07
                        <span class="d-day">D-6</span>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="height: 39px;"><b>진행중인 프로젝트가 없습니다.</b></td>
                    <!--<td><span class="status ongoing">진행 완료</span></td>
                    <td>AI 영상 시세팅 개발</td>
                    <td>인공지능</td>
                    <td>5개월<br>4,500만원</td>
                    <td>Python, TensorFlow, 머신러닝 경험</td>
                    <td>25. 04. 07
                        <span class="d-day">D-10</span>
                    </td>-->
                </tr>
                </tbody>
            </table>
        </section>
    </main>
</div>
</body>
</html>