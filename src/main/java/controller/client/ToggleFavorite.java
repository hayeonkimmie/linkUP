package controller.client;

import service.client.ClientFavoritesServiceImpl;
import service.client.IClientFavoritesService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/toggleFavorite")
public class ToggleFavorite extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ToggleFavorite() { // 기본 생성자
        super();
    }

    private final IClientFavoritesService service = new ClientFavoritesServiceImpl();

    //AJAX 요청 (./client/toggleFavorite.js -> Servlet)
    // AJAX 요청 처리 (찜 해제 전용)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String clientId = (String) session.getAttribute("userId");

        if (clientId == null || clientId.isEmpty()) {
            clientId = "client001"; // 테스트용 기본값
        }

        String freelancerId = request.getParameter("freelancerId");

        System.out.println("[ToggleFavorite] clientId = " + clientId);
        System.out.println("[ToggleFavorite] freelancerId = " + freelancerId);

        try {
            // 마이페이지에서는 해제만 허용
            String result = service.removeFavoriteOnly(clientId, freelancerId);
            response.getWriter().write(result);  // 'removed' 또는 'not_found'
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("error");
        }
    }

}
