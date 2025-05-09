<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up 포트폴리오 수정</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_main_portfolio.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_main_portfolio_write_and_modify.css'/>">
</head>
<body>
    <!-- 헤더 인클루드 영역 -->
    <div id="header-placeholder"></div>
    <%--    <jsp:include page="../home/header.jsp" />--%>
<div class="container">
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <main class="content">
        <section class="section">
            <h3>포트폴리오 수정</h3>
        </section>

        <form id="portfolioForm" method="post"
              action="${contextPath}/my-page/portfolio-modify?id=${portfolio.portfolioId}"
              enctype="multipart/form-data">
            <div class="portfolio-register-container">
                <!-- 포트폴리오 제목 -->
                <div class="portfolio-title-section">
                    <span style="font-size: 20px;">포트폴리오 제목</span><br/>
                    <input type="text" name="title" id="title" class="portfolio-title-input"
                           placeholder="포트폴리오 제목을 입력해 주세요" required value="${portfolio.title}"/>
                </div>

                <div class="thumbnail-project-info">
                    <!-- 썸네일 업로드 -->
                    <div class="thumbnail-upload-section">
                        <div class="thumbnail-upload-box">
                            <label class="upload-placeholder">
                                <img src="../img/${portfolio.thumbnail}"  alt="이미지선택" id="preview" width="100px"
                                     height="inherit" onclick="document.getElementById('thumbnail').click();"/>
                            </label>
                        </div>
                    </div>
                    <div class="project-info-right">
                        <!-- 프로젝트 기간 -->
                        <c:set var="startMonth" value=""/>
                        <c:if test="${portfolio.portProjStart ne null}">
                            <fmt:formatDate value="${portfolio.portProjStart}" pattern="yyyy-MM" var="startMonth"/>
                        </c:if>

                        <c:set var="endMonth" value=""/>
                        <c:if test="${portfolio.portProjEnd ne null}">
                            <fmt:formatDate value="${portfolio.portProjEnd}" pattern="yyyy-MM" var="endMonth"/>
                        </c:if>
                        <div class="project-period">
                            <span>프로젝트 기간</span> <br/>
                            <input id="portProjStart" name="portProjStart" type="month" required value="${startMonth}"/>
                            ~
                            <input id="portProjEnd" name="portProjEnd" type="month" value="${endMonth}"/>
                        </div>
                        <!-- 팀 구성 및 역할 -->
                        <div class="team-role">
                            <span>팀 구성 및 역할</span><br/>
                            <input type="text" id="teamRole" name="teamRole" placeholder="ex) 개인 프로젝트 or FE 개발 4명, BE 개발 5명"
                                   required value=" ${portfolio.teamRole }"/>
                        </div>
                    </div>
                </div><!-- 프로젝트 기간 -->
                <!-- 스킬 -->
                <div class="skills-section">
                    <h5>스킬</h5>
                    <input type="text" id="skills" placeholder="스킬을 입력하고 쉼표(,) 또는 Enter 키로 구분해주세요"/>
                    <a id="clear-skills">초기화</a>
                    <div class="skill-tags" style="margin-top: 10px;">
                        <c:if test="${not empty portfolio.skillList }">
                            <c:forEach var="skill" items="${portfolio.skillList }">
                                <span class="skill-tag" data-skill="${skill}">${skill}
                                    <button class="delete-skill-btn">X</button></span>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
                <!-- 포트폴리오 소개 -->
                <div class="portfolio-description">
                    <h5>포트폴리오 소개</h5>
                    <textarea id="introduction" name="introduction"
                              placeholder="프로젝트 소개 및 기여 내용을 서술해 주세요">${portfolio.introduce }</textarea>
                </div>

                <!-- 첨부파일 등록 -->
                <div class="file-upload-section">
                    <h5>첨부파일</h5>
                    <div class="file-upload-buttons">
                        <button id="add-project-log" type="button">진행했던 프로젝트 이력 불러오기</button>
                        <button id="add-outer-url" type="button">외부 URL 추가</button>
                        <button id="add-attachment-file" type="button">내 PC에서 파일 첨부</button>
                    </div>
                    <table>
                        <tbody>
                        <% if (request.getAttribute("portfolio") != null && ((dto.Portfolio) request.getAttribute("portfolio")).getProjectId() != null) { %>
                        <tr class="project-id-section">
                            <td><label for="portfolioId">링크업을 통해 참여했던 프로젝트</label>
                            </td>
                            <td>
                                <% if (request.getAttribute("projectInfoMap") != null && !((java.util.Map) request.getAttribute("projectInfoMap")).isEmpty()) { %>
                                <select class="project-id-select" id="portfolioId" name="projectId"
                                        style="width: 100%; height: 40px;">
                                    <%
                                        java.util.Map<Integer, String> projectInfoMap =
                                                (java.util.Map<Integer, String>) request.getAttribute("projectInfoMap");
                                        dto.Portfolio portfolio = (dto.Portfolio) request.getAttribute("portfolio");

                                        for (java.util.Map.Entry<Integer, String> entry : projectInfoMap.entrySet()) {
                                            Integer key = entry.getKey();
                                            String projectName = entry.getValue();
                                            String selected = "";
                                            if (portfolio.getProjectId() != null && key.equals(portfolio.getProjectId())) {
                                                selected = "selected";
                                            }
                                    %>
                                    <option value="<%= key %>" <%= selected %>>
                                        <%= projectName %>
                                    </option>
                                    <% } %>
                                </select>
                                <% } %> </td>
                            <td>
                                <button type="button" class="delete-tr">X</button>
                            </td>
                        </tr>
                        <% } %>
                        <c:if test="${portfolio.externalUrlList ne null}">
                            <c:forEach var="externalUrl" items="${portfolio.externalUrlList}" varStatus="status">
                                <tr class="url-section">
                                    <td><label for="url-${status.index}">외부 링크</label></td>
                                    <td>
                                        <input type="text" value="${externalUrl}" name="url-${status.index}"
                                               id="url-${status.index}" placeholder="https://example.com"/>
                                    </td>
                                    <td>
                                        <button type="button" class="delete-tr">X</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${portfolio.attachmentList ne null}">
                            <c:forEach var="attachment" items="${portfolio.attachmentList}" varStatus="status">
                                <tr class="file-section">
                                    <td><label for="attachment-${status.index}">첨부파일 <p class="attachment-notification ">10mb 이하의 파일을 올려주세요.</p></label></td>
                                    <td>
                                        <a>${attachment}</a>
                                        <span onclick="document.getElementById('attachment-${status.index}').click();">파일 변경</span>
                                        <input style="display: none" type="file" name="attachment-${status.index}"
                                               id="attachment-${status.index}"
                                               accept=".pdf, .doc, .docx, .ppt, .pptx, .xls, .xlsx"/>
                                    </td>
                                    <td>
                                        <button type="button" class="delete-tr">X</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
                <!-- 버튼 그룹 -->
                <div class="button-group">
                    <!-- 임시저장 -->
                    <%--                    <button id="temp-submit-btn" type="button">임시저장</button>--%>
                    <!-- 등록 검증 후 제출 -->
                    <button id="submit-btn" type="submit" class="save-btn">포트폴리오 수정</button>
                    <!-- 목록 이동 -->
                    <button id="list-btn" type="button">목록</button>
                    <button class="delete-btn" id="delete-btn"
                    <%-- onclick="location.href='${contextPath}/my-page/portfolio-delete?id=${portfolio.portfolioId}'--%>
                    ">
                    포트폴리오 삭제
                    </button>
                </div>
                <input type="file" id="thumbnail" name="thumbnail" accept="image/*" style="display:none"/>

                <div class="portfolioIdList" id="portfolioIdList" style="display:none; ">
                    <c:if test="${projectInfoMap ne null}">
                        <option value="">참여했던 프로젝트 명을 선택해주세요.</option>
                        <c:forEach var="project" items="${projectInfoMap }">
                            <option value="${project.key}">${project.value}</option>
                        </c:forEach>
                    </c:if>
                    <c:if test="${projectInfoMap eq null}">
                        <option value="">조회된 프로젝트가 없습니다.</option>
                    </c:if>
                </div>
                <input type="hidden" id="tempSaved" name="tempSaved" value=""/>
                <input type="hidden" name="skillDescription" id="skillDescriptionHidden"
                       value="<c:forEach items='${portfolio.skillList}' var='skill' varStatus='status'>${skill}<c:if test='${!status.last}'>,</c:if></c:forEach>"/>
                <input type="hidden" id="externalUrlListHidden" name="externalUrlListHidden" value=""/>
                <input type="hidden" id="attachmentListHidden" name="attachmentHidden" value=""/>
            </div>
        </form>
    </main>
</div>
<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
<%--    <script src="${contextPath}/js/freelancer_my_page_portfolio_modify.js"></script>--%>
<script>
    const listBtn = document.getElementById('list-btn');
    // 목록: 확인창 후 이동
    listBtn.addEventListener('click', () => {
        if (confirm('입력한 내용이 저장되지 않습니다. 목록으로 이동하시겠습니까?')) {
            location.href = `${contextPath}/my-page/portfolio-list`;
        }
    });

    function deletePortfolio() {
        const deleteBtn = document.getElementById('delete-btn');
        deleteBtn.addEventListener('click', () => {
            if (confirm('해당 포트폴리오를 삭제하시겠습니까?')) {
                location.href = `${contextPath}/my-page/portfolio-delete?id=${portfolio.portfolioId}`;
            }
        });
    }
</script>
<script src="${contextPath}/js/freelancer_my_page_portfolio_modify.js"></script>
</body>
</html>