<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  aside {
    width: 260px;
    background-color: white;
    border-right: 1px solid #e0e0e0;
    height: 100vh;
    padding: 20px;
    font-family: Arial, sans-serif;
  }

  .sidebar-title {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 30px;
    display: flex;
    align-items: center;
  }

  .sidebar-title::before {
    content: "📁";
    margin-right: 8px;
  }

  .menu-section {
    margin-bottom: 20px;
  }

  .menu-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    cursor: pointer;
    font-size: 14px;
    font-weight: bold;
    padding: 8px 0;
  }

  .toggle-icon {
    font-size: 16px;
    padding: 0 8px;
    color: #666;
  }

  .submenu {
    display: flex; /* ✅ 기본값: 펼쳐진 상태 */
    flex-direction: column;
    margin-left: 12px;
    margin-top: 6px;
  }

  .submenu a {
    text-decoration: none;
    color: #333;
    font-size: 14px;
    padding: 6px 10px;
    border-radius: 6px;
    display: block;
    margin: 2px 0;
  }

  .submenu a:hover {
    background-color: #f0f0f0;
  }

  .submenu a.active {
    background-color: #e6edff;
    color: #1a3cff;
  }
</style>

<aside>
  <div class="sidebar-title">프로젝트 관리</div>

  <!-- 사용자 관리 -->
  <div class="menu-section">
    <div class="menu-header" onclick="toggleMenu('userMenu')">
      사용자 관리
      <span class="toggle-icon" id="icon-userMenu">▲</span>
    </div>
    <div class="submenu" id="userMenu">
      <a href="<c:url value='/admin/users'/>">전체 사용자 조회</a>
    <%--      <a href="user_info.jsp">전체 사용자 조회</a>--%>
    </div>
  </div>

  <!-- 프로젝트 관리 -->
  <div class="menu-section">
    <div class="menu-header" onclick="toggleMenu('projectMenu')">
      프로젝트 관리
      <span class="toggle-icon" id="icon-projectMenu">▲</span>
    </div>
    <div class="submenu" id="projectMenu">
      <a href="project_list.jsp">프로젝트 조회</a>
      <a href="settlement.jsp">정산 관리</a>
    </div>
  </div>

  <!-- Q&A 관리 -->
  <div class="menu-section">
    <div class="menu-header" onclick="toggleMenu('qaMenu')">
      Q&A 관리
      <span class="toggle-icon" id="icon-qaMenu">▲</span>
    </div>
    <div class="submenu" id="qaMenu">
      <a href="qna_manage.jsp">전체 문의 내역</a>
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
</script>
