<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>프로젝트 정산</title>
    <link rel="stylesheet" href="../css/admin/admin_header.css">
    <link rel="stylesheet" href="../css/settlement.css">
    <script>
        const defaultOpenMenuId = "projectMenu";
    </script>
    <script src="../js/include_common.js"></script>
</head>

<body>
    <div id="header-include"></div>

    <div class="layout-wrapper">
        <div id="menu-include"></div>

        <div>
          
        </div>

        <div class="content">
            
            <div class="card filter-box">
              <h1>이번달 프로젝트 정산</h1>
                <div class="filter-row">
                    <div class="filter-item">
                        <label>기준 날짜</label>
                        <input type="date" />
                    </div>
                    <div class="filter-item">
                        <label>프로젝트명</label>
                        <input type="text" placeholder="프로젝트명 검색" />
                    </div>
                    <div class="filter-item">
                        <label>정산 상태</label>
                        <select>
                            <option>전체</option>
                            <option>정산 완료</option>
                            <option>정산 대기</option>
                        </select>
                    </div>
                    <div class="filter-item">
                        <label>구인자 승인 상태</label>
                        <select>
                            <option>전체</option>
                            <option>승인</option>
                            <option>미승인</option>
                        </select>
                    </div>
                    <div class="filter-action">
                        <button>🔍 검색</button>
                    </div>
                </div>
            </div>
            <!-- <h3 class="section-title">정산할 프로젝트 목록</h3> -->
            <div class="card">
                <h1>정산할 프로젝트 목록</h1>
                <table class="settlement-table">
                    <thead>
                        <tr>
                            <th>프로젝트명</th>
                            <th>프로젝트 기간</th>
                            <th>담당자</th>
                            <th>담당자 연락처</th>
                            <th>지급 금액</th>
                            <th>수수료</th>
                            <th>결제 금액</th>
                            <th>인원</th>
                            <th>결산일</th>
                            <th>결산</th>
                            <th class="settlement-col">정산</th> <!-- 가운데 정렬 적용 -->
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="entry" items="${projectList}">
                        <c:set var="p" value="${entry.value}" />
                        <tr>
                            <td>${p.projectName}</td>
                            <td>${p.projectDuration}</td>
                            <td>${p.projectManager}</td>
                            <td>${p.managerPhone}</td>
                            <td><fmt:formatNumber value="${p.totalAmount}" type="currency" currencySymbol="₩"/></td>
                            <td><fmt:formatNumber value="${p.totalFee}" type="currency" currencySymbol="₩"/></td>
                            <td><fmt:formatNumber value="${p.totalSettlement}" type="currency" currencySymbol="₩"/></td>
                            <td>${p.participant}명</td>
                            <td>${p.settleDate}일</td>
                            <td>
                                <select class="settlement-status-select">
                                    <option ${p.clientStatus eq 'N' ? 'selected' : ''}>미납</option>
                                    <option ${p.clientStatus eq 'T' ? 'selected' : ''}>미결산</option>
                                    <option ${p.clientStatus eq 'C' ? 'selected' : ''}>완료</option>
                                </select>
                            </td>
                            <td class="settlement-col">
                                <c:choose>
                                    <c:when test="${p.settleStatus eq '진행중' || p.settleStatus eq '정산완료'}">
                                        <a href="<c:url value='/admin/settlement'>
                        <c:param name='slistid' value='${p.projectId}'/>
                    </c:url>" class="settlement-btn">정산하기</a>
                                        <a href="<c:url value='/admin/settlement'>
                        <c:param name='contractid' value='${p.projectId}'/>
                    </c:url>" class="settlement-btn light">정산내역</a>
                                    </c:when>
                                    <c:otherwise>
                                        <div style="height: 38px;"></div>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
                <div style="text-align: right; margin-top: 16px;">
                    <button class="settlement-save-btn">결산 상태 저장</button>
                </div>
            </div>
        </div>
    </div>
</body>

</html>