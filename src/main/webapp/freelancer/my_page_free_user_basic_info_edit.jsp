<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer/freelancer_my_page_info_edit.css'/>">
    <script src="${contextPath}/js/freelancer_my_page_info_edit.js"></script>

</head>

<body>
<div class="header">
    <!-- 헤더 인클루드 영역 -->
    <jsp:include page="/common/header.jsp"/>
</div>
<div class="container">
    <!-- 사이드바 -->
    <jsp:include page="/freelancer/sidebar.jsp"/>
    <!-- 메인 콘텐츠 -->
    <main class="content">
        <section class="section">
            <div class="content-header">
                <div class="content-header-text">
                    <h3>기본 정보 설정</h3>
                </div>
            </div>
            <form action="${contextPath}/edit-info?type=basic" method="post" enctype="multipart/form-data">
                <div class="img names">
                    <div class="form-row profile-upload">
                        <div class="profile-box">
                            <label for="profileImg" class="upload-placeholder">
                                <img id="preview" class="preview-img"
                                     src="/image?filename=${freelancer.profileImg}" />
                            </label>
                            <input type="file" name="profileImg" id="profileImg" accept="image/*"
                                   style="display:none" value="${freelancer.profileImg}" onchange="readURL(this)">
                        </div>
                    </div>
                    <div class="form-row profile-info">
                        <div>
                            <span>이름</span>
                            <input type="text" placeholder="이름" value="${freelancer.name}" name="name" class="required" required/>
                        </div>
                        <div>
                            <span>닉네임</span>
                            <input type="text" placeholder="닉네임" value="${freelancer.nickname}" name="nickname" class="required" required/>
                        </div>
                        <div>
                            <span>아이디</span>
                            <input type="text" value="${freelancer.freelancerId}" readonly/>
                        </div>
                    </div>
                </div>

                <div class="form-row">
                    <input type="email" name="email" placeholder="이메일" value="${freelancer.email}" required class="required"/>
                </div>

                <div class="form-row">
                    <input type="text" name="phoneNum" id="phone" placeholder="휴대폰 번호" value="${freelancer.phoneNum}" required class="required" maxlength="13"/>
                </div>

                <div class="form-row">
                    <input type="text" name="address" placeholder="주소" value="${freelancer.address}" required class="required"/>
                </div>

                <h3>비밀번호</h3>
                <div class="form-row">
                    <input type="password" placeholder="현재 비밀번호" id="currId" value="${freelancer.password}" disabled/>
                </div>
                <div class="form-row">
                    <input type="password" name="newPassword" placeholder="새 비밀번호" value="" name="newPassword" id="newPassword" oninput="checkPasswordMatch()"/>
                    <input type="password" placeholder="새 비밀번호 확인" value="" id="newPasswordCheck" oninput="checkPasswordMatch()"/>
                    <br/>
                    <div id="worning" style="display: none; font-size:small; font-weight: bolder; color:red;">비밀번호가 일치하지 않습니다.</div>
                </div>

                <h3>계좌번호</h3>
                <div class="form-row" id="CheckBank">
                    <label for="bank">은행</label>
                    <input type="text" name="bank" id="bank" value="${freelancer.bank}" required/>
                    <input type="text" placeholder="현재 계좌번호" name="accountNum" id="accountNum" value="${freelancer.accountNum}"  required/>
                </div>
                <div class="form-row center">
                    <button type="submit" class="submit-btn">저장</button>
                </div>
            </form>
        </section>
    </main>
</div>
<div id="pwCheckContainer" style="display: none; margin-top: 10px;">
    <input type="password" id="pwInput" placeholder="비밀번호 입력">
    <button onclick="verifyPassword()">확인</button>
    <span id="pwError" style="color: red; display: none;">비밀번호가 일치하지 않습니다.</span>
</div>
<script>

    function readURL(input) {
        if(input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('preview').src = e.target.result;
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
<%--<script>
    function checkPasswordMatch() {
        const pw = document.querySelector("#newPassword").value;
        const pwCheck = document.querySelector("#newPasswordCheck").value;
        const warning = document.querySelector("#worning");

        if (pw !== pwCheck) {
            warning.removeAttribute("style"); // display: none; 제거
        } else {
            warning.setAttribute("style", "display: none;"); // display: none; 추가
        }
    }
    document.querySelectorAll("#bank, #accountNum").forEach(el => {
        el.addEventListener("click", () => {
            document.querySelector("#pwCheckContainer").style.display = "block";
            document.querySelector("#pwError").style.display = "none";
            document.querySelector("#pwInput").value = "";
            document.querySelector("#pwInput").focus();
        });
    });

    // 비밀번호 확인 함수
    function verifyPassword() {
        const inputPw = document.querySelector("#pwInput").value;
        const correctPw =document.querySelector("#currId").value;

        if (inputPw === correctPw) {
            document.querySelector("#pwCheckContainer").style.display = "none";
            // 인증 성공 후 원하는 기능 실행
            console.log("비밀번호 인증 성공");
        } else {
            document.querySelector("#pwError").style.display = "inline";
        }
    }
</script>--%>
</body>
</html>
