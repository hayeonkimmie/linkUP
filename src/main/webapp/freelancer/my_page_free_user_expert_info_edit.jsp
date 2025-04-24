<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <%--    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_main_info_edit.css'/>">--%>
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page_expert_info_edit.css'/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="<c:url value='/js/freelancer_my_page_info_edit.js' />"></script>
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
                        <h3>전문가 정보 설정</h3>
                    </div>
                </div>
                <form action="${contextPath}/edit-info?type=expert" method="post" enctype="multipart/form-data">
                    <!-- 분야 -->
                    <div class="form-group">
                        <div class="category-section">
                            <label>전문 분야</label>
                            <select id="category_name" name="category">
                                <option value="">1차 카테고리</option>
                                <c:forEach items="${categoriesMap}" var="category">
                                    <option value="${category.value}">${category.key}</option>
                                </c:forEach>
                            </select>
                            <select id="subCategory" name="subCategory"></select>
                            <div class="specialization">
                                <c:if test="${freelancer.category ne null}">
                                    <span>${freelancer.category} <a class="delete-category-btn">X</a></span>
                                </c:if>
                                <%--                        <a class="clear_fields clear-category-btn">초기화</a>--%>
                            </div>
                        </div>
                        <!-- 스킬 -->
                        <div class="skills-section">
                            <label>스킬</label>
                            <input type="text" id="skills" name="skillsInput" placeholder="스킬을 입력하고 쉼표(,) 또는 Enter 키로 구분해주세요"/>
                            <a id="clear-skills">초기화</a>
                            <div class="skill-tags-wrapper">
                                <div class="skill-tags" style="margin-top: 10px;">
                                    <c:if test="${freelancer.skillList ne null}">
                                        <c:forEach items="${freelancer.skillList}" var="skill">
                                            <span class="skill-tag">${skill} <a class="delete-skill-btn">X</a></span>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 학력 -->
                    <div class="form-group">
                        <label>학력</label>
                        <div id="education-container">
                            <c:choose>
                                <c:when test="${not empty freelancer.academicList}">
                                    <c:forEach items="${freelancer.academicList}" var="academic">
                                        <div class="education-box">
                                            <a class="button buttonDeleteField">X</a>
                                            <!-- 학력구분 -->
                                            <div class="dropdown input-academicType">
                                                <div class="label">학력구분 <span class="star">*</span></div>
                                                <c:set var="types" value="${fn:split('고등학교,대학교(2,3년),대학교(4년),대학원', ',')}"/>
                                                <select name="academicType" required>
                                                    <option value="">학교 구분</option>
                                                    <c:forEach var="type" items="${types}">
                                                        <option value="${type}" ${academic.academicType eq type ? 'selected' : ''}>${type}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <!-- 학교명 -->
                                            <div class="input input-academicName input-with-icon">
                                                <div class="label">학교명 <span class="star">*</span></div>
                                                <input type="text" name="academicName" value="${academic.academicName}"
                                                       required/>
                                            </div>
                                            <!-- 졸업 상태 -->
                                            <div class="dropdown input-graduateStatus">
                                                <div class="label">졸업상태 <span class="star">*</span></div>
                                                <select name="graduateStatus" required>
                                                    <option value="">졸업 구분</option
                                                    <c:set var="statuses" value="${fn:split('졸업,재학중,중퇴,휴학,기타', ',')}"/>

                                                    <c:forEach var="status" items="${statuses}">
                                                    <option value="${status}" ${academic.graduateStatus eq status ? 'selected' : ''}>${status}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <c:choose>
                                                <c:when test="${academic.academicType ne '고등학교'}">
                                                    <div class="input input-major input-with-icon">
                                                        <div class="label">전공명 <span class="star">*</span></div>
                                                        <input type="text" name="academicMajor"
                                                               value="${academic.academicMajor}" required/>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${academic.academicType eq  '고등학교' }">
                                                        <div class="input input-education-file">
                                                        </div>
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="input input-month-group">
                                                <div class="input-education-date">
                                                    <label>입학연월 *</label>
                                                    <input type="month" name="entranceDate" value="${academic.entranceDate}"
                                                           required/>
                                                </div>
                                                <div class="input-education-date">
                                                    <label>졸업연월 *</label>
                                                    <input type="month" name="graduateDate"
                                                           value="${academic.graduateDate}"/>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="education-box">
                                        <a class="buttonDeleteField">X</a>
                                        <div class="dropdown input-academicType">
                                            <div class="label">학력구분 <span class="star">*</span></div>
                                            <c:set var="types" value="${fn:split('고등학교,대학교(2,3년),대학교(4년),대학원', ',')}"/>
                                            <select name="academicType" required>
                                                <option value="">학교 구분</option>
                                                <c:forEach var="type" items="${types}">
                                                    <option value="${type}">${type}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="dropdown input-graduateStatus">
                                            <div class="label">졸업상태 <span class="star">*</span></div>
                                            <select name="graduateStatus" required>
                                                <option value="">졸업 구분</option>
                                                <c:forEach var="status" items="졸업,재학중,중퇴,휴학,기타">
                                                    <option value="${status}">${status}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <a id="education_addbutton">+ 학력 추가</a>
                        </div>
                    </div>

                    <!-- 경력 -->
                    <div class="form-group">
                        <label>경력</label>
                        <div id="career-container">
                            <c:if test="${not empty careerList}">
                                <c:forEach items="${careerList}" var="career">
                                    <div class="career-box">
                                        <div class="career-row">
                                            <a class="buttonDeleteField">X</a>
                                            <input type="text" name="companyName" placeholder="회사명" required
                                                   value="${career.companyName}">
                                            <input type="text" name="departmentName" placeholder="부서명"
                                                   value="${career.departmentName}">
                                            <input type="month" name="joinDate" placeholder="입사년도" required
                                                   value="${career.joinDate}">
                                            <input type="month" name="resignDate" placeholder="퇴사년도"
                                                   value="${career.resignDate}">
                                        </div>
                                        <div class="career-row">
                                            <input type="text" name="position" placeholder=" 직급/직책" value="${career.position}"/>
                                            <input type="text" name="jobTitle" placeholder="담당직무"
                                                   value="${career.jobTitle}"/>
                                            <input type="number" name="salary" placeholder="연봉" value="${career.salary}">
                                            <span>만 원</span>
                                        </div>
                                        <span>담당업무</span>
                                        <textarea name="jobDescription" placeholder="담당하신 업무와 성과에 대해 간단하게 적어주세요." required>${career.jobDescription}</textarea>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                        <a class="add-career-btn">+ 경력 추가</a>
                    </div>
                    <div class="form form-group formCertificate">
                        <label>자격증</label>
                        <div id="license_containers">
                            <c:if test="${freelancer.licenseList ne null}">
                                <c:forEach items="${freelancer.licenseList}" var="license">
                                    <div class="license-box">
                                        <div class="row">
                                            <div class="input input-certificate-name is-label" data-comp_type="">
                                                <label>자격증 명 <span class="star">*</span></label>
                                                <input type="text" name="licenseName"
                                                       value="${license.licenseName}"
                                                       maxlength="50">
                                            </div>
                                            <div class="input input-certificate-agency is-label">
                                                <label>점수 / 급</label>
                                                <input type="text" name="licenseGrade" value="${license.licenseGrade}"
                                                       maxlength="50">
                                            </div>
                                            <div class="input input-certificate-agency is-label">
                                                <label>발행처</label>
                                                <input type="text" name="licenseAgency" value="${license.licenseAgency}"
                                                       maxlength="50">
                                            </div>
                                            <div class="input input-certificate-term is-label">
                                                <label>취득월</label>
                                                <input type="month" name="licenseDate"
                                                       value="${license.licenseDate}"
                                                       data-format-type="month">
                                            </div>
                                        </div>
                                        <a class="buttonDeleteField">X</a>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                        <a class="button buttonAddField">자격증 추가</a>
                    </div>

                    <!-- 자기소개서 -->
                    <div class="form-group">
                        <label>자기소개서</label>
                        <%--  <input type="text" placeholder="자기소개서 제목" value="" name="introductionTitle"/>--%>
                        <textarea placeholder="자신에 대해 설명해 주세요." name="introduction" style="resize: none" required>${freelancer.introduction}</textarea>
                    </div>

                    <!-- 포트폴리오 -->
                    <div class="file-upload-section">
                        <h3>첨부파일</h3>
                        <div class="file-upload-buttons">
                            <c:if test="${freelancer.allPortfolioInfoMap ne null}"><button id="add-portfolio-info" type="button">등록한 포트폴리오 불러오기</button></c:if>
                            <button id="add-outer-url" type="button">외부 URL 추가</button>
                            <button id="add-attachment-file" type="button">내 PC에서 파일 첨부</button>
                        </div><%--${freelancer.attachment}${freelancer.externalUrl}--%>
                        <table>
                            <tbody>
                            <c:if test="${freelancer.allPortfolioInfoMap ne null}">
                                <tr>
                                    <td><label>링크업을 통해 참여했던 프로젝트</label>
                                    </td>
                                    <td>
                                        <c:if test="${freelancer.allPortfolioInfoMap ne null}">
                                            <select class="project-id-select" id="portfolioId" name="project-id-select">
                                                <c:forEach var="project" items="${freelancer.projectInfoMap }">
                                                    <option value="${project.id }">${project.name }</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <button type="button" class="delete-tr">X</button>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${portfolio.externalUrlList ne null}">
                                <c:forEach var="externalUrl" items="${portfolio.externalUrlList }">
                                    <tr>
                                        <td><label>외부 링크</label></td>
                                        <td>
                                            <input type="text" value="${externalUrl }" name="externalUrl" placeholder="https://example.com"/>
                                            <button type="button" class="delete-tr">X</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${portfolio.attachmentList ne null}">
                                <c:forEach var="attachment" items="${portfolio.attachmentList }">
                                    <tr>
                                        <td><label>첨부파일</label></td>
                                        <td>
                                            <a href="fileDown?filename=${attachment }">${attachment }</a>
                                            <a href="#" id="selectFile" onclick="document.getElementById('dfile').click();">
                                                <c:choose>
                                                    <c:when test="${attachment eq null}">
                                                        <input type="file" id="dfile" name="attachment" accept=".pdf, .doc, .docx, .ppt, .pptx, .xls, .xlsx"/>
                                                        <span>파일 선택</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${attachment }
                                                    </c:otherwise>
                                                </c:choose>
                                            </a>
                                            <button type="button" class="delete-tr">X</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>

                    <!-- 희망근무조건 -->
                    <div class="form-group wanting-condition">
                        <label>희망 근무 조건</label>
                        <div>
                            <!-- 상주 가능 여부는 div.radio-group으로 감싸기 -->
                            <div class="radio-group">
                                <span>상주 가능 여부:</span>
                                <input type="radio" name="onsitePossible" value="Y"
                                ${freelancer.isResident ? 'checked' : ''} /> 가능
                                <input type="radio" name="onsitePossible" value="N"
                                ${!freelancer.isResident ? 'checked' : ''} /> 불가능
                                <input type="checkbox" name="negotiation"
                                ${freelancer.isNegotiable ? 'checked' : ''} /> 협의 가능
                            </div>
                            <div class="salary-wrap">
                                <input type="number" placeholder="희망 급여(월)" name="desiredSalary">
                                <span>만 원</span>
                            </div>
                            <input type="text" placeholder="희망 근무지" name="desiredLocation" value="${freelancer.desiredLocation}">
                            <textarea placeholder="기타 희망사항" name="otherRequest" style="resize: none">${freelancer.otherRequest}</textarea>
                        </div>
                        <input type="hidden" name="skillDescription" id="skillDescriptionHidden" value=""/>
                        <div class="submit-wrap">
                            <button type="submit" class="save-btn">저장</button>
                        </div>
                    </div>
                </form>
            </section>
        </main>
    </div>
<script>

    function changeFile(input) {
        if(input.files && input.files[0]) {
            document.getElementById("selectFile").text = input.files[0].name;
        }
    }
</script>
</body>
</html>