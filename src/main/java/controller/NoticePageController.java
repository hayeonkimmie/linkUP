package controller;

import dto.Notice;
import service.home.NoticeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/noticePage")
public class NoticePageController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NoticeService noticeService = new NoticeService();

    public NoticePageController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. id 파라미터 받아오기
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);

            // 2. 공지사항 상세 가져오기
            Notice notice = noticeService.getNoticePage(id);

            // 3. request에 담기
            request.setAttribute("notice", notice);

            // 4. noticePage.jsp로 이동
            request.getRequestDispatcher("./home/noticePage.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
