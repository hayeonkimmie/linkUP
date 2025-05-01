<%--
  Author: 이원영
  Date: 25. 4. 14.
  Time: 오후 2:10
  Description: 프리랜서(개인, 구직자)의 상세 페이지
  Read Data :
    - AdminFreelancer freelancer (프리랜서 정보)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>구직자 상세 정보</title>
    <link rel="stylesheet" href="${contextPath}/css/admin/user_detail.css" />
    <script>
        const defaultOpenMenuId = "userMenu";
    </script>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css" />
    <style>
        .content {
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 48px 64px;
            background-color: #f5f6fa;
        }

    </style>
</head>
<body>
<div class="header">
    <div id="header-placeholder"></div>
</div>

<div class="layout-wrapper">
    <div class="content">
        <div class="profile-banner">
            <img class="profile-image" src="${contextPath}/img/${freelancer.profileImg}" alt="프로필 이미지"/>
            <div class="profile-info">
                <h1 class="name">${freelancer.name} <sapan>(${freelancer.nickname})</sapan></h1>
                <div class="skill-badges">
                    <c:forEach var="tag" items="${fn:split(freelancer.skill, '^')}">
                        <span class="badge">${tag}</span>
                    </c:forEach>
                </div>

                <!-- ✅ 포트폴리오 보기 버튼 -->
                <div style="margin-top: 20px;">
                    <a href="${contextPath}/portfolio-list?freelancerid=${freelancer.freelancerId}"
                       class="portfolio-btn">이 전문가의 포트폴리오 보기</a>
                </div>
            </div>
        </div>


        <%--    <hr class="section-divider" />--%>
        <div class="card">
            <h2>자기소개</h2>
            <p class="introduction-text">${freelancer.introduction}</p>
            <h2>경력사항</h2>
            <c:choose>
                <c:when test="${not empty careerList}">
                    <div class="career-container">
                        <c:forEach var="career" items="${careerList}">
                            <div class="career-card">
                                <h3 class="company-name">${career.companyName}</h3>
                                <p class="job-title">${career.position} / ${career.jobTitle}</p>
                                <p class="department">${career.departmentName}</p>
                                <p class="job-desc">${career.jobDescription}</p>
                                <ul class="career-meta">
                                    <li><strong>연봉:</strong> <fmt:formatNumber value="${career.salary}" pattern="#,###"/> 만원</li>
                                    <li><strong>재직 기간:</strong>
                                        <fmt:formatDate value="${career.joinDate}" pattern="yyyy-MM-dd"/> ~
                                        <c:choose>
                                            <c:when test="${career.resignDate != null}">
                                                <fmt:formatDate value="${career.resignDate}" pattern="yyyy-MM-dd"/>
                                            </c:when>
                                            <c:otherwise>재직중</c:otherwise>
                                        </c:choose>
                                    </li>
                                </ul>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>등록된 경력 정보가 없습니다.</p>
                </c:otherwise>
            </c:choose>


            <h2>자격증</h2>
            <c:set var="licenseArray" value="${fn:split(freelancer.license, '^')}" />
            <c:if test="${not empty licenseArray}">
                <div class="license-card">
                    <p><strong>자격증명:</strong> ${licenseArray[0]}</p>

                    <c:choose>
                        <c:when test="${not empty licenseArray[1] and fn:length(licenseArray) >= 4}">
                            <p><strong>급수:</strong> ${licenseArray[1]}</p>
                            <p><strong>발급처:</strong> ${licenseArray[2]}</p>
                            <p><strong>취득일:</strong> ${licenseArray[3]}</p>
                        </c:when>
                        <c:otherwise>
                            <p><strong>발급처:</strong> ${licenseArray[1]}</p>
                            <p><strong>취득일:</strong> ${licenseArray[2]}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>

            <h2>기타 정보</h2>
            <ul class="info-list">
                <li><strong>이메일:</strong> ${freelancer.email}</li>
                <li><strong>전화번호:</strong> ${freelancer.phoneNum}</li>
                <li><strong>주소:</strong> ${freelancer.address}</li>
                <li><strong>기타 요청사항:</strong> ${freelancer.otherRequest}</li>
            </ul>
        </div>
    </div>
    <script>
        function getContrastYIQ(hexcolor) {
            hexcolor = hexcolor.replace("#", "");
            const r = parseInt(hexcolor.substr(0, 2), 16);
            const g = parseInt(hexcolor.substr(2, 2), 16);
            const b = parseInt(hexcolor.substr(4, 2), 16);
            const yiq = (r * 299 + g * 587 + b * 114) / 1000;
            return yiq >= 128 ? "#000" : "#fff";
        }
        function getRandomHexColor() {
            const r = Math.floor(Math.random() * 200);
            const g = Math.floor(Math.random() * 200);
            const b = Math.floor(Math.random() * 200);
            return (
                "#" +
                [r, g, b]
                    .map(x => {
                        const hex = x.toString(16);
                        return hex.length === 1 ? "0" + hex : hex;
                    })
                    .join("")
            );
        }
        window.addEventListener("DOMContentLoaded", () => {
            document.querySelectorAll(".skill-badges .badge").forEach(badge => {
                const bgColor = getRandomHexColor();
                badge.style.backgroundColor = bgColor;
                badge.style.color = getContrastYIQ(bgColor);
            });
        });
    </script>
    <script>
        const contextPath = '${pageContext.request.contextPath}';
    </script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/headerLogin.js"></script>
</div>
</body>
</html>
