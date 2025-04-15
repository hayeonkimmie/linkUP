<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>프로젝트 상세 정보</title>
    <link rel="stylesheet" href="../css/admin_header.css">
    <link rel="stylesheet" href="../css/project_detail.css">
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
                <h2 class="project-title">서비스 리뉴얼</h2>
                <p class="company-name">테크놀로지 주식회사</p>
                <hr class="divider">
                <div class="section">
                    <h3>📌 프로젝트 설명</h3>
                    <p class="description">
                        기존 웹 서비스의 UI/UX를 개선하고 최신 기술 스택으로 리뉴얼하는 프로젝트입니다.
                        반응형 디자인 적용과 성능 최적화가 주요 목표입니다.
                    </p>
                </div>
                <div class="summary-box">
                    <div class="summary-section">
                        <h4>👤 관리자 정보</h4>
                        <p><strong>이름:</strong> 김민수</p>
                        <p><strong>이메일:</strong> minsu.kim@techcorp.kr</p>
                        <p><strong>연락처:</strong> 010-1234-5678</p>
                    </div>
                    <div class="summary-section">
                        <h4>📅 프로젝트 기간</h4>
                        <p><strong>시작일:</strong> 2023-05-01</p>
                        <p><strong>종료일:</strong> 2023-08-31</p>
                        <p><strong>남은 기간:</strong> <span class="highlight">-578일</span></p>
                    </div>
                </div>
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
                                <tr>
                                    <td>김민수</td>
                                    <td>프로젝트 매니저</td>
                                    <td>minsu.kim@techcorp.kr</td>
                                    <td>010-1234-5678</td>
                                </tr>
                                <tr>
                                    <td>이상철</td>
                                    <td>개발 리더</td>
                                    <td>sangchul.lee@techcorp.kr</td>
                                    <td>010-2345-6789</td>
                                </tr>
                                <tr>
                                    <td>박지영</td>
                                    <td>디자이너</td>
                                    <td>jiyoung.park@techcorp.kr</td>
                                    <td>010-3456-7890</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    </div>
</body>

</html>