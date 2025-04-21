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
            if ("all".equals(userType) || Objects.isNull(userType) || userType.trim().isEmpty()) {
                freelancerList = hasKeyword
                        ? freelancerService.searchFreelancersByKeyword(keyword)
                        : freelancerService.selectAllFreelancer();
                clientList = hasKeyword
                        ? clientService.selectClientsByKeyword(keyword)
                        : clientService.getAllClients();

            } else if ("freelancer".equals(userType)) {
                // ✅ 구직자만 조회
                freelancerList = hasKeyword
                        ? freelancerService.searchFreelancersByKeyword(keyword)
                        : freelancerService.selectAllFreelancer();

            } else if ("client".equals(userType)) {
                // ✅ 구인자만 조회
                clientList = hasKeyword
                        ? clientService.selectClientsByKeyword(keyword)
                        : clientService.getAllClients();
            }

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
