<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <c:set var ="contextPath" value="${pageContext.request.contextPath }"/>
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/freelancer_my_page_info_edit.css'/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="./../js/freelancer_my_page_info_edit.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>
    <body>
        <!-- 헤더 include -->
        <div class="container">
            <!-- 사이드바 자리 표시 -->
            <aside class="sidebar">
                <div class="profile">
                    <img src="./../img/basic_profile_img.png" alt=" " />
                    <p>닉네임</p>
                    <p>마이페이지</p>
                </div>
                <ul>
                    <li class="profile-settings">
                        <h3>프로필 설정</h3>
                        <a href="#">기본 정보 설정</a>
                        <a href="#">전문가 정보 설정</a>
                    </li>
                    <a href="#">포트폴리오</a>
                    <a href="#">찜한 프로젝트</a>
                    <a href="#">지원한 프로젝트 내역</a>
                    <a href="#" class="active">진행중인 / 완료된 프로젝트</a>
                    <a href="#">프로젝트 후기</a>
                    <a href="#">문의내역</a>
                </ul>
            </aside>

            <main class="content">
                <section class="section">
                    <h3>전문가 정보 설정</h3>
                    <form id="expertForm">
                        <!-- 분야 -->
                        <div class="form-group">
                            <label>전문 분야</label>
                            <select id="specialization1">
                                <option value="">비즈니스</option>
                                <option value="">디자인</option>
                                <option value="">IT·프로그래밍</option>
                                <option value="">영상·사진·음향</option>
                                <option value="">마케팅</option>
                                <option value="">번역·통역</option>
                                <option value="">문서·글쓰기</option>
                                <option value="">창업·사업</option>
                                <option value="">세무·법무·노무</option>
                                <option value="">취미 레슨</option>
                            </select>
                            <select id="specialization2">
                                <option value="">분야를 선택해 주세요</option>
                                <option value="웹 개발">웹 개발</option>
                                <option value="모바일 개발">모바일 개발</option>
                                <option value="AI/ML">AI/ML</option>
                                <option value="데이터 분석">데이터 분석</option>
                            </select>

                            <select id="specialization3">
                                <option value="">분야를 선택해 주세요</option>
                                <option value="프론트">프론트</option>
                                <option value="백엔드">백엔드</option>
                                <option value="AI/ML">AI/ML</option>
                                <option value="데이터 분석">데이터 분석</option>
                            </select>

                            <div class="specialization">
                                <button type="button" class="clear_fields clear-skill-btn">초기화</button>
                            </div>
                        </div>

                        <!-- 스킬 -->
                        <div class="form-group">
                            <label>스킬</label>
                            <select>
                                <option>찾으시는 스킬이 있나요?</option>
                                <option>a</option>
                                <option>b</option>
                                <option>c</option>
                            </select>
                            <div class="skill-tags">
                                <button type="button" class="clear_fields clear-skill-btn">초기화</button>
                            </div>
                        </div>

                        <!-- 학력 -->
                        <div class="form-group">
                            <label>학력</label>
                            <div id="education-container">
                                <div class="row">
                                    <div class="dropdown dropdown-education-category">
                                        <div class="label" aria-hidden="false">학력구분</div>
                                        <select> <span>학교구분 <span class="star">*</span></span>
                                            <option value="0">고등학교</option>
                                            <option value="1">대학교(2,3년)</option>
                                            <option value="2">대학교(4년)</option>
                                            <option value="3">대학원</option>
                                        </select>
                                    </div>
                                    <div class="input input-education-name search is-label">
                                        학교명 <span class="star">*</span>
                                        <input type="text">
                                    </div>
                                    <button type="button" class="button buttonDeleteField"><span>학교 삭제</span></button>
                                </div>
                                <!-- 복제된 요소들이 여기에 추가됨 -->
                            </div>
                            <button id="education_addbutton">+ 학력 추가</button>
                        </div>

                        <!-- 경력 -->
                        <div class="form-group">
                            <label>경력</label>
                            <div id="career-container">
                                <div class="career-box">
                                    <div class="career-row">
                                        <input type="text" placeholder="회사명">
                                        <input type="text" placeholder="부서명">
                                        <input type="text" placeholder="입사년도">
                                        <input type="text" placeholder="퇴사년도">
                                    </div>
                                    <div class="career-row">
                                        <select>
                                            <option>직급/직책</option>
                                        </select>
                                        <input type="text" placeholder="담당직무">
                                        <input type="text" placeholder="인원">
                                    </div>
                                    <textarea placeholder="담당업무 내용"></textarea>
                                </div>
                            </div>
                            <button type="button" class="add-career-btn">+ 경력 추가</button>
                        </div>

                        <!-- 포트폴리오 -->
                        <div class="form-group">
                            <label>포트폴리오</label>
                            <table>
                                <thead>
                                    <tr>
                                        <th>url</th>
                                        <th>첨부파일</th>
                                        <th>포트폴리오</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>https://github.com/sdsaadfa</td>
                                        <td>포트폴리오.pdf</td>
                                        <td></td>
                                        <td><button>X</button></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td>2024 KOSTA(주)의 홈페이지 리뉴얼</td>
                                        <td><button>X</button></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td>2024 KOSTA(주)의 홈페이지 개선</td>
                                        <td><button>X</button></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- 자기소개서 -->
                        <div class="form-group">
                            <label>자기소개서</label>
                            <input type="text" placeholder="자기소개서 제목">
                            <textarea placeholder="자신에 대해 설명해 주세요."></textarea>
                        </div>

                        <!-- 희망근무조건 -->
                        <div class="form-group">
                            <label>희망 근무 조건</label>
                            <div>
                                <span>상주 가능 여부:</span>
                                <label><input type="radio" name="onsite"> 가능</label>
                                <label><input type="radio" name="onsite"> 불가능</label>
                                <label><input type="checkbox" name="negotiation"> 협의 가능</label>
                            </div>
                            <input type="text" placeholder="희망 급여(월)">
                            <input type="text" placeholder="희망 근무지">
                            <textarea placeholder="기타 희망사항"></textarea>
                        </div>

                        <div class="submit-wrap">
                            <button type="submit" class="save-btn">저장</button>
                        </div>
                    </form>
                </section>
            </main>
        </div>
    </body>
</html>