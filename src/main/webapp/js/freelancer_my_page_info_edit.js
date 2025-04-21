window.onload = function () {
    // 폼 유효성 검사를 통해 저장 버튼 상태 변경
    const submitBtn = document.querySelector(".submit-btn");
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
    // 필드 변화 감지
    form.querySelectorAll("input[required], select[required]").forEach(el => {
        el.addEventListener("input", validateForm);
        el.addEventListener("change", validateForm);
    });
    document.getElementsByClassName("clear_field").addEventListener("click", function () {
        const tags = document.querySelectorAll(".skill-tags span");
        tags.forEach(tag => tag.remove());
    });

    // 특정 select 요소에서 선택된 값을 기반으로 전문 분야(span)를 추가하는 함수
    function addSpecialization(selectElement) {
        const value = selectElement.value; // 선택된 값 가져오기
        const container = document.querySelector(".Specialization"); // 전문 분야가 들어갈 컨테이너(span들이 있는 div 등) 선택

        // 값이 존재하고, 이미 추가된 span 중 동일한 값이 없을 경우에만 추가
        if (value && ![...container.querySelectorAll("span")].some(s => s.textContent === value)) {
            const span = document.createElement("span"); // 새로운 span 요소 생성
            span.textContent = value; // span 안에 텍스트로 선택된 값 삽입
            container.insertBefore(span, container.querySelector(".clear_fields"));
            // "clear_fields" 버튼 앞에 새로운 span을 삽입
        }
    }
    // 첫 번째 select 요소가 변경될 때 addSpecialization 호출
        document.getElementById("specialization1").addEventListener("change", function () {
            addSpecialization(this); // this는 현재 이벤트가 발생한 select 요소
        });

    // 두 번째 select 요소가 변경될 때 addSpecialization 호출
        document.getElementById("specialization2").addEventListener("change", function () {
            addSpecialization(this);
        });

    // clear_fields 버튼을 클릭하면 추가된 모든 span을 제거
        document.querySelector(".clear_fields").addEventListener("click", function () {
            const spans = this.parentElement.querySelectorAll("span"); // 현재 버튼의 부모 요소 내 모든 span 찾기
            spans.forEach(span => span.remove()); // 각 span 요소 삭제
        });

  /*  document.addEventListener('DOMContentLoaded', function () {
        const educationBtn = document.querySelector('.add-education-btn');
        const careerBtn = document.querySelector('.add-career-btn');

        educationBtn.addEventListener('click', function () {
            const container = document.getElementById('education-container');
            const newEducation = document.createElement('div');
            newEducation.classList.add('education-box');
            newEducation.innerHTML = `
            <div class="education-row">
                <select><option>학교구분</option></select>
                <input type="text" placeholder="학교명">
                <input type="text" placeholder="입학년도">
                <input type="text" placeholder="졸업년도">
            </div>
            <div class="education-row">
                <select><option>대학교(2,3년제)</option></select>
                <input type="text" placeholder="학교명">
                <input type="text" placeholder="입학년도">
                <input type="text" placeholder="졸업년도">
                <input type="text" placeholder="전공명">
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
                <input type="text" placeholder="회사명">
                <input type="text" placeholder="부서명">
                <input type="text" placeholder="입사년도">
                <input type="text" placeholder="퇴사년도">
            </div>
            <div class="career-row">
                <select><option>직급/직책</option></select>
                <input type="text" placeholder="담당직무">
                <input type="text" placeholder="인원">
            </div>
            <textarea placeholder="담당업무 내용"></textarea>
        `;
            container.appendChild(newCareer);
        });
    });*/
    /*document.addEventListener('DOMContentLoaded', function () {
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
                    alert("최소 하나는 남겨야 합니다.");
                }
            }
        });
    });*/
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