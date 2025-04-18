<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile Write</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/headerSt.css'/>">
    <%--    <link rel="stylesheet" href="${contextPath}/css/headerSt.css">--%>

    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_main_portfolio_write_and_modify.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_main_portfolio.css'/>">
    <%--    <script src="../js/freelancer_my_page_portfolio_write_and_modify.js"></script>--%>
    <%--<script src="<c:url value='/js/freelancer_my_page_portfolio_write_and_modify.js'/>"></script>--%>
    <script src="${contextPath}/js/freelancer_my_page_portfolio_write_and_modify.js"></script>
</head>

<body>
<div class="header">
    <jsp:include page="/common/header.jsp"/>
</div>

<div class="container">
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <main class="content">
        <section class="section">
            <h3>포트폴리오 수정</h3>
        </section>

        <form id="portfolioForm" method="post" action="portfolio-modify" enctype="multipart/form-data">
            <div class="portfolio-register-container">
                <!-- 포트폴리오 제목 -->
                <div class="portfolio-title-section">
                    <span>포트폴리오 제목</span>
                    <input type="text" name="title" id="title" class="portfolio-title-input" placeholder="포트폴리오 제목을 입력해 주세요" required value="title"/>
                </div>

                <!-- 썸네일 업로드 -->
                <div class="thumbnail-upload-section">
                    <span>포트폴리오 썸네일 사진</span>
                    <div class="thumbnail-upload-box">
                        <label class="upload-placeholder">
                            <img src="${portfolio.thumbnail}" alt="이미지선택" id="preview" width="320px" height="inherit" onclick="document.getElementById('thumbnail').click();"/>
                        </label>
                    </div>
                </div>

                <!-- 프로젝트 기간 -->
                <div class="project-period">
                    <span>프로젝트 기간</span>
                    <input id="portProjStart" name="portProjStart" type="month" required value="
                        <c:if test="${not empty portfolio.portProjStart}">
                            <fmt:formatDate value="${portfolio.portProjStart}" pattern="yyyy-MM"/>
                        </c:if>
                    "/>
                    ~
                    <input id="portProjEnd"  name="portProjEnd" type="month" value="
                    <c:if test="${not empty portfolio.portProjEnd}">
                        <fmt:formatDate value="${portfolio.portProjEnd}" pattern="yyyy-MM"/>
                    </c:if>
                    "/>
                </div>

                <!-- 팀 구성 및 역할 -->
                <div class="team-role">
                    <span>팀 구성 및 역할</span>
                    <input type="text" id="teamRole" name="teamRole" placeholder="ex) 개인 프로젝트 or FE 개발 4명, BE 개발 5명" required value=" ${portfolio.teamRole }"/>
                </div>

                <!-- 스킬 -->
                <div class="skills-section">
                    <span>스킬</span>
                    <input type="text" id="skills" placeholder="스킬을 입력하고 쉼표(,) 또는 Enter 키로 구분해주세요" />
                    <div class="skill-tags" style="margin-top: 10px;">
                        <c:forEach var="skill" items="${portfolio.skillList }">
                            <span>${skill}</span>
                        </c:forEach>
                    </div>
                    <button type="button" id="clear-skills">초기화</button>
                </div>
                <!-- 포트폴리오 소개 -->
                <div class="portfolio-description">
                    <span>포트폴리오 소개</span>
                    <textarea id="introduction" name="introduction" placeholder="프로젝트 소개 및 기여 내용을 서술해 주세요">${portfolio.introduce }</textarea>
                </div>

                <!-- 첨부파일 등록 -->
                <div class="file-upload-section">
                    <h3>첨부파일</h3>
                    <%--<button id="add-project-log" type="button">진행했던 프로젝트 이력 불러오기</button>--%>
                    <button id="add-outer-url" type="button">외부 URL 추가</button>
                    <button id="add-attachment-file" type="button">내 PC에서 파일 첨부</button>
                    <table>
                        <tbody>
                            <c:if test="${portfolio.projectId ne null}">
                                <tr>
                                    <td><label>링크업을 통해 참여했던 프로젝트</label>
                                    </td>
                                    <td>
                                        <a href="/projectDetail?id=${portfolio.projectId }">${portfolio.projectName }</a>
                                    </td>
                                    <td><button type="button">X</button></td>
                                </tr>
                            </c:if>
                            <c:if test="${portfolio.externalUrlList ne null}">
                                <c:forEach var="externalUrl" items="${portfolio.externalUrlList }">
                                    <tr>
                                        <td><label>외부 링크</label></td>
                                        <td>
                                            <a href="${externalUrl }">${externalUrl }</a>
                                        </td>
                                        <td><button type="button">X</button></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${portfolio.attachmentList ne null}">
                                <c:forEach var="attachment" items="${portfolio.attachmentList }">
                                    <tr>
                                        <td><label>첨부파일</label></td>
                                        <td>
                                            <a href="fileDown?filename=${attachment }">${attachment }</a>
                                        </td>
                                        <td><button type="button">X</button></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>

                <!-- 버튼 그룹 -->
                <div class="button-group">
                    <button id="submit-btn" type="submit">포트폴리오 등록</button>
                    <!-- 목록 이동 -->
                    <button id="list-btn" type="button">목록</button>
                    <button id="temp-submit-btn" type="button">포트폴리오 임시저장</button>
                    <button id="delete-btn">포트폴리오 삭제</button>
                </div>
                <input type="file" id="thumbnail" name="thumbnail" accept="image/*" style="display:none"/>
                <input type="hidden" name="skillDescription" id="skillDescriptionHidden" value="" />
            </div>
        </form>
    </main>
</div>
<script>
</script>
</body>
</html>