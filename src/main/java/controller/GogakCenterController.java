package controller;

import dto.News;
import dto.Notice;
import service.home.INewsService;
import service.home.INoticeService;
import service.home.NewsService;
import service.home.NoticeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/gogakCenter")
public class GogakCenterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GogakCenterController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        INoticeService noticeService = new NoticeService();
        INewsService newsService = new NewsService();

        List<Notice> noticeList = null;
        List<News> newsList = null;

        try {
            noticeList = noticeService.getGogakCenterNoticesList(1);
            newsList = newsService.getGogakCenterNewsList(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("noticeList", noticeList);
        request.setAttribute("newsList", newsList);

        request.getRequestDispatcher("./home/gogakCenter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
