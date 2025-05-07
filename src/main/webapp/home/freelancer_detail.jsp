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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
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
            <img class="profile-image" src="${contextPath}/img/${freelancerBasic.profileImg}" alt="프로필 이미지"/>
            <div class="profile-info">
                <h1 class="name">${freelancerBasic.name} <sapan>(${freelancerBasic.nickname})</sapan>
                <c:choose>
                    <c:when test="${isLiked}">
                        <a style="text-decoration: none;" href="${contextPath}/JJimFree?freelancerId=${freelancerId}&action=cancel">
                            <i class="bi bi-heart-fill text-danger" style="color: red;"></i>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a style="text-decoration: none;" href="${contextPath}/JJimFree?freelancerId=${freelancerId}&action=like'">
                            <i class="bi bi-heart text-danger" style="color: red;"></i>
                        </a>
                    </c:otherwise>
                </c:choose>
                </h1>
                <div class="skill-badges">
                    <c:forEach var="tag" items="${fn:split(freelancerExpert.skill, '^')}">
                        <span class="badge">${tag}</span>
                    </c:forEach>
                </div>

                <!-- ✅ 포트폴리오 보기 버튼 -->
                <div style="margin-top: 20px;">
                    <a href="${contextPath}/portfolio-list?freelancerid=${freelancerId}"
                       class="portfolio-btn">이 전문가의 포트폴리오 보기</a>
                </div>
            </div>
        </div>


        <%--    <hr class="section-divider" />--%>
        <div class="card">
            <h2>자기소개</h2>
            <p class="introduction-text">${freelancerExpert.introduction}</p>
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
                                    <c:if test="${not empty career.salary}"><li><strong>연봉:</strong> <fmt:formatNumber value="${career.salary}" pattern="#,###"/> 만원</li></c:if>
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
            <%--<c:set var="licenseArray" value="${fn:split(freelancer.license, '^')}" />
            <c:if test="${not empty licenseArray}">
                <div class="license-card">
                    <p><strong>자격증명:</strong> ${licenseArray[0]}</p>
                    <c:choose>
                        <c:when test="${not empty licenseArray[1] and fn:length(licenseArray) >= 4}">
                            <p><strong>급수:</strong> ${licenseArray[1]}</p>
                            <p><strong>발급처:</strong> ${licenseArray[2]}</p>
                            <fmt:parseDate var="parsedDate" value="${licenseArray[3]}" pattern="yyyy-MM-dd" />
                            <p><strong>취득일:</strong> <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM" /></p>
                        </c:when>
                        <c:otherwise>
                            <p><strong>발급처:</strong> ${licenseArray[2]}</p>
                            <fmt:parseDate var="parsedDate" value="${licenseArray[3]}" pattern="yyyy-MM-dd" />
                            <p><strong>취득일:</strong> <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM" /></p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>--%>
            <c:if test="${not empty freelancerExpert.licenseList}">
                <c:forEach items="${freelancerExpert.licenseList}" var="license" varStatus="status">
                    <div class="license-card">
                        <p><strong>자격증명:</strong> ${license.licenseName}</p>
                        <c:choose>
                            <c:when test="${not empty license.licenseGrade}">
                                <p><strong>급수:</strong> ${license.licenseGrade}</p>
                                <p><strong>발급처:</strong> ${license.licenseDate}</p>
                                <p><strong>취득일:</strong> <fmt:formatDate value="${license.licenseDate}" pattern="yyyy-MM" /></p>
                            </c:when>
                            <c:otherwise>
                                <p><strong>발급처:</strong> ${license.licenseDate}</p>
                                <p><strong>취득일:</strong> <fmt:formatDate value="${license.licenseDate}" pattern="yyyy-MM" /></p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
            </c:if>
            <h2>기타 정보</h2>
            <ul class="info-list">
                <li><strong>이메일:</strong> ${freelancerBasic.email}</li>
                <li><strong>전화번호:</strong> ${freelancerBasic.phoneNum}</li>
                <li><strong>주소:</strong> ${freelancerBasic.address}</li>
                <li><strong>기타 요청사항:</strong> ${freelancerExpert.otherRequest}</li>
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
