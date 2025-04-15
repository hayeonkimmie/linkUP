package controller;

import dto.PrepareSettlement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/project")
public class ProjectController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProjectController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        List<PrepareSettlement> projectList = new ArrayList<>();

        projectList.add(new PrepareSettlement("clientId","테크놀로지 주식회사", 1, "웹 서비스 리뉴얼", "2023-05-01 ~ 2023-08-31",
                "김민수", "010-1234-5678", 50000000, 1000000, 51000000, 5, 31, "진행중"));

        projectList.add(new PrepareSettlement("clientId","글로벌 솔루션즈", 2, "모바일 앱 개발", "2023-06-01 ~ 2023-09-30",
                "이지은", "010-2345-6789", 35000000, 800000, 35800000, 4, 30, "구인중"));

        projectList.add(new PrepareSettlement("clientId","스마트 시스템즈", 3, "클라우드 마이그레이션", "2023-04-15 ~ 2023-07-31",
                "박준호", "010-3456-7890", 85000000, 1500000, 86500000, 6, 31, "진행중"));

        projectList.add(new PrepareSettlement("clientId","세이프티 테크", 4, "보안 시스템 구축", "2023-05-20 ~ 2023-08-15",
                "최유진", "010-4567-8901", 45000000, 700000, 45700000, 3, 15, "지연"));

        projectList.add(new PrepareSettlement("clientId","인텔리전스 앱", 5, "AI 솔루션 개발", "2023-06-10 ~ 2023-10-31",
                "정석훈", "010-5678-9012", 120000000, 2500000, 122500000, 8, 31, "진행중"));

        projectList.add(new PrepareSettlement("clientId","데이터 인사이트", 6, "데이터 분석 플랫폼", "2023-03-01 ~ 2023-06-30",
                "한지원", "010-6789-0123", 65000000, 900000, 65900000, 5, 30, "완료"));

        projectList.add(new PrepareSettlement("clientId","디자인 허브", 7, "UI/UX 개선 프로젝트", "2023-05-15 ~ 2023-08-15",
                "임수진", "010-7890-1234", 25000000, 500000, 25500000, 2, 15, "구인중"));

        projectList.add(new PrepareSettlement("clientId","넥스트 테크놀로지", 8, "블록체인 시스템 통합", "2023-07-01 ~ 2023-12-31",
                "송인재", "010-8901-2345", 95000000, 2000000, 97000000, 7, 31, "구인중"));

        request.setAttribute("projectList", projectList);

        request.getRequestDispatcher("/admin/project_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
