<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>포트폴리오 조회</title>
    <link rel="stylesheet" href="./../css/freelancer_my_page.css"/>
    <link rel="stylesheet" href="./../css/freelancer_main_portfolio_detail.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="header" style="height: 100px;">
    <!-- 헤더 인클루드 영역 -->
    <!--#include virtual="./../header.html" -->
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
            <li><h3><a href="#" class="active">포트폴리오</a></h3></li>
            <li><h3><a href="#">찜한 프로젝트</a></h3>
            <li><h3><a href="#">지원한 프로젝트 내역</a></h3>
            <li><h3><a href="#">진행중인 / 완료된 프로젝트</a></h3>
            <li><h3><a href="#">프로젝트 후기</a></h3>
            <li><h3><a href="#">문의내역</a></h3>
        </ul>
    </aside>
    <!-- 메인 콘텐츠 -->

    <main class="content">
        <section class="section">
            <h2>포트폴리오 제목</h2>
            <div class="portfolio-info">
                <div class="portfolio-thumbnail">
                    <img src="./../img/portfolio_thumbnail.png" alt="썸네일" style="width: 100%; height: 100%; border-radius: 8px;">
                </div>
                <div class="portfolio-meta">
                    <p><strong>프로젝트 기간</strong> <span>📅 2021.04 ~ 2021.08</span></p>
                    <p><strong>팀 구성 및 역할</strong> <span>3명(팀원) 프론트엔드 개발자</span></p>
                    <p><strong>클라이언트</strong> <span>C 마케팅</span></p>
                </div>
            </div>
        </section>

        <section class="section">
            <h3>포트폴리오 소개</h3>
            <div class="description-text">
                개발 환경
                운영체제 Mac (Ventura)
                언어 Python 3.9.6, Dart
                IDE Visual Studio Code, Postman, FlutterFlow
                서버 AWS EC2 (ubuntu), AWS S3 (image storage server)
                DB AWS RDS (MySQL 8.0.32)
                프레임워크 Django 4.05, django-restframework 3.13.1
                API RESTful API (JSON)
                기타 Docker, Nginx, Gunicorn
                ...
            </div>
        </section>

        <section class="section">
            <h3>스킬</h3>
            <div class="skills">
                <span>React</span><span>Typescript</span><span>Javascript</span>
                <span>HTML/CSS</span><span>Next.js</span><span>Redux</span>
                <span>Node.js</span><span>GraphQL</span><span>Webpack</span><span>Figma</span>
            </div>
        </section>

        <section class="section">
            <h3>첨부파일 등록</h3>
            <table>
                <thead>
                <tr>
                    <th>항목</th>
                    <th>내용</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><label>프로젝트 상세페이지</label></td>
                        <td><a href="#">참여했던 프로젝트 제목</a></td>
                    </tr>
                    <tr>
                        <td><label>외부 URL</label></td>
                        <td><a href="#" target="_blank">https://#</a></td>
                    </tr>
                    <tr>
                        <td><label>첨부파일</label></td>
                        <td>
                            <a>포트폴리오.pdf</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </section>

        <div class="action-buttons">
            <button class="edit-btn">포트폴리오 수정</button>
            <button class="delete-btn">포트폴리오 삭제</button>
        </div>
    </main>
</div>
</body>
</html>