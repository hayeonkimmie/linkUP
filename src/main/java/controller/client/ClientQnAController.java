package controller.client;

import dto.QnA;
import service.client.IQnAService;
import service.client.QnAServiceImpl;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientQnA")
public class ClientQnAController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientQnAController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

    // 로그인한 사용자 아이디 가져오기
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId"); // 세션에서 userId를 가져옴

    // 로그인 확인
        if (userId == null || userId.isEmpty()) {
    // 로그인되지 않은 경우, 로그인 페이지로 리다이렉트
    response.sendRedirect("login.jsp"); // 실제 로그인 페이지 경로로 수정
    return;
}

        System.out.println("로그인한 사용자 ID: " + userId); // 디버깅용 로그

        // 검색 바 키워드 검색
        String keyword = request.getParameter("keyword");

        // clientQnA 필터 정보 받아오기
        String status = request.getParameter("status"); // waiting or complete or null
        String sort = request.getParameter("sort"); // asc or desc or null

        // 페이징 처리
        String pageStr = request.getParameter("page");
        Integer page = (pageStr == null) ? 1: Integer.parseInt(pageStr);
        PageInfo pageInfo = new PageInfo(page);
        IQnAService service = new QnAServiceImpl();

        try{
            List<QnA> qnaList = service.getQnAListWithFilter(userId, pageInfo, status, sort, keyword);

            // 검색 처리
            request.setAttribute("keyword", keyword);

            // 필터 처리
            request.setAttribute("status", status);
            request.setAttribute("sort", sort);

            // 페이지 정보
            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("qnaList", qnaList);

            // 출력 테스트
            System.out.println(status);
            System.out.println(sort);
            System.out.println(qnaList);
            System.out.println(pageInfo);
            request.getRequestDispatcher("./client/clientQnA.jsp").forward(request,response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
