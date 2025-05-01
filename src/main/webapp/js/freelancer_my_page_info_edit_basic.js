document.addEventListener('DOMContentLoaded', function () {
    const previewImg = document.getElementById('preview');
    const profileInput = document.getElementById('profileImg');
    const phoneInput = document.getElementById('phone');
    const nicknameInput = document.getElementById('nickname');
    const nicknameCheckResult = document.getElementById("nicknameCheckResult");
    const currPasswordInput = document.getElementById('currPassword');
    const passwordCheckResult = document.getElementById("passwordCheckResult");
    const newPasswordInput = document.getElementById('newPassword');
    const confirmNewPasswordInput = document.getElementById('confirmNewPassword');
    const passwordMatchResult = document.getElementById("passwordMatchResult");
    const bankInput = document.getElementById('bank');
    const maskedAccountInput = document.getElementById("accountNumberDisplay");
    const realAccountInput = document.getElementById("accountNumberReal");
    const emailInput = document.getElementById('email');
    const submitBtn = document.getElementById('submitBtn');

    // 수정 여부 플래그
    let isNicknameEdited = false;
    let isPasswordEdited = false;
    let isNewPasswordEdited = false;
    let isEmailEdited = false;
    let isPhoneEdited = false;

    // 유효성 플래그
    let isNicknameValid = false;
    let isPasswordValid = false;
    let isNewPasswordMatch = false;
    let isEmailValid = false;
    let isPhoneValid = false;

    // submit 버튼 활성화 검사
    function updateSubmitButtonState() {
        let canSubmit = true;

        if (isNicknameEdited && !isNicknameValid) canSubmit = false;
        if (isPasswordEdited && !isPasswordValid) canSubmit = false;
        if (isNewPasswordEdited && !isNewPasswordMatch) canSubmit = false;
        if (isEmailEdited && !isEmailValid) canSubmit = false;
        if (isPhoneEdited && !isPhoneValid) canSubmit = false;

        submitBtn.disabled = !canSubmit;
        submitBtn.style.opacity = canSubmit ? "1" : "0.5";
        submitBtn.style.cursor = canSubmit ? "pointer" : "not-allowed";
    }

    // 프로필 이미지 미리보기
    function readURL(input) {
        if (!input || !input.files || !input.files[0]) return;
        const reader = new FileReader();
        reader.onload = (e) => {
            if (previewImg) previewImg.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    }

    // 닉네임 중복 확인
    function checkNickname() {
        const nickname = nicknameInput.value.trim();

        if (nickname === "") {
            nicknameCheckResult.textContent = "닉네임을 입력해주세요";
            nicknameCheckResult.style.color = "red";
            isNicknameValid = false;
            updateSubmitButtonState();
            return; // 더 이상 진행하지 않음
        }

        fetch(contextPath + "/check-nickname", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: "nickname=" + encodeURIComponent(nickname)
        })
            .then(res => res.text())
            .then(data => {
                isNicknameValid = (data === "true");
                nicknameCheckResult.textContent = isNicknameValid ? "사용 가능한 닉네임입니다." : "이미 사용 중인 닉네임입니다.";
                nicknameCheckResult.style.color = isNicknameValid ? "green" : "red";
                updateSubmitButtonState();
            });
    }

    // 현재 비밀번호 확인
    function checkPassword() {
        const password = currPasswordInput.value.trim();
        fetch(contextPath + "/check-password", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: "password=" + encodeURIComponent(password)
        })
            .then(res => res.text())
            .then(data => {
                isPasswordValid = (data === "true");
                passwordCheckResult.textContent = isPasswordValid ? "비밀번호가 일치합니다." : "비밀번호가 틀립니다.";
                passwordCheckResult.style.color = isPasswordValid ? "green" : "red";

                [
                    newPasswordInput, confirmNewPasswordInput,
                    bankInput, maskedAccountInput, realAccountInput,
                    phoneInput, emailInput
                ].forEach(input => {
                    if (input) {
                        input.readOnly = !isPasswordValid;
                    }
                });

                updateSubmitButtonState();
            });
    }

    // 새 비밀번호 일치 확인
    function checkPasswordMatch() {
        const pw1 = newPasswordInput.value.trim();
        const pw2 = confirmNewPasswordInput.value.trim();

        if (pw1 && pw2) {
            isNewPasswordMatch = (pw1 === pw2);
            passwordMatchResult.textContent = isNewPasswordMatch ? "비밀번호가 일치합니다." : "비밀번호가 일치하지 않습니다.";
            passwordMatchResult.style.color = isNewPasswordMatch ? "green" : "red";
        } else {
            isNewPasswordMatch = false;
            passwordMatchResult.textContent = '';
        }

        updateSubmitButtonState();
    }

    // 이메일 유효성 검사
    function checkEmail() {
        const email = emailInput.value.trim();
        isEmailValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
        updateSubmitButtonState();
    }

    // 전화번호 자동 포맷 + 유효성 검사
    function formatPhoneNumber(value) {
        const digits = value.replace(/\D/g, '');
        if (digits.length < 4) return digits;
        if (digits.length < 8) return `${digits.slice(0, 3)}-${digits.slice(3)}`;
        return `${digits.slice(0, 3)}-${digits.slice(3, 7)}-${digits.slice(7, 11)}`;
    }

    function checkPhone() {
        const phone = phoneInput.value.replace(/\D/g, '');
        isPhoneValid = phone.length >= 10 && phone.length <= 11;
        updateSubmitButtonState();
    }

    function handleAccountMasking(e) {
        const input = e.target;
        const raw = input.value.replace(/\D/g, "");
        if (!realAccountInput) return;
        realAccountInput.value = raw;

        if (input.readOnly) {
            let masked = raw;
            if (raw.length > 3 && raw.length <= 7) {
                masked = raw.slice(0, 3) + "-" + "*".repeat(raw.length - 3);
            } else if (raw.length > 7 && raw.length <= 13) {
                masked = raw.slice(0, 3) + "-" + "*".repeat(4) + "-" + raw.slice(7);
            }
            input.value = masked;
        } else {
            input.value = raw;
        }
    }

    function initEventListeners() {
        profileInput?.addEventListener('change', () => readURL(profileInput));

        nicknameInput?.addEventListener('input', function () {
            isNicknameEdited = true;
            checkNickname();
        });

        currPasswordInput?.addEventListener('input', function () {
            isPasswordEdited = true;
            checkPassword();
        });

        newPasswordInput?.addEventListener('input', function () {
            isNewPasswordEdited = true;
            checkPasswordMatch();
        });

        confirmNewPasswordInput?.addEventListener('input', function () {
            isNewPasswordEdited = true;
            checkPasswordMatch();
        });

        emailInput?.addEventListener('input', function () {
            isEmailEdited = true;
            checkEmail();
        });

        phoneInput?.addEventListener('input', function (e) {
            isPhoneEdited = true;
            e.target.value = formatPhoneNumber(e.target.value);
            checkPhone();
        });

        maskedAccountInput?.addEventListener("input", handleAccountMasking);
    }

    initEventListeners();

    // 초기에 readonly 걸어주기
    if (phoneInput) phoneInput.readOnly = true;
    if (emailInput) emailInput.readOnly = true;
    if (bankInput) bankInput.readOnly = true;
    if (maskedAccountInput) maskedAccountInput.readOnly = true;

    // submit 버튼은 초기 활성화
    submitBtn.disabled = false;
    submitBtn.style.opacity = "1";
    submitBtn.style.cursor = "pointer";

    // 초기 계좌번호 마스킹
    if (maskedAccountInput && realAccountInput && maskedAccountInput.readOnly) {
        const raw = realAccountInput.value || "";
        let masked = raw;
        if (raw.length > 3 && raw.length <= 7) {
            masked = raw.slice(0, 3) + "-" + "*".repeat(raw.length - 3);
        } else if (raw.length > 7 && raw.length <= 13) {
            masked = raw.slice(0, 3) + "-" + "*".repeat(4) + "-" + raw.slice(7);
        }
        maskedAccountInput.value = masked;
    }
});
