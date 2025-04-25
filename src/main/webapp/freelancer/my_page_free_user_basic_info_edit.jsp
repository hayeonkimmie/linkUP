<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="${contextPath}/css/freelancer/freelancer_my_page.css"/>
    <link rel="stylesheet" href="${contextPath}/css/freelancer/freelancer_my_page_info_edit.css"/>
    <script src="${contextPath}/js/freelancer_my_page_info_edit_basic.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css" />
</head>
<body>
<div class="header">
    <!-- 헤더 인클루드 영역 -->
    <jsp:include page="../home/header.jsp" />
</div>
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
            <form action="${contextPath}/my-page/edit-info?type=basic" method="post" enctype="multipart/form-data">
                <div class="img names">
                    <div class="form-row profile-upload">
                        <div class="profile-box ">
                            <label for="profileImg" class="upload-placeholder">
                                <img id="preview" class="preview-img" src="/image?filename=${freelancer.profileImg}" alt="프로필 사진"/>
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
                            <input type="text" value="${freelancer.freelancerId}" disabled/>
                        </div>
                    </div>
                </div>

                <div class="form-row">
                    <input type="email" id="email" name="email" placeholder="이메일" value="${freelancer.email}" required class="required" disabled/>
                </div>

                <div class="form-row">
                    <input type="text" name="phoneNum" id="phone" placeholder="휴대폰 번호" value="${freelancer.phoneNum}" required class="required" maxlength="13"/>
                </div>

                <div class="form-row">
                    <input type="text" name="address" placeholder="주소" value="${freelancer.address}" required class="required"/>
                </div>

                <h3>비밀번호</h3>
                <div class="form-row">
                    <input type="password" placeholder="현재 비밀번호" id="currPassword" value=""/>
                </div>
                <div class="form-row">
                    <input type="password" name="newPassword" placeholder="새 비밀번호" value="" id="newPassword" oninput="checkPasswordMatch()" disabled/>
                    <input type="password" placeholder="새 비밀번호 확인" value="" id="confirmNewPassword" oninput="checkPasswordMatch()" disabled/>
                </div>
                <div class="form-row" id="warning" style="display: none; font-size:small; font-weight: bolder; color:red;">비밀번호가 일치하지 않습니다.</div>
                <h3>계좌번호</h3>
                <div class="form-row" id="CheckBank">
                    <label for="bank">은행</label>
                    <input type="text" name="bank" id="bank" value="${freelancer.bank}" required disabled/>
                    <input type="text" placeholder="현재 계좌번호" name="accountNum" id="accountNum" value="${freelancer.accountNum}" disabled required/>
                </div>
                <div id="errorMsg" style="color: red; margin-bottom: 10px;"></div>
                <div class="form-row center">
                    <button type="submit" class="submit-btn" id="submitBtn">저장</button>
                </div>
            </form>
        </section>
    </main>
</div>
</body>
</html>
