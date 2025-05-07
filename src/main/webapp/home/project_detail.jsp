<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>프로젝트 상세</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/css/home/project_detail.css"/>
    <link rel="stylesheet" href="${contextPath}/css/common/headerSt.css"/>
</head>
<body>
<div id="header-placeholder"></div>

<div class="background">
    <div class="job-detail-wrapper">
        <main class="main-content full-width">
            <div class="job-header-background">
                <div class="job-header-box">
                    <div class="header-content-container aligned-box">
                        <!-- 좌측 텍스트 블럭 -->
                        <div class="header-text-content">
                            <p class="company-name">${project.clientId}</p>
                            <p class="job-title">${project.projectName}</p>
                            <div class="subtitle-row">
                                <div class="subtitle">${project.advertisementTitle}</div>
                            </div>
                            <p class="subtitle-description">
                                <c:out value="${project.projectDescription}"/>
                            </p>
                        </div>

                        <!-- 우측 로고 및 버튼 -->
                        <div class="header-logo-box vertical-center">
                            <%--<img src="${contextPath}/img/${project.profileImg != null ? project.profileImg : 'default_logo.png'}"
                                 alt="회사 로고" class="logo-img"/>--%>
                            <img src="${contextPath}/img/${project.thumbnail}"
                                 alt="회사 로고" class="logo-img"/>
                            <c:if test="${sessionScope.role eq 'jobseeker'}">
                                <button class="apply-button" onclick="openModal()">지원하기</button>
                            </c:if>
                        </div>
                        <c:choose>
                            <c:when test="${isLiked}">
                                <a href="${contextPath}/JJimProject?projectId=${projectId}&action=cancel" style="
                                   font-size: 32px; text-decoration: none;">
                                    <i class="bi bi-heart-fill text-danger" style="color: red;"></i>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${contextPath}/JJimProject?projectId=${projectId}&action=like" style="
                                   font-size: 32px; text-decoration: none;">
                                    <i class="bi bi-heart text-danger" style="color: red;"></i>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <hr class="divider"/>
                    <div class="meta-info">
                        <%--                        <div class="meta-item">--%>
                        <%--                            <div class="meta-label">정산일</div>--%>
                        <%--                            <div class="meta-value"><fmt:formatDate value="${project.settleDay}" pattern="yyyy-MM-dd"/></div>--%>
                        <%--                        </div>--%>
<%--                        <div class="meta-item">--%>
<%--                            <div class="meta-label">프로젝트 기간</div>--%>
<%--                            <div class="meta-value">${project.duration}일</div>--%>
<%--                        </div>--%>
                        <div class="meta-item">
                            <div class="meta-label">예상 작업 기간</div>
                            <div class="meta-value">
                                <fmt:formatDate value="${project.startDate}" pattern="yyyy-MM-dd"/>
                                &nbsp;~&nbsp;
                                <fmt:formatDate value="${project.endDate}" pattern="yyyy-MM-dd"/>
                            </div>

                        </div>
                        <div class="meta-item">
                            <div class="meta-label">구인 마감일</div>
                            <div class="meta-value"><fmt:formatDate value="${project.deadlineDate}"
                                                                    pattern="yyyy-MM-dd"/></div>
                        </div>
<%--                        <div class="meta-item full">--%>
<%--                            <div class="meta-label">담당자 연락처</div>--%>
<%--                            <div class="meta-value contact">${project.mphone}</div>--%>
<%--                        </div>--%>
                        <div class="meta-item full">
                            <div class="meta-label">담당자</div>
                            <div class="meta-value contact">${project.manager}</div>
                        </div>
                        <div class="meta-item full">
                            <div class="meta-label">이메일</div>
                            <div class="meta-value contact">${project.email}</div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="job-detatil-container">
                <section class="job-section">
                    <h3>업무 내용</h3>
                    <p><strong>&lt;상세 설명&gt;</strong></p>
                    <pre>${project.jobDetails}</pre>
                </section>

                <section class="job-section">
                    <h3>근무 조건</h3>
                    <ul>
                        <li>근무 형태: ${project.workingMethod}</li>
                        <li>근무 환경: ${project.workingEnvironment}</li>
                        <li>근무 시간: ${project.workingHours}</li>
                    </ul>
                </section>

                <section class="job-section">
                    <h3>자격 요건</h3>
                    <p>${project.qualification}</p>

                    <h3>우대 사항</h3>
                    <p>${project.preferentialConditions}</p>
                </section>
                <section class="job-section">
                    <h3>모집 인원</h3>
                    <c:forEach var="pl" items="${payLevelList}">
                        <p>
                            <c:out value="${pl.pay.work}"/>
                            <c:out value="${pl.pay.people}"/>명,
                            <c:out value="${pl.level.level}"/>
                        </p>
                    </c:forEach>
                </section>

                <section>
                    <h3>우대 사항</h3>
                    <p>${project.preferentialConditions}</p>
                </section>
            </div>
        </main>

    </div>
</div>


<!-- ✅ 지원하기 모달 -->
<div id="applyModal" class="modal-overlay" style="display:none;">
    <div class="modal-content">
        <div class="modal-header">
            <h3>${project.projectName} 입사지원</h3>
            <span class="close-button" onclick="closeModal()">&times;</span>
        </div>

        <div class="modal-body">
            <div class="form-group">
                <label for="positionSelect">지원 포지션</label>
                <select id="positionSelect" class="form-control">
                    <option value="">포지션 선택</option>
                    <c:forEach var="pay" items="${projectPayList}">
                        <option value="${pay.categoryName}">${pay.categoryName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="modal-footer">
                <button class="btn cancel" onclick="closeModal()">취소</button>
                <button class="btn submit" onclick="submitApplication()">입사지원</button>
            </div>
        </div>
    </div>
</div>

<script>
    const contextPath = '${contextPath}';
</script>
<script src="${contextPath}/js/header.js"></script>
<script>
    function openModal() {
        document.getElementById("applyModal").style.display = "flex";
    }

    function closeModal() {
        document.getElementById("applyModal").style.display = "none";
    }

    function submitApplication() {
        const position = document.getElementById("positionSelect").value;
        const projectId = '${project.projectId}';
        const userId = '${userid}';

        console.log("지원 포지션:", position);
        console.log("프로젝트 ID:", projectId);
        console.log("사용자 ID:", userId);

        if (!position) {
            alert("지원 포지션을 선택해 주세요.");
            return;
        }

        const requestData = {
            projectId: projectId,
            userId: userId,
            position: position
        };

        fetch(`${contextPath}/applyProject`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("서버 오류");
                }
                return response.json(); // 응답이 JSON이 아니면 생략 가능
            })
            .then(result => {
                // ✅ 여기에서 페이지 이동 처리
                window.location.href = `${contextPath}/my-page/apply-proj-list`;
            })
            .catch(error => {
                console.error("지원 실패", error);
                alert("입사지원 중 오류가 발생했습니다.");
            });
    }

</script>

<script>
    function openApplyModal() {
        document.getElementById("applyModal").style.display = "block";
    }

    function closeApplyModal() {
        document.getElementById("applyModal").style.display = "none";
    }
</script>

</body>
</html>
