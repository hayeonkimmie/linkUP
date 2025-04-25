document.addEventListener('DOMContentLoaded', function () {
    const previewImg = document.getElementById('preview');
    const profileInput = document.getElementById('profileImg');
    const emailInput = document.getElementById('email');
    const phoneInput = document.getElementById('phone');
    const currPasswordInput = document.getElementById('currPassword');
    const newPasswordInput = document.getElementById('newPassword');
    const confirmNewPasswordInput = document.getElementById('confirmNewPassword');
    const bankInput = document.getElementById('bank');
    const accountNumInput = document.getElementById('accountNum');
    const warning = document.getElementById('warning');
    const errorMsg = document.getElementById('errorMsg');
    const submitBtn = document.getElementById('submitBtn');

    let originalBank = '';
    let originalAccountNum = '';
    let hasInteractedWithPassword = false;

    // 프로필 이미지 미리보기
    function readURL(input) {
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = (e) => {
                previewImg.src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        }
    }

    // 전화번호 하이픈 자동 포맷팅
    function formatPhoneNumber(value) {
        const digits = value.replace(/[^0-9]/g, '');
        if (digits.length < 4) return digits;
        if (digits.length < 8) return `${digits.slice(0, 3)}-${digits.slice(3)}`;
        return `${digits.slice(0, 3)}-${digits.slice(3, 7)}-${digits.slice(7, 11)}`;
    }

    // 현재 비밀번호가 올바른지
    function isValidCurrentPassword() {
        if (!hasInteractedWithPassword) return false;
        return currPasswordInput.value === `${freelancer.password}`;
    }

    // 새 비밀번호와 확인값이 일치하는지
    function isNewPasswordConfirmed() {
        return newPasswordInput.value === confirmNewPasswordInput.value;
    }

    //  새 비밀번호 일치 여부 경고 표시
    function updatePasswordWarning() {
        warning.style.display = isNewPasswordConfirmed() ? 'none' : 'block';
    }

    // 민감 필드 disabled 설정
    function setSensitiveFieldsDisabled(disabled) {
        newPasswordInput.disabled = disabled;
        confirmNewPasswordInput.disabled = disabled;
        bankInput.disabled = disabled;
        accountNumInput.disabled = disabled;
        emailInput.disabled = disabled;
    }

    // 전체 유효성 검사
    function validateForm() {
        const allRequiredFilled = Array.from(document.querySelectorAll('.required')).every(input => input.value.trim() !== '');
        const passwordValid = isValidCurrentPassword();

        if (!passwordValid) {
            setSensitiveFieldsDisabled(true);
            errorMsg.textContent = hasInteractedWithPassword ? '비밀번호를 정확히 입력해주세요.' : '';
            submitBtn.disabled = true;
            return;
        }

        setSensitiveFieldsDisabled(false);
        errorMsg.textContent = '';

        const passwordConfirmed = !newPasswordInput.value || isNewPasswordConfirmed();
        const accountValid = !isAccountModified() || passwordValid;

        submitBtn.disabled = !(allRequiredFilled && passwordConfirmed && accountValid);
    }

    // 계좌 변경 여부 확인
    function isAccountModified() {
        return bankInput.value !== originalBank || accountNumInput.value !== originalAccountNum;
    }

    // 이벤트 바인딩
    function initEventListeners() {
        profileInput.addEventListener('change', () => readURL(profileInput));

        phoneInput.addEventListener('input', (e) => {
            e.target.value = formatPhoneNumber(e.target.value);
        });

        currPasswordInput.addEventListener('focus', () => {
            hasInteractedWithPassword = true;
        });

        currPasswordInput.addEventListener('input', () => {
            hasInteractedWithPassword = true;
            updatePasswordWarning();
            validateForm();
        });

        [newPasswordInput, confirmNewPasswordInput, bankInput, accountNumInput].forEach(input => {
            input.addEventListener('input', () => {
                updatePasswordWarning();
                validateForm();
            });
        });

        document.querySelectorAll('.required').forEach(input => {
            input.addEventListener('input', validateForm);
        });
    }

    // 초기 설정
    window.onload = () => {
        originalBank = bankInput.value;
        originalAccountNum = accountNumInput.value;
        setSensitiveFieldsDisabled(true); // 최초에 모두 비활성화
        initEventListeners();
        validateForm();
    };
});