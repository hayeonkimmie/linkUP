<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>지원자 관리</title>
  <link rel="stylesheet" href="${contextPath}/css/style.css" />
  <link rel="stylesheet" href="${contextPath}/css/style.css" />
  <style>
    /* 그대로 복붙해도 되지만, 가독성을 위해 style은 필요 시 따로 분리 권장 */
    /* ... 기존 CSS는 그대로 유지 ... */
  </style>
</head>
<body>

<div class="layout">

  <!--  공통 사이드바 include -->
  <jsp:include page="../common/sidebar.jsp" />

  <!-- 메인 콘텐츠 -->
  <main class="main">

    <!-- 프로젝트 정보 카드 -->
    <div class="project-card">
      <h2>${project.title}
        <span class="status-badge">${project.status}</span>
      </h2>
      <p>등록일: ${project.createdAt}</p>
      <p>${project.description}</p>
      <p>
        <strong>예산:</strong> ${project.budget} |
        <strong>기간:</strong> ${project.duration} |
        <strong>지원자:</strong> ${project.applicantCount}명 |
        <strong>마감일:</strong> ${project.deadline}
      </p>
      <div class="tags">
        <c:forEach var="tag" items="${project.tags}">
          <span class="tag">${tag}</span>
        </c:forEach>
      </div>
    </div>

    <!-- 지원자 목록 테이블 -->
    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>지원자</th>
            <th>직무</th>
            <th>경력</th>
            <th>평점</th>
            <th>상태</th>
            <th>액션</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="applicant" items="${applicants}">
            <tr>
              <td><strong>${applicant.name}</strong><br>${applicant.position}</td>
              <td>${applicant.career}</td>
              <td>⭐ ${applicant.rating}</td>
              <td>
                <span class="status status-${applicant.status}">
                  ${applicant.status}
                </span>
              </td>
              <td>
                <button class="action-btn">메시지</button>
                <button class="action-btn">수락</button>
                <button class="action-btn">보류</button>
                <button class="action-btn">거절</button>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

  </main>
</div>

</body>
</html>
