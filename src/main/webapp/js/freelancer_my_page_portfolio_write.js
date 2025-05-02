window.addEventListener('DOMContentLoaded', () => {
    initFileSection();
    setupDynamicInputListeners();
    initSkillTagsTextInput();
    initThumbnailUpload();
});

function initFileSection() {
    const tbody = document.querySelector(".file-upload-section tbody");

    const addProjectLogBtn = document.getElementById("add-project-log");
    const addOuterUrlBtn = document.getElementById("add-outer-url");
    const addAttachmentFileBtn = document.getElementById("add-attachment-file");

    function addDeleteEvent(button) {
        button.addEventListener("click", function () {
            this.closest("tr").remove();
            updateIndexes();
        });
    }

    function updateIndexes() {
        let urlIndex = 1;
        let attachmentIndex = 1;

        const rows = tbody.querySelectorAll("tr");

        rows.forEach((tr) => {
            if (tr.classList.contains("project-id-section")) {
                const label = tr.querySelector("label");
                const select = tr.querySelector("select");
                label.setAttribute("for", `project-id-select`);
                select.setAttribute("id", `project-id-select`);
                select.setAttribute("name", `project-id-select`);
            }

            if (tr.classList.contains("url-section")) {
                const label = tr.querySelector("label");
                const input = tr.querySelector("input[type='url']");
                label.setAttribute("for", `url-${urlIndex}`);
                input.setAttribute("id", `url-${urlIndex}`);
                input.setAttribute("name", `url-${urlIndex}`);
                urlIndex++;
            }

            if (tr.classList.contains("file-section")) {
                const label = tr.querySelector("label");
                const input = tr.querySelector("input[type='file']");
                label.setAttribute("for", `attachment-${attachmentIndex}`);
                input.setAttribute("id", `attachment-${attachmentIndex}`);
                input.setAttribute("name", `attachment-${attachmentIndex}`);
                attachmentIndex++;
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
            <td><label for="projectId">링크업을 통해 참여했던 프로젝트</label></td>
            <td>
                <select class="project-id-select" name="project-id-select" style="width: 100%; height: 40px;">
                    ${options}
                </select>
            </td>
            <td>
                <button type="button" class="delete-tr">X</button>
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
            </td>
            <td>
                <button type="button" class="delete-tr">X</button>
            </td>
        `;
        tbody.appendChild(tr);
        addDeleteEvent(tr.querySelector("button"));
        updateIndexes();
        setupDynamicInputListeners();
    });

    addAttachmentFileBtn?.addEventListener("click", () => {
        const tr = document.createElement("tr");
        tr.classList.add("file-section");
        tr.innerHTML = `
            <td><label>첨부파일 <p class="attachment-notification ">10mb 이하의 파일을 올려주세요.</p></label></td>
            <td>
                <input type="file" class="file-item"/>
            </td>
            <td>
                <button type="button" class="delete-tr">X</button>
            </td>
        `;
        tbody.appendChild(tr);
        addDeleteEvent(tr.querySelector("button"));
        updateIndexes();
        setupDynamicInputListeners();
    });

    // 초기 로딩 데이터도 삭제버튼 바인딩
    tbody.querySelectorAll(".delete-tr").forEach(button => {
        addDeleteEvent(button);
    });
}

function initSkillTagsTextInput() {
    const input = document.getElementById('skills');
    const skillTagsContainer = document.querySelector('.skill-tags');
    const clearBtn = document.getElementById('clear-skills');
    const hiddenInput = document.getElementById('skill-description-hidden');

    if (!input || !skillTagsContainer || !clearBtn || !hiddenInput) return;

    function addSkillTag(skillValue) {
        const trimmed = skillValue.trim();
        if (!trimmed) return;

        const exists = [...skillTagsContainer.querySelectorAll('span[data-skill]')]
            .some(span => span.getAttribute('data-skill').toLowerCase() === trimmed.toLowerCase());
        if (exists) return;

        const span = document.createElement('span');
        span.classList.add('skill-tag');
        span.setAttribute('data-skill', trimmed);
        span.innerHTML = `${trimmed} <a class="delete-skill-btn">X</a>`;

        span.querySelector('.delete-skill-btn').addEventListener('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            span.remove();
            updateHiddenField();
        });

        skillTagsContainer.appendChild(span);
        updateHiddenField();
    }

    function updateHiddenField() {
        const allTags = [...skillTagsContainer.querySelectorAll('span[data-skill]')]
            .map(span => span.getAttribute('data-skill'));
        hiddenInput.value = allTags.join('^');
        console.log("hiddenInput" + hiddenInput.value);
    }

    input.addEventListener('keydown', function (e) {
        if (["Enter", ",", " "].includes(e.key)) {
            e.preventDefault();
            const values = input.value.split(',').map(v => v.trim()).filter(v => v !== '');
            values.forEach(val => addSkillTag(val));
            input.value = '';
        }
    });

    clearBtn.addEventListener('click', () => {
        skillTagsContainer.innerHTML = '';
        updateHiddenField();
    });

    // 초기 렌더된 스킬 태그에 삭제 버튼 이벤트 바인딩
    skillTagsContainer.querySelectorAll('.delete-skill-btn').forEach(button => {
        button.addEventListener('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            this.closest('span').remove();
            updateHiddenField();
        });
    });
}
function collectAndSetExternalUrls() {
    const urlSet = new Set();
    document.querySelectorAll('input[id^="url-"]').forEach(input => {
        if (input.value.trim() !== '') {
            urlSet.add(input.value.trim()); // Set에는 add()를 사용
        }
    });
    const result = Array.from(urlSet).join('^'); // Set → Array 변환 후 join
    console.log('External URLs:', result);
    document.getElementById('external-url-hidden').value = result;
}

function collectAndSetAttachments() {
    const attachmentSet = new Set(); // 이름도 명확히
    document.querySelectorAll('input[id^="attachment-"]').forEach(input => {
        if (input.files && input.files.length > 0) {
            attachmentSet.add(input.files[0].name); // Set에는 add()
        }
    });
    const result = Array.from(attachmentSet).join('^');
    console.log('Attachments:', result);
    document.getElementById('attachment-hidden').value = result;
}


// input 생성될 때마다 change 이벤트 등록
function setupDynamicInputListeners() {
    // 기존 input에 대해서도
    document.querySelectorAll('input[id^="url-"], input[id^="attachment-"]').forEach(input => {
        if (!input.dataset.listenerAdded) {
            input.addEventListener('input', () => {
                collectAndSetExternalUrls();
                collectAndSetAttachments();
            });
            input.dataset.listenerAdded = "true"; // 중복 방지용 플래그
        }
    });
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
document.getElementById('submit-btn').addEventListener('click', function(event) {
    collectAndSetExternalUrls();
    collectAndSetAttachments();
    // 이 시점에서 hidden input 들이 채워짐
});