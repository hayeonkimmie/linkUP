package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Date;
import dto.QnA;
import service.freelancer.QnAService;

@WebServlet("/QnA")
public class insertQnAController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public insertQnAController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    request.getRequestDispatcher("./home/QnA.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");



        String title = request.getParameter("questionTitle");
        String content = request.getParameter("questionContent");
        String userId = (String) request.getSession().getAttribute("userId"); // 세션에서 로그인한 사용자 ID 가져오기


        if (userId == null) {
            userId = "client004"; // 테스트용 임시 userId
        }


        QnA qna = new QnA();
        qna.setQnaId(19); // 만약 DB가 Auto Increment면 무시하거나 null로 두세요
        qna.setUserId(userId);
        qna.setQuestionTitle(title);
        qna.setQuestionContent(content);
        qna.setQuestionDate(new Date()); // 현재 시간
        qna.setAnswerContent(null);
        qna.setAnswerDate(null);

        QnAService qnaService = new QnAService();
        int result = 0;
        try {
            result = qnaService.insertQnA(qna); // insert 성공시 1 반환
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) { // 성공
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('문의 등록이 완료되었습니다.'); location.href='" + request.getContextPath() + "/gogakCenter';</script>");
        } else { // 실패
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('문의 등록을 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
        }
    }

}
