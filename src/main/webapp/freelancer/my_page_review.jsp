<%--
  Created by IntelliJ IDEA.
  User: sangk
  Date: 25. 4. 9.
  Time: 오후 5:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link up Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="./../js/freelancer_my_page_portfolio.js"></script>
    <link rel="stylesheet" href="./../css/freelancer_my_page.css" />
    <link rel="stylesheet" href="./../css/freelancer_my_page_project_review.css"/>
    <script src="./../js/freelancer_my_page_project_review.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
<!-- 헤더 include -->
<!--#include file="header.html" -->

<div class="container">
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
    <!-- 메인 콘텐츠 -->
    <div class="content">
        <!-- 페이지 제목 -->
        <div class="topbar">
            <h1>프로젝트 후기</h1>
        </div>

        <!-- 탭 메뉴 -->
        <div class="tabs">
            <button class="tab-btn active" data-tab="received">받은 리뷰 <span class="badge">2</span></button>
            <button class="tab-btn" data-tab="written">작성한 리뷰 <span class="badge">2</span></button>
            <button class="tab-btn" data-tab="write">후기 작성하기</button>
        </div>

        <!-- 탭 콘텐츠 -->
        <div class="tab-content-container">

            <!-- 받은 리뷰 -->
            <div class="tab-content active" id="received">
                <div class="review-card">
                    <div class="card-header">
                        <div class="user">구인자1</div>
                        <div class="project">프로젝트 명</div>
                    </div>
                    <div class="stars">★★★★★</div>
                    <div class="card-body">
                        전문적이고 커뮤니케이션도 원활했습니다.
                    </div>
                </div>
            </div>
            <!-- 작성한 리뷰 (form + 아코디언) -->
            <div class="tab-content" id="written">
                <form class="accordion-item">
                    <div class="accordion-header">
                        <div class="review-card">
                            <div class="card-header">
                                <div class="user">구인자1</div>
                                <div class="project">프로젝트 명</div>
                            </div>
                            <div class="stars">★★★★★</div>
                            <div class="card-body">
                                전문적이고 커뮤니케이션도 원활했습니다.
                            </div>
                        </div>
                    </div>
                    <div class="accordion-body review-form">
                        <div class="form-row">
                            <label>별점</label>
                            <div class="rating-select">
                                <span class="star" data-value="1">★</span>
                                <span class="star" data-value="2">★</span>
                                <span class="star" data-value="3">★</span>
                                <span class="star" data-value="4">★</span>
                                <span class="star" data-value="5">★</span>
                                <input type="number" value="" style="display: none;" name="">
                            </div>
                        </div>
                        <div class="form-row">
                            <label>후기 내용</label>
                            <textarea rows="5" name="" placeholder="협업 경험에 대해 자유롭게 작성해 주세요."></textarea>
                        </div>
                        <div class="btn-group right">
                            <button type="submit" class="btn light">등록</button>
                            <button type="button" class="btn">삭제</button>
                        </div>
                    </div>
                </form>

                <!-- 항목 2 -->
                <form class="accordion-item">
                    <div class="accordion-header">
                        <div class="review-card">
                            <div class="card-header">
                                <div class="user">구인자1</div>
                                <div class="project">프로젝트 명</div>
                            </div>
                            <div class="stars">★★★★★</div>
                            <div class="card-body">
                                전문적이고 커뮤니케이션도 원활했습니다.
                            </div>
                        </div>
                    </div>
                    <div class="accordion-body review-form">
                        <div class="form-row">
                            <label>별점</label>
                            <div class="rating-select">
                                <span class="star" data-value="1">★</span>
                                <span class="star" data-value="2">★</span>
                                <span class="star" data-value="3">★</span>
                                <span class="star" data-value="4">★</span>
                                <span class="star" data-value="5">★</span>
                                <input type="number" value="" style="display: none;" name="">
                            </div>
                        </div>
                        <div class="form-row">
                            <label>후기 내용</label>
                            <textarea rows="5" name="" placeholder="협업 경험에 대해 자유롭게 작성해 주세요."></textarea>
                        </div>
                        <div class="btn-group right">
                            <button type="submit" class="btn light">등록</button>
                            <button type="button" class="btn">삭제</button>
                        </div>
                    </div>
                </form>
            </div>

            <!-- 후기 작성하기 (form + 아코디언) -->
            <div class="tab-content" id="write">
                <div class="accordion-review">
                    <!-- 항목 1 -->
                    <form class="accordion-item" action="" method="post">
                        <div class="accordion-header">
                            <span class="user">구인자3</span>
                            <input type="text" value="" style="display: none;" name=""/>
                            <span class="project">브랜딩 웹사이트 제작</span>
                            <input type="text" value="" style="display: none;" name="project_id"/>
                        </div>
                        <div class="accordion-body review-form">
                            <div class="form-row">
                                <label>별점</label>
                                <div class="rating-select">
                                    <span class="star" data-value="1">★</span>
                                    <span class="star" data-value="2">★</span>
                                    <span class="star" data-value="3">★</span>
                                    <span class="star" data-value="4">★</span>
                                    <span class="star" data-value="5">★</span>
                                    <input type="number" value="" style="display: none;" name="">
                                </div>
                            </div>
                            <div class="form-row">
                                <label>후기 내용</label>
                                <textarea rows="5" name="" placeholder="협업 경험에 대해 자유롭게 작성해 주세요."></textarea>
                            </div>
                            <div class="btn-group right">
                                <button type="submit" class="btn light">등록</button>
                                <button type="button" class="btn">삭제</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div> <!-- tab-content-container -->
</div> <!-- content -->
</div> <!-- container -->
</body>
</html>