package controller.admin;

import dao.admin.QnaDAO;
import service.admin.IQnaService;
import service.admin.QnaService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/admin/qna-answer")
public class QnaAnswerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public QnaAnswerController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int qnaId = Integer.parseInt(request.getParameter("qnaId"));
        String answerContent = request.getParameter("answerContent");

        QnaDAO dao = new QnaDAO();
        IQnaService qnaService = new QnaService(dao);
        try {
            qnaService.updateAnswer(qnaId, answerContent);
            response.sendRedirect(request.getContextPath() + "/admin/qna");  // 답변 저장 후 목록으로
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "답변 저장 중 오류 발생");
        }
    }
}
