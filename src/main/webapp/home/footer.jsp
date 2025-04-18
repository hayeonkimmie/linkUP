<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>

  <link rel="stylesheet" href="${contextPath}/css/footer.css">
</head>
<body>
<footer class="footer">
  <div class="footer-top">
    <div class="footer-contact">
      <h4>고객 문의</h4>
      <p class="phone"><span>☎</span> 02 - 1234 - 1234</p>
      <p class="hours">09:00 - 18:00 주말·공휴일 제외</p>
      <p class="email">YUYU@linkup.com</p>
    </div>
    <div class="footer-links">
      <div>
        <h5></h5>
        <ul>
          <li><a href="#"></a></li>
        </ul>
      </div>
      <div>
        <h5></h5>
        <ul>
          <li><a href="#"></a></li>
          <li><a href="#"></a></li>
          <li><a href="#"></a></li>
        </ul>
      </div>
      <div>
        <h5></h5>
        <ul>
          <li><a href="#"></a></li>
          <li><a href="#"></a></li>
        </ul>
      </div>
    </div>
  </div>

  <div class="footer-bottom">
    <p>
      (주) 링크업 | 대표이사 : 홍길동 | 서울특별시 금천구 가산디지털1로 70 9층<br>
      사업자등록번호 : 123-45-12345 | 통신판매업신고 2025-서울금천-0000 |
      직업정보제공사업등록번호 제1234-1235121-12313 고객센터 : 1234-1234
    </p>
    <p class="copyright">2025 LINKUP.Corp</p>
  </div>
</footer>

</body>
</html>