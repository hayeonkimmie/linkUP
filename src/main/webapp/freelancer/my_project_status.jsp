<%--
  Created by IntelliJ IDEA.
  User: sangk
  Date: 25. 4. 9.
  Time: 오전 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 프로젝트 현황</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/common/headerSt.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_get_my_proj.css'/>">
    <script src="${contextPath}/js/freelancer_my_get_my_proj.js"></script>
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

        <div class="topbar">
            <h2>내 프로젝트 현황</h2>
            <p>진행중인 / 완료된 프로젝트 목록을 확인하세요</p>
        </div>
        <div class="main-content">
            <div class="tabs">
                <div class="tab active" onclick="switchTab('ongoing')">진행중인 프로젝트 <span>(${goingProjCnt})</span></div>
                <div class="tab" onclick="switchTab('completed')">완료된 프로젝트 <span>(${completedProjCnt})</span></div>
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
                        <th>작업방식</th>
                        <th colspan="2">마감일</th>
                        <th>정산 내역 확인하기</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty onGoingProjectList}">
                                <tr>
                                    <td colspan="8">진행중인 프로젝트가 없습니다.</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${onGoingProjectList}" var="project">
                                    <tr class="clickable" onclick="toggleDetails(this)">
                                        <td><a href="${project.projectId}">${project.projectName}</a><br/>${project.clientName}</td>
                                        <td>${project.categories}</td>
                                        <td>${project.projectDuration}</td>
                                        <td>${project.totalBudget}</td>
                                        <td>
                                            <c:if test="${project.reqSkills ne null}">${project.reqSkills}, </c:if>
                                                ${project.qualification}</td>
                                        <td>${project.workingEnvironment}
                                            <c:if test="${project.workingEnvironment ne '재택'}">(${project.workingMethod})</c:if>| ${project.workingHours}
                                        </td>
                                        <td>${project.deadlineDate}</td>
                                        <td><span class="d-day">${project.dDay}</span></td>
                                        <td><!-- 예시: 버튼 클릭 시 -->
                                            <button onclick="showSettlementModal(${project.projectId})">정산 내역 보기</button>
                                        </td>
                                    </tr>
                                    <tr class="accordion-row">
                                        <td colspan="9">
                                            <p>프로젝트 소개</p>
                                            <p>${project.projectDescription}</p>
                                            <p>프로젝트 상세 업무 내용</p>
                                            <p>${project.jobDetails}</p>
                                            <hr/>
                                            <p>프로젝트 담당자 정보</p>
                                            <p>${project.projectManager}</p>
                                            <p>${project.managerPhone}</p>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
            <!--완료된 프로젝트-->
            <div id="completed" class="tab-content" style="display: none;">
                <table>
                    <c:choose>
                        <c:when test="${empty ongoingProjects}">
                            <tr>
                                <td colspan="7">진행중인 프로젝트가 없습니다.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${completedProjectList}" var="project">
                                <tr class="clickable" onclick="toggleDetails(this)">
                                    <td><a href="${project.projectId}">${project.projectName}</a></td>
                                    <td>${project.field}</td>
                                    <td>${project.period}</td>
                                    <td>${project.totalBudget}</td>
                                    <td>${project.requirements}</td>
                                    <td>${project.deadline}</td>
                                    <td><!-- 예시: 버튼 클릭 시 -->
                                        <button onclick="showSettlementModal(${project.projectId})">정산 내역 보기</button>
                                    </td>
                                </tr>
                                <tr class="accordion-row">
                                    <td colspan="7">${project.description}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
            <div class="pagination" id="paging">
                <c:choose>
                    <c:when test="${pageInfo.curPage > 1}">
                        <a href="?page=${pageInfo.curPage - 1}">&lt;</a>
                    </c:when>
                    <c:otherwise>
                        <a>&lt;</a>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" var="page">
                    <c:choose>
                        <c:when test="${page eq pageInfo.curPage}">
                            <a href="?page=${page}" class="select">${page}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${page}" class="btn">${page}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${pageInfo.curPage < pageInfo.allPage}">
                        <a href="?page=${pageInfo.curPage + 1}">&gt;</a>
                    </c:when>
                    <c:otherwise>
                        <a>&gt;</a>
                    </c:otherwise>
                </c:choose>
            </div> <!-- end of pagination -->
        </div>
    </div>
<%--    <jsp:include page="/freelancer/settlement_modal.jsp"/>--%>
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
/*        function openModal() {
            document.getElementById('settlementModal').style.display = 'flex';
        }
        function closeModal() {
            document.getElementById('settlementModal').style.display = 'none';
        }*/
    </script>
</body>
</html>
