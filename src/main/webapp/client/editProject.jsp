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
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/css/home/makeProject.css">
</head>
<body>
<div id="header-placeholder"></div>
<div class="bg-gray-50 p-6">

    <br>
    <br>
    <div class="max-w-4xl mx-auto bg-white p-6 rounded shadow">
        <h1 class="text-2xl font-bold mb-4">프로젝트 수정</h1>
        <form id="job-form" action="${contextPath}/editProject" method="post" class="space-y-6">
            <!-- 숨겨진 projectId -->
            <input type="hidden" name="projectId" value="${project.projectId}" />

            <!-- 기본 정보 -->
            <section>
                <label class="block font-medium">공고 제목 <span class="text-red-500">*</span>
                    <input name="advertisementTitle" type="text" class="mt-1 w-full border p-2 rounded" value="${project.advertisementTitle}" required>
                </label>

                <label class="block font-medium mt-4">프로젝트명 <span class="text-red-500">*</span>
                    <input name="projectName" type="text" class="mt-1 w-full border p-2 rounded" value="${project.projectName}" required>
                </label>

                <label class="block font-medium mt-4">모집 분야
                    <input id="recruit-field-input" type="text" class="mt-1 w-full border p-2 rounded" placeholder="엔터나 쉼표로 태그를 적어주세요">
                </label>

                <!-- 모집분야 태그 영역 -->
                <div id="recruit-field-tags" class="flex flex-wrap gap-2 mt-2"></div>

                <div class="mt-4">
                    <span class="font-medium">근무 방식</span> <span class="text-red-500">*</span>
                    <div class="flex space-x-2 mt-2">
                        <button type="button" data-work-type="remote" class="px-4 py-2 border rounded ${project.workingMethod == 'remote' ? 'bg-blue-500 text-white' : ''}">원격</button>
                        <button type="button" data-work-type="onsite" class="px-4 py-2 border rounded ${project.workingMethod == 'onsite' ? 'bg-blue-500 text-white' : ''}">상주</button>
                        <button type="button" data-work-type="hybrid" class="px-4 py-2 border rounded ${project.workingMethod == 'hybrid' ? 'bg-blue-500 text-white' : ''}">혼합</button>
                    </div>
                    <input type="hidden" name="workingMethod" value="${project.workingMethod}" />

                    <div id="work-time-toggle" class="mt-2 ${project.workingMethod == 'remote' ? 'hidden' : ''}">
                        <label class="block mt-2 font-medium">근무 시간
                            <input name="workingHours" type="text" class="mt-1 w-full border p-2 rounded" value="${project.workingHours}" placeholder="00:00 - 00:00">
                        </label>
                    </div>
                </div>

                <label class="block font-medium mt-4">근무 기간 <span class="text-red-500">*</span>
                    <input name="duration" type="text" class="mt-1 w-full border p-2 rounded" value="${project.duration}" required>
                </label>

                <label class="block font-medium mt-4">근무 지역 <span class="text-red-500">*</span>
                    <input name="workingEnvironment" type="text" class="mt-1 w-full border p-2 rounded" value="${project.workingEnvironment}" required>
                </label>

                <div class="mt-4">
                    <span class="font-medium">산업</span>
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mt-2">
                        <div>
                            <label class="block font-medium mb-1">산업 분야</label><span class="text-red-500">*</span>
                            <select id="industry-primary" class="w-full border p-2 rounded">
                                <option value="">산업 분야</option>
                                <!-- 카테고리 옵션은 JavaScript로 채워짐 -->
                            </select>
                        </div>
                        <div>
                            <label class="block font-medium mb-1">세부 분야</label><span class="text-red-500">*</span>
                            <select id="industry-secondary" name="subCategoryId" class="w-full border p-2 rounded">
                                <option value="">세부 분야</option>
                                <!-- 서브 카테고리 옵션은 JavaScript로 채워짐 -->
                            </select>
                        </div>
                    </div>
                </div>
            </section>

            <!-- 포지션 정보 -->
            <section class="w-full max-w-5xl bg-white p-8 rounded-2xl container-shadow">
                <h2 class="text-lg font-semibold">포지션 정보</h2>
                <br>
                <div id="position-container" class="space-y-4">
                    <c:choose>
                        <c:when test="${not empty positions}">
                            <c:forEach items="${positions}" var="position" varStatus="status">
                                <div class="position-block border p-4 rounded relative">
                                    <button type="button" class="remove-position absolute top-2 right-2 text-red-500">삭제</button>

                                    <label class="block mt-2 font-medium">레벨</label><span class="text-red-500">*</span>
                                    <select name="lvId" class="mt-1 w-full border p-2 rounded">
                                        <option value="">선택해주세요</option>
                                        <option value="1" ${position.lvId == 1 ? 'selected' : ''}>초급</option>
                                        <option value="2" ${position.lvId == 2 ? 'selected' : ''}>중급</option>
                                        <option value="3" ${position.lvId == 3 ? 'selected' : ''}>고급</option>
                                    </select>

                                    <label class="block mt-2 font-medium">구인 인원 <span class="text-red-500">*</span></label>
                                    <input name="people" type="number" class="mt-1 w-full border p-2 rounded" value="${position.people}" required>

                                    <label class="block mt-2 font-medium">급여 / 페이</label><span class="text-red-500">*</span>
                                    <input name="projectFee" type="text" class="mt-1 w-full border p-2 rounded" value="${position.projectFee}" required>

                                    <label class="block mt-2 font-medium">담당업무</label><span class="text-red-500">*</span>
                                    <select name="work" class="position-task-select mt-1 w-full border p-2 rounded" required>
                                        <option value="">담당업무를 선택하세요</option>
                                        <!-- 모집 분야에 따라 JavaScript로 옵션이 채워짐 -->
                                    </select>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="position-block border p-4 rounded relative">
                                <button type="button" class="remove-position absolute top-2 right-2 text-red-500 hidden">삭제</button>

                                <label class="block mt-2 font-medium">레벨</label><span class="text-red-500">*</span>
                                <select name="lvId" class="mt-1 w-full border p-2 rounded">
                                    <option value="">선택해주세요</option>
                                    <option value="1">초급</option>
                                    <option value="2">중급</option>
                                    <option value="3">고급</option>
                                </select>

                                <label class="block mt-2 font-medium">구인 인원 <span class="text-red-500">*</span></label>
                                <input name="people" type="number" class="mt-1 w-full border p-2 rounded" required>

                                <label class="block mt-2 font-medium">급여 / 페이</label><span class="text-red-500">*</span>
                                <input name="projectFee" type="text" class="mt-1 w-full border p-2 rounded" required>

                                <label class="block mt-2 font-medium">담당업무</label><span class="text-red-500">*</span>
                                <select name="work" class="position-task-select mt-1 w-full border p-2 rounded" required>
                                    <option value="">담당업무를 선택하세요</option>
                                    <!-- 모집 분야에 따라 JavaScript로 옵션이 채워짐 -->
                                </select>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <button type="button" id="add-position" class="mt-2 px-4 py-2 border rounded text-blue-500">+ 포지션 추가</button>
            </section>

            <!-- 기술 스택 -->
            <section>
                <h2 class="text-lg font-semibold">필요 기술 스택</h2>

                <label class="block font-medium mt-2">원하는 기술<span class="text-red-500">*</span>
                    <input name="reqSkills" type="text" class="mt-1 w-full border p-2 rounded" value="${project.reqSkills}" required>
                </label>

                <label class="block font-medium mt-2">우대 기술<span class="text-red-500">*</span>
                    <input name="wantedSkills" type="text" class="mt-1 w-full border p-2 rounded" value="${project.wantedSkills}" required>
                </label>
            </section>

            <!-- 업무 내용 -->
            <section>
                <h2 class="text-lg font-semibold">업무 내용</h2>

                <label class="block font-medium mt-2">프로젝트 개요<span class="text-red-500">*</span>
                    <textarea name="projectDescription" class="mt-1 w-full border p-2 rounded textin" required>${project.projectDescription}</textarea>
                </label>

                <label class="block font-medium mt-2">상세 업무 내용<span class="text-red-500">*</span>
                    <textarea name="jobDetails" class="mt-1 w-full border p-2 rounded textin" required>${project.jobDetails}</textarea>
                </label>
            </section>

            <!-- 지원자격 -->
            <section>
                <h2 class="text-lg font-semibold">지원자격</h2>

                <label class="block font-medium mt-2">필수 경험<span class="text-red-500">*</span>
                    <textarea name="qualification" class="mt-1 w-full border p-2 rounded" required>${project.qualification}</textarea>
                </label>

                <label class="block font-medium mt-2">우대 사항<span class="text-red-500">*</span>
                    <textarea name="preferentialConditions" class="mt-1 w-full border p-2 rounded" required>${project.preferentialConditions}</textarea>
                </label>

                <label class="block font-medium mt-2">모집 마감 기한 <span class="text-red-500">*</span>
                    <input name="deadlineDate" type="date" class="mt-1 w-full border p-2 rounded" value="${project.deadlineDate}" required>
                </label>
            </section>

            <!-- 연락처 -->
            <section>
                <h2 class="text-lg font-semibold">연락처 설정</h2>

                <label class="block font-medium mt-2">담당자 이름 <span class="text-red-500">*</span>
                    <input name="manager" type="text" class="mt-1 w-full border p-2 rounded" value="${project.manager}" required>
                </label>

                <label class="block font-medium mt-2">담당자 이메일 <span class="text-red-500">*</span>
                    <input name="memail" type="email" class="mt-1 w-full border p-2 rounded" value="${project.memail}" required>
                </label>

                <label class="block font-medium mt-2">담당자 전화번호 <span class="text-red-500">*</span>
                    <input name="mphone" type="tel" class="mt-1 w-full border p-2 rounded" value="${project.mphone}" required>
                </label>
            </section>

            <button id="submit-button" type="button" class="w-full bg-blue-500 text-white py-3 rounded disabled:opacity-50">
                프로젝트 수정하기
            </button>
        </form>
    </div> <!-- max-w-4xl ... -->
</div>   <!-- bg-gray-50 p-6 -->
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const jobForm = document.getElementById('job-form');
        const workTypeButtons = document.querySelectorAll('[data-work-type]');
        const workTimeToggle = document.getElementById('work-time-toggle');
        const recruitFieldInput = document.getElementById('recruit-field-input');
        const recruitFieldTags = document.getElementById('recruit-field-tags');
        const addPositionBtn = document.getElementById('add-position');
        const positionContainer = document.getElementById('position-container');
        const categoryList = JSON.parse('<c:out value="${categoryListJSON}" escapeXml="false"/>');
        const recruitFields = [];

        // 기존 모집 분야 데이터 처리
        const jobPositions = "${project.jobPosition}".split(',');
        jobPositions.forEach(position => {
            if (position.trim()) {
                recruitFields.push(position.trim());
                addRecruitFieldTag(position.trim());
            }
        });

        // 근무 방식 처리
        workTypeButtons.forEach(button => {
            button.addEventListener('click', function () {
                workTypeButtons.forEach(btn => btn.classList.remove('bg-blue-500', 'text-white'));
                this.classList.add('bg-blue-500', 'text-white');

                const workType = this.getAttribute('data-work-type');
                document.querySelector('input[name="workingMethod"]').value = workType;

                workTimeToggle.classList.toggle('hidden', !['onsite', 'hybrid'].includes(workType));
                validateForm();
            });
        });

        // 모집 분야 태그 처리
        recruitFieldInput.addEventListener('keydown', function (e) {
            if (e.key === 'Enter' || e.key === ',') {
                e.preventDefault();
                const value = recruitFieldInput.value.trim().replace(',', '');
                if (value && !recruitFields.includes(value)) {
                    recruitFields.push(value);
                    addRecruitFieldTag(value);
                    recruitFieldInput.value = '';
                    updatePositionTaskOptions();
                } else {
                    recruitFieldInput.value = '';
                }
            }
        });

        function addRecruitFieldTag(text) {
            const tag = document.createElement('div');
            tag.className = 'flex items-center bg-purple-100 text-purple-800 px-3 py-1 rounded-full text-sm';

            const span = document.createElement('span');
            span.textContent = text;

            const button = document.createElement('button');
            button.type = 'button';
            button.className = 'ml-2 text-purple-500 hover:text-purple-700 remove-tag';
            button.innerHTML = '&times;';

            const hiddenInput = document.createElement('input');
            hiddenInput.type = 'hidden';
            hiddenInput.name = 'jobPosition';
            hiddenInput.value = text;

            tag.append(span, button, hiddenInput);
            recruitFieldTags.appendChild(tag);

            button.addEventListener('click', () => {
                recruitFields.splice(recruitFields.indexOf(text), 1);
                tag.remove();
                updatePositionTaskOptions();
                validateForm();
            });

            validateForm();
        }

        function updatePositionTaskOptions() {
            const taskSelects = document.querySelectorAll('.position-task-select');
            taskSelects.forEach(select => {
                // 현재 선택된 값 저장
                const currentValue = select.value;

                select.innerHTML = '<option value="">담당업무를 선택하세요</option>';
                recruitFields.forEach(field => {
                    const option = document.createElement('option');
                    option.value = field;
                    option.textContent = field;
                    if (field === currentValue) {
                        option.selected = true;
                    }
                    select.appendChild(option);
                });
            });

            // 기존 포지션 정보의 담당업무 설정
            <c:forEach items="${positions}" var="position" varStatus="status">
            const taskSelectIndex${status.index} = document.querySelectorAll('.position-task-select')[${status.index}];
            if (taskSelectIndex${status.index}) {
                const workOption = document.createElement('option');
                workOption.value = "${position.work}";
                workOption.textContent = "${position.work}";
                workOption.selected = true;

                // 옵션이 이미 존재하는지 확인
                let optionExists = false;
                Array.from(taskSelectIndex${status.index}.options).forEach(option => {
                    if (option.value === "${position.work}") {
                        option.selected = true;
                        optionExists = true;
                    }
                });

                // 옵션이 없으면 추가
                if (!optionExists && "${position.work}" !== "") {
                    taskSelectIndex${status.index}.appendChild(workOption);
                }
            }
            </c:forEach>
        }

        // 산업 카테고리 초기화
        const primarySelect = document.getElementById('industry-primary');
        const secondarySelect = document.getElementById('industry-secondary');

        // 카테고리 옵션 채우기
        categoryList.forEach(cat => {
            const option = document.createElement('option');
            option.value = cat.categoryId;
            option.textContent = cat.categoryName;
            primarySelect.appendChild(option);

            // 현재 프로젝트의 카테고리 선택
            if (cat.subCategories && cat.subCategories.some(sub => sub.subCategoryId === parseInt('${project.subCategoryId}'))) {
                option.selected = true;

                // 서브 카테고리 채우기
                cat.subCategories.forEach(sub => {
                    const subOption = document.createElement('option');
                    subOption.value = sub.subCategoryId;
                    subOption.textContent = sub.subCategoryName;
                    if (sub.subCategoryId === parseInt('${project.subCategoryId}')) {
                        subOption.selected = true;
                    }
                    secondarySelect.appendChild(subOption);
                });
            }
        });

        primarySelect.addEventListener('change', function () {
            const selectedCategory = categoryList.find(c => c.categoryId === parseInt(this.value));
            secondarySelect.innerHTML = '<option value="">세부 분야</option>';
            if (selectedCategory?.subCategories) {
                selectedCategory.subCategories.forEach(sub => {
                    const option = document.createElement('option');
                    option.value = sub.subCategoryId;
                    option.textContent = sub.subCategoryName;
                    secondarySelect.appendChild(option);
                });
            }
            validateForm();
        });

        secondarySelect.addEventListener('change', validateForm);

        // 포지션 추가
        addPositionBtn.addEventListener('click', () => {
            const positionTemplate = document.querySelector('.position-block');
            const clone = positionTemplate.cloneNode(true);
            clone.querySelectorAll('input, select').forEach(el => {
                el.value = '';
                el.setAttribute('required', 'required');
            });

            // 담당업무 옵션 업데이트
            const taskSelect = clone.querySelector('.position-task-select');
            taskSelect.innerHTML = '<option value="">담당업무를 선택하세요</option>';
            recruitFields.forEach(field => {
                const option = document.createElement('option');
                option.value = field;
                option.textContent = field;
                taskSelect.appendChild(option);
            });

            clone.querySelector('.remove-position')?.classList.remove('hidden');
            clone.querySelector('.remove-position').addEventListener('click', function() {
                clone.remove();
                validateForm();
            });

            positionContainer.appendChild(clone);
            validateForm();
        });

        // 기존 포지션 삭제 버튼 이벤트 추가
        document.querySelectorAll('.remove-position').forEach(button => {
            button.addEventListener('click', function() {
                this.closest('.position-block').remove();
                validateForm();
            });
        });

        // 버튼 클릭 시 유효성 검사 후 제출
        document.getElementById('submit-button').addEventListener('click', function(e) {
            e.preventDefault();
            validateAndSubmit();
        });

        // 실제 유효성 검사
        function validateAndSubmit() {
            let allFilled = true;
            let errorFields = [];

            // 필수 입력 필드 검사
            const requiredFields = jobForm.querySelectorAll('[required]');
            requiredFields.forEach(field => {
                const value = field.value.trim();
                if (!value) {
                    allFilled = false;
                    field.classList.add('border-red-500');
                    const label = field.closest('label')?.textContent?.trim() || field.name;
                    errorFields.push(`- ${label.replace('*', '').trim()} 입력 필요`);
                } else {
                    field.classList.remove('border-red-500');
                }
            });

            // 모집 분야 검사
            const jobPositionInputs = document.querySelectorAll('input[name="jobPosition"]');
            if (jobPositionInputs.length === 0) {
                allFilled = false;
                recruitFieldInput.classList.add('border-red-500');
                errorFields.push('- 모집 분야를 1개 이상 입력해주세요.');
            } else {
                recruitFieldInput.classList.remove('border-red-500');
            }

            // 근무 방식 검사
            const workingMethod = document.querySelector('input[name="workingMethod"]').value;
            if (!workingMethod) {
                allFilled = false;
                errorFields.push('- 근무 방식을 선택해주세요.');
            }

            // 산업 분야 검사
            if (!primarySelect.value) {
                allFilled = false;
                errorFields.push('- 산업 분야를 선택해주세요.');
                primarySelect.classList.add('border-red-500');
            } else {
                primarySelect.classList.remove('border-red-500');
            }

            if (!secondarySelect.value) {
                allFilled = false;
                errorFields.push('- 세부 분야를 선택해주세요.');
                secondarySelect.classList.add('border-red-500');
            } else {
                secondarySelect.classList.remove('border-red-500');
            }

            // 포지션 정보 검사
            const positionBlocks = document.querySelectorAll('.position-block');
            if (positionBlocks.length === 0) {
                allFilled = false;
                errorFields.push('- 최소 1개 이상의 포지션 정보가 필요합니다.');
            } else {
                positionBlocks.forEach((block, index) => {
                    const lv = block.querySelector('select[name="lvId"]');
                    const ppl = block.querySelector('input[name="people"]');
                    const fee = block.querySelector('input[name="projectFee"]');
                    const work = block.querySelector('select[name="work"]');

                    // 각 필드 검사
                    if (lv && !lv.value) {
                        allFilled = false;
                        errorFields.push(`- ${index + 1}번째 포지션의 레벨 선택 필요`);
                        lv.classList.add('border-red-500');
                    } else if (lv) {
                        lv.classList.remove('border-red-500');
                    }

                    if (ppl && !ppl.value) {
                        allFilled = false;
                        errorFields.push(`- ${index + 1}번째 포지션의 인원 수 입력 필요`);
                        ppl.classList.add('border-red-500');
                    } else if (ppl) {
                        ppl.classList.remove('border-red-500');
                    }

                    if (fee && !fee.value) {
                        allFilled = false;
                        errorFields.push(`- ${index + 1}번째 포지션의 페이 입력 필요`);
                        fee.classList.add('border-red-500');
                    } else if (fee) {
                        fee.classList.remove('border-red-500');
                    }

                    if (work && !work.value) {
                        allFilled = false;
                        errorFields.push(`- ${index + 1}번째 포지션의 담당업무 선택 필요`);
                        work.classList.add('border-red-500');
                    } else if (work) {
                        work.classList.remove('border-red-500');
                    }
                });
            }

            if (!allFilled) {
                alert("다음 항목을 확인해주세요:\n\n" + errorFields.join('\n'));
                return;
            }

            // 모든 검증 통과 시 폼 제출
            jobForm.submit();
        }

        // 폼 유효성 검사
        function validateForm() {
            const submitButton = document.getElementById('submit-button');
            submitButton.disabled = false;
        }

        // 초기화
        updatePositionTaskOptions();
        validateForm();
    });
</script>

<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>



</body>

</html>