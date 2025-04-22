package controller.home;

import dto.News;
import service.home.INewsService;
import service.home.NewsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/news")
public class NewsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NewsController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        INewsService newsService = new NewsService();
        List<News> newsList = null;
        try {
            newsList = newsService.getNewsList(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("newsList", newsList);
        request.getRequestDispatcher("./home/news.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
