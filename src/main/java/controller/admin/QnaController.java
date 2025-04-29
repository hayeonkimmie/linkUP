package controller.admin;

import dao.admin.QnaDAO;
import dto.QnA;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/qna")
public class QnaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public QnaController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String keyword = request.getParameter("keyword");
        keyword = (keyword != null && keyword.trim().isEmpty()) ? null : keyword;
        String category = request.getParameter("category");
        category = (category != null && category.trim().isEmpty()) ? null : category;
        String answerStatus = request.getParameter("answerStatus");
        answerStatus = (answerStatus != null && answerStatus.trim().isEmpty()) ? null : answerStatus;
        String startDate = request.getParameter("startDate");
        startDate = (startDate != null && startDate.trim().isEmpty()) ? null : startDate;
        String endDate = request.getParameter("endDate");
        endDate = (endDate != null && endDate.trim().isEmpty()) ? null : endDate;

        String pageParam = request.getParameter("page");
        int curPage = (pageParam == null || pageParam.isEmpty()) ? 1 : Integer.parseInt(pageParam);
        int perPage = 8;
        int offset = (curPage - 1) * perPage;

        try {
            QnaDAO qnaDAO = new QnaDAO();
            List<QnA> qnaList = qnaDAO.selectPagedQna(offset, perPage, keyword, category, answerStatus, startDate, endDate);
            int totalCount = qnaDAO.countQna(keyword, category, answerStatus, startDate, endDate);
            PageInfo pageInfo = new PageInfo(curPage);
            int allPage = (int) Math.ceil((double) totalCount / perPage);
            pageInfo.setAllPage(allPage);
            int startPage = Math.max(1, curPage - 2);
            int endPage = Math.min(allPage, startPage + 4);
            pageInfo.setStartPage(startPage);
            pageInfo.setEndPage(endPage);

            for(QnA qna : qnaList){
                System.out.println(qna);
            }

            request.setAttribute("qnaList", qnaList);
            request.setAttribute("totalCount", totalCount);
            request.setAttribute("pageInfo", pageInfo);

            request.getRequestDispatcher("/admin/qna_manage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류 발생");
        }
    }
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//
//        String[] selectedIds = request.getParameterValues("qnaIds");
//
//        if (selectedIds != null) {
//            QnaDAO qnaDAO = new QnaDAO();
//            try {
//                for (String idStr : selectedIds) {
//                    int id = Integer.parseInt(idStr);
//                    qnaDAO.deleteQna(id);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        response.sendRedirect(request.getContextPath() + "/admin/qna"); // 삭제 후 다시 목록으로
//    }
}
