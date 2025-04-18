<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>알림 설정</title>
    <link rel="stylesheet" href="${contextPath}/css/headerSt.css" />
    <link rel="stylesheet" href="${contextPath}/css/alarm_settings.css" />
</head>
<body>
<div class="layout">
    <aside class="sidebar">
        <div class="profile">
            <img src="https://via.placeholder.com/80" alt="프로필 이미지">
            <p>닉네임</p>
            <p><a href="#">마이페이지</a></p>
        </div>
        <h3>프로필 설정</h3>
        <h3>프로젝트</h3>
        <ul>
            <li><a href="#">구인 등록</a></li>
            <li><a href="#">프로젝트 조회</a></li>
            <li><a href="#">지원자 관리</a></li>
        </ul>
        <h3><a href="#">찜한 구인자</a></h3>
        <h3><a href="#">리뷰 내역</a></h3>
        <h3><a href="#">문의 내역</a></h3>
        <h3>설정</h3>
        <ul>
            <li><a href="#" class="active">알림 설정</a></li>
            <li><a href="#">결제 정보</a></li>
        </ul>
    </aside>
    <main class="main">
        <h2 class="section-title">알림 설정</h2>
        <div class="alert-setting">
            <h4>알림 기본 설정</h4>
            <p class="alert-info">알림을 받는 방식을 설정하세요.</p>

            <div class="alert-item">
                <span>구직자 지원 알림</span>
                <label class="toggle-switch">
                    <input type="checkbox" checked>
                    <span class="slider"></span>
                </label>
            </div>
            <div class="alert-item">
                <span>지원자 승인/거절 알림</span>
                <label class="toggle-switch">
                    <input type="checkbox">
                    <span class="slider"></span>
                </label>
            </div>

            <div class="alert-description">
                <strong>알림 설정 안내</strong><br>
                중요한 알림은 이메일과 푸시 알림으로 모두 발송됩니다. 특정 알림을 비활성화하더라도 서비스 이용에 필수적인 알림(예: 보안 관련, 계정 관련)은 계속 발송됩니다.
            </div>
            <button class="btn-save">설정 저장</button>
        </div>
    </main>
</div>
</body>
</html>
