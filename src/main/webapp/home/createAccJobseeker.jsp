<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="${contextPath}/css/home/createAccJobseeker.css">
</head>

<body>
<div class="container">
    <a href="${contextPath}/home/main.jsp" class="logo">
        <img src="${contextPath}/img/링크업 로고.png" alt="Link up 로고">
    </a>

    <p class="text1">간단한 정보로<br>바로 가입 가능!</p>

    <form action="${contextPath}/createAccJobseeker" method="post">
        <label>아이디 <span class="required">*</span></label>
        <input type="text" name="id" id="id" placeholder="아이디를 입력해주세요">

        <label>이메일 <span class="required">*</span></label>
        <input type="text" name="email" id="email" placeholder="이메일을 입력해주세요">

        <label>비밀번호 <span class="required">*</span></label>
        <input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요 (8자리 이상)">

        <input type="password" id="confirmPassword" placeholder="비밀번호를 한번 더 입력해주세요">

        <label>전화번호 <span class="required">*</span></label>
        <input type="text" name="phone" id="phone" placeholder="전화번호를 입력해주세요(-제외)">

        <div class="checkbox-group">
            <label><input type="checkbox" id="allAgree"> 모두 동의합니다.</label>
            <label><input type="checkbox" class="agree required" required> 만 14세 이상입니다.<span class="required">(필수)</span></label>
            <label><input type="checkbox" class="agree required" required> 서비스 이용약관에 동의합니다.<span class="required">(필수)</span></label>
            <label><input type="checkbox" class="agree required" required> 개인정보 수집/이용에 동의합니다.<span class="required">(필수)</span></label>
            <label><input type="checkbox" class="agree"> 마케팅 수신 · 홍보 목적의 개인정보 수집 및 이용에 동의합니다.</label>
        </div>
        <a href="${contextPath}/main">
        <button type="submit" id="submitBtn" disabled>가입하기</button>
        </a>
    </form>
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
