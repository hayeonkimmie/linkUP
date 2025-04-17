package controller;

import dto.PrepareSettlement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/settlement")
public class SettlementController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SettlementController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        ArrayList<PrepareSettlement> projectList = new ArrayList<>();

        projectList.add(new PrepareSettlement("C001", "AI 테크", 101, "AI 서비스 개발", "24.03.01 ~ 24.05.31",
                "박영희", "010-9876-5432", 24250000, 727500, 24977500, 4, 12, "완료"));

        projectList.add(new PrepareSettlement("C002", "블록체인랩", 102, "블록체인 플랫폼 개발", "24.04.01 ~ 24.06.30",
                "김철수", "010-1234-5678", 29100000, 873000, 29973000, 5, 0, "미결산"));

        projectList.add(new PrepareSettlement("C003", "데이터코어", 103, "빅데이터 시각화 시스템", "24.02.15 ~ 24.04.30",
                "한지우", "010-2468-1357", 18400000, 552000, 18952000, 3, 20, "미납"));

        request.setAttribute("projectList", projectList);
        request.getRequestDispatcher("/admin/settlement.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
