<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <link rel="stylesheet" href="./../css/freelancer_my_page.css"/>
    <link rel="stylesheet" href="../css/freelancer_main_portfolio_write_and_modify.css"/>
    <script src="./../js/freelancer_my_page_portfolio_write_and_modify.js"></script>
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
            <div class="content-header">
                <div class="content-header-text">
                    <h3>포트폴리오 작성</h3>
                </div>
            </div>
        </section>
        <!-- 본문 -->
        <div class="portfolio-register-container">
            <!-- 포트폴리오 제목 입력 -->
            <div class="portfolio-title-section">
                <input type="text" class="portfolio-title-input" placeholder="포트폴리오 제목을 입력해 주세요">
            </div>

            <!-- 썸네일 업로드 -->
            <div class="thumbnail-upload-section">
                <label>포트폴리오 썸네일 사진</label>
                <div class="thumbnail-upload-box">
                    <input type="file" id="thumbnail" hidden accept="image/*"/>
                    <label for="thumbnail" class="upload-placeholder">포트폴리오 썸네일 사진을 업로드 해주세요<br>+</label>
                </div>
            </div>

            <!-- 프로젝트 기간 -->
            <div class="project-period">
                <label>프로젝트 기간</label>
                <input type="month"> ~ <input type="month">
            </div>

            <!-- 팀 구성 및 역할 -->
            <div class="team-role">
                <label>팀 구성 및 역할</label>
                <input type="text" placeholder="ex) 개인 프로젝트 or FE 개발 4명, BE 개발 5명">
            </div>

            <!-- 스킬 -->
            <div class="skills-section">
                <label for="skills">스킬</label>
                <select class="skills-select" name="skills" id="skills" multiple>
                    <option>찾으시는 스킬이 있나요?</option>
                    <option value="React">React</option>
                    <option value="TypeScript">TypeScript</option>
                    <option value="JavaScript">JavaScript</option>
                    <option value="HTML/CSS">HTML/CSS</option>
                    <option value="Next.js">Next.js</option>
                    <option value="Redux">Redux</option>
                    <option value="Node.js">Node.js</option>
                    <option value="GraphQL">GraphQL</option>
                    <option value="Webpack">Webpack</option>
                </select>
                <div class="skill-tags">
                    <button id="clear-skills" type="button">선택 초기화</button>
                </div>
            </div>

            <!-- 포트폴리오 소개 -->
            <div class="portfolio-description">
                <label for="introduction">포트폴리오 소개</label>
                <textarea id="introduction" placeholder="프로젝트에 대한 소개, 본인이 맡은 역할 및 기여에 대한 내용을 서술해 주세요"></textarea>
            </div>

            <!-- 첨부파일 등록 -->
            <div class="file-upload-section">
              <span id="add-attachment">
                <h3>첨부파일</h3>
                <button id="add-project-log">진행했던 프로젝트 이력 불러오기 </button>
                <button id="add-outer-url">외부 url 추가</button>
                <button id="add-attachment-file">내 PC에서 파일 첨부</button>
              </span>
                <table>
                    <thead>
                    <tr>
                        <th>항목</th>
                        <th>내용</th>
                    </tr>
                    </thead>
                    <tbody>
                        <!-- 버튼 클릭시 복제  -->
                    </tbody>
                </table>
            </div>
            <!-- 버튼 영역 -->
            <div class="button-group">
                <button id="submit-btn">포트폴리오 등록</button>
                <button id="temp-submit-btn">포트폴리오 임시저장</button>
                <button id="delete-btn">포트폴리오 삭제</button>
            </div>
        </div>
    </main>
</div>
</body>
</html>