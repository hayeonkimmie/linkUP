// ==================== [썸네일 이미지 미리보기] ====================
window.addEventListener('DOMContentLoaded', () => {
    const uploadBtn = document.querySelector('.upload-placeholder');
    const fileInput = document.getElementById('thumbnail');

    if (uploadBtn && fileInput) {
        // 버튼 클릭 시 파일 선택창 열기
        uploadBtn.addEventListener('click', () => {
            fileInput.click();
        });

        // 파일 선택 시 이미지 미리보기 표시
        fileInput.addEventListener('change', function () {
            if (this.files && this.files[0]) {
                const file = this.files[0];
                const reader = new FileReader();

                reader.onload = function (e) {
                    uploadBtn.innerHTML = `
                        <img src="${e.target.result}" 
                             alt="썸네일 미리보기" 
                             style="max-width: 100%; height: auto; border-radius: 8px;" />
                    `;
                };

                reader.readAsDataURL(file); // 이미지 base64로 변환
            }
        });
    }
});

// ==================== [첨부파일 / 외부링크 / 프로젝트 불러오기 동적 추가] ====================
window.onload = function () {
    const tbody = document.querySelector(".file-upload-section tbody");

    const addProjectLogBtn = document.getElementById("add-project-log");
    const addOuterUrlBtn = document.getElementById("add-outer-url");
    const addAttachmentFileBtn = document.getElementById("add-attachment-file");

    // 삭제 버튼 이벤트 부여 함수
    function addDeleteEvent(button) {
        button.addEventListener("click", function () {
            this.closest("tr").remove();
            updateIndexes(); // 삭제 시 인덱스 재정렬
        });
    }

    // 동적으로 추가된 tr에 ID/NAME 동기화
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

    // 프로젝트 이력 추가
    addProjectLogBtn.addEventListener("click", function () {
        const tr = document.createElement("tr");
        tr.classList.add("project-id-section");
        tr.innerHTML = `
            <td><label>프로젝트 상세페이지</label></td>
            <td>
                <select class="project-id-select">
                    <option value="">참여했던 프로젝트 제목</option>
                </select>
                <button type="button">X</button>
            </td>
        `;
        tbody.appendChild(tr);
        addDeleteEvent(tr.querySelector("button"));
        updateIndexes();
    });

    // 외부 URL 추가
    addOuterUrlBtn.addEventListener("click", function () {
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

    // 파일 첨부 추가
    addAttachmentFileBtn.addEventListener("click", function () {
        const tr = document.createElement("tr");
        tr.classList.add("file-section");
        tr.innerHTML = `
            <td><label>첨부파일</label></td>
            <td>
                <input type="file" class="file-item"/>
                <button type="button">X</button>
            </td>
        `;
        tbody.appendChild(tr);
        addDeleteEvent(tr.querySelector("button"));
        updateIndexes();
    });
};

// ==================== [스킬 선택 시 태그 추가 및 삭제 기능] ====================
window.addEventListener('DOMContentLoaded', () => {
    const skillsSelect = document.getElementById('skills');
    const skillTagsContainer = document.querySelector('.skill-tags');
    const clearBtn = document.getElementById('clear-skills');

    // 선택한 스킬을 span 태그로 추가
    function addSkillTag(skillValue) {
        // 중복 방지
        if (skillTagsContainer.querySelector(`span[data-skill="${skillValue}"]`)) return;

        const span = document.createElement('span');
        span.classList.add('skill-tag');
        span.setAttribute('data-skill', skillValue);
        span.innerHTML = `
            ${skillValue}
            <button class="delete-skill-btn">X</button>
        `;

        // 삭제 버튼 클릭 시 span 제거 및 select 해제
        span.querySelector('.delete-skill-btn').addEventListener('click', function (e) {
            e.stopPropagation();
            span.remove();
            skillsSelect.querySelector(`option[value="${skillValue}"]`).selected = false;
        });

        // span 자체 클릭 시 다시 select에 선택
        span.addEventListener('click', function () {
            const option = skillsSelect.querySelector(`option[value="${skillValue}"]`);
            if (option) {
                option.selected = true;
                span.remove();
            }
        });

        skillTagsContainer.appendChild(span);
    }

    // select에서 값이 변경될 때 태그 추가
    skillsSelect.addEventListener('change', function () {
        const selectedOptions = Array.from(skillsSelect.selectedOptions);
        selectedOptions.forEach(option => {
            addSkillTag(option.value);
        });
    });

    // 초기화 버튼 클릭 시 전체 태그 및 select 초기화
    clearBtn.addEventListener('click', () => {
        skillTagsContainer.querySelectorAll('.skill-tag').forEach(tag => tag.remove());
        Array.from(skillsSelect.options).forEach(option => option.selected = false);
    });
});

/*


window.addEventListener('DOMContentLoaded', () => {
    const uploadBtn = document.querySelector('.upload-placeholder');
    const fileInput = document.getElementById('thumbnail');

    if (uploadBtn && fileInput) {
        uploadBtn.addEventListener('click', () => {
            fileInput.click();
        });

        fileInput.addEventListener('change', function () {
            if (this.files && this.files[0]) {
                const file = this.files[0];
                const reader = new FileReader();

                reader.onload = function (e) {
                    uploadBtn.innerHTML = `
            <img src="${e.target.result}" 
                 alt="썸네일 미리보기" 
                 style="max-width: 100%; height: auto; border-radius: 8px;" />
          `;
                };

                reader.readAsDataURL(file); // 파일을 base64로 읽어서 이미지로 미리보기
            }
        });
    }
});
window.onload = function () {
    const tbody = document.querySelector(".file-upload-section tbody");

    const addProjectLogBtn = document.getElementById("add-project-log");
    const addOuterUrlBtn = document.getElementById("add-outer-url");
    const addAttachmentFileBtn = document.getElementById("add-attachment-file");

    function addDeleteEvent(button) {
        button.addEventListener("click", function () {
            this.closest("tr").remove();
            updateIndexes(); // 삭제 시에도 인덱스를 다시 업데이트
        });
    }

    function updateIndexes() {
        const rows = tbody.querySelectorAll("tr");

        rows.forEach((tr, index) => {
            const rowIndex = index + 1; // 1-based index

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

    addProjectLogBtn.addEventListener("click", function () {
        const tr = document.createElement("tr");
        tr.classList.add("project-id-section");
        tr.innerHTML = `
      <td><label>프로젝트 상세페이지</label></td>
      <td>
        <select class="project-id-select">
          <option value="">참여했던 프로젝트 제목</option>
        </select>
        <button type="button">X</button>
      </td>
    `;
        tbody.appendChild(tr);
        addDeleteEvent(tr.querySelector("button"));
        updateIndexes();
    });

    addOuterUrlBtn.addEventListener("click", function () {
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

    addAttachmentFileBtn.addEventListener("click", function () {
        const tr = document.createElement("tr");
        tr.classList.add("file-section");
        tr.innerHTML = `
      <td><label>첨부파일</label></td>
      <td>
        <input type="file" class="file-item"/>
        <button type="button">X</button>
      </td>
    `;
        tbody.appendChild(tr);
        addDeleteEvent(tr.querySelector("button"));
        updateIndexes();
    });
};
window.addEventListener('DOMContentLoaded', () => {
    const skillsSelect = document.getElementById('skills');
    const skillTagsContainer = document.querySelector('.skill-tags');
    const clearBtn = document.getElementById('clear-skills');

    // 스킬 추가 함수
    function addSkillTag(skillValue) {
        if (skillTagsContainer.querySelector(`span[data-skill="${skillValue}"]`)) {
            return; // 중복 방지
        }

        const span = document.createElement('span');
        span.classList.add('skill-tag');
        span.setAttribute('data-skill', skillValue);
        span.innerHTML = `
      ${skillValue}
      <button class="delete-skill-btn">X</button>
    `;

        // 삭제 버튼 클릭 시 동작
        span.querySelector('.delete-skill-btn').addEventListener('click', function (e) {
            e.stopPropagation(); // 부모 span 클릭 이벤트 방지
            span.remove();
            skillsSelect.querySelector(`option[value="${skillValue}"]`).selected = false;
        });

        // span 클릭 시 다시 select에서 선택되도록
        span.addEventListener('click', function () {
            const option = skillsSelect.querySelector(`option[value="${skillValue}"]`);
            if (option) {
                option.selected = true;
                span.remove(); // 태그에서 제거
            }
        });

        skillTagsContainer.appendChild(span);
    }

    // select에서 값이 선택되었을 때
    skillsSelect.addEventListener('change', function () {
        const selectedOptions = Array.from(skillsSelect.selectedOptions);

        selectedOptions.forEach(option => {
            addSkillTag(option.value);
        });
    });

    // 초기화 버튼 클릭 시 전체 제거
    clearBtn.addEventListener('click', () => {
        skillTagsContainer.innerHTML = '';
        Array.from(skillsSelect.options).forEach(option => option.selected = false);
    });
});*/
