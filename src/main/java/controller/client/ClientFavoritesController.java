package controller.client;

import dto.ClientFavorites;
import service.client.ClientFavoritesServiceImpl;
import service.client.IClientFavoritesService;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientFavorites")
public class ClientFavoritesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientFavoritesController() {
        super();
    }

    // 찜 등록 / 해제 (AJAX)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String clientId = (String) session.getAttribute("userId");

        if (clientId == null || clientId.isEmpty()) {
            clientId = "client001"; // 테스트용
        }

        String freelancerId = request.getParameter("freelancerId");

        IClientFavoritesService service = new ClientFavoritesServiceImpl();
        System.out.println("⭐ freelancerId = " + freelancerId);
        System.out.println("⭐ clientId = " + clientId);

        try {
            String result = service.toggleFavorite(clientId, freelancerId);
            response.getWriter().write(result); // "added", "removed", "error"
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("error");
        }
    }

    // 찜한 프리랜서 목록 조회
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 세션에서 clientId 확인
        HttpSession session = request.getSession();
        String clientId = (String) session.getAttribute("userId");
        if (clientId == null || clientId.isEmpty()) {
            clientId = "client001"; // 테스트용 기본값
        }

        System.out.println("세션 clientId: " + clientId); // 디버깅

        // 페이지 & 정렬 처리
        String pageStr = request.getParameter("page");
        int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);

        String sort = request.getParameter("sort");
        if (sort == null || sort.trim().isEmpty()) {
            sort = "recent"; //  기본 정렬 강제 설정
        }

        // JSP에서 selected 처리 가능하게 값 넘겨줌
        request.setAttribute("sort", sort);

        PageInfo pageInfo = new PageInfo(page);
        IClientFavoritesService service = new ClientFavoritesServiceImpl();

        try {
            // 정렬 조건 항상 포함하여 호출
            List<ClientFavorites> jjimList = service.getClientFavoritesWithFilter(pageInfo, clientId, sort, null);

            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("clientFavoritesList", jjimList);
            request.getRequestDispatcher("./client/favorites.jsp").forward(request, response);

            System.out.println("최종 결과 리스트 사이즈: " + jjimList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
