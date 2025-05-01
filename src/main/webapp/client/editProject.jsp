<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>프로젝트 수정</title>
    <link rel="stylesheet" href="${contextPath}/css/common/headerLoginSt.css" />
    <link rel="stylesheet" href="${contextPath}/css/client/sideBar.css" />
    <link rel="stylesheet" href="${contextPath}/css/client/editProject.css">
</head>
<body>
<div id="header-placeholder"></div>

<div class="layout">
    <!-- 공통 사이드바 include -->
    <jsp:include page="../common/sidebar.jsp" />

    <!-- 메인 컨텐츠 -->
    <main class="main">
        <h2 class="section-title">프로젝트 수정</h2>

        <!-- 수정용 form -->
        <form id="job-form" action="${contextPath}/editProject" method="post">
            <!-- 숨겨진 projectId -->
            <input type="hidden" name="projectId" value="${project.projectId}" />

            <!-- 기본 정보 -->
            <h3>구인 등록</h3>
            <div class="form-grid">
                <div class="form-group">
                    <label>공고 제목 <span class="text-red-500">*</span></label>
                    <input name="advertisementTitle" type="text" value="${project.advertisementTitle}" required />
                </div>

                <div class="form-group">
                    <label>프로젝트명 <span class="text-red-500">*</span></label>
                    <input name="projectName" type="text" value="${project.projectName}" required />
                </div>

                <div class="form-group">
                    <label>모집 분야 <span class="text-red-500">*</span></label>
                    <input type="text" name="jobPosition" value="${project.jobPosition}" placeholder="예: 백엔드,프론트엔드" required />
                </div>

                <div class="form-group">
                    <label>근무 방식 <span class="text-red-500">*</span></label>
                    <select name="workingMethod" required>
                        <option value="remote" ${project.workingMethod == 'remote' ? 'selected' : ''}>원격</option>
                        <option value="onsite" ${project.workingMethod == 'onsite' ? 'selected' : ''}>상주</option>
                        <option value="hybrid" ${project.workingMethod == 'hybrid' ? 'selected' : ''}>혼합</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>근무 시간</label>
                    <input name="workingHours" type="text" value="${project.workingHours}" />
                </div>

                <div class="form-group">
                    <label>근무 기간 (개월) <span class="text-red-500">*</span></label>
                    <input name="duration" type="number" value="${project.duration}" required min="1" />
                </div>

                <div class="form-group">
                    <label>근무 지역 <span class="text-red-500">*</span></label>
                    <input name="workingEnvironment" type="text" value="${project.workingEnvironment}" required />
                </div>
            </div>

            <!-- 필수 기술 및 우대 조건 -->
            <h3>필수 기술 및 우대 조건</h3>
            <div class="form-grid">
                <div class="form-group">
                    <label>필수 기술 <span class="text-red-500">*</span></label>
                    <input name="reqSkills" type="text" value="${project.reqSkills}" required />
                </div>

                <div class="form-group">
                    <label>우대 기술</label>
                    <input name="wantedSkills" type="text" value="${project.wantedSkills}" />
                </div>
            </div>

            <!-- 업무 내용 -->
            <h3>업무 내용</h3>
            <div class="form-grid">
                <div class="form-group" style="grid-column: 1 / -1">
                    <label>프로젝트 개요 <span class="text-red-500">*</span></label>
                    <textarea name="projectDescription" required>${project.projectDescription}</textarea>
                </div>

                <div class="form-group" style="grid-column: 1 / -1">
                    <label>상세 업무 <span class="text-red-500">*</span></label>
                    <textarea name="jobDetails" required>${project.jobDetails}</textarea>
                </div>
            </div>

            <!-- 지원자격 -->
            <h3>지원자격</h3>
            <div class="form-grid">
                <div class="form-group" style="grid-column: 1 / -1">
                    <label>필수 경험 <span class="text-red-500">*</span></label>
                    <textarea name="qualification" required>${project.qualification}</textarea>
                </div>

                <div class="form-group" style="grid-column: 1 / -1">
                    <label>우대 사항</label>
                    <textarea name="preferentialConditions">${project.preferentialConditions}</textarea>
                </div>

                <div class="form-group">
                    <label>모집 마감 기한 <span class="text-red-500">*</span></label>
                    <input name="deadlineDate" type="date" value="${project.deadlineDate}" required />
                </div>
            </div>

            <!-- 연락처 설정 -->
            <h3>연락처 설정</h3>
            <div class="form-grid">
                <div class="form-group">
                    <label>담당자 이름 <span class="text-red-500">*</span></label>
                    <input name="manager" type="text" value="${project.manager}" required />
                </div>

                <div class="form-group">
                    <label>담당자 이메일 <span class="text-red-500">*</span></label>
                    <input name="memail" type="email" value="${project.memail}" required />
                </div>

                <div class="form-group">
                    <label>담당자 전화번호 <span class="text-red-500">*</span></label>
                    <input name="mphone" type="tel" value="${project.mphone}" required />
                </div>

                <div class="form-group">
                    <label>세부 분야 ID <span class="text-red-500">*</span></label>
                    <input name="subCategoryId" type="number" value="${project.subCategoryId}" required />
                </div>
            </div>

            <div class="button-group-wrapper">
                <button type="submit" class="btn-primary">프로젝트 수정하기</button>
            </div>
        </form>
    </main>
</div>

<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
</body>
</html>