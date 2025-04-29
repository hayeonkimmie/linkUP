<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="${contextPath}/css/freelancer/freelancer_my_page.css"/>
    <link rel="stylesheet" href="${contextPath}/css/freelancer/freelancer_my_page_info_edit.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css"/>
</head>
<body>
<div id="header-placeholder"></div>
<div class="container">
    <!-- 사이드바 -->
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <!-- 메인 콘텐츠 -->
    <main class="content">
        <c:if test="${not empty message}">
            <script>
                alert("${message}");
            </script>
        </c:if>

        <c:if test="${not empty error}">
            <script>
                alert("${error}");
            </script>
        </c:if>
        <script>console.log(`${freelancer.password}`)</script>
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>기본 정보 설정</h3>
                </div>
            </div>
            <form action="${contextPath}/my-page/edit-basic-info" method="post" enctype="multipart/form-data">
                <div class="img names">
                    <div class="form-row profile-upload">
                        <div class="profile-box ">
                            <label for="profileImg" class="upload-placeholder">
                                <img id="preview" class="preview-img"
                                <c:choose>
                                    <c:when test="${freelancer.profileImg} ne null"> src="../img/${freelancer.profileImg}"</c:when>
                                    <c:otherwise> src= "../img/basic_profile_img.png"</c:otherwise>
                                </c:choose> alt="프로필 사진"/>
                                <%--<img id="preview" class="preview-img" src="/image?filename=${freelancer.profileImg}"
                                     alt="프로필 사진"/>--%>
                            </label>
                            <input type="file" name="profileImg" id="profileImg" accept="image/*"
                                   style="display:none" value="${freelancer.profileImg}" onchange="readURL(this)">
                        </div>
                    </div>
                    <div class="form-row profile-info">
                        <div>
                            <span>이름</span>
                            <input type="text" placeholder="이름" value="${freelancer.name}" name="name" class="required"
                                   required/>
                        </div>
                        <div>
                            <span>닉네임</span>
                            <input type="text" placeholder="닉네임" value="${freelancer.nickname}" id="nickname"
                                   name="nickname" class="required" required/>
                            <%--<button type="button" id="checkNicknameBtn">중복 체크</button>--%>
                            <span id="nicknameCheckResult"></span>
                        </div>
                        <div>
                            <span>아이디</span>
                            <input type="text" value="${freelancer.freelancerId}" disabled/>
                        </div>
                    </div>
                </div>

                <div class="form-row">
                    <input type="email" id="email" name="email" placeholder="이메일" value="${freelancer.email}" required
                           class="required" readonly/>
                </div>

                <div class="form-row">
                    <input type="text" name="phoneNum" id="phone" placeholder="휴대폰 번호" value="${freelancer.phoneNum}"
                           required class="required" maxlength="13" readonly/>
                </div>

                <div class="form-row">
                    <input type="text" id="address" name="address" placeholder="주소" onclick="execDaumPostcode()"
                           required value="${freelancer.address}" readonly="readonly" class="required"/>

                </div>

                <h3>비밀번호</h3>
                <div class="form-row">
                    <input type="password" placeholder="현재 비밀번호" id="currPassword" value=""/>
                    <%--<button id="checkPasswordBtn" type="button">비밀번호 확인</button>--%>
                    <div id="passwordCheckResult" style="margin-top: 5px; font-size: 0.9em;"></div>

                </div>
                <div class="form-row">
                    <input type="password" name="newPassword" placeholder="새 비밀번호" value="" id="newPassword"
                            readonly/>
                    <input type="password" placeholder="새 비밀번호 확인" value="" id="confirmNewPassword"
                           readonly/>
                    <span id="passwordMatchResult"></span>
                </div>
<%--                <div class="form-row" id="warning"
                     style="display: none; font-size:small; font-weight: bolder; color:red;">비밀번호가 일치하지 않습니다.
                </div>--%>
                <h3>계좌번호</h3>
                <div class="form-row" id="CheckBank">
                    <label for="bank">은행</label>
                    <input type="text" name="bank" id="bank" value="${freelancer.bank}" required readonly/>
                    <input type="text" id="accountNumberDisplay" placeholder="계좌번호 입력" maxlength="20"
                           value="${freelancer.accountNum}" readonly required/>
                    <input type="hidden" id="accountNumberReal" name="accountNum" value="${freelancer.accountNum}"/>
                </div>
                <div id="errorMsg" style="color: red; margin-bottom: 10px;"></div>
                <div class="form-row center">
                    <button type="submit" class="submit-btn" id="submitBtn">저장</button>
                </div>
            </form>
        </section>
    </main>
</div>

<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                console.log(data);

                // 주소 변수
                let addr = '';

                // 도로명 주소 선택 시
                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                } else { // 지번 주소 선택 시
                    addr = data.jibunAddress;
                }

                // 선택된 주소만 #address 필드에 입력
                document.getElementById('address').value = addr;
            }
        }).open();
    }
</script>
<script src="${contextPath}/js/freelancer_my_page_info_edit_basic.js"></script>
<%--<script>
    document.addEventListener("DOMContentLoaded", function () {

        // 닉네임 중복 확인
        document.getElementById("checkNicknameBtn").addEventListener("click", function () {
            const nickname = document.getElementById("nickname").value;
            fetch(contextPath + "/check-nickname", {
                method: "POST",
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                body: "nickname=" + encodeURIComponent(nickname)
            })
            .then(res => res.text())
            .then(data => {
                console.log(data)
                document.getElementById("nicknameCheckResult").textContent =
                    data === "true" ? "사용 가능한 닉네임입니다." : "이미 사용 중인 닉네임입니다.";
            });
        });

        // 비밀번호 확인
        document.getElementById("checkPasswordBtn").addEventListener("click", function () {
            const password = document.getElementById("currPassword").value;
            fetch(contextPath + "/check-password", {
                method: "POST",
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                body: "password=" + encodeURIComponent(password)
            })
            .then(res => res.text())
            .then(data => {
                const isPasswordCorrect = data === "true";
                document.getElementById("passwordCheckResult").textContent =
                    isPasswordCorrect ? "비밀번호가 일치합니다." : "비밀번호가 틀립니다.";
                if (isPasswordCorrect) {
                    document.getElementById("newPassword").disabled = false;
                    document.getElementById("confirmNewPassword").disabled = false;
                    document.getElementById("bank").disabled = false;
                    document.getElementById("accountNumberDisplay").disabled = false;
                }
                /*else{
                    document.getElementById("newPassword").disabled = true;
                    document.getElementById("confirmNewPassword").disabled = true;
                    document.getElementById("bank").disabled = true;
                    document.getElementById("accountNumberDisplay").disabled = true;
                }*/
            });
        });

        document.getElementById("accountNumberDisplay").addEventListener("input", function (e) {
            const raw = e.target.value.replace(/[^0-9]/g, ""); // 숫자만 추출
            const realInput = document.getElementById("accountNumberReal");
            // 실제 서버 전송용 값 설정
            realInput.value = raw;
            // 마스킹 처리
            let masked = "";
            if (raw.length <= 3) {
                masked = raw;
            } else if (raw.length <= 7) {
                masked = raw.slice(0, 3) + "-" + "*".repeat(raw.length - 3);
            } else if (raw.length <= 13) {
                const visible = raw.slice(0, 3) + "-" + "*".repeat(4) + "-" + raw.slice(7);
                masked = visible;
            } else {
                masked = raw; // 너무 길면 그냥 출력
            }
            e.target.value = masked;
        });
    });
</script>--%>

<%--<script>--%>
<%--    document.querySelector(".settlement-save-btn").addEventListener("click", () => {--%>
<%--        const updates = [];--%>
<%--        document.querySelectorAll("tbody tr").forEach(row => {--%>
<%--            const projectId = row.querySelector("[name='projectId']")?.value;--%>
<%--            const status = row.querySelector(".settlement-status-select")?.value;--%>
<%--            if (projectId && status) {--%>
<%--                updates.push({ projectId, status });--%>
<%--            }--%>
<%--        });--%>
<%--        fetch("/linkup/admin/update-client-status", {--%>
<%--            method: "PUT",--%>
<%--            headers: {--%>
<%--                "Content-Type": "application/json"--%>
<%--            },--%>
<%--            body: JSON.stringify(updates)--%>
<%--        })--%>
<%--            .then(res => {--%>
<%--                if (res.ok) {--%>
<%--                    alert("결산 상태가 저장되었습니다.");--%>
<%--                    location.reload();--%>
<%--                } else {--%>
<%--                    alert("❌ 저장 실패");--%>
<%--                }--%>
<%--            })--%>
<%--            .catch(err => {--%>
<%--                console.error("❌ 오류 발생:", err);--%>
<%--            });--%>
<%--    });--%>
<%--</script>--%>

</body>
</html>
