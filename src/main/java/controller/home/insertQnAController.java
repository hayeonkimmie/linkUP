package controller.home;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
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
        String userId = (String) request.getSession().getAttribute("userId");

        String prevPage = request.getParameter("prevPage"); // 이전 페이지 URL 가져오기

        QnA qna = new QnA();
        qna.setUserId(userId);
        qna.setQuestionTitle(title);
        qna.setQuestionContent(content);
        qna.setQuestionDate(new Date());
        qna.setAnswerContent(null);
        qna.setAnswerDate(null);

        QnAService qnaService = new QnAService();
        int result = 0;
        try {
            result = qnaService.insertQnA(qna);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (result > 0) { // 성공
            String redirectUrl = (prevPage != null && !prevPage.isEmpty()) ? prevPage : request.getContextPath() + "/gogakCenter";
            out.println("<script>");
            out.println("alert('문의 등록이 완료되었습니다.');");
            out.println("location.href='" + redirectUrl + "';");
            out.println("</script>");
        } else { // 실패
            out.println("<script>");
            out.println("alert('문의 등록을 실패했습니다. 다시 시도해주세요.');");
            out.println("history.back();");
            out.println("</script>");
        }
    }


}
