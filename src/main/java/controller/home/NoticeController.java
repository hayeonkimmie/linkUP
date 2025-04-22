package controller.home;

import dto.Notice;
import service.home.INoticeService;
import service.home.NoticeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/notice")
public class NoticeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NoticeController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        INoticeService noticeService = new NoticeService();
        List<Notice> noticeList = null;
        try {
            noticeList = noticeService.getNoticesList(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("noticeList", noticeList);
        request.getRequestDispatcher("./home/notice.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
