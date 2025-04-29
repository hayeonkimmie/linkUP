document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById('expertInfoForm');
    const submitBtn = document.querySelector(".save-btn");

    validateForm();
    initDynamicSections();
    initSkillTags();
    initCategoryManagement();
    initFileSection();
    logAllInitialData();

    form.addEventListener("submit", function (e) {
        prepareFormDataForSubmission();
    });

    function prepareFormDataForSubmission() {
        updateSkillsHiddenField();
        renumberAll();
        updateFileAndUrlIndexes();
        updateCategoryField();
    }

    function validateForm() {
        const requiredFields = form.querySelectorAll("input[required], select[required], textarea[required]");
        const updateButtonState = () => {
            const isValid = Array.from(requiredFields).every(field => field.value.trim() !== '');
            submitBtn.disabled = !isValid;
            submitBtn.style.opacity = isValid ? "1" : "0.5";
            submitBtn.style.cursor = isValid ? "pointer" : "not-allowed";
        };
        form.addEventListener("input", updateButtonState);
        form.addEventListener("change", updateButtonState);
        updateButtonState();
    }

    function initDynamicSections() {
        initDynamicSection("career-container", "add-career-btn", getCareerTemplate, "careerList", "career-box");
        initDynamicSection("education-container", "education_addbutton", getEducationTemplate, "educationList", "education-box");
        initDynamicSection("license-container", "add-license-btn", getLicenseTemplate, "licenseList", "license-box");
    }

    function initDynamicSection(containerId, buttonId, templateFn, baseName, boxClass) {
        const container = document.getElementById(containerId);
        const addBtn = document.getElementById(buttonId) || document.querySelector(`.${buttonId}`);
        if (!container || !addBtn) return;

        addBtn.addEventListener("click", () => {
            const box = document.createElement("div");
            box.className = `item-box ${boxClass}`;
            box.innerHTML = templateFn(container.querySelectorAll(`.${boxClass}`).length, baseName);
            container.appendChild(box);
            renumberAll();
        });
    }

    function renumberAll() {
        const sections = [
            {selector: ".career-box", base: "careerList"},
            {selector: ".education-box", base: "educationList"},
            {selector: ".license-box", base: "licenseList"}
        ];
        sections.forEach(({selector, base}) => {
            document.querySelectorAll(selector).forEach((box, idx) => {
                box.querySelectorAll("input, select, textarea").forEach(input => {
                    if (input.name) {
                        const suffix = input.name.replace(/^.*?\[\d+\]\./, '');
                        input.name = `${base}[${idx}].${suffix}`;
                    }
                    if (input.id) {
                        const idSuffix = input.id.replace(/_\d+$/, '');
                        input.id = `${idSuffix}_${idx}`;
                    }
                });
            });
        });
    }

    function getCareerTemplate(idx, base) {
        return `
            <button type="button" class="buttonDeleteField">X</button>
            <input name="${base}[${idx}].companyName" id="companyName_${idx}" placeholder="회사명" required/>
            <input name="${base}[${idx}].departmentName" id="departmentName_${idx}" placeholder="부서명"/>
            <input name="${base}[${idx}].joinDate" type="month" id="joinDate_${idx}"/>
            <input name="${base}[${idx}].resignDate" type="month" id="resignDate_${idx}"/>
            <input name="${base}[${idx}].position" id="position_${idx}" placeholder="직급/직책"/>
            <textarea name="${base}[${idx}].jobDescription" id="jobDescription_${idx}" required></textarea>
        `;
    }

    function getEducationTemplate(idx, base) {
        return `
            <button type="button" class="buttonDeleteField">X</button>
            <select name="${base}[${idx}].academicType" id="academicType_${idx}" required>
                <option value="">학교 구분</option>
                <option value="고등학교">고등학교</option>
                <option value="대학교(2,3년)">대학교(2,3년)</option>
                <option value="대학교(4년)">대학교(4년)</option>
                <option value="대학원">대학원</option>
            </select>
            <input name="${base}[${idx}].academicName" id="academicName_${idx}" placeholder="학교명" required/>
            <input name="${base}[${idx}].academicMajor" id="academicMajor_${idx}" placeholder="전공"/>
            <input name="${base}[${idx}].enterDate" type="month" id="enterDate_${idx}" required/>
            <input name="${base}[${idx}].graduateDate" type="month" id="graduateDate_${idx}"/>
        `;
    }

    function getLicenseTemplate(idx, base) {
        return `
            <button type="button" class="buttonDeleteField">X</button>
            <input name="${base}[${idx}].name" id="licenseName_${idx}" placeholder="자격증명" required/>
            <input name="${base}[${idx}].licenseGrade" id="licenseGrade_${idx}" placeholder="점수/급"/>
            <input name="${base}[${idx}].licenseAgency" id="licenseAgency_${idx}" placeholder="발행처"/>
            <input name="${base}[${idx}].licenseDate" type="month" id="licenseDate_${idx}"/>
        `;
    }

    function initSkillTags() {
        const input = document.getElementById('skills');
        const container = document.querySelector('.skill-tags');
        const hiddenInput = document.getElementById('skillDescriptionHidden');
        const clearBtn = document.getElementById('clear-skills');

        input.addEventListener('keydown', function (e) {
            if (["Enter", ",", " "].includes(e.key)) {
                e.preventDefault();
                const skills = this.value.split(',').map(s => s.trim()).filter(Boolean);
                skills.forEach(skill => {
                    if (!container.querySelector(`[data-skill="${skill}"]`)) {
                        const span = document.createElement('span');
                        span.className = 'skill-tag';
                        span.setAttribute('data-skill', skill);
                        span.innerHTML = `${skill} <a class="delete-skill-btn">X</a>`;
                        span.querySelector('.delete-skill-btn').addEventListener('click', () => {
                            span.remove();
                            updateSkillsHiddenField();
                        });
                        container.appendChild(span);
                    }
                });
                this.value = '';
                updateSkillsHiddenField();
            }
        });

        clearBtn.addEventListener('click', function () {
            container.innerHTML = '';
            updateSkillsHiddenField();
        });

        function updateSkillsHiddenField() {
            hiddenInput.value = Array.from(container.querySelectorAll('.skill-tag'))
                .map(tag => tag.getAttribute('data-skill')).join('^');
        }
    }

    function initCategoryManagement() {
        // 기존 코드와 동일
    }

    function initFileSection() {
        const tbody = document.querySelector(".file-upload-section tbody");
        const addOuterUrlBtn = document.getElementById("add-outer-url");
        const addAttachmentFileBtn = document.getElementById("add-attachment-file");

        function updateFileAndUrlIndexes() {
            tbody.querySelectorAll('tr.url-section').forEach((row, idx) => {
                const input = row.querySelector('input[type="text"]');
                if (input) {
                    input.name = `externalUrlList[${idx}]`;
                    input.id = `url-${idx}`;
                }
            });
            tbody.querySelectorAll('tr.file-section').forEach((row, idx) => {
                const input = row.querySelector('input[type="file"]');
                if (input) {
                    input.name = `attachmentList[${idx}]`;
                    input.id = `attachment-${idx}`;
                }
            });
        }

        addOuterUrlBtn?.addEventListener("click", () => {
            const tr = document.createElement("tr");
            tr.className = "url-section";
            tr.innerHTML = `
                <td>외부 링크</td>
                <td><input type="text" placeholder="https://example.com"/></td>
                <td><button type="button" class="delete-tr">X</button></td>
            `;
            tbody.appendChild(tr);
            tr.querySelector(".delete-tr").addEventListener('click', function () {
                tr.remove();
                updateFileAndUrlIndexes();
            });
            updateFileAndUrlIndexes();
        });

        addAttachmentFileBtn?.addEventListener("click", () => {
            const tr = document.createElement("tr");
            tr.className = "file-section";
            tr.innerHTML = `
                <td>첨부파일</td>
                <td><input type="file" accept=".pdf,.doc,.docx,.ppt,.pptx,.xls,.xlsx"/></td>
                <td><button type="button" class="delete-tr">X</button></td>
            `;
            tbody.appendChild(tr);
            tr.querySelector(".delete-tr").addEventListener('click', function () {
                tr.remove();
                updateFileAndUrlIndexes();
            });
            updateFileAndUrlIndexes();
        });

        updateFileAndUrlIndexes();
    }

    function logAllInitialData() {
        console.log("폼 초기 데이터 로드 완료");
    }

    function updateCategoryField() {
        const categoryHidden = document.getElementById("categoryHidden");
        const subCategory = document.getElementById("subCategory");
        if (categoryHidden && subCategory && !categoryHidden.value) {
            categoryHidden.value = subCategory.value;
        }
    }
});
