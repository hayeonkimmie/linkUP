package controller.client;
import dto.ClientFavorites;
import service.client.ClientFavoritesServiceImpl;
import service.client.IClientFavoritesService;
import util.PageInfo;

import javax.servlet.*;
        import javax.servlet.http.*;
        import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/clientFavorites")
public class ClientFavoritesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientFavoritesController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String clientId = (String) session.getAttribute("userId");

        if (clientId == null || clientId.isEmpty()) {
            clientId = "client001"; //테스트용
        }

        String freelancerId = request.getParameter("freelancerId");

        IClientFavoritesService service = new ClientFavoritesServiceImpl();
        // 출력테스트
        System.out.println("⭐ freelancerId = " + freelancerId);
        System.out.println("⭐ clientId = " + clientId);


        // 찜하기 토글 기능
        try {
            String result = service.toggleFavorite(clientId, freelancerId);
            response.getWriter().write(result); // "added", "removed" 또는 "error"
        } catch (Exception e){
            e.printStackTrace();
        response.getWriter().write("error");

        }
    }


    // 찜한 프리랜서 목록 조회하기
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //  로그인한 사용자 ID 가져오기
        HttpSession session = request.getSession();
        String clientId = (String) session.getAttribute("userId");

        // 세션 코드 (나중에 가져와야 함)
        if (clientId == null || clientId.isEmpty()) {
            clientId = "client001"; // 테스트 코드
            System.out.println("세션 clientId: " + clientId); // 테스트 코드


            String pageStr = request.getParameter("page");
            int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);

            String sort = request.getParameter("sort");
            PageInfo pageInfo = new PageInfo(page);
            IClientFavoritesService service = new ClientFavoritesServiceImpl();

            try {
                List<ClientFavorites> jjimList;
                if (sort != null && !sort.isEmpty()) {
                    jjimList = service.getClientFavoritesWithFilter(pageInfo, clientId, sort, null);
                } else {
                    jjimList = service.getClientFavorites(pageInfo);
                }

                request.setAttribute("pageInfo", pageInfo);
                request.setAttribute("clientFavoritesList", jjimList);
                request.getRequestDispatcher("./client/favorites.jsp").forward(request, response);
                System.out.println("최종 결과 리스트 사이즈: " + jjimList.size());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
