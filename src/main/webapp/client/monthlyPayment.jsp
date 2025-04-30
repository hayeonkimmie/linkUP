<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>월별 정산하기</title>
    <link rel="stylesheet" href="${contextPath}/css/common/headerLoginSt.css"/>
    <link rel="stylesheet" href="${contextPath}/css/client/monthlySettlement.css" />
    <link rel="stylesheet" href="${contextPath}/css/client/sideBar.css" />

</head>
<body>

<div id="header-placeholder"></div>
<div class="layout">
    <!-- 공통 사이드바 include -->
    <jsp:include page="../common/sidebar.jsp" />

    <div class="main">
        <div class="header">
            <h1>월별 정산하기</h1>
        </div>

        <div class="project-info">
            <h2>백오피스 개선</h2>
            <div class="project-details">
                <div class="detail-item">
                    <p><strong>프로젝트 기간:</strong> 2025-04-28 ~ 2025-06-27</p>
                </div>
                <div class="detail-item">
                    <p><strong>총 프로젝트 금액:</strong> 10,665,022원</p>
                </div>
                <div class="detail-item">
                    <p><strong>총 개월 수:</strong> 2개월</p>
                </div>
            </div>
        </div>

        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>정산월</th>
                    <th>정산 기간</th>
                    <th>월별 금액</th>
                    <th>정산 상태</th>
                    <th>작업</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>2025-04</td>
                    <td>2025-04-28 ~ 2025-04-30</td>
                    <td>5,332,511원</td>
                    <td><span class="status pending">미정산</span></td>
                    <td><button class="btn">정산하기</button></td>
                </tr>
                <tr>
                    <td>2025-05</td>
                    <td>2025-05-01 ~ 2025-05-31</td>
                    <td>5,332,511원</td>
                    <td><span class="status completed">완료</span></td>
                    <td><button class="btn" disabled>정산완료</button></td>
                </tr>
                <tr>
                    <td>2025-06</td>
                    <td>2025-06-01 ~ 2025-06-27</td>
                    <td>5,332,511원</td>
                    <td><span class="status pending">미정산</span></td>
                    <td><button class="btn">정산하기</button></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="buttons">
            <a href="#" class="btn btn-secondary">돌아가기</a>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        // 정산하기 버튼에 이벤트 리스너 추가
        const settleBtns = document.querySelectorAll('.btn:not([disabled])');
        settleBtns.forEach(btn => {
            btn.addEventListener('click', function() {
                if (confirm('해당 월의 정산을 진행하시겠습니까?')) {
                    // 버튼 비활성화 및 로딩 표시
                    this.disabled = true;
                    this.textContent = '처리중...';

                    // 실제로는 여기서 AJAX 요청을 보내야 함
                    // 테스트용 타이머
                    setTimeout(() => {
                        alert('정산이 완료되었습니다.');
                        this.textContent = '정산완료';
                        this.disabled = true;
                        const row = this.closest('tr');
                        const statusCell = row.querySelector('.status');
                        statusCell.textContent = '완료';
                        statusCell.classList.remove('pending');
                        statusCell.classList.add('completed');
                    }, 1000);
                }
            });
        });
    });
</script>
<script src="${contextPath}/js/catalog.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
<script src="${contextPath}/js/monthlySettlement.js"></script>
</body>
</html>