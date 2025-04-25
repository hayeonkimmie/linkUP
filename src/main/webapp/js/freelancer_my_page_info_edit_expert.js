document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const submitBtn = document.querySelector(".save-btn");

    validateForm();
    initSkillTagsTextInput();
    initEducationObserver();
    initFileSection();

    initDynamicSection("career-container", "add-career-btn", getCareerTemplate, "careerList", "career-box");
    initDynamicSection("education-container", "add-education-btn", getEducationTemplate, "educationList", "education-box");
    initDynamicSection("license-container", "add-license-btn", getLicenseTemplate, "licenseList", "license-box");

    function validateForm() {
        const requiredFields = form.querySelectorAll("input[required], select[required]");
        let isValid = true;

        requiredFields.forEach(field => {
            if (!field.value || field.value.trim() === "") isValid = false;
        });

        submitBtn.disabled = !isValid;
        submitBtn.style.opacity = isValid ? "1" : "0.5";
        submitBtn.style.cursor = isValid ? "pointer" : "not-allowed";
    }

    form.querySelectorAll("input[required], select[required]").forEach(el => {
        el.addEventListener("input", validateForm);
        el.addEventListener("change", validateForm);
    });

    function initDynamicSection(containerId, buttonClass, templateFn, baseName, boxClass) {
        const container = document.getElementById(containerId);
        const addBtn = document.querySelector(`.${buttonClass}`);

        renumberAll();

        addBtn?.addEventListener("click", () => {
            const box = document.createElement("div");
            box.classList.add("item-box", boxClass);
            box.innerHTML = templateFn(0, baseName);

            box.querySelector(".buttonDeleteField")?.addEventListener("click", () => {
                box.remove();
                renumberAll();
            });

            container.appendChild(box);
            renumberAll();
        });
    }

    function renumberAll() {
        const allTypes = [
            { selector: ".career-box", base: "careerList" },
            { selector: ".education-box", base: "educationList" },
            { selector: ".license-box", base: "licenseList" },
        ];

        allTypes.forEach(type => {
            const rows = document.querySelectorAll(type.selector);
            rows.forEach((row, index) => {
                row.querySelectorAll("input, select, textarea").forEach(el => {
                    const nameKey = el.name?.replace(/^.*?\[\d+\]\./, '') || '';
                    const idKey = el.id?.replace(/_\d+$/, '') || '';
                    if (nameKey) el.name = `${type.base}[${index}].${nameKey}`;
                    if (idKey) el.id = `${idKey}_${index}`;
                });
            });
        });
    }

    document.addEventListener("click", function (e) {
        if (e.target.classList.contains("buttonDeleteField")) {
            const box = e.target.closest(".item-box");
            if (box) {
                box.remove();
                renumberAll();
            }
        }
    });

    function initSkillTagsTextInput() {
        const input = document.getElementById('skills');
        const skillTagsContainer = document.querySelector('.skill-tags');
        const clearBtn = document.getElementById('clear-skills');
        const hiddenInput = document.getElementById('skillDescriptionHidden');

        if (!input || !skillTagsContainer || !clearBtn || !hiddenInput) return;

        function addSkillTag(skillValue) {
            const trimmed = skillValue.trim();
            if (!trimmed || skillTagsContainer.querySelector(`span[data-skill="${trimmed}"]`)) return;

            const span = document.createElement('span');
            span.classList.add('skill-tag');
            span.setAttribute('data-skill', trimmed);
            span.innerHTML = `${trimmed} <a class="delete-skill-btn">X</a>`;

            span.querySelector('.delete-skill-btn').addEventListener('click', function (e) {
                e.preventDefault();
                span.remove();
                updateHiddenField();
            });

            skillTagsContainer.appendChild(span);
            updateHiddenField();
        }

        input.addEventListener('keydown', function (e) {
            if (["Enter", ",", " "].includes(e.key)) {
                e.preventDefault();
                input.value.split(',').forEach(val => addSkillTag(val));
                input.value = '';
            }
        });

        clearBtn.addEventListener("click", () => {
            skillTagsContainer.innerHTML = '';
            updateHiddenField();
        });

        function updateHiddenField() {
            const allTags = [...skillTagsContainer.querySelectorAll('span[data-skill]')]
                .map(span => span.getAttribute('data-skill'));
            hiddenInput.value = allTags.join(',');
        }
    }

    function initEducationObserver() {
        const educationContainers = document.getElementById('education-container');
        if (!educationContainers) return;

        educationContainers.addEventListener('change', function (e) {
            if (!e.target.classList.contains('academicType')) return;

            const selectedValue = e.target.value;
            const parentBox = e.target.closest('.education-box');

            const cloneSource = selectedValue === '고등학교'
                ? document.getElementById('high')
                : document.getElementById('Univeducation');

            if (cloneSource && parentBox && !parentBox.querySelector('.cloned')) {
                const clone = cloneSource.cloneNode(true);
                clone.classList.add('cloned');
                clone.removeAttribute('id');
                parentBox.appendChild(clone);
            }
        });
    }

    function initFileSection() {
        const tbody = document.querySelector(".file-upload-section tbody");
        const addPortfolioLogBtn = document.getElementById("add-portfolio-log");
        const addOuterUrlBtn = document.getElementById("add-outer-url");
        const addAttachmentFileBtn = document.getElementById("add-attachment-file");

        function addDeleteEvent(button) {
            button.addEventListener("click", function () {
                this.closest("tr").remove();
                updateIndexes();
            });
        }

        function updateIndexes() {
            const rows = tbody.querySelectorAll("tr");
            rows.forEach((tr, index) => {
                const idx = index + 1;
                const label = tr.querySelector("label");
                const input = tr.querySelector("input, select");

                if (tr.classList.contains("portfolio-id-section")) {
                    label.setAttribute("for", `portfolio-id-select-${idx}`);
                    input.setAttribute("id", `portfolio-id-select-${idx}`);
                    input.setAttribute("name", `portfolio-id-select-${idx}`);
                }

                if (tr.classList.contains("url-section")) {
                    label.setAttribute("for", `file-url-${idx}`);
                    input.setAttribute("id", `file-url-${idx}`);
                    input.setAttribute("name", `file-url-${idx}`);
                }

                if (tr.classList.contains("file-section")) {
                    label.setAttribute("for", `attachment-${idx}`);
                    input.setAttribute("id", `attachment-${idx}`);
                    input.setAttribute("name", `attachment-${idx}`);
                }
            });
        }

        addPortfolioLogBtn?.addEventListener("click", () => {
            if (document.getElementById("portfolioId")) return;
            const tr = document.createElement("tr");
            tr.classList.add("portfolio-id-section");

            const options = document.getElementById("portfolioIdList")?.innerHTML || "";
            tr.innerHTML = `
                <td><label for="portfolioId">포트폴리오</label></td>
                <td><select id="portfolioId" name="project-id-select">${options}</select></td>
                <td><button type="button">X</button></td>`;
            tbody.appendChild(tr);
            addDeleteEvent(tr.querySelector("button"));
            updateIndexes();
        });

        addOuterUrlBtn?.addEventListener("click", () => {
            const tr = document.createElement("tr");
            tr.classList.add("url-section");
            tr.innerHTML = `
                <td><label>외부 링크</label></td>
                <td><input type="url" placeholder="https://example.com" /></td>
                <td><button type="button">X</button></td>`;
            tbody.appendChild(tr);
            addDeleteEvent(tr.querySelector("button"));
            updateIndexes();
        });

        addAttachmentFileBtn?.addEventListener("click", () => {
            const tr = document.createElement("tr");
            tr.classList.add("file-section");
            tr.innerHTML = `
                <td><label>첨부파일</label></td>
                <td><input type="file" class="file-item new" /></td>
                <td><button type="button">X</button></td>`;
            tbody.appendChild(tr);
            addDeleteEvent(tr.querySelector("button"));
            updateIndexes();
        });

        document.querySelectorAll(".delete-tr").forEach(button => {
            addDeleteEvent(button);
        });
    }
});
/*
document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector("form");
    const submitBtn = document.querySelector(".save-btn");
    const targetInput = document.getElementById('skills');
    const educationContainers = document.getElementById('education-container');
    const careerContainer = document.getElementById('career-container');
    const careerBtn = document.querySelector('.add-career-btn');

    validateForm();
    initFileSection();
    initSkillTagsTextInput();
    initCareerSection();
    initEducationObserver();

    // 폼 유효성 검사
    function validateForm() {
        const requiredFields = form.querySelectorAll("input[required], select[required]");
        let isValid = true;

        requiredFields.forEach(field => {
            if (!field.value || field.value.trim() === "") isValid = false;
        });

        submitBtn.disabled = !isValid;
        submitBtn.style.opacity = isValid ? "1" : "0.5";
        submitBtn.style.cursor = isValid ? "pointer" : "not-allowed";
    }

    form.querySelectorAll("input[required], select[required]").forEach(el => {
        el.addEventListener("input", validateForm);
        el.addEventListener("change", validateForm);
    });

    //  카테고리 셀렉트 로딩
    document.getElementById("category_name").addEventListener("change", function () {
        const selectedValue = this.value;

        fetch(`/getSubCategories?categoryId=${selectedValue}`)
            .then(res => res.text())
            .then(html => {
                document.getElementById("subCategory").innerHTML = "<option value=''>선택하세요</option>" + html;
            })
            .catch(err => console.error("서브카테고리 로딩 실패", err));
    });

    //  태그 초기화
    document.querySelector(".clear_fields").addEventListener("click", function () {
        this.parentElement.querySelectorAll("span").forEach(span => span.remove());
        updateCategoryListHiddenInput();
    });

    function updateCategoryListHiddenInput() {
        const values = [...document.querySelectorAll(".specialization span")].map(span => span.dataset.value);
        document.getElementById("category_list").value = values.join(",");
    }

    // 교육 타입에 따라 항목 클론
    function handleEducationTypeChange() {
        const value = parseInt(targetInput.value, 10);
        const cloneSource = value <= 0
            ? document.getElementById('high')
            : document.getElementById('Univeducation');

        if (cloneSource && educationContainers) {
            const clone = cloneSource.cloneNode(true);
            clone.removeAttribute('id');
            educationContainers.appendChild(clone);
        }
    }

    function initEducationObserver() {
        if (!targetInput) return;

        const observer = new MutationObserver(() => handleEducationTypeChange());

        observer.observe(targetInput, {
            attributes: true,
            attributeFilter: ['value']
        });

        targetInput.addEventListener('change', handleEducationTypeChange);
    }
    function initFileSection() {
        const tbody = document.querySelector(".file-upload-section tbody");
        const addPortfolioLogBtn = document.getElementById("add-portfolio-log");
        const addOuterUrlBtn = document.getElementById("add-outer-url");
        const addAttachmentFileBtn = document.getElementById("add-attachment-file");
        const deleteButtons = document.querySelectorAll(".delete-tr");

        deleteButtons.forEach((button) => {
            button.onclick = function () {
                this.closest("tr").remove();
                updateIndexes();
            };
        });

        function addDeleteEvent(button) {

            button.addEventListener("click", function () {
                this.closest("tr").remove();
                updateIndexes();
            });
        }

        function updateIndexes() {
            const rows = tbody.querySelectorAll("tr");

            rows.forEach((tr, index) => {
                const rowIndex = index + 1;

                if (tr.classList.contains("portfolio-id-section")) {
                    const label = tr.querySelector("label");
                    const select = tr.querySelector("select");

                    label.setAttribute("for", `project-id-select-${rowIndex}`);
                    select.setAttribute("id", `project-id-select-${rowIndex}`);
                    select.setAttribute("name", `project-id-select-${rowIndex}`);
                }

                if (tr.classList.contains("url-section")) {
                    const label = tr.querySelector("label");
                    const input = tr.querySelector("input");

                    label.setAttribute("for", `file-url-${rowIndex}`);
                    input.setAttribute("id", `file-url-${rowIndex}`);
                    input.setAttribute("name", `file-url-${rowIndex}`);
                }

                if (tr.classList.contains("file-section")) {
                    const label = tr.querySelector("label");
                    const input = tr.querySelector("input");

                    label.setAttribute("for", `attachment-${rowIndex}`);
                    input.setAttribute("id", `attachment-${rowIndex}`);
                    input.setAttribute("name", `attachment-${rowIndex}`);
                }
            });
        }

        addPortfolioLogBtn?.addEventListener("click", () => {
            if (document.getElementById("portfolioId")) {
                return;
            }
            const tr = document.createElement("tr");
            tr.classList.add("portfolio-id-section");

            // #portfolioIdList에 정의된 option 목록 가져오기
            const optionList = document.getElementById("portfolioIdList");
            console.log(optionList);
            const options = optionList ? optionList.innerHTML : "";
            tr.innerHTML = `
            <td><label for="portfolioId">포트폴리오</label></td>
            <td>
                <select class="project-id-select new" id="portfolioId" name="project-id-select" style="height: 40px;">
                    ${options}
                </select>
            </td>
            <td>
                <button type="button">X</button>
            </td>
        `;
            tbody.appendChild(tr);
            addDeleteEvent(tr.querySelector("button"));
            updateIndexes();
        });

        addOuterUrlBtn?.addEventListener("click", () => {
            const tr = document.createElement("tr");
            tr.classList.add("url-section");
            tr.innerHTML = `
            <td><label>외부 링크</label></td>
            <td>
                <input type="url" class="new" placeholder="https://example.com" class="file-url"/>
            </td>
            <td>
                <button type="button">X</button>
            </td>
        `;
            tbody.appendChild(tr);
            addDeleteEvent(tr.querySelector("button"));
            updateIndexes();
        });

        addAttachmentFileBtn?.addEventListener("click", () => {
            const tr = document.createElement("tr");
            tr.classList.add("file-section");
            tr.innerHTML = `
            <td><label>첨부파일</label></td>
            <td>
                <input type="file" class="file-item new"/>
            </td>
            <td>
                <button type="button">X</button>
            </td>
        `;
            tbody.appendChild(tr);
            addDeleteEvent(tr.querySelector("button"));
            updateIndexes();
        });
    }
    function initSkillTagsTextInput() {
        const input = document.getElementById('skills');
        const skillTagsContainer = document.querySelector('.skill-tags');
        const clearBtn = document.getElementById('clear-skills');

        if (!input || !skillTagsContainer || !clearBtn) return;

        function addSkillTag(skillValue) {
            const trimmed = skillValue.trim();
            if (!trimmed || skillTagsContainer.querySelector(`span[data-skill="${trimmed}"]`)) return;

            const span = document.createElement('span');
            span.classList.add('skill-tag');
            span.setAttribute('data-skill', trimmed);
            span.innerHTML = ` ${trimmed}
         <a class="delete-skill-btn">X</a>
        `;

            span.querySelector('.delete-skill-btn').addEventListener('click', function (e) {
                e.stopPropagation();
                e.preventDefault();
                span.remove();
                updateHiddenField();
            });

            skillTagsContainer.appendChild(span);
            updateHiddenField();
        }

        // 초기 로드된 .delete-skill-btn에도 이벤트 부여
        skillTagsContainer.querySelectorAll('.delete-skill-btn').forEach(btn => {
            btn.addEventListener('click', function (e) {
                e.stopPropagation();
                e.preventDefault();
                this.closest('span').remove();
                updateHiddenField();
            });
        });

        // 쉼표 또는 엔터 입력 시 태그 추가
        input.addEventListener('keydown', function (e) {
            if (e.key === 'Enter' || e.key === ',' || e.key === ' ') {
                e.preventDefault();
                const values = input.value.split(',');
                values.forEach(val => addSkillTag(val));
                input.value = '';
            }
        });

        // 초기화 버튼
        clearBtn.addEventListener('click', () => {
            skillTagsContainer.innerHTML = '';
            updateHiddenField();
        });

        // 히든 필드 업데이트
        function updateHiddenField() {
            const allTags = Array.from(skillTagsContainer.querySelectorAll('span[data-skill]'))
                .map(span => span.getAttribute('data-skill'));
            const hiddenInput = document.getElementById('skillDescriptionHidden');
            if (hiddenInput) hiddenInput.value = allTags.join(',');
        }
    }

    const targetInput = document.getElementById('skills');
    const educationContainers = document.getElementById('education-container');
    const observer = new MutationObserver(() => {
        handleEducationTypeChange();
    });

    observer.observe(targetInput, {
        attributes: true,
        attributeFilter: ['value']
    });
});
*/