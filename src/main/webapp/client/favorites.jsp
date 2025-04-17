<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>프리랜서 카드</title>
    <link rel="stylesheet" href="../css/style.css" />
    <link rel="stylesheet" href="../css/headerSt.css" />
    <style>
        * {
            box-sizing: border-box;
            font-family: sans-serif;
        }
        body {
            background-color: #f9fafb;
            margin: 0;
            padding: 20px;
        }
        .container {
            flex: 1;
            margin-left: 40px; /* 사이드바와 카드 사이 간격 조절 */
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }
        .layout {
            display: flex;
            align-items: flex-start;
            gap: 0px;
            margin-top: 20px;
        }
        .sidebar {
            width: 300px; /* 사이드바의 고정 너비 */
        }


        .card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
            padding: 10px;
            width: 420px;
            position: relative;
        }
        .card img {
            width: 100px;
            height: 100px;
            border-radius: 500%;
            object-fit: cover;
        }
        .card .heart {
            position: absolute;
            top: 20px;
            right: 20px;
            color: red;
            font-size: 30px;
        }
        .info {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .info-text {
            display: flex;
            flex-direction: column;
        }
        .name {
            font-weight: bold;
            font-size: 18px;
        }
        .job, .location {
            font-size: 14px;
            color: #666;
        }
        .rating-project {
            display: flex;
            justify-content: space-between;
            margin: 30px 0;
            font-size: 14px;
        }
        .tags {
            display: flex;
            flex-wrap: wrap;
            gap: 5px;
            margin-bottom: 20px;
        }
        .tag {
            background-color: #eef2ff;
            color: #3730a3;
            padding: 5px 10px;
            border-radius: 8px;
            font-size: 12px;
        }
        .profile-button {
            display: block;
            width: 100%;
            background-color: #2563eb;
            color: white;
            padding: 15px;
            text-align: center;
            border-radius: 10px;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div>
    <header class="header">
        <div class="header-container">
            <div class="logo-search">
                <a href="" class="logo">
                    <img src="/Users/hayeon/Documents/devs/workspace/mini-project/링크업 로고.png" alt="Link up 로고">
                </a>
                <input type="text" class="search-bar" placeholder="어떤 전문가를 찾고 계신가요" />
            </div>
            <div class="auth-buttons">
                <a href="#" class="login">로그인</a>
                <a href="#" class="signup">회원가입</a>
            </div>
        </div>

        <nav class="category-nav">
            <ul>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle">■ 전체보기</a>
                    <ul class="dropdown-menu">
                        <li><a href="#">비즈니스</a></li>
                        <li><a href="#">디자인</a></li>
                        <li><a href="#">IT·프로그래밍</a></li>
                        <li><a href="#">영상·사진·음향</a></li>
                        <li><a href="#">마케팅</a></li>
                        <li><a href="#">번역·통역</a></li>
                        <li><a href="#">문서·글쓰기</a></li>
                        <li><a href="#">창업·사업</a></li>
                        <li><a href="#">세무·법무·노무</a></li>
                        <li><a href="#">취미 레슨</a></li>
                        <!-- 전체 보기 목록 - 추가되어야함 -->
                        <!-- 임시로 href 를 #으로 해놨음 -->
                    </ul>
                </li>
                <li><a href="">디자인</a></li>
                <li><a href="">IT·프로그래밍</a></li>
                <li><a href="">영상·사진·음향</a></li>
                <li><a href="">마케팅</a></li>
                <li><a href="">번역·통역</a></li>
                <li><a href="">문서·글쓰기</a></li>
                <li><a href="">창업·사업</a></li>
                <li><a href="">세무·법인·노무</a></li>
                <li><a href="">취미 레슨</a></li>

            </ul>
        </nav>
    </header>


</div>
<div class="layout">
    <aside class="sidebar">
        <!-- 프로필 사진 추가 -->
        <div class="profile">
            <img src="https://via.placeholder.com/80" alt=" " />
            <label class="camera-icon" for="profile-upload"></label>
            <input type="file" id="profile-upload" accept="image/*" />
            <p>닉네임</p>
            <p>마이페이지</p>
        </div>
        <h3>프로필 설정</h3><br>
        <h3>프로젝트</h3>
        <ul>
            <li><a href="#">구인 등록</a></li>
            <li><a href="#">프로젝트 조회</a></li>
            <li><a href="#">거래내역</a></li>
            <li><a href="#">지원자 관리</a></li>
        </ul>
        <h3>찜한 구인자</h3>
        <h3>리뷰 내역</h3>
        <h3>문의 내역</h3>
        <h3>알림 설정</h3>
    </aside>

    <main class="main">
        <h2 class="section-title">찜한 구인자</h2>

        <div class="container">
            <div class="card">
                <span class="heart">❤️</span>
                <div class="info">
                    <img src="https://randomuser.me/api/portraits/men/10.jpg" alt="김개발">
                    <div class="info-text">
                        <div class="name">김개발</div>
                        <div class="job">프론트엔드 개발자</div>
                        <div class="location">📍 서울</div>
                    </div>
                </div>
                <div class="rating-project">
                    <div>⭐ 4.8/5.0</div>
                    <div>📁 프로젝트 15개</div>
                </div>
                <div class="tags">
                    <span class="tag">React</span>
                    <span class="tag">TypeScript</span>
                    <span class="tag">Tailwind CSS</span>
                </div>
                <a href="#" class="profile-button">프로필 보기</a>
            </div>

            <div class="card">
                <span class="heart">❤️</span>
                <div class="info">
                    <img src="https://randomuser.me/api/portraits/women/11.jpg" alt="이다자인">
                    <div class="info-text">
                        <div class="name">이다자인</div>
                        <div class="job">UI/UX 디자이너</div>
                        <div class="location">📍 부산</div>
                    </div>
                </div>
                <div class="rating-project">
                    <div>⭐ 4.5/5.0</div>
                    <div>📁 프로젝트 12개</div>
                </div>
                <div class="tags">
                    <span class="tag">Figma</span>
                    <span class="tag">Adobe XD</span>
                    <span class="tag">Sketch</span>
                </div>
                <a href="#" class="profile-button">프로필 보기</a>
            </div>

            <div class="card">
                <span class="heart">❤️</span>
                <div class="info">
                    <img src="https://randomuser.me/api/portraits/men/12.jpg" alt="박백엔드">
                    <div class="info-text">
                        <div class="name">박백엔드</div>
                        <div class="job">백엔드 개발자</div>
                        <div class="location">📍 대전</div>
                    </div>
                </div>
                <div class="rating-project">
                    <div>⭐ 4.9/5.0</div>
                    <div>📁 프로젝트 20개</div>
                </div>
                <div class="tags">
                    <span class="tag">Node.js</span>
                    <span class="tag">Express</span>
                    <span class="tag">MongoDB</span>
                </div>
                <a href="#" class="profile-button">프로필 보기</a>
            </div>

            <div class="card">
                <span class="heart">❤️</span>
                <div class="info">
                    <img src="https://randomuser.me/api/portraits/women/13.jpg" alt="최풀스택">
                    <div class="info-text">
                        <div class="name">최풀스택</div>
                        <div class="job">풀스택 개발자</div>
                        <div class="location">📍 인천</div>
                    </div>
                </div>
                <div class="rating-project">
                    <div>⭐ 4.7/5.0</div>
                    <div>📁 프로젝트 18개</div>
                </div>
                <div class="tags">
                    <span class="tag">React</span>
                    <span class="tag">Node.js</span>
                    <span class="tag">PostgreSQL</span>
                </div>
                <a href="#" class="profile-button">프로필 보기</a>
            </div>
    </main>
</div>
</body>
</html>
