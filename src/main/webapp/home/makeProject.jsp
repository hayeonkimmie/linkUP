<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>구인 등록</title>
  <link rel="stylesheet" href="../css/home/headerLoginSt.css" />
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${contextPath}/css/home/makeProject.css">
  <script>
    document.addEventListener('DOMContentLoaded', function () {
      const workTypeButtons = document.querySelectorAll('[data-work-type]');
      const workTimeToggle = document.getElementById('work-time-toggle');
      const modal = document.getElementById('position-modal');
      const modalClose = document.getElementById('modal-close');
      const addPositionBtn = document.getElementById('add-position');
      const positionContainer = document.getElementById('position-container');
      const submitButton = document.getElementById('submit-button');
      const jobForm = document.getElementById('job-form');
      let skipValidationOnce = false;

      // 산업 데이터 (공통)
      const industryData = {
        "웹 제작": ["홈페이지 신규 제작", "쇼핑몰 신규 제작", "랜딩페이지"],
        "웹 유지보수": ["홈페이지 수정·유지보수", "쇼핑몰 수정·유지보수", "퍼블리싱", "검색최적화·SEO", "애널리틱스"],
        "프로그램": ["프로그램 스토어", "수익 자동화", "업무 자동화", "크롤링·스크래핑", "일반 프로그램", "프로그램 수정·유지보수", "서버·클라우드", "엑셀·스프레드시트", "봇·챗봇"],
        "모바일": ["앱", "앱 패키징", "앱 수정·유지보수"],
        "AI": ["AI·GPT 서비스 개발", "AI·GPT 챗봇", "AI·GPT 자동화 프로그램", "프롬프트 엔지니어링", "머신러닝·딥러닝", "컴퓨터 비전·자연어 처리"],
        "데이터": ["데이터 구매·구축", "데이터 라벨링", "데이터 전처리·분석·시각화", "데이터베이스"],
        "트렌드": ["게임∙AR∙VR", "메타버스", "블록체인·NFT"],
        "직무직군": ["UI·UX 기획", "프론트엔드", "백엔드", "풀스택", "데브옵스·인프라", "데이터·ML·DL"]
      };

      // 근무 방식 선택
      workTypeButtons.forEach(btn => {
        btn.addEventListener('click', () => {
          workTypeButtons.forEach(b => b.classList.remove('btn-selected'));
          btn.classList.add('btn-selected');
          const type = btn.dataset.workType;
          workTimeToggle.style.display = (type === 'onsite' || type === 'hybrid') ? 'block' : 'none';
        });
      });

      // 산업 메인 셀렉트 (기본 정보 영역)
      const primarySelect = document.getElementById('industry-primary');
      const secondarySelect = document.getElementById('industry-secondary');

      primarySelect.innerHTML = '<option value="">산업 분야</option>';
      Object.keys(industryData).forEach(primary => {
        const option = document.createElement('option');
        option.value = primary;
        option.textContent = primary;
        primarySelect.appendChild(option);
      });

      primarySelect.addEventListener('change', function () {
        const selected = this.value;
        secondarySelect.innerHTML = '<option value="">세부 분야</option>';
        if (industryData[selected]) {
          industryData[selected].forEach(sub => {
            const option = document.createElement('option');
            option.value = sub;
            option.textContent = sub;
            secondarySelect.appendChild(option);
          });
        }
      });

      // ✅ 포지션 산업 셀렉트 초기화 함수
      function initializePositionIndustrySelect(block) {
        const primary = block.querySelector('.position-industry-primary');
        const secondary = block.querySelector('.position-industry-secondary');

        if (!primary || !secondary) return;

        primary.innerHTML = '<option value="">산업 분야</option>';
        Object.keys(industryData).forEach(key => {
          const option = document.createElement('option');
          option.value = key;
          option.textContent = key;
          primary.appendChild(option);
        });

        primary.addEventListener('change', function () {
          const selected = this.value;
          secondary.innerHTML = '<option value="">세부 분야</option>';
          if (industryData[selected]) {
            industryData[selected].forEach(sub => {
              const option = document.createElement('option');
              option.value = sub;
              option.textContent = sub;
              secondary.appendChild(option);
            });
          }
        });
      }

      // ✅ 초기 포지션 셀렉트도 설정
      const firstPosition = document.querySelector('.position-block');
      initializePositionIndustrySelect(firstPosition);

      // 포지션 추가
      addPositionBtn.addEventListener('click', () => {
        const positionTemplate = document.querySelector('.position-block');
        const clone = positionTemplate.cloneNode(true);
        clone.querySelectorAll('input').forEach(input => {
          input.value = '';
          input.removeAttribute('required');
        });
        clone.style.display = 'block';

        const removeBtn = clone.querySelector('.remove-position');
        if (removeBtn) {
          removeBtn.classList.remove('hidden');
        }

        positionContainer.appendChild(clone);
        initializePositionIndustrySelect(clone); // ⭐ 새 포지션에도 셀렉트 초기화
      });

      // 포지션 삭제
      document.addEventListener('click', function (e) {
        if (e.target.classList.contains('remove-position')) {
          const positions = document.querySelectorAll('.position-block');
          if (positions.length > 1) {
            skipValidationOnce = true;
            e.target.closest('.position-block').remove();
          } else {
            modal.style.display = 'block';
          }
        }
      });

      // 모달 닫기
      modalClose.addEventListener('click', () => {
        modal.style.display = 'none';
      });

      window.addEventListener('click', (event) => {
        if (event.target === modal) {
          modal.style.display = 'none';
        }
      });

      // 유효성 검사
      jobForm?.addEventListener('input', () => {
        if (skipValidationOnce) {
          skipValidationOnce = false;
          return;
        }

        const requiredFields = jobForm.querySelectorAll('[required]');
        let allFilled = true;
        requiredFields.forEach(field => {
          if (!field.value.trim()) {
            allFilled = false;
          }
        });
        submitButton.disabled = !allFilled;
      });

      // 제출 버튼 클릭 시 검사 후 전송
      submitButton?.addEventListener('click', function (e) {
        e.preventDefault();

        const requiredFields = jobForm.querySelectorAll('[required]');
        let allFilled = true;

        requiredFields.forEach(field => {
          if (!field.value.trim()) {
            allFilled = false;
            field.classList.add('border-red-500');
          } else {
            field.classList.remove('border-red-500');
          }
        });

        if (!allFilled) {
          alert('필수 입력 항목을 모두 작성해주세요.');
        } else {
          jobForm.submit();
        }
      });
    });


  </script>
</head>
<body>
<div id="header-login-placeholder"></div>
<div class="bg-gray-50 p-6">

  <br>
  <br>
  <div class="max-w-4xl mx-auto bg-white p-6 rounded shadow">
    <h1 class="text-2xl font-bold mb-4">구인 등록</h1>
    <form id="job-form" class="space-y-6">
      <!-- 기본 정보 -->
      <section>
        <label class="block font-medium">공고 제목 <span class="text-red-500">*</span>
          <input type="text" class="mt-1 w-full border p-2 rounded" required>
        </label>
        <label class="block font-medium mt-4">프로젝트명 <span class="text-red-500">*</span>
          <input type="text" class="mt-1 w-full border p-2 rounded" required>
        </label>
        <label class="block font-medium mt-4">모집 분야 <span class="text-red-500">*</span>
          <input type="text" class="mt-1 w-full border p-2 rounded" required>
        </label>

        <div class="mt-4">
          <span class="font-medium">근무 방식</span> <span class="text-red-500">*</span>
          <div class="flex space-x-2 mt-2">
            <button type="button" data-work-type="remote" class="px-4 py-2 border rounded">원격</button>
            <button type="button" data-work-type="onsite" class="px-4 py-2 border rounded">상주</button>
            <button type="button" data-work-type="hybrid" class="px-4 py-2 border rounded">혼합</button>
          </div>
          <div id="work-time-toggle" class="mt-2 hidden">
            <label class="block mt-2 font-medium">근무 시간
              <input type="text" class="mt-1 w-full border p-2 rounded" placeholder="00:00 - 00:00">
            </label>
          </div>
        </div>

        <label class="block font-medium mt-4">근무 기간 <span class="text-red-500">*</span>
          <input type="text" class="mt-1 w-full border p-2 rounded">
        </label>
        <label class="block font-medium mt-4">근무 지역 <span class="text-red-500">*</span>
          <input type="text" class="mt-1 w-full border p-2 rounded">
        </label>
        <label class="block font-medium mt-4">프로젝트 비용 <span class="text-red-500">*</span>
          <input type="text" class="mt-1 w-full border p-2 rounded" required>
        </label>

        <div class="mt-4">
          <span class="font-medium">산업</span>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mt-2">
            <div>
              <label class="block font-medium mb-1">산업 분야</label>
              <select id="industry-primary" class="w-full border p-2 rounded">
                <option value="">산업 분야</option>
              </select>
            </div>
            <div>
              <label class="block font-medium mb-1">세부 분야</label>
              <select id="industry-secondary" class="w-full border p-2 rounded">
                <option value="">세부 분야</option>
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
          <div class="position-block border p-4 rounded relative">
            <button type="button" class="remove-position absolute top-2 right-2 text-red-500 hidden">삭제</button>
            <label class="block mt-2 font-medium">직군</label>
            <div class="mt-4">
              <label class="block font-medium mb-1">산업 분야</label>
              <select class="position-industry-primary w-full border p-2 rounded">
                <option value="">산업 분야</option>
              </select>
            </div>
            <div class="mt-4">
              <label class="block font-medium mb-1">세부 분야</label>
              <select class="position-industry-secondary w-full border p-2 rounded">
                <option value="">세부 분야</option>
              </select>
            </div>
            <label class="block mt-2 font-medium">레벨</label>
            <select class="mt-1 w-full border p-2 rounded">
              <option value="">선택해주세요</option>
              <option value="junior">초급</option>
              <option value="mid">중급</option>
              <option value="senior">고급</option>
            </select>
            <label class="block mt-2 font-medium">구인 인원 <span class="text-red-500">*</span></label>
            <input type="number" class="mt-1 w-full border p-2 rounded" required>
            <label class="block mt-2 font-medium">급여 / 페이</label>
            <input type="text" class="mt-1 w-full border p-2 rounded">
            <label class="block mt-2 font-medium">담당업무</label>
            <input type="text" class="mt-1 w-full border p-2 rounded">
          </div>
        </div>
        <button type="button" id="add-position" class="mt-2 px-4 py-2 border rounded text-blue-500">+ 포지션 추가</button>
      </section>

      <!-- 모달 -->
      <div id="position-modal" class="modal">
        <div class="modal-content">
          <p class="mb-4">최소 하나의 포지션 정보는 남겨두어야 합니다.</p>
          <button id="modal-close" class="bg-blue-500 text-white px-4 py-2 rounded">닫기</button>
        </div>
      </div>

      <!-- 기술 스택 -->
      <section>
        <h2 class="text-lg font-semibold">필요 기술 스택</h2>
        <label class="block font-medium mt-2">원하는 기술
          <input type="text" class="mt-1 w-full border p-2 rounded">
        </label>
        <label class="block font-medium mt-2">우대 기술
          <input type="text" class="mt-1 w-full border p-2 rounded">
        </label>
      </section>

      <!-- 업무 내용 -->
      <section>
        <h2 class="text-lg font-semibold">업무 내용</h2>
        <label class="block font-medium mt-2">프로젝트 개요
          <input type="text" class="mt-1 w-full border p-2 rounded textin">
        </label>
        <label class="block font-medium mt-2">상세 업무 내용
          <input type="text" class="mt-1 w-full border p-2 rounded textin">
        </label>
      </section>

      <!-- 지원자격 -->
      <section>
        <h2 class="text-lg font-semibold">지원자격</h2>
        <label class="block font-medium mt-2">필수 경험
          <input type="text" class="mt-1 w-full border p-2 rounded ">
        </label>
        <label class="block font-medium mt-2">우대 사항
          <input type="text" class="mt-1 w-full border p-2 rounded">
        </label>
        <label class="block font-medium mt-2">모집 마감 기한 <span class="text-red-500">*</span>
          <input type="date" class="mt-1 w-full border p-2 rounded" required>
        </label>
      </section>

      <!-- 연락처 -->
      <section>
        <h2 class="text-lg font-semibold">연락처 설정</h2>
        <label class="block font-medium mt-2">담당자 이메일 <span class="text-red-500">*</span>
          <input type="email" class="mt-1 w-full border p-2 rounded" required>
        </label>
        <label class="block font-medium mt-2">담당자 전화번호 <span class="text-red-500">*</span>
          <input type="tel" class="mt-1 w-full border p-2 rounded" required>
        </label>
      </section>

      <button id="submit-button" type="submit" class="w-full bg-blue-500 text-white py-3 rounded disabled:opacity-50">프로젝트 등록하기</button>
    </form>
  </div>
</div>
<script src="../js/headerLogin.js"></script>
</body>
</html>
