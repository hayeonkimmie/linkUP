package controller;
import dto.ClientFavorites;
import service.client.ClientFavoritesServiceImpl;
import service.client.IClientFavoritesService;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/favorites")
public class ClientFavoritesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientFavoritesController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pageStr = request.getParameter("page");
        Integer page = null;
        if (pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }

        PageInfo pageInfo = new PageInfo(page);
        IClientFavoritesService service = new ClientFavoritesServiceImpl();
        try {
            List<ClientFavorites> jjimList = service.getClientFavorites(pageInfo);
            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("jjimList", jjimList);
            System.out.println(jjimList);
            System.out.println(pageInfo);
            request.getRequestDispatcher("./client/favorites.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


