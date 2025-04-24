<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page_project_review.css'/>">
    <script src="${contextPath}/js/freelancer_my_page_project_review_write.js"></script>
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
    <div class="content">
        <!-- 페이지 제목 -->
        <div class="topbar">
            <h1>프로젝트 후기</h1>
        </div>

        <!-- 탭 메뉴 -->
        <div class="tabs">
            <button class="tab-btn active" data-tab="write">후기 작성하기</button>
        </div>

        <!-- 탭 콘텐츠 -->
        <div class="tab-content-container">
            <!-- 후기 작성하기 (form + 아코디언) -->
            <div class="tab-content active" id="write">
                <div class="accordion-item">
                    <!-- 항목 1 -->
                <c:choose>
                    <c:when test="${empty unWrittenReviewList} || ${unWrittenReviewList eq null}">
                        <div class="no-review">
                            <p>작성할 수 있는 리뷰가 없습니다.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="review" items="${unWrittenReviewList}">
                            <div class="accordion-header">
                                <div class="review-card">
                                    <div class="card-header">
                                        <div class="user"><img
                                                src="${contextPath}/image?filename=${review.rUserProfileImg}"/>${review.rUserInfo}
                                        </div>
                                        <div class="project">${review.projectName}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-body review-form">
                                <form action="${contextPath}/my-page/project-review-write" method="post">
                                    <input type="hidden" name="reviewId" value="${review.reviewId}"/>
                                    <input type="hidden" name="projectId" value="${review.projectId}"/>
                                    <div class="form-row">
                                        <label>별점</label>
                                        <div class="rating-select">
                                            <span class="star" data-value="1">★</span>
                                            <span class="star" data-value="2">★</span>
                                            <span class="star" data-value="3">★</span>
                                            <span class="star" data-value="4">★</span>
                                            <span class="star" data-value="5">★</span>
                                            <input type="text" value="" style="display: none;" name="star"
                                                   required/>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label>후기 내용</label>
                                        <textarea rows="5" name="comment" required
                                                  placeholder="협업 경험에 대해 자유롭게 작성해 주세요."></textarea>
                                    </div>
                                    <div class="btn-group right">
                                        <button type="submit" class="btn light">등록</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        </c:forEach>
                        <div class="pagination" id="paging">
                            <c:choose>
                                <c:when test="${pageInfo.curPage > 1}">
                                    <a href="?page=${pageInfo.curPage-1 }">&lt;</a>
                                </c:when>
                                <c:otherwise>
                                    <a>&lt;</a>
                                </c:otherwise>
                            </c:choose>

                            <c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1" var="page">
                                <c:choose>
                                    <c:when test="${page eq pageInfo.curPage }">
                                        <a href="?page=${page }" class="select">${page }</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="?page=${page }" class="btn">${page }</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:choose>
                                <c:when test="${pageInfo.curPage<pageInfo.allPage }">
                                    <a href="?page=${pageInfo.curPage+1 }">&gt;</a>
                                </c:when>
                                <c:otherwise>
                                    <a>&gt;</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div> <!-- tab-content-container -->
</div> <!-- content -->
</div> <!-- container -->
</body>
</html>