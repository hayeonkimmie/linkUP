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

    <div></div>

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
        <div class="card">
            <h1>정산할 프로젝트 목록</h1>
            <table class="settlement-table">
                <thead>
                <tr>
                    <th>프로젝트명</th>
                    <th>프로젝트 기간</th>
                    <th>담당자</th>
                    <th>담당자 연락처</th>
                    <th>총 정산 금액</th>
                    <th>수수료</th>
                    <th>결제 금액</th>
                    <th>인원</th>
                    <th>결산일</th>
                    <th>결산</th>
                    <th class="settlement-col">정산</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="entry" items="${projectList}">
                    <c:set var="p" value="${entry.value}" />
                    <tr>
                        <input type="hidden" name="projectId" value="${p.projectId}" />
                        <td>${p.projectName}</td>
                        <td>${p.projectDuration}</td>
                        <td>${p.projectManager}</td>
                        <td>${p.managerPhone}</td>
                        <td><fmt:formatNumber value="${p.totalAmount}" type="currency" currencySymbol="₩"/></td>
                        <td><fmt:formatNumber value="${p.totalFee}" type="currency" currencySymbol="₩"/></td>
                        <td><fmt:formatNumber value="${p.totalSettlement}" type="currency" currencySymbol="₩"/></td>
                        <td>${p.totalContracts - p.settledCount}명</td>
                        <td>${p.settleDate}일</td>
                        <td>
                            <select class="settlement-status-select">
                                <option value="N" ${p.clientStatus eq 'N' ? 'selected' : ''}>미납</option>
                                <option value="T" ${p.clientStatus eq 'T' ? 'selected' : ''}>미결산</option>
                                <option value="C" ${p.clientStatus eq 'C' ? 'selected' : ''}>완료</option>
                            </select>
                        </td>
                        <td class="settlement-col">
                            <c:choose>
                                <c:when test="${p.clientStatus eq 'C'}">
                                    <a href="<c:url value='/admin/settlement'><c:param name='contractid' value='${p.projectId}'/></c:url>" class="settlement-btn">정산하기</a>
                                    <a href="<c:url value='/admin/settlement'><c:param name='slistid' value='${p.projectId}'/></c:url>" class="settlement-btn light">정산내역</a>
                                </c:when>
                                <c:when test="${p.clientStatus eq 'T'}">
                                    <a href="<c:url value='/admin/settlement'><c:param name='slistid' value='${p.projectId}'/></c:url>" class="settlement-btn light">정산내역</a>
                                </c:when>
                                <c:when test="${p.clientStatus eq 'N'}">
                                    <button class="settlement-btn request-btn">정산 요청</button>
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
<script>
    document.querySelector(".settlement-save-btn").addEventListener("click", () => {
        const updates = [];
        document.querySelectorAll("tbody tr").forEach(row => {
            const projectId = row.querySelector("[name='projectId']")?.value;
            const status = row.querySelector(".settlement-status-select")?.value;
            if (projectId && status) {
                updates.push({ projectId, status });
            }
        });
        fetch("/linkup/admin/update-client-status", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(updates)
        })
            .then(res => {
                if (res.ok) {
                    alert("결산 상태가 저장되었습니다.");
                    location.reload();
                } else {
                    alert("❌ 저장 실패");
                }
            })
            .catch(err => {
                console.error("❌ 오류 발생:", err);
            });
    });
</script>
</body>

</html>
