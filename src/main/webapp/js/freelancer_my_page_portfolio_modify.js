document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const submitBtn = document.querySelector(".save-btn");
    initSkillTagsTextInput();
    initFileSection();
    initThumbnailUpload();
// 폼 제출 전 데이터 준비 이벤트 추가
    form.addEventListener("submit", function(e) {
        prepareFormDataForSubmission();
        // 폼 제출 시 모든 섹션의 데이터 로깅
        logAllSectionsData();
    });

    // 페이지 로드 직후, 초기 항목들의 인덱스 재설정 및 기존 값 로깅
    // renumberAll();
    logAllSectionsData();
    function initFileSection() {
        const tbody = document.querySelector(".file-upload-section tbody");
        const addOuterUrlBtn = document.getElementById("add-outer-url");
        const addAttachmentFileBtn = document.getElementById("add-attachment-file");
        const addProjectLogBtn = document.getElementById("add-project-log");

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
        addProjectLogBtn?.addEventListener("click", () => {
            if (document.querySelector(".project-id-section")) {
                alert("프로젝트는 하나만 등록할 수 있습니다.");
                return;
            }
            const tr = document.createElement("tr");
            tr.classList.add("project-id-section");

            const optionList = document.getElementById("portfolioIdList");
            const options = optionList ? optionList.innerHTML : "";

            tr.innerHTML = `
            <td><label for="portfolioId">프로젝트 상세페이지</label></td>
            <td>
                <select name="projectId" class="project-id-select" style="width: 100%; height: 40px;">
                    ${options}
                </select>
            </td>
            <td>
                <button type="button" class="delete-tr">X</button>
            </td>
        `;
            tbody.appendChild(tr);
            addDeleteEvent(tr.querySelector("button"));
        });
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
                <td><label for="attachment-${fileIndex}">첨부파일 <p class="attachment-notification ">10mb 이하의 파일을 올려주세요.</p></label></td>
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
    function prepareFormDataForSubmission() {
        // 🔹 스킬 리스트 준비
        const skillTags = Array.from(document.querySelectorAll('.skill-tag'))
            .map(tag => tag.getAttribute('data-skill'));
        document.getElementById('skillDescriptionHidden').value = skillTags.join('^');

        // 🔹 외부 URL 리스트 준비
        const linkList = logLinkData();
        if (linkList && Array.isArray(linkList)) {
            const externalUrlList = linkList.map(item => item.url || '');
            document.getElementById('externalUrlListHidden').value = externalUrlList.join('^');
        }
        const fileList = logFileData();
        if (fileList && Array.isArray(fileList)) {
            const attachmentList = fileList.map(item => item.fileName || item.linkText || '');
            document.getElementById('attachmentListHidden').value = attachmentList.join('^');
        }
    }
    // 모든 섹션의 데이터를 콘솔에 로깅하는 함수
    function logAllSectionsData() {
        logSkillData();
        logFileData();
        logLinkData();
    }
    function initThumbnailUpload() {
        const uploadBtn = document.querySelector('.upload-placeholder');
        const fileInput = document.getElementById('thumbnail');

        if (uploadBtn && fileInput) {
            fileInput.addEventListener('change', function () {
                if (this.files && this.files[0]) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        uploadBtn.innerHTML = `
                            <img src="${e.target.result}" 
                                 alt="썸네일 미리보기" 
                                 style="max-width: 100%; height: auto; border-radius: 8px;"/>
                        `;
                    };
                    reader.readAsDataURL(this.files[0]);
                }
            });
        }
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