<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 25. 4. 16.
  Time: 오전 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Link Up</title>

    <link rel="stylesheet" href="../css/main.css">
    <link href="https://fonts.googleapis.com/css2?family=SUIT&display=swap" rel="stylesheet">
</head>
<header>
    <div>
        <img src="../img/링크업 로고.png" alt="Link Up 로고" style="height: 40px;" />
    </div>
    <div class="auth-buttons">
        <a href="login.jsp" class="login">로그인</a>
        <a href="createAcc.jsp" class="signup">회원가입</a>
    </div>
</header>

</br>
<div class="main-title">사람과 사람을 연결해주는 사이트 <strong>LINK UP!</strong></div>
</br>
<div class="search-box">
    <div class="search-box">
        <select id="search-type" class="search-type">
            <option value="company">회사명</option>
            <option value="project">구인</option>
            <option value="expert">구직</option>
        </select>
        <input class="search-input" type="text" id="search-keyword" placeholder="검색어를 입력하세요" />
        <button class="search-button" onclick="handleSearch()">검색</button>
    </div>
</div>
</br>
</br>
</br>
<div class="categories">
    <div class="category-item">
        <a href="catalog.html">
            <img src="../img/웹제작.png" alt="웹제작" />
            <div class="category-label">웹제작</div>
        </a>
    </div>
    <div class="category-item">
        <img src="../img/유지보수.png" alt="유지보수" />
        <div class="category-label">유지보수</div>
    </div>
    <div class="category-item">
        <img src="c:\Users\KOSTA\Downloads\design-tools_11933683.png" alt="프로그램" />
        <div class="category-label">프로그램</div>
    </div>
    <div class="category-item">
        <img src="c:\Users\KOSTA\Downloads\design-tools_11933683.png" alt="모바일" />
        <div class="category-label">모바일</div>
    </div>
    <div class="category-item">
        <img src="c:\Users\KOSTA\Downloads\design-tools_11933683.png" alt="Ai" />
        <div class="category-label">Ai</div>
    </div>
    <div class="category-item">
        <img src="c:\Users\KOSTA\Downloads\design-tools_11933683.png" alt="데이터" />
        <div class="category-label">데이터</div>
    </div>
    <div class="category-item">
        <img src="c:\Users\KOSTA\Downloads\design-tools_11933683.png" alt="트렌드" />
        <div class="category-label">트렌드</div>
    </div>
    <div class="category-item">
        <img src="c:\Users\KOSTA\Downloads\design-tools_11933683.png" alt="직무직군" />
        <div class="category-label">직무직군</div>
    </div>
</div>
</br>
</br>
</br>
</br>
</br>
</br>
<div class="section">
    </br>
    </br>
    <div class="section-row">
        <div class="section-title">
            <a href="catalog2.html">
                <div class="title-text">IT - FrontEnd</div>
                <button class="arrow-button">→</button>
            </a>
        </div>
        <div class="cards">
            <div class="card">
                <div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div>
            </div>
            <div class="card">
                <div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div>
            </div>
            <div class="card">
                <div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div>
            </div>
        </div>
    </div>
    <div class="section-row">
        <div class="section-title">
            <div class="title-text">IT - BackEnd</div>
            <button class="arrow-button">→</button>
        </div>
        <div class="cards">
            <div class="card">
                <div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div>
            </div>
            <div class="card">
                <div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div>
            </div>
            <div class="card">
                <div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div>
            </div>
        </div>
    </div>
    <div class="section-row">
        <div class="section-title">
            <div class="title-text">IT - App</div>
            <button class="arrow-button">→</button>
        </div>
        <div class="cards">
            <div class="card">
                <div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div>
            </div>
            <div class="card">
                <div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div>
            </div>
            <div class="card">
                <div class="card-text">입고 알기쉬는 그래피티 디자인 제작해드립니다.<br><br>★ 4.3 (154)<br>33,000원<br>starbbung</div>
            </div>
        </div>
    </div>
</div>
</br>
</br>
</br>
</body>

</html>