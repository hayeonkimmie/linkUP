window.onload = function () {
    const contextPath = "${pageContext.request.contextPath}";
    const submitBtn = document.querySelector(".save-btn");
    const form = document.querySelector("form");

    // 폼 유효성 검사 함수 - 필수 필드가 모두 채워졌는지 확인하여 버튼 상태 조정
    function validateForm() {
        const requiredFields = form.querySelectorAll("input[required], select[required]");
        let isValid = true;

        requiredFields.forEach(field => {
            if (!field.value || field.value.trim() === "") {
                isValid = false;
            }
        });

        submitBtn.disabled = !isValid;
        submitBtn.style.opacity = isValid ? "1" : "0.5";
        submitBtn.style.cursor = isValid ? "pointer" : "not-allowed";
    }

    // input과 select 요소 변경 감지하여 폼 유효성 검사 실행
    form.querySelectorAll("input[required], select[required]").forEach(el => {
        el.addEventListener("input", validateForm);
        el.addEventListener("change", validateForm);
    });

    // 스킬 태그 초기화 버튼 처리
    const clearBtns = document.getElementsByClassName("clear_field");
    Array.from(clearBtns).forEach(btn => {
        btn.addEventListener("click", function () {
            const tags = document.querySelectorAll(".skill-tags span");
            tags.forEach(tag => tag.remove());
        });
    });

    // 숨겨진 input에 두 번째 셀렉트 박스의 value만 ,로 조인하여 저장
    function updateCategoryListHiddenInput() {
        const values = [...document.querySelectorAll(".specialization span")]
            .map(span => span.dataset.value);
        document.getElementById("category_list").value = values.join(",");
    }

    // 첫 번째 셀렉트 선택 값 저장용 변수
    let selectedMainCategory = null;

    // 두 번째 셀렉트 변경 시 실행되는 함수 - span에 조합된 텍스트 표시, value는 서브카테고리만
    function addCategoryFromSubCategory(subSelectElement) {
        const subValue = subSelectElement.value;
        const subText = subSelectElement.options[subSelectElement.selectedIndex].text;
        const container = document.querySelector(".specialization");

        if (!selectedMainCategory || !subValue) return;

        const combinedText = `${selectedMainCategory.text} > ${subText}`;

        // 중복 방지: 같은 서브카테고리 value가 있는지 체크
        if ([...container.querySelectorAll("span")].some(s => s.dataset.value === subValue)) return;

        const span = document.createElement("span");
        span.textContent = combinedText + ' ';
        span.dataset.value = subValue; // 숨겨진 input엔 subValue만 반영됨

        const removeBtn = document.createElement("button");
        removeBtn.type = "button";
        removeBtn.textContent = "❌";
        removeBtn.addEventListener("click", () => {
            span.remove();
            updateCategoryListHiddenInput();
        });

        span.appendChild(removeBtn);
        container.insertBefore(span, container.querySelector(".clear_fields"));
        updateCategoryListHiddenInput();
        subSelectElement.selectedIndex = 0;
    }

    // 첫 번째 select가 변경될 때 - 선택값 저장 + 서브카테고리 AJAX 로드
    document.getElementById("category_name").addEventListener("change", function () {
        selectedMainCategory = {
            value: this.value,
            text: this.options[this.selectedIndex].text
        };

        const selectedValue = this.value;

        fetch(`/getSubCategories?categoryId=${selectedValue}`)
            .then(res => res.text())
            .then(html => {
                document.getElementById("subCategory").innerHTML = "<option value=''>선택하세요</option>" + html;
            })
            .catch(err => console.error("서브카테고리 로딩 실패", err));
    });

    // 두 번째 select가 변경될 때 span 태그 추가
    document.getElementById("subCategory").addEventListener("change", function () {
        addCategoryFromSubCategory(this);
    });

    // clear_fields 버튼 클릭 시 전체 span 제거 및 hidden input 초기화
    document.querySelector(".clear_fields").addEventListener("click", function () {
        const spans = this.parentElement.querySelectorAll("span");
        spans.forEach(span => span.remove());
        updateCategoryListHiddenInput();
    });

    function initFileSection() {
        const tbody = document.querySelector(".file-upload-section tbody");

        const addPortfolioListBtn = document.getElementById("add-portfolio-info");
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

                    label.setAttribute("for", `portfolio-id-select-${rowIndex}`);
                    select.setAttribute("id", `portfolio-id-select-${rowIndex}`);
                    select.setAttribute("name", `portfolio-id-select-${rowIndex}`);
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

        addPortfolioListBtn?.addEventListener("click", () => {
            const tr = document.createElement("tr");
            tr.classList.add("project-id-section");
            // #portfolioIdList에 정의된 option 목록 가져오기
            const optionList = document.getElementById("portfolioIdList");
            console.log(optionList);
            const options = optionList ? optionList.innerHTML : "";
            tr.innerHTML = `
            <td><label>포트폴리오</label></td>
            <td>
                <select class="portfolio-id-select" name="project-id-select" style="width: 100%; height: 40px;">
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
      document.addEventListener('DOMContentLoaded', function () {
          const educationBtn = document.querySelector('.add-education-btn');
          const careerBtn = document.querySelector('.add-career-btn');

          educationBtn.addEventListener('click', function () {
              const container = document.getElementById('education-container');
              const newEducation = document.createElement('div');
              newEducation.classList.add('education-box');
              newEducation.innerHTML = `
              <div class="education-row">
                  <select name="academicType">
                      <option>학교구분</option>
                      <option value="고등학교">고등학교</option>
                      <option value="대학교(2, 3년)">대학교(2, 3년)</option>
                      <option value="대학교(4년)">대학교(4년)</option>
                      <option value="대학원">대학원</option>
                      <option value="대입자격검정고시">대입자격검정고시</option>
                  </select>
                  <input type="text" name="academicName" placeholder="학교명">
                  <input type="text" name="entranceDate" placeholder="입학년도">
                  <input type="text" name="graduateDate" placeholder="졸업년도">
              </div>
              
              <div class="education-row">
                  <select><option>대학교(2,3년제)</option></select>
                  <input type="text" name="academicName" placeholder="학교명">
                  <input type="text" name="entranceDate" placeholder="입학년도">
                  <input type="text" name="graduateDate" placeholder="졸업년도">
                  <input type="text" name="academicMajor" placeholder="전공명">
              </div>
          `;
              container.appendChild(newEducation);
          });

          careerBtn.addEventListener('click', function () {
              const container = document.getElementById('career-container');
              const newCareer = document.createElement('div');
              newCareer.classList.add('career-box');
              newCareer.innerHTML = `
              <div class="career-row">
                  <input type="text" name="companyName" placeholder="회사명" value="" >
                  <input type="text" name="departmentName" placeholder="부서명" value="" >
                  <input type="month" name="joinDate" placeholder="입사일" dat value="" >
                  <input type="month" name="resignDate" placeholder="퇴사년도" value="" >
                  <input type="text" name="jobDescription placeholder="담당직무"" value="" >
                  <input type="number" name="jobDescription placeholder="연봉" value="" > <span>만 원</span> 
              </div>
              <span>담당업무</span><textarea placeholder="담당하신 업무와 성과에 대해 간단하게 적어주세요."></textarea>
          `;
              container.appendChild(newCareer);
          });
      });
    document.addEventListener('DOMContentLoaded', function () {
        // 학력 추가
        document.getElementById('add-education-btn').addEventListener('click', function () {
            const original = document.querySelector('#education-container .row');
            if (!original) return;
            const clone = original.cloneNode(true);
            clone.querySelectorAll('input').forEach(input => input.value = '');
            document.getElementById('education-container').appendChild(clone);
        });

        // 경력 추가
        document.getElementById('add-career-btn').addEventListener('click', function () {
            const original = document.querySelector('#career-container .career-box');
            if (!original) return;
            const clone = original.cloneNode(true);
            clone.querySelectorAll('input').forEach(input => input.value = '');
            document.getElementById('career-container').appendChild(clone);
        });

        // 삭제 처리 (공통)
        document.addEventListener('click', function (event) {
            if (event.target.closest('.buttonDeleteField')) {
                const box = event.target.closest('.row') || event.target.closest('.career-box');
                const container = box?.parentElement;
                if (container && container.children.length > 1) {
                    box.remove();
                } else {
                    return;
                }
            }
        });
    });
    document.getElementById('education_addbutton').addEventListener('click', function () {
        // 복제할 div 찾기 (첫 번째 container)
        const original = document.querySelector('.row');
        if (!original) return;

        // 복제
        const clone = original.cloneNode(true); // true = 하위 요소까지 복제

        // education_containers에 추가
        const container = document.getElementById('education_containers');
        container.appendChild(clone);
      });

      document.addEventListener('click', function (event) {
      // 삭제 버튼이 클릭되었는지 확인
      if (event.target.closest('.buttonDeleteField')) {
        // 클릭된 버튼 기준으로 가장 가까운 .row 부모를 찾음
        const row = event.target.closest('.row');
        if (row) {
          row.remove(); // .row 및 그 하위 요소 삭제
        }
      }
    });
    document.addEventListener('DOMContentLoaded', function () {
        const targetInput = document.getElementById('Univeducation_Schl_Type_Code_c1103');
        const educationContainers = document.getElementById('education_containers');

        // 감시할 MutationObserver 설정 (value 변경을 감지하기 위함)
        const observer = new MutationObserver(() => {
            handleeducationTypeChange();
        });

        observer.observe(targetInput, {
            attributes: true,
            attributeFilter: ['value']
        });

        function handleeducationTypeChange() {
            const value = parseInt(targetInput.value, 10);

            let cloneSource;
            if (value <= 0) {
                cloneSource = document.getElementById('high');
            } else {
                cloneSource = document.getElementById('Univeducation');
            }

            if (cloneSource && educationContainers) {
                const clone = cloneSource.cloneNode(true);
                // ID 충돌 방지를 위해 고유 ID 제거
                clone.removeAttribute('id');

                // 필요시 name/id 등 내부 요소들의 속성 정리
                educationContainers.appendChild(clone);
            }
        }

        // input의 value 변경을 트리거하는 수동 이벤트 감지 (선택 UI 사용 시 필요)
        targetInput.addEventListener('change', handleeducationTypeChange);
    });
};