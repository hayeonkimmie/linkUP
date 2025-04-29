package controller.admin;

import dao.admin.QnaDAO;
import dto.QnA;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/admin/qnadetail")
public class QnaDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public QnaDetailController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int qnaId = Integer.parseInt(request.getParameter("qnaId"));
        QnaDAO dao = new QnaDAO();
        QnA qna = null;
        try {
            qna = dao.selectQnaById(qnaId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("qna", qna);
        request.getRequestDispatcher("/admin/qna_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
