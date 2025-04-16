<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 15.
  Time: 오후 6:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Link up</title>
  <link rel="stylesheet" href="../css/login.css"/>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
  <a href="#" class="logo">
    <img src="../1x/링크업 로고.png" alt="Link up 로고">
  </a>

  <div class="role-toggle">
    <button class="role-btn selected" data-role="recruiter">사업자</button>
    <button class="role-btn" data-role="jobseeker">일반</button>
  </div>

  <input type="text" placeholder="아이디를 입력하세요" />
  <input type="password" placeholder="비밀번호를 입력하세요" />
  <button class="login-btn">로그인 하기</button>

  <div class="sub-links">
    <a href="#" onclick="showPopup('id-popup')">아이디 찾기</a>
    <span>|</span>
    <a href="#" onclick="showPopup('pw-popup')">비밀번호 찾기</a>
  </div>

  <div class="sns-login">
    <button class="kakao-btn">카카오톡으로 로그인하기</button>
    <button class="google-btn">구글 계정으로 로그인하기</button>
  </div>

  <a href="createAcc1.jsp" class="signup-link">회원가입 하러가기</a>
</div>

<!-- 비밀번호 찾기 팝업 -->
<div class="popup" id="pw-popup">
  <div class="popup-content">
    <h2>비밀번호 찾기</h2>
    <p>가입 시 등록한 이메일 주소를 입력해 주세요.<br />비밀번호 재설정 링크를 보내드려요.</p>
    <label>이메일 주소</label>
    <input type="email" placeholder="이메일을 입력해 주세요." />
    <button class="disabled-btn" disabled>비밀번호 재설정 링크 받기</button>
  </div>
</div>

<!-- 아이디 찾기 팝업 -->
<div class="popup" id="id-popup">
  <div class="popup-content">
    <h2>아이디 찾기</h2>
    <p>본인인증 받으신 정보를 입력해 주세요.<br />이메일로 아이디(이메일 주소)를 보내드려요.</p>
    <label>전화번호</label>
    <input type="email" placeholder="전화번호를 입력해 주세요." />
    <button class="disabled-btn" disabled>아이디 받기</button>
  </div>
</div>

<!-- 성공 팝업 -->
<div class="popup" id="successpw-popup">
  <div class="popup-content" style="text-align: center;">
    <h2>링크 발송</h2>
    <div style="margin: 20px 0;">
      <div style="display: inline-block; background: #c472c7; border-radius: 50%; padding: 20px;">
        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="white" viewBox="0 0 24 24">
          <path d="M20.285 6.709a1 1 0 00-1.414-1.418L9 15.163l-3.877-3.87a1 1 0 10-1.414 1.414l4.586 4.586a1 1 0 001.414 0l10.576-10.584z"/>
        </svg>
      </div>
    </div>
    <p>입력하신 이메일로 비밀번호<br />재설정 링크를 보내드렸습니다.</p>
  </div>
</div>

<div class="popup" id="successid-popup">
  <div class="popup-content" style="text-align: center;">
    <h2>링크 발송</h2>
    <div style="margin: 20px 0;">
      <div style="display: inline-block; background: #c472c7; border-radius: 50%; padding: 20px;">
        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="white" viewBox="0 0 24 24">
          <path d="M20.285 6.709a1 1 0 00-1.414-1.418L9 15.163l-3.877-3.87a1 1 0 10-1.414 1.414l4.586 4.586a1 1 0 001.414 0l10.576-10.584z"/>
        </svg>
      </div>
    </div>
    <p>입력하신 이메일로 아이디를<br />보내드렸습니다.</p>
  </div>
</div>

<script>
  function showPopup(id) {
    const popup = document.getElementById(id);
    popup.style.display = 'flex';

    if (id === 'success-popup') {
      setTimeout(() => {
        closePopup(id);
      }, 3000);
      return;
    }

    const inputs = popup.querySelectorAll("input");
    const button = popup.querySelector("button.disabled-btn");

    if (!inputs.length || !button) return;

    function checkInputs() {
      let allFilled = true;
      inputs.forEach(input => {
        if (input.value.trim() === "") {
          allFilled = false;
        }
      });

      if (allFilled) {
        button.disabled = false;
        button.classList.remove("disabled-btn");
        button.classList.add("active-btn");
      } else {
        button.disabled = true;
        button.classList.add("disabled-btn");
        button.classList.remove("active-btn");
      }
    }

    inputs.forEach(input => input.addEventListener("input", checkInputs));
    checkInputs();
  }

  function closePopup(id) {
    document.getElementById(id).style.display = 'none';
  }

  window.addEventListener('click', function (e) {
    const popups = document.querySelectorAll('.popup');
    popups.forEach(popup => {
      if (e.target === popup) {
        popup.style.display = 'none';
      }
    });
  });

  document.addEventListener("DOMContentLoaded", () => {
    const pwBtn = document.querySelector("#pw-popup button:not(.close-btn)");
    if (pwBtn) {
      pwBtn.addEventListener("click", function () {
        if (!pwBtn.disabled) {
          closePopup('pw-popup');
          showPopup('successpw-popup');
        }
      });
    }

    const idBtn = document.querySelector("#id-popup button:not(.close-btn)");
    if (idBtn) {
      idBtn.addEventListener("click", function () {
        if (!idBtn.disabled) {
          closePopup('id-popup');
          showPopup('successid-popup');
        }
      });
    }

    const roleButtons = document.querySelectorAll(".role-btn");
    roleButtons.forEach(btn => {
      btn.addEventListener("click", () => {
        roleButtons.forEach(b => b.classList.remove("selected"));
        btn.classList.add("selected");
      });
    });
  });
</script>
</body>
</html>
