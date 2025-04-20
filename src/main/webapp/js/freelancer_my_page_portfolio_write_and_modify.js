window.addEventListener('DOMContentLoaded', () => {
    initFileSection();
    initSkillTagsTextInput();
    initThumbnailUpload();
    moveToList();
});
const contextPath = "${pageContext.request.contextPath}";
function initFileSection() {
    const tbody = document.querySelector(".file-upload-section tbody");

    const addProjectLogBtn = document.getElementById("add-project-log");
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

            if (tr.classList.contains("project-id-section")) {
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

    addProjectLogBtn?.addEventListener("click", () => {
        if (document.getElementById("portfolioId")) {
            return;
        }
        const tr = document.createElement("tr");
        tr.classList.add("project-id-section");

        // #portfolioIdList에 정의된 option 목록 가져오기
        const optionList = document.getElementById("portfolioIdList");
        console.log(optionList);
        const options = optionList ? optionList.innerHTML : "";
        tr.innerHTML = `
            <td><label for="portfolioId">프로젝트 상세페이지</label></td>
            <td>
                <select class="project-id-select" id="portfolioId" name="project-id-select" style="width: 100%; height: 40px;">
                    ${options}
                </select>
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
                <input type="url" placeholder="https://" class="file-url"/>
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
                <input type="file" class="file-item"/>
                <button type="button" class="delete-tr">X</button>
            </td>
        `;
        tbody.appendChild(tr);
        addDeleteEvent(tr.querySelector("button"));
        updateIndexes();
    });
}
/*스킬 입력*/
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
            <button class="delete-skill-btn">X</button>
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

    // 폼 전송 전 히든 필드에 태그들 합쳐 넣기 (쉼표 또는 '^'로 구분)
    function updateHiddenField() {
        const allTags = Array.from(skillTagsContainer.querySelectorAll('span[data-skill]'))
            .map(span => span.getAttribute('data-skill'));
        const hiddenInput = document.getElementById('skillDescriptionHidden');
        if (hiddenInput) hiddenInput.value = allTags.join('^');
    }
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
                             style="max-width: 100%; height: auto; border-radius: 8px;" onclick="readURL(this)"/>
                    `;
                };
                reader.readAsDataURL(this.files[0]);
            }
        });
    }
}
function moveToList (){
    const listBtn = document.getElementById('list-btn');
    // 목록: 확인창 후 이동
    listBtn.addEventListener('click', () => {
        if (confirm('입력한 내용이 저장되지 않습니다. 목록으로 이동하시겠습니까?')) {
            location.href = `${contextPath}/my-page/portfolio-list`;
        }
    });
}
function readURL(input) {
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = e => document.getElementById("preview").src = e.target.result;
        reader.readAsDataURL(input.files[0]);
    }
}