<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>마이페이지</title>
<%--    <link rel="stylesheet" href="${contextPath}/css/client/style.css" />--%>
    <link rel="stylesheet" href="${contextPath}/css/common/headerLoginSt.css"/>
    <link rel="stylesheet" href="${contextPath}/css/client/clientProfile.css" />
    <link rel="stylesheet" href="${contextPath}/css/client/sideBar.css" />

</head>

<body>
<!-- 공통 헤더 include -->
<div id="header-placeholder"></div>


<div class="layout">
    <!-- 공통 사이드바 include -->
    <jsp:include page="../common/sidebar.jsp" />

    <!-- 메인 컨텐츠 -->
    <main class="main">
<%--        서블릿에서 messag & error받아오기 --%>
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

        <%--    프로필 수정(메인컨텐츠) 전송 할 수 있는 기능 POST --%>
<%--    사용자가 form 제출하면 해당 주소로 POST요청--%>
        <form action="${contextPath}/clientProfile" method="post">
            <h2 class="section-title">프로필 설정</h2>

            <h3>기본 정보</h3>
            <div class="form-grid">
                <div class="form-group">
                    <%--        아이디는 읽기만 가능 (수정 불가로 readonly)--%>
                    <label>아이디</label>
                    <input type="text" name="userId" value="${profile.info.userId}" readonly
                           style="background-color: #f5f5f5; color: #999; border: 1px solid #ccc;" />
                </div>
                <div class="form-group">
                    <label>이름</label>
                    <input type="text" name="name" value="${profile.info.name}" />
                </div>
                <div class="form-group">
                    <label>인사담당자 이메일</label>
                    <input type="email" name="email" value="${profile.info.email}" />
                </div>
                <div class="form-group">
                    <label>담당자 휴대폰 번호</label>
                    <input type="text" name="mobile" value="${profile.info.phoneNumber}" />
                </div>
            </div>

            <h3>회사/기관 정보</h3>
            <div class="form-grid">
                <div class="form-group">
                    <label>회사/기관명</label>
                    <input type="text" name="companyName" value="${profile.detail.name}" />
                </div>
                <div class="form-group">
                    <label>회사/기관 홈페이지</label>
                    <input type="url" name="companyWebsite" value="${profile.detail.companyWebsiteUrl}" />
                </div>
                <div class="form-group">
                    <label>설립일자</label>
                    <input type="text" name="establishAt" value="${profile.detail.registrationDate}" />
                </div>
                <div class="form-group">
                    <label>사업자등록번호</label>
                    <input type="text" name="bizNum" value="${profile.detail.companyRegNo}" />
                </div>
                <div class="form-group">
                    <label>대표번호</label>
                    <input type="text" name="mainPhone" value="${profile.detail.companyPhoneNumber}" />
                </div>
                <div class="form-group">
                    <label>팩스 번호</label>
                    <input type="text" name="fax" value="${profile.detail.companyFaxNum}" />
                </div>

                <div class="form-group" style="grid-column: 1 / -1">
                    <label>회사 소개</label>
                    <textarea name="intro">${profile.detail.companyDescription}</textarea>
                </div>
            </div>

            <h3>비밀번호 관리</h3>
            <div class="form-grid">
                <div class="form-group" style="grid-column: 1 / -1;">
                    <label>현재 비밀번호</label>
                    <input type="password" name="currentPw" />
                </div>
                <div class="form-group">
                    <label>새 비밀번호</label>
                    <input type="password" name="newPw" />
                </div>
                <div class="form-group">
                    <label>새 비밀번호 확인</label>
                    <input type="password" name="newPwConfirm" />
                </div>
            </div>

            <!-- 비밀번호 변경 버튼 (제출 시 action=changePw로 전달됨) -->
            <!-- 비밀번호 변경 & 저장 버튼 나란히 -->
            <div class="button-group-wrapper">
                <button class="btn-primary" type="submit" name="action" value="changePw">비밀번호 변경</button>
                <button class="button-save" type="submit" name="action" value="saveInfo">저장</button>
            </div>
        </form>
    </main>
</div>

<script>
    const contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${contextPath}/js/catalog.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/headerLogin.js"></script>


</body>
</html>