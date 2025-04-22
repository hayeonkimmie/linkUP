<%--
  Author: 이원영
  Date: 25. 4. 14.
  Time: 오후 2:10
  Description: Project_list.jsp에서 넘어간 프로젝트의 상세 정보
  Read Data :
    - Mybatis ResultMap -> AdminProjectDetail + AdminProjectDetailParticipant
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>프로젝트 상세 정보</title>
    <link rel="stylesheet" href="../css/admin/admin_header.css">
    <link rel="stylesheet" href="../css/admin/project_detail.css">
    <script>
        const defaultOpenMenuId = "projectMenu";
    </script>
    <script src="../js/include_common.js"></script>
</head>
<body>

<div id="header-include"></div>

<div class="layout-wrapper">
    <div id="menu-include"></div>

    <div class="content">
        <div class="card">
            <h2 class="project-title">${project.projectName}</h2>
            <p class="company-name">${project.clientName}</p>

            <hr class="divider">

            <!-- 📌 프로젝트 설명 -->
            <div class="section">
                <h3>📌 프로젝트 설명</h3>
                <p class="description">${project.projectDescription}</p>
            </div>

            <!-- 👤 관리자 정보 + 프로젝트 기간 -->
            <div class="summary-box">
                <div class="summary-section">
                    <h4>👤 관리자 정보</h4>
                    <p><strong>이름:</strong> ${project.manager}</p>
                    <p><strong>이메일:</strong> ${project.memail}</p>
                    <p><strong>연락처:</strong> ${project.mphone}</p>
                </div>

                <div class="summary-section">
                    <h4>📅 프로젝트 기간</h4>
                    <p><strong>시작일:</strong> <fmt:formatDate value="${project.createdDate}" pattern="yyyy-MM-dd" /></p>
                    <p><strong>종료일:</strong> <fmt:formatDate value="${project.deadlineDate}" pattern="yyyy-MM-dd" /></p>
                    <c:set var="remainingRaw" value="${(project.deadlineDate.time - now.time) / (1000*60*60*24)}" />
                    <fmt:formatNumber var="remaining" value="${remainingRaw}" maxFractionDigits="0" />
                    <p><strong>남은 기간:</strong> <span class="highlight">${remaining}일</span></p>
                </div>
            </div>

            <!-- 👥 참여 인원 -->
            <div class="section">
                <h3>👥 참여 인원</h3>
                <div class="table-wrapper">
                    <table>
                        <thead>
                        <tr>
                            <th>이름</th>
                            <th>역할</th>
                            <th>이메일</th>
                            <th>연락처</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="p" items="${project.participants}">
                            <tr>
                                <td>${p.participantName}</td>
                                <td>${p.participantRole}</td>
                                <td>${p.participantEmail}</td>
                                <td>${p.participantPhone}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
