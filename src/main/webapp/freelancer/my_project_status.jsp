<%--
  Created by IntelliJ IDEA.
  User: sangk
  Date: 25. 4. 9.
  Time: 오전 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_get_my_proj.css'/>">
    <script src="/../js/freelancer_my_get_my_proj.js"></script>
</head>
<body>

<div class="header">
    <!-- 헤더 인클루드 영역 -->
    <jsp:include page="/common/header.jsp"/>
</div>
<div class="container">
    <!-- 사이드바 -->
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <!-- 메인 콘텐츠 -->
    <div class="content">

        <div class="main-content">
            <div>
                <h2>내 프로젝트 현황</h2>
                <p>지원한 프로젝트 목록을 확인하세요</p>
            </div>

            <div class="tabs">
                <div class="tab active" onclick="switchTab('ongoing')">진행중인 프로젝트 <span>(2)</span></div>
                <div class="tab" onclick="switchTab('completed')">완료된 프로젝트 <span>(2)</span></div>
            </div>

            <!--진행중인 프로젝트-->
            <div id="ongoing" class="tab-content">
                <table>
                    <thead>
                    <tr>
                        <th>프로젝트 정보</th>
                        <th>분야</th>
                        <th>기간</th>
                        <th>예산</th>
                        <th>요구사항</th>
                        <th>마감일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="clickable" onclick="toggleDetails(this)">
                        <td>반응형 웹사이트 리디자인</td>
                        <td>UI/UX 디자인</td>
                        <td>2개월</td>
                        <td>800만원</td>
                        <td>React, TypeScript, 3년 이상</td>
                        <td>2024년 12월 31일</td>
                        <td><!-- 예시: 버튼 클릭 시 -->
                            <button onclick="showSettlementModal(${projectid})">정산 내역 보기</button>
                        </td>
                    </tr>
                    <tr class="accordion-row">
                        <td colspan="7">프로젝트 소개<br>신규 모바일 앱을 위한 RESTful API 개발 프로젝트입니다.</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--완료된 프로젝트-->
            <div id="completed" class="tab-content" style="display: none;">
                <table>
                    <thead>
                    <tr>
                        <th>프로젝트 정보</th>
                        <th>분야</th>
                        <th>기간</th>
                        <th>예산</th>
                        <th>요구사항</th>
                        <th>마감일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="clickable" onclick="toggleDetails(this)">
                        <td>이커머스 플랫폼 구축</td>
                        <td>풀스택 개발</td>
                        <td>6개월</td>
                        <td>5,000만원</td>
                        <td>React, TypeScript, 3년 이상</td>
                        <td>2024년 12월 31일</td>
                        <td><!-- 예시: 버튼 클릭 시 -->
                            <button onclick="showSettlementModal(${projectid})">정산 내역 보기</button>
                        </td>
                    </tr>
                    <tr class="accordion-row">
                        <td colspan="7">프로젝트 소개<br> 온라인 쇼핑몰 시스템 전반 구축 프로젝트입니다.</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <jsp:include page="/freelancer/settlement_modal.jsp"/>
    <script>
        function showSettlementModal(projectId) {
            fetch(`/settlement?projectId=${projectId}`)
                .then(response => response.text())
                .then(html => {
                    const modalContainer = document.createElement('div');
                    modalContainer.innerHTML = html;
                    document.body.appendChild(modalContainer);
                    document.getElementById("settlementModal").style.display = "flex";
                });
        }
        function openModal() {
            document.getElementById('settlementModal').style.display = 'flex';
        }
        function closeModal() {
            document.getElementById('settlementModal').style.display = 'none';
        }
    </script>
</body>
</html>
