/**
 * UserListController.java
 * LinkUP 사용자들의 목록 조회를 담당하는 서블릿
 * GET :
 *      1-1. keyword Parameter를 통해 검색인지 아닌지 구분
 *      1-2. usertype Parameter를 통해 사용자 유형을 구분
 *      2-1. usertype이 null 또는 비어있으면 전체 조회
 *      2-2. usertype이 freelancer이면 프리랜서 목록 조회
 *      2-3. usertype이 client이면 클라이언트 목록 조회
 */
package controller.admin;

import dao.admin.ClientDAO;
import dao.admin.FreelancerDAO;
import dao.admin.IClientDAO;
import dao.admin.IFreelancerDAO;
import dto.Freelancer;
import dto.ClientUserInfo;
import service.admin.ClientService;
import service.admin.FreelancerService;
import service.admin.IClientService;
import service.admin.IFreelancerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/admin/users")
public class UserListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserListController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String keyword = request.getParameter("keyword");
        String userType = request.getParameter("usertype");

        IClientDAO clientDAO = new ClientDAO();
        IFreelancerDAO freelancerDAO = new FreelancerDAO();
        IClientService clientService = new ClientService(clientDAO);
        IFreelancerService freelancerService = new FreelancerService(freelancerDAO);

        List<Freelancer> freelancerList = new ArrayList<>();
        List<ClientUserInfo> clientList = new ArrayList<>();

        try {
            boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();

            if (hasKeyword) {
                if ("all".equals(userType) || userType == null || userType.trim().isEmpty()) {
                    freelancerList = freelancerService.searchFreelancersByKeyword(keyword);
                    clientList = clientService.selectClientsByKeyword(keyword);

                } else if ("freelancer".equals(userType)) {
                    freelancerList = freelancerService.searchFreelancersByKeyword(keyword);

                } else if ("client".equals(userType)) {
                    clientList = clientService.selectClientsByKeyword(keyword);
                }
            }
            // 👉 else문으로 아무것도 안 불러오게 한다. (빈 리스트 그대로)

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("freelancerList", freelancerList);
        request.setAttribute("clientList", clientList);
        request.setAttribute("paramKeyword", keyword);
        request.setAttribute("paramUsertype", userType);
        request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
