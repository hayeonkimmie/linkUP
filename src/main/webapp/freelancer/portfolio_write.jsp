<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile Write</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <%--    <link rel="stylesheet" href="<c:url value='/css/common/headerSt.css'/>">--%>
    <%--    <link rel="stylesheet" href="${contextPath}/css/headerSt.css">--%>

    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_main_portfolio_write_and_modify.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_main_portfolio.css'/>">
    <%--    <script src="../js/freelancer_my_page_portfolio_write.js"></script>--%>
    <%--<script src="<c:url value='/js/freelancer_my_page_portfolio_write.js'/>"></script>--%>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div id="header-placeholder"></div>

<div class="container">
    <jsp:include page="/freelancer/sidebar.jsp"/>

    <main class="content">
        <section class="section">
            <h3>포트폴리오 작성</h3>
        </section>

        <form id="portfolioForm" method="post"  action="portfolio-write" enctype="multipart/form-data">
            <div class="portfolio-register-container">

                <!-- 포트폴리오 제목 -->
                <div class="portfolio-title-section">
                    <input type="text" name="title" id="title" class="portfolio-title-input" placeholder="포트폴리오 제목을 입력해 주세요" required value=""/>
                </div>

                <div class="thumbnail-project-info">
                    <!-- 썸네일 업로드 -->
                    <div class="thumbnail-upload-section">
                        <div class="thumbnail-upload-box">
                            <label class="upload-placeholder">
                                <img src="${contextPath}/img/default_upload.png" alt="이미지선택" id="preview"  style="width: 100%; height: auto; border-radius: 8px;" onclick="document.getElementById('thumbnail').click();"/>
                            </label>
                        </div>
                    </div>

                    <div class="project-info-right">
                        <!-- 프로젝트 기간 -->
                        <div class="project-period">
                            <span>프로젝트 기간</span>
                            <input id="portProjStart" name="portProjStart" type="month" value=""/>
                            ~
                            <input id="portProjEnd" name="portProjEnd" type="month" value=""/>
                        </div>

                        <!-- 팀 구성 및 역할 -->
                        <div class="team-role">
                            <span>팀 구성 및 역할</span>
                            <input type="text" id="teamRole" name="teamRole" placeholder="ex) 개인 프로젝트 or FE 개발 4명, BE 개발 5명" required value=""/>
                        </div>
                    </div>
                </div>

                <!-- 스킬 -->
                <div class="skills-section">
                    <span>스킬</span>
                    <input type="text" id="skills" placeholder="스킬을 입력하고 쉼표(,) 또는 Enter 키로 구분해주세요" />
                    <a id="clear-skills">초기화</a>
                    <div class="skill-tags" style="margin-top: 10px;">
                    </div>
                </div>
                <!-- 포트폴리오 소개 -->
                <div class="portfolio-description">
                    <span>포트폴리오 소개</span>
                    <textarea id="introduction" name="introduction" placeholder="프로젝트 소개 및 기여 내용을 서술해 주세요"></textarea>
                </div>

                <!-- 첨부파일 등록 -->
                <!-- 첨부파일 등록 -->
                <div class="file-upload-section">
                    <h3>첨부파일</h3>
                    <div class="file-upload-buttons">
                        <button id="add-project-log" type="button">진행했던 프로젝트 이력 불러오기</button>
                        <button id="add-outer-url" type="button">외부 URL 추가</button>
                        <button id="add-attachment-file" type="button">내 PC에서 파일 첨부</button>
                    </div>
                    <table>
                        <tbody>

                        </tbody>
                    </table>
                </div>

                <!-- 버튼 그룹 -->
                <div class="button-group">
                    <!-- 임시저장 -->
                    <%--<button id="temp-submit-btn" type="button">임시저장</button>--%>
                    <!-- 등록 검증 후 제출 -->
                    <button id="submit-btn" type="submit">포트폴리오 등록</button>
                    <!-- 목록 이동 -->
                    <button id="list-btn" type="button" class="list-btn" >목록</button>
                </div>
                <input type="file" id="thumbnail" name="thumbnail" accept="image/*" style="display:none"/>
                <input type="hidden" id="skill-description-hidden" name="skillDescriptionHidden"/>
                <input type="hidden" id="external-url-hidden" name="externalUrlListHidden"/>
                <input type="hidden" id="attachment-hidden" name="attachmentListHidden"/>
                <div class="portfolioIdList" id="portfolioIdList" style="display:none; ">
                    <c:choose>
                        <c:when test="${empty projectInfoMap}">
                            <option value="">조회된 프로젝트가 없습니다.</option>
                        </c:when>
                        <c:otherwise>
                            <option value="">참여했던 프로젝트 명을 선택해주세요.</option>
                            <c:forEach var="project" items="${projectInfoMap }">
                                <%--                                <option value="${project.key}">${project.value.projectName}</option>--%>
                                <option value="${project.key}">${project.value}</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <input type="hidden" id="tempSaved" name="tempSaved" value=""/>
            </div>
        </form>
    </main>
</div>
<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
<script>
    function readURL(input) {
        if(input.files && input.files[0]){
            var reader = new FileReader();
            reader.onload = function(e){
                document.getElementById("preview").src = e.target.result;
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    // 파일 선택(change) 시 실행할 이벤트 핸들러 등록
    document.addEventListener('DOMContentLoaded', function() {
        const input = document.getElementById('thumbnail');
        if (input) {
            input.addEventListener('change', function() {
                console.log("선택된 파일:", input.files[0]);
                readURL(input);
            });
        }
    });

    const listBtn = document.getElementById('list-btn');
    // 목록: 확인창 후 이동

    listBtn.addEventListener('click', () => {
        if (confirm('입력한 내용이 저장되지 않습니다. 목록으로 이동하시겠습니까?')) {
            location.href = '${contextPath}/my-page/portfolio-list';
        }
    });

</script>
<%--<script src="${contextPath}/js/freelancer_my_page_portfolio_modify.js"></script>--%>
<script src="${contextPath}/js/freelancer_my_page_portfolio_write.js"></script>
</body>
</html>