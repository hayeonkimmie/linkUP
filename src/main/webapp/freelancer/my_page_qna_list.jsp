<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>문의 내역 - Link up</title>
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/headerSt.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page_qna_log.css'/>">
    <script src="${contextPath}/js/freelancer_my_get_inquiry_log.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

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
    <main class="content">
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>문의 내역</h3>
                </div>
            </div>
            <c:choose>
                <c:when test="${empty QnAList}">
                    <div class="qna-empty empty">
                        <p>등록된 문의사항이 없습니다.</p>
                    </div>
                </c:when>
                <c:otherwise>
                <table class="inquiry-table">
                    <thead>
                    <tr>
                        <th>제목</th>
                        <th>상태</th>
                        <th>등록일</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="qna" items="${QnAList }">
                            <tr class="accordion-toggle">
                                <td>${qna.questionTitle}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${qna.answerContent eq null}">
                                            <span class="status waiting">답변 대기중</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="status completed">답변 완료</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><fmt:formatDate value="${qna.questionDate}" pattern="yyyy-MM-dd" /></td>
                            </tr>
                            <tr class="accordion-content qna-detail">
                                <td colspan="3">
                                    <div class="accordion-content-inner question">
                                        <h4>문의 내용</h4>
                                        <p>${qna.questionContent }</p>
                                        <hr/>
                                        <c:choose>
                                            <c:when test="${qna.answerContent ne null}">
                                                <h4>답변 내용</h4>
                                                <p>${qna.answerContent }</p>
                                                <td><fmt:formatDate value="${qna.answerDate}" pattern="yyyy-MM-dd" /></td>
                                            </c:when>
                                            <c:otherwise>
                                                <h4>답변 내용</h4>
                                                <p>답변이 등록되지 않았습니다.</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- 페이지네이션 -->
                <div class="pagination" id="paging">
                    <c:choose>
                        <c:when test="${pageInfo.curPage > 1}">
                            <a href="page=${pageInfo.curPage-1 }">&lt;</a>
                        </c:when>
                        <c:otherwise>
                            <a>&lt;</a>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1" var="page">
                        <c:choose>
                            <c:when test="${page eq pageInfo.curPage }">
                                <a href="page=${page }" class="select">${page }</a>
                            </c:when>
                            <c:otherwise>
                                <a href="page=${page }" class="btn">${page }</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${pageInfo.curPage<pageInfo.allPage }">
                            <a href="page=${pageInfo.curPage+1 }">&gt;</a>
                        </c:when>
                        <c:otherwise>
                            <a>&lt;</a>
                        </c:otherwise>
                    </c:choose>
                </div>
                </c:otherwise>
            </c:choose>
        </section>
    </main>
</div>
</body>

</html>