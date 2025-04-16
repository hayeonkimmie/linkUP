<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <!--    <link rel="stylesheet" href="./../css/freelancer_main_info_edit.css">-->
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/freelancer_my_page.css">
    <link rel="stylesheet" href="../css/freelancer_my_page_info_edit.css">
    <script src="../js/freelancer_my_page_info_edit.js"></script>
    <link rel="stylesheet" href="../css/alarm_settings.css">
    <script src="../js/alarm_settings.js"></script>
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
                <h3 class="active">프로필 설정</h3>
                <a class="active" href="#">기본 정보 설정</a>
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
                    <h3>기본 정보 설정</h3>
                </div>
            </div>
            <form>
                <div class="form-row profile-upload">
                    <label for="profile_img" class="upload-placeholder"><!--프로필 이미지 변경--></label>
                    <div class="profile-box">
                        <img src="./../img/basic_profile_img.png" alt="profile" id="preview" width="150px"
                             onclick="document.getElementById('profile_img').click();"/>
                        <!--<img src="${contextPath}/img/plus.png" alt="profile" id="preview" width="100px" />-->
                        <input type="file" name="profile_img" id="profile_img" accept="image/*" onchange="readURL(this);"
                               style="display:none">
                    </div>
                </div>

                <div class="form-row">
                    <input type="text" placeholder="이름" value="" class="required"/>
                    <input type="date" id="birthDisplay" readonly placeholder="생년월일" required/>
                    <input type="date" id="birth" style="display: none;"/>
                </div>

                <div class="form-row">
                    <input type="text" value="아이디" readonly/>
                </div>

                <div class="form-row">
                    <input type="email" placeholder="이메일" value="" class="required"/>
                </div>

                <div class="form-row">
                    <input type="text" placeholder="휴대폰 번호" value="" class="required"/>
                </div>

                <div class="form-row">
                    <input type="text" placeholder="주소" value="" class="required"/>
                    <button type="button" class="search-btn">🔍</button>
                </div>

                <h3>비밀번호</h3>
                <div class="form-row">
                    <input type="password" placeholder="현재 비밀번호" value="" disabled/>
                </div>
                <div class="form-row">
                    <input type="password" placeholder="새 비밀번호" value=""/>
                    <input type="password" placeholder="새 비밀번호 확인" value=""/>
                    <br/>
                    <div class="worning" style="display: none; font-size:small; font-weight: bolder; color:red;">비밀번호가 일치하지 않습니다.</div>
                </div>

                <h3>계좌번호</h3>
                <div class="form-row">
                    <label for="bank">은행</label>
                    <select name="bank_type" id="bank" class="required">
                        <option value="">은행을 선택해 주세요</option>
                        <option value="javascript">카카오</option>
                    </select>
                    <input type="text" placeholder="현재 계좌번호" class="required"/>
                </div>
                <div class="toggle-row">
                    <label for="projectUpdate">프로젝트 지원 결과 알림</label>
                    <label class="switch">
                        <input type="checkbox" id="" checked>
                        <span class="slider round"></span>
                    </label>
                </div>
                <div class="toggle-row">
                    <label for="projectUpdate">진행중인 프로젝트 마감일 알림</label>
                    <label class="switch">
                        <input type="checkbox" id="" checked>
                        <span class="slider round"></span>
                    </label>
                </div>
                <div class="toggle-row">
                    <label for="projectUpdate">찜한 프로젝트 모집 마감일 알림</label>
                    <label class="switch">
                        <input type="checkbox" id="" checked>
                        <span class="slider round"></span>
                    </label>
                </div>
                <div class="form-row center">
                    <button type="submit" class="submit-btn">저장</button>
                </div>
            </form>
        </section>
    </main>
</div>
</body>
</html>
