<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page_expert_info_edit.css'/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const contextPath = '${pageContext.request.contextPath}';
    </script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>
<body>

<div id="header-placeholder"></div>
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
            <form action="${contextPath}/my-page/edit-expert-info" method="post" enctype="multipart/form-data">
                <!-- 분야 -->
                <div class="form-group">
                    <div class="category-section">
                        <label>전문 분야</label>
                        <select id="category_name" name="category">
                            <option value="">1차 카테고리</option>
                            <c:forEach items="${selectCategoryList}" var="category">
                                <option value="${category.categoryId}">${category.categoryName}</option>
                            </c:forEach>
                        </select>
                        <select id="subCategory" name="subCategory">
                            <option value="">2차 카테고리</option>
                        </select>
                        <div class="specialization">
                            <c:if test="${freelancer.category ne null}">
                                <span>${freelancer.category}</span>
                            </c:if>
                            <%--                           <a class="clear_fields clear-category-btn">초기화</a>--%>
                        </div>
                    </div>
                    <!-- 스킬 -->
                    <div class="skills-section">
                        <label>스킬</label>
                        <input type="text" id="skills" name="skillsInput"
                               placeholder="스킬을 입력하고 쉼표(,) 또는 Enter 키로 구분해주세요"/>
                        <a id="clear-skills">초기화</a>
                        <div class="skill-tags-wrapper">
                            <div class="skill-tags" style="margin-top: 10px;">
                                <c:if test="${freelancer.skillList ne null}">
                                    <c:forEach items="${freelancer.skillList}" var="skill">
                                        <span class="skill-tag" data-skill="${skill}">${skill} <a
                                                class="delete-skill-btn">X</a></span>
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
                                <c:forEach items="${freelancer.academicList}" var="academic" varStatus="status">
                                    <div class="education-box item-box">
                                        <button type="button" class="buttonDeleteField">X</button>
                                        <div class="dropdown input-academicType">
                                            <div class="label">학력구분 <span class="star">*</span></div>
                                            <select name="educationList[${status.index}].academicType" required
                                                    class="academicType">
                                                <option value="">학교 구분</option>
                                                <c:forEach var="type"
                                                           items="${fn:split('고등학교^대학교(2,3년)^대학교(4년)^대학원', '^')}">
                                                    <option value="${type}" ${academic.academicType eq type ? 'selected' : ''}>${type}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="input input-academicName input-with-icon">
                                            <div class="label">학교명 <span class="star">*</span></div>
                                            <input type="text" name="educationList[${status.index}].academicName"
                                                   value="${academic.academicName}" required/>
                                        </div>
                                        <div class="dropdown input-graduateStatus">
                                            <div class="label">졸업상태 <span class="star">*</span></div>
                                            <select name="educationList[${status.index}].graduateStatus">
                                                <option value="">졸업 구분</option>
                                                <c:forEach var="statusVal" items="${fn:split('졸업,재학중,중퇴,휴학,기타', ',')}">
                                                    <option value="${statusVal}" ${academic.graduateStatus eq statusVal ? 'selected' : ''}>${statusVal}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <c:if test="${academic.academicType ne '고등학교'}">
                                            <div class="input input-major input-with-icon">
                                                <div class="label">전공명 <span class="star">*</span></div>
                                                <input type="text" name="educationList[${status.index}].academicMajor"
                                                       value="${academic.academicMajor}" required/>
                                            </div>
                                        </c:if>
                                        <div class="input input-month-group">
                                            <div class="input-education-date">
                                                <label>입학연월 *</label>
                                                <input type="month" name="educationList[${status.index}].enterDate"
                                                       value="${academic.entranceDate}" required/>
                                            </div>
                                            <div class="input-education-date">
                                                <label>졸업연월 *</label>
                                                <input type="month" name="educationList[${status.index}].graduateDate"
                                                       value="${academic.graduateDate}"/>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div class="education-box item-box">
                                    <button type="button" class="buttonDeleteField">X</button>
                                    <div class="dropdown input-academicType">
                                        <div class="label">학력구분 <span class="star">*</span></div>
                                        <c:set var="types" value="${fn:split('고등학교^대학교(2,3년)^대학교(4년)^대학원', '^')}"/>
                                        <select name="academicType" required>
                                            <option value="">학교 구분</option>
                                            <c:forEach var="type" items="${types}">
                                                <option value="${type}">${type}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="input input-academicName input-with-icon">
                                        <div class="label">학교명 <span class="star">*</span></div>
                                        <input type="text" name="educationList[0].academicName"
                                               value="" required/>
                                    </div>
                                    <div class="dropdown input-graduateStatus">
                                        <div class="label">졸업상태</div>
                                        <select name="educationList[0].graduateStatus">
                                            <option value="">졸업 구분</option>
                                            <c:forEach var="statusVal" items="${fn:split('졸업,재학중,중퇴,휴학,기타', ',')}">
                                                <option value="${statusVal}">${statusVal}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="input input-major input-with-icon academic-major-field">
                                        <div class="label">전공명</div>
                                        <input type="text" name="educationList[0].academicMajor" id="academicMajor_0" placeholder="전공" />
                                    </div>
                                    <div class="input input-month-group">
                                        <div class="input-education-date">
                                            <label>입학연월 </label>
                                            <input type="month" name="educationList[0].enterDate"
                                                   value=""/>
                                        </div>
                                        <div class="input-education-date">
                                            <label>졸업연월 </label>
                                            <input type="month" name="educationList[0].graduateDate"
                                                   value=""/>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <button type="button" class="add-education-btn" id="education_addbutton">+ 학력 추가</button>
                </div>

                <!-- 경력 -->
                <div class="form-group">
                    <label>경력</label>
                    <div id="career-container">
                        <c:if test="${not empty careerList}">
                            <c:forEach items="${careerList}" var="career" varStatus="status">
                                <div class="career-box item-box">
                                    <button type="button" class="buttonDeleteField">X</button>
                                    <div class="career-row">
                                        <input type="text" name="careerList[${status.index}].companyName"
                                               placeholder="회사명" required
                                               value="${career.companyName}"/>
                                        <input type="text" name="careerList[${status.index}].departmentName"
                                               placeholder="부서명"
                                               value="${career.departmentName}"/>
                                        <span>입사</span>
                                        <input name="careerList[${status.index}].joinDate" type="month"
                                               value="<fmt:formatDate value='${career.joinDate}' pattern='yyyy-MM'/>"/>
                                        <span>퇴사</span>
                                        <input name="careerList[${status.index}].resignDate" type="month"
                                               value="<fmt:formatDate value='${career.resignDate}' pattern='yyyy-MM'/>"/>
                                    </div>
                                    <div class="career-row">
                                        <input type="text" name="careerList[${status.index}].position"
                                               placeholder=" 직급/직책"
                                               value="${career.position}"/>
                                        <input type="text" name="careerList[${status.index}].jobTitle"
                                               placeholder="담당직무"
                                               value="${career.jobTitle}"/>
                                        <input type="text" name="careerList[${status.index}].salary" placeholder="연봉"
                                               value="${career.salary}"
                                               oninput="this.value = this.value.replace(/[^0-9]/g, '')">

                                        <span>만 원</span>
                                    </div>
                                    <span>담당업무</span>
                                    <textarea name="careerList[${status.index}].jobDescription"
                                              placeholder="담당하신 업무와 성과에 대해 간단하게 적어주세요."
                                              required>${career.jobDescription}</textarea>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <button type="button" class="add-career-btn">+ 경력 추가</button>
                </div>
                <div class="form form-group formCertificate">
                    <label>자격증</label>
                    <div id="license-container">
                        <c:if test="${freelancer.licenseList ne null}">
                            <c:forEach items="${freelancer.licenseList}" var="license" varStatus="status">
                                <div class="license-box item-box">
                                    <div class="license-row">
                                        <button type="button" class="buttonDeleteField">X</button>
                                        <div class="input input-certificate-name is-label">
                                            <label>자격증 명 <span class="star">*</span></label>
                                            <input type="text" name="licenseList[${status.index}].name"
                                                   value="${license.licenseName}" maxlength="50" required/>
                                        </div>
                                        <div class="input input-certificate-agency is-label">
                                            <label>점수 / 급</label>
                                            <input type="text" name="licenseList[${status.index}].licenseGrade"
                                                   value="${license.licenseGrade}" maxlength="50"/>
                                        </div>
                                        <div class="input input-certificate-agency is-label">
                                            <label>발행처</label>
                                            <input type="text" name="licenseList[${status.index}].licenseAgency"
                                                   value="${license.licenseAgency}" maxlength="50"/>
                                        </div>
                                        <div class="input input-certificate-term is-label">
                                            <label>취득월</label>
                                            <input name="licenseList[${status.index}].licenseDate" type="month"
                                                   value="<fmt:formatDate value='${license.licenseDate}' pattern='yyyy-MM'/>"/>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <button type="button" class="add-license-btn">+ 자격증 추가</button>
                </div>

                <!-- 자기소개서 -->
                <div class="form-group">
                    <label>자기소개서</label>
                    <textarea placeholder="자신에 대해 설명해 주세요." name="introduction" style="resize: none"
                              required>${freelancer.introduction}</textarea>
                </div>

                <!-- 첨부파일 -->
                <div class="file-upload-section">
                    <h3>첨부파일</h3>
                    <div class="file-upload-buttons">
                        <%-- <c:if test="${allPortfolioInfoMap ne null}">
                             <button id="add-portfolio-info" type="button">등록한 포트폴리오 불러오기</button>
                         </c:if>--%>
                        <button id="add-outer-url" type="button">외부 URL 추가</button>
                        <button id="add-attachment-file" type="button">내 PC에서 파일 첨부</button>
                    </div>
                    <%--${freelancer.attachment}${freelancer.externalUrl}--%>
                    <table>
                        <tbody>
                        <%--<c:if test="${freelancer.portfolioInfoList ne null}">
                            <c:forEach items="${freelancer.portfolioInfoList}" var="portfolioInfo" varStatus="status">
                                <tr class="portfolio-id-section">
                                    <td><label for="portfolio-id-select-${status.index + 1}">포트폴리오</label></td>
                                    <td>
                                        <select class="project-id-select portfolioIdList" name="portfolioIds[${status.index}]" id="portfolio-id-select-${status.index + 1}">
                                            <c:forEach var="entry" items="${allPortfolioInfoMap}">
                                                <option value="${entry.key}" <c:if test="${entry.key eq portfolioInfo.portfolioId}">selected</c:if>>${entry.value.title}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><button type="button" class="delete-tr">X</button></td>
                                </tr>
                            </c:forEach>
                        </c:if>--%>

                        <c:if test="${freelancer.externalUrlList ne null}">
                            <c:forEach var="externalUrl" items="${freelancer.externalUrlList}" varStatus="status">
                                <tr class="url-section">
                                    <td><label for="url-${status.index}">외부 링크</label></td>
                                    <td>
                                        <input type="text" value="${externalUrl}" name="url-${status.index}"
                                               id="file-url-${status.index}" placeholder="https://example.com"/>
                                    </td>
                                    <td>
                                        <button type="button" class="delete-tr">X</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${freelancer.attachmentList ne null}">
                            <c:forEach var="attachment" items="${freelancer.attachmentList}" varStatus="status">
                                <tr class="file-section">
                                    <td><label for="attachment-${status.index}">첨부파일</label></td>
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
                            <input type="checkbox" name="negotiation" value="Y"
                            ${freelancer.isNegotiable ? 'checked' : ''} /> 협의 가능
                        </div>
                        <div class="salary-wrap">
                            <input type="text" placeholder="희망 급여(월)" name="desiredSalary"
                                   value="${freelancer.desiredSalary}"
                                   oninput="this.value = this.value.replace(/[^0-9]/g, '')">
                            <span>만 원</span>
                        </div>
                        <input type="text" placeholder="희망 근무지" name="desiredLocation"
                               value="${freelancer.desiredLocation}">
                        <textarea placeholder="기타 희망사항" name="otherRequest"
                                  style="resize: none">${freelancer.otherRequest}</textarea>
                    </div>
                    <input type="hidden" name="skillDescription" id="skillDescriptionHidden"
                           value="<c:forEach items='${freelancer.skillList}' var='skill' varStatus='status'>${skill}<c:if test='${!status.last}'>,</c:if></c:forEach>"/>
                    <input type="hidden" name="subCategory" id="categoryHidden" value="${freelancer.subCategoryId}"/>
                    <input type="hidden" id="careerListHidden" name="careerListJson">
                    <input type="hidden" id="educationListHidden" name="educationListJson">
                    <input type="hidden" id="licenseListHidden" name="licenseListJson">
                    <input type="hidden" id="externalUrlListHidden" name="externalUrlListJson">
                    <input type="hidden" id="attachmentListHidden" name="attachmentListJson">
                </div>
                <div class="submit-wrap">
                    <button type="submit" class="save-btn">저장</button>
                </div>
            </form>
        </section>
    </main>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const categorySelect = document.getElementById("category_name");
        const subCategorySelect = document.getElementById("subCategory");
        const specializationSpan = document.querySelector(".specialization span");

        // 히든 필드가 없다면 동적으로 추가
        let categoryHidden = document.getElementById("categoryHidden");
        if (!categoryHidden) {
            categoryHidden = document.createElement("input");
            categoryHidden.type = "hidden";
            categoryHidden.id = "categoryHidden";
            categoryHidden.name = "categoryHidden";
            document.querySelector(".category-section").appendChild(categoryHidden);
        }
        categorySelect.addEventListener("change", function () {
            const categoryId = this.value;
            console.log(categoryId)
            fetch(`${pageContext.request.contextPath}/getSubCategories?categoryId=` + categoryId)
                .then(res => res.json())
                .then(data => {
                    subCategorySelect.innerHTML = "";
                    if (data.length === 0) {
                        subCategorySelect.innerHTML = "<option value=''>하위 카테고리 없음</option>";
                    } else {
                        data.forEach(item => {
                            const option = document.createElement("option");
                            option.value = item.subCategoryId;
                            option.textContent = item.subCategoryName;
                            subCategorySelect.appendChild(option);
                        });
                    }
                })
                .catch(err => {
                    console.error("하위 카테고리 로딩 실패", err);
                });
        });
        // 서브카테고리 변경 이벤트 추가
        subCategorySelect.addEventListener("change", function () {
            // 선택된 서브카테고리 value를 hidden 필드에 저장
            const subCategoryValue = this.value;
            categoryHidden.value = subCategoryValue;
            console.log("categoryHidden : "+categoryHidden.value);
            // 카테고리와 서브카테고리의 텍스트 조합
            const mainCategoryText = categorySelect.options[categorySelect.selectedIndex].text;
            const subCategoryText = this.options[this.selectedIndex].text;

            // 두 카테고리가 모두 선택된 경우에만 텍스트 표시 (1차 카테고리가 기본값이 아닌 경우)
            if (categorySelect.value && subCategoryValue && mainCategoryText !== '1차 카테고리' && subCategoryText !== '하위 카테고리 없음') {
                const fullCategoryText = mainCategoryText + ' > ' + subCategoryText;
                specializationSpan.textContent = fullCategoryText;
            } else {
                specializationSpan.textContent = '';
            }
        });
    });
</script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
<script>
    function changeFile(input) {
        if (input.files && input.files[0]) {
            document.getElementById("selectFile").text = input.files[0].name;
        }
    }
</script>
<%--<script src="${contextPath}/js/freelancer_my_page_info_edit_expert.js"></script>--%>
<script src="${contextPath}/js/freelancer_my_page_info_edit_expert2.js"></script>
</body>
</html>