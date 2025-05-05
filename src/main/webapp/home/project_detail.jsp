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
<div class="header">
    <div id="header-placeholder"></div>
</div>

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
                            <img src="${contextPath}/img/${project.profileImg != null ? project.profileImg : 'default_logo.png'}"
                                 alt="회사 로고" class="logo-img"/>
                            <c:if test="${sessionScope.role eq 'jobseeker'}">
                                <button class="apply-button" onclick="openModal()">지원하기</button>
                            </c:if>
                        </div>
                        <%--<button>
                            <c:choose>
                                <c:when test="${isLiked}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16" onclick="location.href='${contextPath}/JJimProject?projectId=${projectId}&action=cancel'"/>
                                        <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314"/>
                                    </svg>
                                </c:when>
                                <c:otherwise>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" onclick="location.href='${contextPath}/JJimProject?projectId=${projectId}&action=like'"/>
                                        <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15" />
                                    </svg>
                                </c:otherwise>
                            </c:choose>
                        </button>--%>
                        <c:choose>
                            <c:when test="${isLiked}">
                                <a href="${contextPath}/JJimProject?projectId=${projectId}&action=cancel">
                                    <i class="bi bi-heart-fill text-danger" style="color: red;"></i>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${contextPath}/JJimProject?projectId=${projectId}&action=like">
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
                        <div class="meta-item">
                            <div class="meta-label">프로젝트 기간</div>
                            <div class="meta-value">${project.duration}일</div>
                        </div>
                        <div class="meta-item">
                            <div class="meta-label">마감일</div>
                            <div class="meta-value"><fmt:formatDate value="${project.deadlineDate}"
                                                                    pattern="yyyy-MM-dd"/></div>
                        </div>
                        <div class="meta-item full">
                            <div class="meta-label">담당자 연락처</div>
                            <div class="meta-value contact">${project.mphone}</div>
                        </div>
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
