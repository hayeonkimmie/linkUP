<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>월별 정산하기</title>
    <link rel="stylesheet" href="${contextPath}/css/common/headerLoginSt.css"/>
    <link rel="stylesheet" href="${contextPath}/css/client/monthlySettlement.css"/>
    <link rel="stylesheet" href="${contextPath}/css/client/sideBar.css"/>
</head>
<body>

<div id="header-placeholder"></div>
<div class="layout">
    <jsp:include page="../common/sidebar.jsp"/>

    <div class="main">
        <div class="header">
            <h1>월별 정산하기</h1>
        </div>

        <div class="project-info">
            <h2>${projectInfo.projectName}</h2>

            <!-- ✅ 정산 회차 추가 -->
            <div class="round-info">
                <p><strong>정산 회차:</strong> ${round}회차</p>
            </div>

            <div class="project-details">
                <div class="detail-item">
                    <p class="inline-flex">
                        <strong>프로젝트 기간:</strong>
                        <span class="nowrap">${projectInfo.startDate} ~ ${projectInfo.endDate}</span>
                    </p>
                </div>
                <div class="detail-item">
                    <p class="inline-flex">
                        <strong>총 프로젝트 금액:</strong>
                        <span><fmt:formatNumber value="${projectInfo.totalAmount}" type="number" groupingUsed="true" />원</span>
                    </p>
                </div>
                <div class="detail-item">
                    <p class="inline-flex">
                        <strong>총 개월 수:</strong>
                        <span>${projectMonthCount}개월</span>
                    </p>
                </div>
            </div>

        </div>

        <form action="${contextPath}/submit-settlement" method="post" id="settleForm">
            <input type="hidden" name="round" value="${round}"/>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>번호</th>
                        <th>이름</th>
                        <th>정산 기간</th>
                        <th>금액</th>
                        <th>선택</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="target" items="${settleTargets}" varStatus="status">
                        <tr class="${target.clientStatus == 'C' ? 'already-requested' : ''}">
                            <td>${status.index + 1}</td>
                            <td>${target.freelancerName}</td>
                            <td>${target.settlePeriod}</td>
                            <td><fmt:formatNumber value="${target.pay}" type="number" groupingUsed="true"/>원</td>
                            <td>
                                <c:choose>
                                    <c:when test="${target.clientStatus == 'C'}">
                                        <input type="checkbox" disabled />
                                        <span class="status-label">요청됨</span>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" name="freelancerNames" value="${target.freelancerName}" />
                                        <input type="hidden" name="contractIds" value="${target.contractId}" />
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="buttons">
                <button type="submit" class="btn">정산하기</button>
                <a href="${contextPath}/clientRecruitMgt" class="btn btn-secondary">돌아가기</a>
            </div>
        </form>

    </div>
</div>

<script>
    const contextPath = '${contextPath}';
</script>

<script src="${contextPath}/js/catalog.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
<script src="${contextPath}/js/monthlySettlement.js"></script>
</body>
</html>
