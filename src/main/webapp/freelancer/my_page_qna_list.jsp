<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>문의 내역 - Link up</title>
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page_qna_log.css'/>">
    <script src="../js/freelancer_my_get_inquiry_log.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

</head>
<body>
<div class="header" style="height: 100px;">
    <!-- 헤더 인클루드 영역 -->
</div>
<div class="container">
    <aside class="sidebar">
        <div class="profile">
            <img src="./../img/basic_profile_img.png" alt="profile"/>
            <p>닉네임</p>
            <p>마이페이지</p>
        </div>
        <ul>
            <li class="profile-settings">
                <h3>프로필 설정</h3>
                <a href="#">기본 정보 설정</a>
                <a href="#">전문가 정보 설정</a>
            </li>
            <li><h3><a href="#">포트폴리오</a></h3></li>
            <li><h3><a href="#">찜한 프로젝트</a></h3></li>
            <li><h3><a href="#">지원한 프로젝트 내역</a></h3></li>
            <li><h3><a href="#">진행중인 / 완료된 프로젝트</a></h3></li>
            <li><h3><a href="#">프로젝트 후기</a></h3></li>
            <li><h3><a href="#">문의내역</a></h3></li>
        </ul>
    </aside>
    <main class="content">
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>문의 내역</h3>
                </div>
            </div>
            <table class="inquiry-table">
                <thead>
                <tr>
                    <th>제목</th>
                    <th>상태</th>
                    <th>등록일</th>
                </tr>
                </thead>
                <tbody>
                <tr class="accordion-toggle">
                    <td>포트폴리오 등록 관련 문의 드립니다.</td>
                    <td><span class="status waiting">답변 대기중</span></td>
                    <td>2025년 5월 15일</td>
                </tr>
                <tr class="accordion-content">
                    <td colspan="3">
                        <div>
                            <div>
                                <strong>문의제목</strong><br>
                                <span>2025-04-13</span>
                            </div>
                            <div>
                                구직자가 문의한 내용<br><br>
                            </div>
                        </div>
                        <div>
                            <div>
                                <strong>답변</strong><br>
                                <span>2025-04-13</span>
                            </div>
                            <div>
                                관리자 답변 예정<br><br>
                            </div>
                        </div>
                    </td>
                </tr>

                <tr class="accordion-toggle">
                    <td>프로젝트 지원 관련 문의 드립니다.</td>
                    <td><span class="status completed">답변 완료</span></td>
                    <td>2025년 5월 10일</td>
                </tr>
                <tr class="accordion-content">
                    <td colspan="3">
                        <strong>문의내용:</strong><br>
                        지원 절차가 헷갈립니다.<br><br>
                        <strong>답변:</strong><br>
                        관리자 답변 완료
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="pagination">
              <a href="#">&laquo;</a>
              <a href="#">1</a>
              <a href="#" class="active-page">2</a>
              <a href="#">3</a>
              <a href="#">4</a>
              <a href="#">5</a>
              <a href="#">6</a>
              <a href="#">&raquo;</a>
            </div>
            <!-- 페이지네이션 -->
        </section>
    </main>
</div>
</body>

</html>