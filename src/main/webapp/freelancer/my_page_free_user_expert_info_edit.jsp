<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page_info_edit.css'/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${contextPath}/js/freelancer_my_page_info_edit.js"></script>
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
                    <label>전문 분야</label>
                    <select id="category_name">
                    <c:forEach items="${categoriesMap}" var="category">
                        <option value="${category.value}">${category.key}</option>
                    </c:forEach>
                    </select>
                    <select id="subCategory" name="subCategory" id="subCategory"></select>
                    <div class="specialization">
                        <c:if test="${not empty freelancer.categoryList}">
                            <c:forEach items="${freelancer.categoryList}" var="category_name">
                                <span>${category_name}X</span>
                            </c:forEach>
                        </c:if>
                        <button type="button" class="clear_fields clear-category-btn">초기화</button>
                    </div>
                </div>

                <!-- 스킬 -->
                <div class="skills-section">
                    <span>스킬</span>
                    <input type="text" id="skills" placeholder="스킬을 입력하고 쉼표(,) 또는 Enter 키로 구분해주세요"/>
                    <div class="skill-tags" style="margin-top: 10px;">
                        <c:if test="${not empty freelancer.skill}">
                            <c:forEach items="${fn:split(freelancer.skill, ',')}" var="skill">
                                <span class="skill-tag">${skill}X</span>
                            </c:forEach>
                        </c:if>
                    </div>
                    <button type="button" id="clear-skills">초기화</button>
                </div>

                <!-- 학력 -->
                <div class="form-group">
                    <label>학력</label>
                    <div id="education-container">
                        <c:if test="${not empty freelancer.academicList}">
                            <c:forEach items="${freelancer.academicList}" var="academic">
                                <div class="education-box">
                                    <div class="row">
                                        <div class="dropdown dropdown-education-category">
                                            <div class="label" aria-hidden="false">학력구분 <span class="star">*</span></div>
                                            <select name="academicType">
                                                <option value="">학교구분</option>
                                                <option value="고등학교" <c:if test="${academic.academicType == '고등학교'}">selected</c:if>>고등학교</option>
                                                <option value="대학교(2,3년)" <c:if test="${academic.academicType == '대학교(2,3년)'}">selected</c:if>>대학교(2,3년)</option>
                                                <option value="대학교(4년)" <c:if test="${academic.academicType == '대학교(4년)'}">selected</c:if>>대학교(4년)</option>
                                                <option value="대학원" <c:if test="${academic.academicType == '대학원'}">selected</c:if>>대학원</option>
                                            </select>
                                        </div>
                                        <input type="checkbox" name="isQuit" id="isQuit" value="${academic.isQuit}" <c:if test="${academic.isQuit}">checked</c:if>/>
                                    </div>
                                    <div class="input input-education-name search is-label">
                                        학교명 <span class="star">*</span>
                                        <input type="text" name="academicName" value="${academic.academicName}" required/>
                                    </div>
                                    <c:when test="${academic.academicType != '고등학교'}">
                                        <div class="input input-education-graduate">
                                            <label>전공명</label>
                                            <input type="text" name="academicMajor" required/>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${academic.academicType == '고등학교' }">
                                            <div class="input input-education-file">
                                            </div>
                                        </c:if>
                                    </c:otherwise>
                                    <div class="input input-education-date">
                                        <label>입학연월</label>
                                        <input type="month" name="entranceDate" value="${academic.entranceDate}" required/>
                                        <label>졸업연월</label>
                                        <input type="month" name="graduateDate" value="${academic.graduateDate}" />
                                    </div>
                                    <button type="button" class="button buttonDeleteField">
                                        <span>학교 삭제</span>
                                    </button>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <button id="education_addbutton">+ 학력 추가</button>
                </div>

                <!-- 경력 -->
                <div class="form-group">
                    <label>경력</label>
                    <div id="career-container">
                        <button type="button" class="button buttonDeleteField"><span>경력 삭제</span></button>
                        <div class="career-box">
                            <div class="career-row">
                                <input type="text" name="companyName" placeholder="회사명" required value="">
                                <input type="text" name="departmentName" placeholder="부서명" value="">
                                <input type="text" name="joinDate" placeholder="입사년도" required value="">
                                <input type="text" name="resignDate" placeholder="퇴사년도" value="">
                            </div>
                            <div class="career-row">
                                <select>
                                    <option>직급/직책</option>
                                </select>
                                <input type="text" placeholder="담당직무" required value="" value="">
                                <input type="number" name="jobDescription" placeholder="연봉" value=""> <span>만 원</span>
                            </div>
                            <span>담당업무</span><textarea placeholder="담당하신 업무와 성과에 대해 간단하게 적어주세요." required></textarea>
                        </div>
                    </div>
                    <button type="button" class="add-career-btn">+ 경력 추가</button>
                </div>
                <div class="form formCertificate">
                    <div id="license_containers">
                        <div class="license_container license_container1">
                            <div class="row">
                                <div class="input input-certificate-name is-label" data-comp_type="">
                                    <label for="license_Search_c">자격증 명 <span class="star">*</span></label>
                                    <input type="text" name="licenses[].licenseName" id="license_Search_c" value=""
                                           maxlength="50" value="">
                                </div>
                                <div class="input input-certificate-agency is-label">
                                    <label>발행처</label>
                                    <input type="text" name="License[c].Lc_Pub" id="License_Lc_Pub_c" value=""
                                           data-type="Lc_Pub" maxlength="50" value="">
                                    <div class="validation hidden" aria-hidden="true"></div>
                                </div>
                                <div class="input input-certificate-term is-label">
                                    <label>취득월</label>
                                    <input type="month" name="License[c].Lc_YYMM" id="License_Lc_YYMM_c" value=""
                                           data-format-type="month" placeholder="2017.10" value="">
                                </div>
                            </div>
                            <button type="button" class="button buttonDeleteField dev-btn-del-license">
                                <span>자격증 삭제</span></button>
                        </div>
                    </div>

                    <button type="button" class="button buttonAddField"><span>자격증 추가</span></button>
                </div>

                <!-- 자기소개서 -->
                <div class="form-group">
                    <label>자기소개서</label>
                    <input type="text" placeholder="자기소개서 제목">
                    <textarea placeholder="자신에 대해 설명해 주세요." name="introduction"></textarea>
                </div>

                <!-- 포트폴리오 -->
                <div class="file-upload-section">
                    <h3>첨부파일</h3>
                    <div class="file-upload-buttons">
                        <button id="add-portfolio-info" type="button">등록한 포트폴리오 불러오기</button>
                        <button id="add-outer-url" type="button">외부 URL 추가</button>
                        <button id="add-attachment-file" type="button">내 PC에서 파일 첨부</button>
                    </div>
                    <table>
                        <thead>
                        <tr>
                            <th>항목</th>
                            <th>내용</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>

                <!-- 희망근무조건 -->
                <div class="form-group">
                    <label>희망 근무 조건</label>
                    <div>
                        <span>상주 가능 여부:</span>
                        <input type="radio" name="onsitePossible" value="Y"> 가능
                        <input type="radio" name="onsitePossible" value="N"> 불가능
                        <input type="checkbox" name="negotiation" value="Y"> 협의 가능
                    </div>
                    <input type="text" placeholder="희망 급여(월)" name="desiredSalary"> 만 원
                    <input type="text" placeholder="희망 근무지" name="desiredLocation">
                    <textarea placeholder="기타 희망사항" name="otherRequest"></textarea>
                </div>
                <input type="hidden" name="skillDescription" id="skillDescriptionHidden" value=""/>
                <input type="hidden" name="categoryList" id="category_list" value=""/>
                <div class="submit-wrap">
                    <button type="submit" class="save-btn">저장</button>
                </div>
            </form>
        </section>
    </main>
</div>
</body>
</html>