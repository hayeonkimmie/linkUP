package controller;

import dto.News;
import dto.Notice;
import service.home.NewsService;
import service.home.NoticeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/newsPage")
public class NewsPageController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewsService newsService = new NewsService();

    public NewsPageController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 1. id 파라미터 받아오기
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);

            // 2. 공지사항 상세 가져오기
            News news = newsService.getNewsPage(id);

            // 3. request에 담기
            request.setAttribute("news", news);

            // 4. noticePage.jsp로 이동
            request.getRequestDispatcher("./home/newsPage.jsp").forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
