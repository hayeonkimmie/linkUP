document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const submitBtn = document.querySelector(".save-btn");
    const categoryHidden = document.getElementById('categoryHidden');

    // 필수 초기화 함수 호출
    initCategoryManagement();
    initSkillTagsTextInput();
    initEducationObserver();
    initFileSection();
    updateCategoryField();
    initSectionChangeLogging(); // 섹션별 변경 로깅 기능 추가

    initDynamicSection("career-container", "add-career-btn", getCareerTemplate, "careerList", "career-box");
    initDynamicSection("education-container", "education_addbutton", getEducationTemplate, "educationList", "education-box");
    initDynamicSection("license-container", "add-license-btn", getLicenseTemplate, "licenseList", "license-box");

    // 폼 제출 전 데이터 준비 이벤트 추가
    form.addEventListener("submit", function(e) {
        prepareFormDataForSubmission();
        // 폼 제출 시 모든 섹션의 데이터 로깅
        logAllSectionsData();
    });

    // 페이지 로드 직후, 초기 항목들의 인덱스 재설정 및 기존 값 로깅
    renumberAll();
    logAllSectionsData();

    document.querySelectorAll('tr.file-section input[type="file"]').forEach(input => {
        input.addEventListener('change', function () {
            if (this.files && this.files.length > 0) {
                const fileName = this.files[0].name;
                const aTag = this.closest('td').querySelector('a');
                if (aTag) {
                    aTag.textContent = fileName;
                }
            }
        });
    });

    // 폼 제출 전 최종 데이터 준비 함수 개선
    function prepareFormDataForSubmission() {
        // 🔹 커리어 리스트 준비
        const careerList = logCareerData();
        if (careerList && Array.isArray(careerList)) {
            document.getElementById('careerListHidden').value = JSON.stringify(careerList);
        }

        // 🔹 학력 리스트 준비
        const educationList = logEducationData();
        if (educationList && Array.isArray(educationList)) {
            document.getElementById('educationListHidden').value = JSON.stringify(educationList);
        }

        // 🔹 자격증 리스트 준비
        const licenseList = logLicenseData();
        if (licenseList && Array.isArray(licenseList)) {
            document.getElementById('licenseListHidden').value = JSON.stringify(licenseList);
        }

        // 🔹 스킬 리스트 준비
        const skillTags = Array.from(document.querySelectorAll('.skill-tag'))
            .map(tag => tag.getAttribute('data-skill'));
        document.getElementById('skillDescriptionHidden').value = skillTags.join('^');

        // 🔹 외부 URL 리스트 준비
        const urlInputs = document.querySelectorAll('tr.url-section input[type="text"]');
        const externalUrlList = Array.from(urlInputs).map(input => input.value || '');
        document.getElementById('externalUrlListHidden').value = JSON.stringify(externalUrlList);

        // 🔹 외부 URL 리스트 준비
        const linkList = logLinkData();
        if (linkList && Array.isArray(linkList)) {
            // logLinkData()는 { index, url } 형태이므로 url 값만 뽑아야 함
            const externalUrlList = linkList.map(item => item.url || '');
            document.getElementById('externalUrlListHidden').value = JSON.stringify(externalUrlList);
        }

        // 🔹 첨부파일 리스트 준비
        const fileList = logFileData();
        if (fileList && Array.isArray(fileList)) {
            // logFileData()는 { index, linkText, fileName } 형태
            const attachmentList = fileList.map(item => item.fileName || item.linkText || '');
            document.getElementById('attachmentListHidden').value = JSON.stringify(attachmentList);
        }
    }

    function updateFileAndUrlIndexes() {
        document.querySelectorAll('tr.url-section').forEach((row, idx) => {
            const input = row.querySelector('input[type="text"]');
            if (input) {
                input.name = `externalUrlList[${idx}]`;
                input.id = `url-${idx}`;
            }
        });

        document.querySelectorAll('tr.file-section').forEach((row, idx) => {
            const input = row.querySelector('input[type="file"]');
            if (input) {
                input.name = `attachmentList[${idx}]`;
                input.id = `attachment-${idx}`;
            }
        });
    }

    // 모든 섹션의 데이터를 콘솔에 로깅하는 함수
    function logAllSectionsData() {
        logCareerData();
        logEducationData();
        logLicenseData();
        logSkillData();
        logFileData();
        logLinkData();
        logCategoryData();
    }
    function logCategoryData() {
        const categorySelect = document.getElementById('category_name');
        const subCategorySelect = document.getElementById('subCategory');
        if (!categorySelect || !subCategorySelect) return;
        const categoryData = {
            mainCategoryId: categorySelect.value || '',
            mainCategoryName: categorySelect.options[categorySelect.selectedIndex]?.text || '',
            subCategoryId: subCategorySelect.value || '',
            subCategoryName: subCategorySelect.options[subCategorySelect.selectedIndex]?.text || '',
            selectedCategoryId: categoryHidden.value || ''
        };
        console.log('🔄 카테고리 데이터 업데이트:', categoryData);
    }
    function initCategoryManagement() {
        const categorySelect = document.getElementById("category_name");
        const subCategorySelect = document.getElementById("subCategory");
        const specializationSpan = document.querySelector(".specialization span");

        if (!categorySelect || !subCategorySelect) return;

        // 초기 서브카테고리 로드 (이미 선택된 카테고리가 있는 경우)
        if (categorySelect.value) {
            loadSubCategories(categorySelect.value, categoryHidden.value);
        }

        categorySelect.addEventListener("change", function() {
            const categoryId = this.value;
            loadSubCategories(categoryId);
            logCategoryData(); // 카테고리 변경 시 로깅
        });

        subCategorySelect.addEventListener("change", function() {
            const subCategoryId = this.value;
            const mainCategoryText = categorySelect.options[categorySelect.selectedIndex].text;
            const subCategoryText = this.options[this.selectedIndex].text;

            // hidden 필드에 서브카테고리 ID 저장
            categoryHidden.value = subCategoryId;
            console.log("카테고리 선택 업데이트:", subCategoryId);

            // 화면에 선택된 카테고리 표시
            if (categorySelect.value && subCategoryId &&
                mainCategoryText !== '1차 카테고리' &&
                subCategoryText !== '하위 카테고리 없음') {
                const fullCategoryText = mainCategoryText + ' > ' + subCategoryText;
                specializationSpan.textContent = fullCategoryText;
            } else {
                specializationSpan.textContent = '';
            }

            logCategoryData(); // 서브카테고리 변경 시 로깅
        });

        function loadSubCategories(categoryId, selectedSubCategoryId = null) {
            fetch(`${contextPath}/getSubCategories?categoryId=${categoryId}`)
                .then(res => res.json())
                .then(data => {
                    subCategorySelect.innerHTML = "<option value=''>2차 카테고리</option>";

                    if (data.length === 0) {
                        subCategorySelect.innerHTML = "<option value=''>하위 카테고리 없음</option>";
                    } else {
                        data.forEach(item => {
                            const option = document.createElement("option");
                            option.value = item.subCategoryId;
                            option.textContent = item.subCategoryName;

                            if (selectedSubCategoryId && item.subCategoryId == selectedSubCategoryId) {
                                option.selected = true;
                            }

                            subCategorySelect.appendChild(option);
                        });

                        if (selectedSubCategoryId) {
                            const event = new Event('change');
                            subCategorySelect.dispatchEvent(event);
                        }
                    }
                })
                .catch(err => {
                    console.error("하위 카테고리 로딩 실패", err);
                });
        }
    }

    function updateCategoryField() {
        const subCategory = document.getElementById("subCategory");
        if (categoryHidden && subCategory && !categoryHidden.value) {
            categoryHidden.value = subCategory.value;
        }
    }

    function initDynamicSection(containerId, buttonId, templateFn, baseName, boxClass) {
        const container = document.getElementById(containerId);
        const addBtn = document.getElementById(buttonId) || document.querySelector(`.${buttonId}`);

        if (!container || !addBtn) return;

        addBtn.addEventListener("click", () => {
            const box = document.createElement("div");
            box.classList.add("item-box", boxClass);
            box.innerHTML = templateFn(getNextIndex(box.classList[1]), baseName);

            box.querySelector(".buttonDeleteField")?.addEventListener("click", () => {
                box.remove();
                renumberAll();
                if (boxClass === "career-box") logCareerData();
                else if (boxClass === "education-box") logEducationData();
                else if (boxClass === "license-box") logLicenseData();
            });

            container.appendChild(box);
            renumberAll();
            if (boxClass === "career-box") logCareerData();
            else if (boxClass === "education-box") logEducationData();
            else if (boxClass === "license-box") logLicenseData();
        });

        container.querySelectorAll('.buttonDeleteField').forEach(btn => {
            btn.addEventListener('click', function() {
                const boxClass = this.closest('.item-box').classList[1];
                this.closest('.item-box').remove();
                renumberAll();
                if (boxClass === "career-box") logCareerData();
                else if (boxClass === "education-box") logEducationData();
                else if (boxClass === "license-box") logLicenseData();
            });
        });

        function getNextIndex(className) {
            return document.querySelectorAll('.' + className).length;
        }
    }

    // 인덱스와 ID 갱신 함수 (초기 로드 시에도 호출)
    function renumberAll() {
        const sections = [
            {selector: ".career-box", baseName: "careerList"},
            {selector: ".education-box", baseName: "educationList"},
            {selector: ".license-box", baseName: "licenseList"}
        ];

        sections.forEach(({selector, baseName}) => {
            document.querySelectorAll(selector).forEach((box, idx) => {
                box.querySelectorAll('input, select, textarea').forEach(input => {
                    if (input.name) {
                        const suffix = input.name.replace(/^.*?\[\d+\]\./, '');
                        input.name = `${baseName}[${idx}].${suffix}`;
                    }
                    if (input.id) {
                        const idSuffix = input.id.replace(/_\d+$/, '');
                        input.id = `${idSuffix}_${idx}`;
                    }
                });
            });
        });
    }

    function getCareerTemplate(index, base) {
        return `
            <button type="button" class="buttonDeleteField">X</button>
            <div class="career-row">
                <input type="text" name="${base}[${index}].companyName" id="companyName_${index}" placeholder="회사명" required />
                <input type="text" name="${base}[${index}].departmentName" id="departmentName_${index}" placeholder="부서명" />
                <span>입사<span class="star">*</span></span>
                <input type="month" name="${base}[${index}].joinDate" id="joinDate_${index}" required/>
                <span>퇴사</span>
                <input type="month" name="${base}[${index}].resignDate" id="resignDate_${index}" />
            </div>
            <div class="career-row">
                <input type="text" name="${base}[${index}].position" id="position_${index}" placeholder="직급/직책" />
                <input type="text" name="${base}[${index}].jobTitle" id="jobTitle_${index}" placeholder="담당직무" />
                <input type="text" name="${base}[${index}].salary" id="salary_${index}" placeholder="연봉" oninput="this.value = this.value.replace(/[^0-9]/g, '')" />
                <span>만 원</span>
            </div>
            <span>담당업무</span>
            <textarea name="${base}[${index}].jobDescription" id="jobDescription_${index}" placeholder="담당하신 업무와 성과에 대해 간단하게 적어주세요." required></textarea>
        `;
    }

    function getEducationTemplate(index, base) {
        return `
            <button type="button" class="buttonDeleteField">X</button>
            <div class="dropdown input-academicType">
                <div class="label">학력구분 <span class="star">*</span></div>
                <select name="${base}[${index}].academicType" id="academicType_${index}" class="academicType">
                    <option value="">학교 구분</option>
                    <option value="고등학교">고등학교</option>
                    <option value="대학교(2, 3년)">대학교(2, 3년)</option>
                    <option value="대학교(4년)">대학교(4년)</option>
                    <option value="대학원">대학원</option>
                </select>
            </div>
            <div class="input input-academicName input-with-icon">
                <div class="label">학교명 <span class="star">*</span></div>
                <input type="text" name="${base}[${index}].academicName" id="academicName_${index}" placeholder="학교명" required />
            </div>
            <div class="dropdown input-graduateStatus">
                <div class="label">졸업상태 <span class="star">*</span></div>
                <select name="${base}[${index}].graduateStatus" id="graduateStatus_${index}">
                    <option value="">졸업 구분</option>
                    <option value="졸업">졸업</option>
                    <option value="재학중">재학중</option>
                    <option value="중퇴">중퇴</option>
                    <option value="휴학">휴학</option>
                    <option value="기타">기타</option>
                </select>
            </div>
            <div class="input input-major input-with-icon academic-major-field">
                <div class="label">전공명</div>
                <input type="text" name="${base}[${index}].academicMajor" id="academicMajor_${index}" placeholder="전공" />
            </div>
            <div class="input input-month-group">
                <div class="input-education-date">
                    <label>입학연월</label>
                    <input type="month" name="${base}[${index}].enterDate" id="enterDate_${index}" />
                </div>
                <div class="input-education-date">
                    <label>졸업연월</label>
                    <input type="month" name="${base}[${index}].graduateDate" id="graduateDate_${index}" />
                </div>
            </div>
        `;
    }

    function getLicenseTemplate(index, base) {
        return `
            <div class="license-row">
                <button type="button" class="buttonDeleteField">X</button>
                <div class="input input-certificate-name is-label">
                    <label>자격증 명 <span class="star">*</span></label>
                    <input type="text" name="${base}[${index}].name" id="licenseName_${index}" placeholder="자격증명" required maxlength="50"/>
                </div>
                <div class="input input-certificate-agency is-label">
                    <label>점수 / 급</label>
                    <input type="text" name="${base}[${index}].licenseGrade" id="licenseGrade_${index}" placeholder="점수 / 급" maxlength="50" />
                </div>
                <div class="input input-certificate-agency is-label">
                    <label>발행처</label>
                    <input type="text" name="${base}[${index}].licenseAgency" id="licenseAgency_${index}" placeholder="발행처" maxlength="50" />
                </div>
                <div class="input input-certificate-term is-label">
                    <label>취득월</label>
                    <input type="month" name="${base}[${index}].licenseDate" id="licenseDate_${index}" />
                </div>
            </div>
        `;
    }

    function initSkillTagsTextInput() {
        const input = document.getElementById('skills');
        const skillTagsContainer = document.querySelector('.skill-tags');
        const clearBtn = document.getElementById('clear-skills');
        const hiddenInput = document.getElementById('skillDescriptionHidden');

        if (!input || !skillTagsContainer || !clearBtn || !hiddenInput) return;

        function isSkillDuplicate(skill) {
            const tags = skillTagsContainer.querySelectorAll('span.skill-tag');
            const lowerSkill = skill.toLowerCase().trim();
            for (let i = 0; i < tags.length; i++) {
                const tagSkill = tags[i].getAttribute('data-skill').toLowerCase().trim();
                if (tagSkill === lowerSkill) return true;
            }
            return false;
        }

        function addSkillTag(skillValue) {
            const trimmed = skillValue.trim();
            if (!trimmed) return false;
            if (isSkillDuplicate(trimmed)) {
                console.log(`중복 스킬 무시: "${trimmed}"`);
                return false;
            }
            const span = document.createElement('span');
            span.classList.add('skill-tag');
            span.setAttribute('data-skill', trimmed);
            span.innerHTML = `${trimmed} <a class="delete-skill-btn">X</a>`;
            span.querySelector('.delete-skill-btn').addEventListener('click', function(e) {
                e.preventDefault();
                span.remove();
                updateSkillsHiddenField();
                logSkillData();
            });
            skillTagsContainer.appendChild(span);
            updateSkillsHiddenField();
            logSkillData();
            return true;
        }

        input.addEventListener('keydown', function(e) {
            if (e.key === 'Enter' || e.key === ',' || e.key === ' ') {
                e.preventDefault();
                const skillValues = this.value.split(',').map(v => v.trim()).filter(v => v);
                let anyAdded = false;
                skillValues.forEach(value => {
                    if (addSkillTag(value)) anyAdded = true;
                });
                if (anyAdded) {
                    this.value = '';
                    logSkillData();
                }
            }
        });

        clearBtn.addEventListener('click', function() {
            skillTagsContainer.innerHTML = '';
            updateSkillsHiddenField();
            logSkillData();
        });

        skillTagsContainer.querySelectorAll('.delete-skill-btn').forEach(btn => {
            btn.addEventListener('click', function(e) {
                e.preventDefault();
                this.closest('span.skill-tag').remove();
                updateSkillsHiddenField();
                logSkillData();
            });
        });

        function updateSkillsHiddenField() {
            const skillTags = Array.from(document.querySelectorAll('.skill-tag'))
                .map(tag => tag.getAttribute('data-skill'));
            document.getElementById('skillDescriptionHidden').value = skillTags.join('^');
            console.log('스킬 목록 업데이트:', hiddenInput.value);
        }

        updateSkillsHiddenField();
        window.updateSkillsHiddenField = updateSkillsHiddenField;
    }

    function initEducationObserver() {
        const educationContainer = document.getElementById('education-container');
        if (!educationContainer) return;
        educationContainer.addEventListener('change', function(e) {
            if (!e.target.classList.contains('academicType')) return;
            const selectedValue = e.target.value;
            const educationBox = e.target.closest('.education-box');
            const majorField = educationBox.querySelector('.academic-major-field');
            if (majorField) {
                if (selectedValue === '고등학교') {
                    majorField.style.display = 'none';
                    const majorInput = majorField.querySelector('input');
                    if (majorInput) majorInput.removeAttribute('required');
                } else {
                    majorField.style.display = 'block';
                    const majorInput = majorField.querySelector('input');
                    if (majorInput) majorInput.setAttribute('required', 'required');
                }
            }
            logEducationData();
        });

        educationContainer.querySelectorAll('.academicType').forEach(select => {
            if (select.value) {
                const event = new Event('change');
                select.dispatchEvent(event);
            }
        });

        educationContainer.addEventListener('change', function(e) {
            if (e.target.tagName === 'INPUT' || e.target.tagName === 'SELECT') {
                logEducationData();
            }
        });

        educationContainer.addEventListener('input', function(e) {
            if (e.target.tagName === 'INPUT' || e.target.tagName === 'TEXTAREA') {
                logEducationData();
            }
        });
    }

    function initFileSection() {
        const tbody = document.querySelector(".file-upload-section tbody");
        const addOuterUrlBtn = document.getElementById("add-outer-url");
        const addAttachmentFileBtn = document.getElementById("add-attachment-file");
        if (!tbody) return;

        function addDeleteEvent(button) {
            button.addEventListener("click", function() {
                this.closest("tr").remove();
                updateFileIndexes();
                logLinkData();
                logFileData();
            });
        }

        function updateFileIndexes() {
            const urlRows = document.querySelectorAll("tr.url-section");
            const fileRows = document.querySelectorAll("tr.file-section");
            urlRows.forEach((row, idx) => {
                const input = row.querySelector("input");
                if (input) {
                    input.name = `externalUrlList[${idx}]`;
                    input.id = `url-${idx}`;
                }
            });
            fileRows.forEach((row, idx) => {
                const input = row.querySelector("input[type='file']");
                if (input) {
                    input.name = `attachmentList[${idx}]`;
                    input.id = `attachment-${idx}`;
                }
            });
        }

        addOuterUrlBtn?.addEventListener("click", () => {
            const tr = document.createElement("tr");
            tr.classList.add("url-section");
            const urlIndex = tbody.querySelectorAll("tr.url-section").length;
            tr.innerHTML = `
                <td><label for="url-${urlIndex}">외부 링크</label></td>
                <td>
                    <input type="text" name="url-${urlIndex}" id="url-${urlIndex}" placeholder="https://example.com"/>
                </td>
                <td>
                    <button type="button" class="delete-tr">X</button>
                </td>
            `;
            tbody.appendChild(tr);
            addDeleteEvent(tr.querySelector(".delete-tr"));
            tr.querySelector('input').addEventListener('change', logLinkData);
            tr.querySelector('input').addEventListener('input', logLinkData);
            logLinkData();
        });

        addAttachmentFileBtn?.addEventListener("click", () => {
            const tr = document.createElement("tr");
            tr.classList.add("file-section");
            const fileIndex = tbody.querySelectorAll("tr.file-section").length;
            tr.innerHTML = `
                <td><label for="attachment-${fileIndex}">첨부파일 <p>10mb 이하의 파일을 올려주세요.</p></label></td>
                <td>
                    <input type="file" name="attachment-${fileIndex}" id="attachment-${fileIndex}" 
                           accept=".pdf, .doc, .docx, .ppt, .pptx, .xls, .xlsx"/>
                </td>
                <td>
                    <button type="button" class="delete-tr">X</button>
                </td>
            `;
            tbody.appendChild(tr);
            addDeleteEvent(tr.querySelector(".delete-tr"));
            tr.querySelector('input[type="file"]').addEventListener('change', logFileData);
            logFileData();
        });

        document.querySelectorAll(".delete-tr").forEach(addDeleteEvent);
        updateFileIndexes();
        tbody.querySelectorAll('input[type="text"]').forEach(input => {
            input.addEventListener('change', logLinkData);
            input.addEventListener('input', logLinkData);
        });
        tbody.querySelectorAll('input[type="file"]').forEach(input => {
            input.addEventListener('change', logFileData);
            input.addEventListener('input', logFileData);
        });
        window.updateFileIndexes = updateFileIndexes;
    }

    function initSectionChangeLogging() {
        const careerContainer = document.getElementById('career-container');
        if (careerContainer) {
            careerContainer.addEventListener('input', function(e) {
                if (e.target.tagName === 'INPUT' || e.target.tagName === 'TEXTAREA') {
                    logCareerData();
                }
            });
            careerContainer.addEventListener('change', function(e) {
                if (e.target.tagName === 'INPUT' || e.target.tagName === 'SELECT') {
                    logCareerData();
                }
            });
        }

        const licenseContainer = document.getElementById('license-container');
        if (licenseContainer) {
            licenseContainer.addEventListener('input', function(e) {
                if (e.target.tagName === 'INPUT' || e.target.tagName === 'TEXTAREA') {
                    logLicenseData();
                }
            });
            licenseContainer.addEventListener('change', function(e) {
                if (e.target.tagName === 'INPUT' || e.target.tagName === 'SELECT') {
                    logLicenseData();
                }
            });
        }

        // 초기 데이터 로깅은 renumberAll() 후에 한번 호출되므로 중복되지 않도록 함
    }

    function logCareerData() {
        const careerBoxes = document.querySelectorAll('.career-box');
        const careerData = [];
        careerBoxes.forEach((box, index) => {
            const career = {
                index: index,
                companyName: box.querySelector(`#companyName_${index}`)?.value || '',
                departmentName: box.querySelector(`#departmentName_${index}`)?.value || '',
                joinDate: box.querySelector(`#joinDate_${index}`)?.value || '',
                resignDate: box.querySelector(`#resignDate_${index}`)?.value || '',
                position: box.querySelector(`#position_${index}`)?.value || '',
                jobTitle: box.querySelector(`#jobTitle_${index}`)?.value || '',
                salary: box.querySelector(`#salary_${index}`)?.value || '',
                jobDescription: box.querySelector(`#jobDescription_${index}`)?.value || ''
            };
            careerData.push(career);
        });
        console.log('🔄 커리어 데이터 업데이트:', careerData);
        return careerData;
    }

    function logEducationData() {
        const educationBoxes = document.querySelectorAll('.education-box');
        const educationData = [];

        educationBoxes.forEach((box, index) => {
            const education = {
                index: index,
                academicType: box.querySelector('[name$=".academicType"]')?.value || '',
                academicName: box.querySelector('[name$=".academicName"]')?.value || '',
                graduateStatus: box.querySelector('[name$=".graduateStatus"]')?.value || '',
                academicMajor: box.querySelector('[name$=".academicMajor"]')?.value || '',
                enterDate: box.querySelector('[name$=".enterDate"]')?.value || '',
                graduateDate: box.querySelector('[name$=".graduateDate"]')?.value || ''
            };
            educationData.push(education);
        });

        console.log('🔄 학력 데이터 업데이트:', educationData);
        return educationData;
    }

    function logLicenseData() {
        const licenseBoxes = document.querySelectorAll('.license-box');
        const licenseData = [];
        licenseBoxes.forEach((box, index) => {
            const license = {
                index: index,
                name: box.querySelector(`#licenseName_${index}`)?.value || '',
                licenseGrade: box.querySelector(`#licenseGrade_${index}`)?.value || '',
                licenseAgency: box.querySelector(`#licenseAgency_${index}`)?.value || '',
                licenseDate: box.querySelector(`#licenseDate_${index}`)?.value || ''
            };
            licenseData.push(license);
        });
        console.log('🔄 자격증 데이터 업데이트:', licenseData);
        return licenseData;
    }

    function logSkillData() {
        const skillTags = document.querySelectorAll('.skill-tag');
        const skillData = Array.from(skillTags).map(tag => tag.getAttribute('data-skill'));
        console.log('🔄 스킬 데이터 업데이트:', skillData);
    }

    function logLinkData() {
        const fileTable = document.querySelector('.file-upload-section tbody');
        if (!fileTable) return;

        const urlRows = fileTable.querySelectorAll('tr.url-section');

        const urlData = [];
        urlRows.forEach((row, index) => {
            const input = row.querySelector('input[type="text"]');
            if (input) {
                urlData.push({
                    index: index,
                    url: input.value || ''
                });
            }
        });
        console.log('🔄 외부 URL 데이터 업데이트:', urlData);
        return urlData;
    }
    function logFileData() {
        const fileTable = document.querySelector('.file-upload-section tbody');
        if (!fileTable) return;

        const fileRows = fileTable.querySelectorAll('tr.file-section');
        const fileData = [];
        fileRows.forEach((row, index) => {
            const input = row.querySelector('input[type="file"]');
            const link = row.querySelector('a');
            if (input || link) {
                fileData.push({
                    index: index,
                    linkText: link?.textContent || '',
                    fileName: input?.files?.[0]?.name || ''
                });
            }
        });
        console.log('🔄 첨부파일 데이터 업데이트:', fileData);
        return fileData;
    }
});
