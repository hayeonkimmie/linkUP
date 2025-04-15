<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>참여자 정산 내역</title>
    <link rel="stylesheet" href="../css/settlement_info_freelancer.css" />
    <link rel="stylesheet" href="../css/admin_header.css">
    <link rel="stylesheet" href="../css/settlement_detail.css">
    <script src="../js/include_common.js"></script>
</head>

<body>
    <div id="header-include"></div>

    <div class="main-layout">
        <div id="menu-include"></div>
        <div class="background">

            <div class="settlement-wrapper">
                <h2 class="page-title">참여자 정산 내역</h2>

                <!-- 참여자 정보 요약 -->
                <section class="participant-summary">
                    <div><strong>이름:</strong> 김현수</div>
                    <div><strong>전화번호:</strong> 010-1234-5678</div>
                    <div><strong>입금 계좌:</strong> 국민은행 123456-78-901234</div>
                    <div><strong>총 정산 금액:</strong> 12,000,000원</div>
                </section>

                <!-- 정산 상세 테이블 -->
                <section class="settlement-table-section">
                    <table class="settlement-table">
                        <thead>
                            <tr>
                                <th>정산일</th>
                                <th>프로젝트명</th>
                                <th>역할</th>
                                <th>참여 기간</th>
                                <th>정산 금액</th>
                                <th>지급 상태</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>2024-05-14</td>
                                <td>블록체인 플랫폼 개발</td>
                                <td>고급 개발자</td>
                                <td>3개월</td>
                                <td>12,000,000원</td>
                                <td><span class="paid">지급 완료</span></td>
                            </tr>
                            <!-- 🔽 더 많은 항목 추가 가능 -->
                        </tbody>
                    </table>
                </section>

                <div class="action-buttons">
                    <button onclick="history.back()">뒤로가기</button>
                    <button class="download-btn">엑셀 다운로드</button>
                </div>
            </div>
        </div>
    </div>

    <script src="../js/include_common.js"></script>
</body>

</html>