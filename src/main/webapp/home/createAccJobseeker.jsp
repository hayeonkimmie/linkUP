<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="../css/createAccJobseeker.css">
</head>

<body>
<div class="container">
    <a href="main.html" class="logo">
        <img src="../img/링크업 로고.png" alt="Link up 로고">
    </a>
    <p class="text1">간단한 정보로<br>바로 가입 가능!</p>
    <label>아이디 <span class="required">*</span></label>
    <input type="text" id="id" placeholder="아이디를 입력해주세요">
    <label>이메일 <span class="required">*</span></label>
    <input type="text" id="email" placeholder="이메일을 입력해주세요">

    <label>비밀번호 <span class="required">*</span></label>
    <input type="password" id="password" placeholder="비밀번호를 입력해주세요 (8자리 이상)">
    <input type="password" id="confirmPassword" placeholder="비밀번호를 한번 더 입력해주세요">

    <label>전화번호 <span class="required">*</span></label>
    <input type="text" id="phone" placeholder="전화번호를 입력해주세요(-제외)">

    <div class="checkbox-group">
        <label><input type="checkbox" id="allAgree"> 모두 동의합니다.</label>
        <label><input type="checkbox" class="agree" required> 만 14세 이상입니다.<span class="required">(필수)</span></label>
        <label><input type="checkbox" class="agree required" required> 서비스 이용약관에 동의합니다. <span
                class="required">(필수)</span></label>
        <label><input type="checkbox" class="agree required" required> 개인정보 수집/이용에 동의합니다. <span
                class="required">(필수)</span></label>
        <label><input type="checkbox" class="agree"> 마케팅 수신 · 홍보 목적의 개인정보 수집 및 이용에 <br>동의합니다.</label>
    </div>
    <a href="main.html">
        <button id="submitBtn" disabled>가입하기</button>
    </a>
</div>

<script>
    const allAgree = document.getElementById('allAgree');
    const checkboxes = document.querySelectorAll('.agree');
    const requiredCheckboxes = document.querySelectorAll('.agree.required');
    const submitBtn = document.getElementById('submitBtn');

    allAgree.addEventListener('change', () => {
        checkboxes.forEach(cb => cb.checked = allAgree.checked);
        validateForm();
    });

    checkboxes.forEach(cb => {
        cb.addEventListener('change', validateForm);
    });

    document.querySelectorAll('input[type="text"], input[type="password"]').forEach(input => {
        input.addEventListener('input', validateForm);
    });

    function validateForm() {
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const phone = document.getElementById('phone').value.trim();

        const allRequiredChecked = Array.from(requiredCheckboxes).every(cb => cb.checked);
        const isValid = email && password.length >= 8 && password === confirmPassword && phone && allRequiredChecked;

        submitBtn.disabled = !isValid;
    }
</script>
</body>

</html>