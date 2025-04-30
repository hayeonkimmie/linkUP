<%--
  Author: 이원영
  Date: 25. 4. 14.
  Time: 오후 2:10
  Description: 로그인 이후 보이는 사이드 메뉴
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  aside {
    width: 280px;
    min-width: 280px;
    flex-shrink: 0;
    background-color: white;
    border-right: 1px solid #e0e0e0;
    height: 100vh;
    padding: 20px;
    font-family: Arial, sans-serif;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .sidebar-title {
    text-align: center;
    margin-bottom: 30px;
  }

  #sidebarIcon {
    font-size: 42px;
    display: block;
    margin-bottom: 8px;
  }

  #sidebarTitleText {
    font-size: 20px;
    font-weight: bold;
    color: #333;
  }

  .menu-section {
    width: 100%;
    margin-bottom: 28px;
  }

  .menu-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    cursor: pointer;
    font-size: 15px;
    font-weight: bold;
    padding: 12px 8px;
    color: #111;
  }

  .toggle-icon {
    font-size: 16px;
    padding-right: 8px;
    color: #888;
  }

  .submenu {
    display: flex;
    flex-direction: column;
    padding-left: 12px;
    margin-top: 6px;
  }

  .submenu a {
    text-decoration: none;
    color: #333;
    font-size: 16px;
    padding: 10px 14px;
    border-radius: 8px;
    margin: 6px 0;
    display: flex;
    align-items: center;
    transition: background-color 0.2s ease;
  }

  .submenu a:hover {
    background-color: #f3f6ff;
  }

  .submenu a.active {
    background-color: #e6edff;
    color: #1a3cff;
    font-weight: bold;
  }
</style>

<aside>
  <!-- ✅ 타이틀 및 아이콘 -->
  <div class="sidebar-title" >
    <span id="sidebarIcon" class="sidebar-icon">📊</span>
    <span id="sidebarTitleText">대시 보드</span>
  </div>
  <!-- 사용자 관리 -->
  <div class="menu-section">
    <div class="menu-header" onclick="toggleMenu('userMenu')">
      사용자 관리
      <span class="toggle-icon" id="icon-userMenu">▲</span>
    </div>
    <div class="submenu" id="userMenu">
      <a href="<c:url value='/admin/users'/>" onclick="setSidebar('사용자 관리', '👥')">전체 사용자 조회</a>
    </div>
  </div>

  <!-- 프로젝트 관리 -->
  <div class="menu-section">
    <div class="menu-header" onclick="toggleMenu('projectMenu')">
      프로젝트 관리
      <span class="toggle-icon" id="icon-projectMenu">▲</span>
    </div>
    <div class="submenu" id="projectMenu">
      <a href="<c:url value='/admin/project'/>" onclick="setSidebar('프로젝트 관리', '🛠️')">프로젝트 조회</a>
    </div>
  </div>

  <div class="menu-section">
    <div class="menu-header" onclick="toggleMenu('settlementMenu')">
      정산 관리
      <span class="toggle-icon" id="icon-settlementMenu">▲</span>
    </div>
    <div class="submenu" id="settlementMenu">
      <a href="<c:url value='/admin/settlement?type=list'/>"
         onclick="setSidebar('정산 목록', '📄')" id="settleListLink">정산 대상</a>
      <a href="<c:url value='/admin/settlement-history'/>"
         onclick="setSidebar('정산 내역', '💰')" id="makeSettlementLink">정산 내역</a>

    </div>
  </div>



  <!-- Q&A 관리 -->
  <div class="menu-section">
    <div class="menu-header" onclick="toggleMenu('qaMenu')">
      Q&A 관리
      <span class="toggle-icon" id="icon-qaMenu">▲</span>
    </div>
    <div class="submenu" id="qaMenu">
      <a href="<c:url value='/admin/qna'/>" onclick="setSidebar('Q&A 관리', '❓')" id="qnaListLink">문의 내역</a>
    </div>
  </div>
</aside>

<script>
  function toggleMenu(id) {
    const submenu = document.getElementById(id);
    const icon = document.getElementById('icon-' + id);
    const isOpen = submenu.style.display === 'flex';
    submenu.style.display = isOpen ? 'none' : 'flex';
    icon.textContent = isOpen ? '▼' : '▲';
  }

  function setSidebar(title, icon) {
    const titleEl = document.getElementById('sidebarTitleText');
    const iconEl = document.getElementById('sidebarIcon');

    if (titleEl && iconEl) {
      titleEl.textContent = title;
      iconEl.textContent = icon;
    }

    // 저장
    localStorage.setItem("sidebarTitle", title);
    localStorage.setItem("sidebarIcon", icon);
  }
</script>
