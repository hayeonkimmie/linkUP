<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>프로젝트 정산</title>
    <link rel="stylesheet" href="../css/admin_header.css">
    <link rel="stylesheet" href="../css/settlement.css">
    <script>
        const defaultOpenMenuId = "projectMenu";
    </script>
    <script src="../js/include_common.js"></script>
</head>

<body>
    <div id="header-include"></div>

    <div class="layout-wrapper">
        <div id="menu-include"></div>

        <div>
          
        </div>

        <div class="content">
            
            <div class="card filter-box">
              <h1>이번달 프로젝트 정산</h1>
                <div class="filter-row">
                    <div class="filter-item">
                        <label>기준 날짜</label>
                        <input type="date" />
                    </div>
                    <div class="filter-item">
                        <label>프로젝트명</label>
                        <input type="text" placeholder="프로젝트명 검색" />
                    </div>
                    <div class="filter-item">
                        <label>정산 상태</label>
                        <select>
                            <option>전체</option>
                            <option>정산 완료</option>
                            <option>정산 대기</option>
                        </select>
                    </div>
                    <div class="filter-item">
                        <label>구인자 승인 상태</label>
                        <select>
                            <option>전체</option>
                            <option>승인</option>
                            <option>미승인</option>
                        </select>
                    </div>
                    <div class="filter-action">
                        <button>🔍 검색</button>
                    </div>
                </div>
            </div>
            <!-- <h3 class="section-title">정산할 프로젝트 목록</h3> -->
            <div class="card">
                <h1>정산할 프로젝트 목록</h1>
                <table class="settlement-table">
                    <thead>
                        <tr>
                            <th>프로젝트명</th>
                            <th>프로젝트 기간</th>
                            <th>담당자</th>
                            <th>담당자 연락처</th>
                            <th>지급 금액</th>
                            <th>수수료</th>
                            <th>결제 금액</th>
                            <th>인원</th>
                            <th>결산일</th>
                            <th>결산</th>
                            <th class="settlement-col">정산</th> <!-- 가운데 정렬 적용 -->
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 샘플 1: 정산 완료 -->
                        <tr>
                          <td>AI 서비스 개발</td>
                          <td>24.03.01 ~ 24.05.31</td>
                          <td>박영희</td>
                          <td>010-9876-5432</td>
                          <td>24,250,000원</td>
                          <td>727,500원</td>
                          <td>24,977,500원</td>
                          <td>4명</td>
                          <td>12일</td>
                          <td>
                            <select class="settlement-status-select">
                              <option>미납</option>
                              <option>미결산</option>
                              <option selected>완료</option>
                            </select>
                          </td>
                          <td class="settlement-col">
                            <a href="settlement_detail.jsp" class="settlement-btn">정산하기</a>
                            <button class="settlement-btn light">정산 내역</button>
                          </td>
                        </tr>
                      
                        <!-- 샘플 2: 결산일 도래, 미결산 상태 → 버튼 없음 -->
                        <tr>
                          <td>블록체인 플랫폼 개발</td>
                          <td>24.04.01 ~ 24.06.30</td>
                          <td>김철수</td>
                          <td>010-1234-5678</td>
                          <td>29,100,000원</td>
                          <td>873,000원</td>
                          <td>29,973,000원</td>
                          <td>5명</td>
                          <td>0일</td>
                          <td>
                            <select class="settlement-status-select">
                              <option>미납</option>
                              <option selected>미결산</option>
                              <option>완료</option>
                            </select>
                          </td>
                          <td class="settlement-col">
                            <div style="height: 38px;"></div>
                          </td>
                        </tr>
                      
                        <!-- 샘플 3: 미납 -->
                        <tr>
                          <td>빅데이터 시각화 시스템</td>
                          <td>24.02.15 ~ 24.04.30</td>
                          <td>한지우</td>
                          <td>010-2468-1357</td>
                          <td>18,400,000원</td>
                          <td>552,000원</td>
                          <td>18,952,000원</td>
                          <td>3명</td>
                          <td>20일</td>
                          <td>
                            <select class="settlement-status-select">
                              <option selected>미납</option>
                              <option>미결산</option>
                              <option>완료</option>
                            </select>
                          </td>
                          <td class="settlement-col">
                            <a href="settlement_detail.jsp" class="settlement-btn">정산하기</a>
                            <a href="settlement_info.jsp" class="settlement-btn light">정산내역</a>
                          </td>
                        </tr>
                      </tbody>
                      
                      
                </table>
                <div style="text-align: right; margin-top: 16px;">
                    <button class="settlement-save-btn">결산 상태 저장</button>
                </div>
            </div>
        </div>
    </div>
</body>

</html>