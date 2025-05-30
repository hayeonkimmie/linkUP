<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Link up - 로그인</title>
    <link rel="stylesheet" href="${contextPath}/css/home/login.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <a href="${contextPath}/mainPage" class="logo">
        <img src="${contextPath}/img/링크업 로고.png" alt="Link up 로고">
    </a>

    <div class="role-toggle">
        <button class="role-btn selected" data-role="recruiter">사업자</button>
        <button class="role-btn" data-role="jobseeker">일반</button>
    </div>

    <!-- 에러 메시지 출력 -->
    <c:if test="${not empty errorMsg}">
        <div class="error-message" style="color: red; margin-bottom: 10px; text-align: center;">
                ${errorMsg}
        </div>
    </c:if>

    <form action="${contextPath}/login" method="post" id="loginForm">
        <input type="hidden" name="role" id="roleInput" value="recruiter"/>
        <input type="text" name="id" placeholder="아이디를 입력하세요" required/>
        <input type="password" name="password" placeholder="비밀번호를 입력하세요" required/>
        <button type="submit" class="login-btn">로그인 하기</button>
    </form>

    <div class="sub-links">
        <a href="#" onclick="showPopup('id-popup')">아이디 찾기</a>
        <span>|</span>
        <a href="#" onclick="showPopup('pw-popup')">비밀번호 찾기</a>
    </div>

    <div class="sns-login">
        <a id="kakao-login-link" href="https://kauth.kakao.com/oauth/authorize?client_id=406379efec436984eb3393e7024851df&redirect_uri=http://localhost:8080/linkup/kakao-recruiter&response_type=code"><!-- 카카오 로그인 -->
            <img alt="카카오 로그인" src="${contextPath }/img/kakao_login_medium_narrow.png"/>
        </a>
        <%--    <button class="google-btn">구글 계정으로 로그인하기</button>--%>
    </div>

    <a href="${contextPath}/createAcc" class="signup-link">회원가입 하러가기</a>
</div>

<!-- 비밀번호 찾기 팝업 -->
<div class="popup" id="pw-popup">
    <div class="popup-content">
        <h2>비밀번호 찾기</h2>
        <p>가입 시 등록한 이메일 주소를 입력해 주세요.<br>비밀번호 재설정 링크를 보내드려요.</p>
        <label>이메일 주소</label>
        <input type="email" placeholder="이메일을 입력해 주세요."/>
        <button class="disabled-btn" disabled>비밀번호 재설정 링크 받기</button>
    </div>
</div>

<!-- 아이디 찾기 팝업 -->
<div class="popup" id="id-popup">
    <div class="popup-content">
        <h2>아이디 찾기</h2>
        <p>본인인증 정보를 입력해 주세요.<br>이메일로 아이디를 보내드려요.</p>
        <label>전화번호</label>
        <input type="text" placeholder="전화번호를 입력해 주세요."/>
        <button class="disabled-btn" disabled>아이디 받기</button>
    </div>
</div>

<!-- 성공 팝업들 -->
<div class="popup" id="successpw-popup">
    <div class="popup-content" style="text-align: center;">
        <h2>링크 발송 완료</h2>
        <p>입력하신 이메일로<br>비밀번호 재설정 링크를 보내드렸습니다.</p>
    </div>
</div>

<div class="popup" id="successid-popup">
    <div class="popup-content" style="text-align: center;">
        <h2>링크 발송 완료</h2>
        <p>입력하신 이메일로<br>아이디를 보내드렸습니다.</p>
    </div>
</div>

<script>
    // 역할 버튼 클릭 시 hidden input 값 변경
    document.querySelectorAll(".role-btn").forEach(btn => {
        btn.addEventListener("click", function () {
            // 모든 버튼에서 selected 클래스 제거
            document.querySelectorAll(".role-btn").forEach(b => b.classList.remove("selected"));

            // 클릭한 버튼에 selected 클래스 추가
            btn.classList.add("selected");

            // roleInput의 값을 버튼의 data-role 속성으로 설정 (기존 기능 유지)
            if (document.getElementById("roleInput")) {
                document.getElementById("roleInput").value = btn.getAttribute("data-role");
            }

            // kakao-login-link의 href 값 변경 (새로 추가하는 기능)
            const kakaoLink = document.querySelector('#kakao-login-link');
            if (kakaoLink) {
                // 클릭한 버튼의 텍스트 확인
                const buttonText = btn.textContent.trim();

                // 버튼에 따라 다른 URL 설정
                if (buttonText === '사업자') {
                    kakaoLink.href = "https://kauth.kakao.com/oauth/authorize?client_id=406379efec436984eb3393e7024851df&redirect_uri=http://localhost:8080/linkup/kakao-recruiter&response_type=code";
                } else if (buttonText === '일반') {
                    kakaoLink.href = "https://kauth.kakao.com/oauth/authorize?client_id=406379efec436984eb3393e7024851df&redirect_uri=http://localhost:8080/linkup/kakao-freelancer&response_type=code";
                }

                console.log(buttonText + " 버튼 클릭됨: 링크 변경됨 - " + kakaoLink.href);
            }
        });
    });

        // ✅ 카카오 로그인 링크 클릭 시
        kakaoLoginLink.addEventListener("click", function (event) {
            const role = roleInput.value || "recruiter";

            // 디버깅 로그 출력
            console.log("[카카오 로그인 클릭됨]");
            console.log("role =", role);

            if (!role) {
            event.preventDefault();
            alert("로그인하기 전에 역할을 선택해주세요.");
            return;
        }

        const clientId = "406379efec436984eb3393e7024851df";
        const redirectUri = "http://localhost:8080/linkup/kakao";
        const kakaoLoginUrl = `https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code`;

        console.log("카카오 로그인 URL:", kakaoLoginUrl);
        window.location.href = kakaoLoginUrl;
    });


function showPopup(id) {
        const popup = document.getElementById(id);
        popup.style.display = 'flex';

        if (id === 'success-popup') {
            setTimeout(() => closePopup(id), 3000);
            return;
        }

        const inputs = popup.querySelectorAll("input");
        const button = popup.querySelector("button.disabled-btn");

        if (!inputs.length || !button) return;

        function checkInputs() {
            let allFilled = [...inputs].every(input => input.value.trim() !== "");
            button.disabled = !allFilled;
            button.classList.toggle("active-btn", allFilled);
            button.classList.toggle("disabled-btn", !allFilled);
        }

        inputs.forEach(input => input.addEventListener("input", checkInputs));
        checkInputs();
    }

    function closePopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    window.addEventListener('click', function (e) {
        document.querySelectorAll('.popup').forEach(popup => {
            if (e.target === popup) popup.style.display = 'none';
        });
    });

    document.addEventListener("DOMContentLoaded", () => {
        const pwBtn = document.querySelector("#pw-popup button:not(.close-btn)");
        if (pwBtn) {
            pwBtn.addEventListener("click", () => {
                if (!pwBtn.disabled) {
                    closePopup('pw-popup');
                    showPopup('successpw-popup');
                }
            });
        }

        const idBtn = document.querySelector("#id-popup button:not(.close-btn)");
        if (idBtn) {
            idBtn.addEventListener("click", () => {
                if (!idBtn.disabled) {
                    closePopup('id-popup');
                    showPopup('successid-popup');
                }
            });
        }
    });
</script>
</body>
</html>
