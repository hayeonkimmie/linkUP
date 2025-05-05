<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css"/>
    <link rel="stylesheet" href="${contextPath}/css/freelancer/freelancer_my_page.css"/>
    <link rel="stylesheet" href="${contextPath}/css/freelancer/freelancer_my_page_info_edit.css"/>
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
        <div class="content-header">
            <div class="content-header-text">
                <h3>기본 정보 설정</h3>
            </div>
        </div>
        <%--<script>console.log(`${freelancer.password}`)</script>--%>
        <section class="section">
            <form action="${contextPath}/my-page/edit-basic-info" method="post" enctype="multipart/form-data">
                <div>
                    <div class="img names">
                        <div class="form-row profile-upload">
                            <div class="profile-box ">
                                <label for="profileImg" class="upload-placeholder">
                                    <img id="preview" class="preview-img"
                                            <c:choose>
                                                <c:when test="${freelancer.profileImg} ne null"> src="../img/${freelancer.profileImg}"</c:when>
                                                <c:otherwise> src= "../img/basic_profile_img2.png"</c:otherwise>
                                            </c:choose> alt="프로필 사진"/>
                                </label>
                                <input type="file" name="profileImg" id="profileImg" accept="image/*"
                                       style="display:none" value="${freelancer.profileImg}" onchange="readURL(this)">
                            </div>
                        </div>
                        <div class="form-row profile-info">
                            <div>
                                <label>이름<span class="star">*</span></label>
                                <input type="text" placeholder="이름" value="${freelancer.name}" name="name" oninput="this.value = this.value.replace(/\s+/g, '')" class="required" maxlength="10"
                                       required/>
                            </div>
                            <div>
                                <label>닉네임<span class="star">*</span></label>
                                <input type="text" placeholder="닉네임" value="${freelancer.nickname}" id="nickname" maxlength="30"
                                       name="nickname" class="required" required/>
                                <%--<button type="button" id="checkNicknameBtn">중복 체크</button>--%>
                                <span id="nicknameCheckResult"></span>
                            </div>
                            <div>
                                <label>아이디</label>
                                <input type="text" value="${freelancer.freelancerId}" disabled/>
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <label>이메일<span class="star">*</span></label>
                        <input type="email" id="email" name="email" placeholder="이메일" value="${freelancer.email}" required
                               class="required" readonly/>
                        <label>휴대폰 번호<span class="star">*</span></label>
                        <input type="text" name="phoneNum" id="phone" placeholder="휴대폰 번호" value="${freelancer.phoneNum}"
                               required class="required" maxlength="13" readonly/>
                    </div>
                    <div class="form-row">
                        <input type="text" id="address" name="address" placeholder="주소" onclick="execDaumPostcode()"
                               required value="${freelancer.address}" class="required"/>
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
                        <label for="accountNumberDisplay">계좌번호</label>
                        <input type="text" id="accountNumberDisplay" placeholder="계좌번호 입력" maxlength="20"
                               value="${freelancer.accountNum}" readonly required/>
                        <input type="hidden" id="accountNumberReal" name="accountNum" value="${freelancer.accountNum}"/>
                    </div>
                    <div id="errorMsg" style="color: red; margin-bottom: 10px;"></div>
                </div>
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
</body>
</html>
