package controller;

import dao.admin.ClientDAO;
import dao.admin.FreelancerDAO;
import dao.admin.IClientDAO;
import dao.admin.IFreelancerDAO;
import dto.Client;
import dto.Freelancer;
import dto.admin.ClientUserInfo;
import service.admin.ClientService;
import service.admin.FreelancerService;
import service.admin.IClientService;
import service.admin.IFreelancerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/users")
public class UserListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserListController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IClientDAO clientDAO = new ClientDAO();
        IFreelancerDAO freelancerDAO = new FreelancerDAO();
        IClientService clientService = new ClientService(clientDAO);
        IFreelancerService freelancerService = new FreelancerService(freelancerDAO);

        List<Freelancer> freelancerList = new ArrayList<>();
        List<ClientUserInfo> clientList = new ArrayList<>();

        try {
            clientList = clientService.getAllClients();
            freelancerList =  freelancerService.selectAllFreelancer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("clientList", clientList);
        request.setAttribute("freelancerList", freelancerList);

        request.getRequestDispatcher("/admin/user_info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
