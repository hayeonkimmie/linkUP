package controller;

import dao.admin.ClientDAO;
import dao.admin.IClientDAO;
import dto.ClientUserDetail;
import service.admin.ClientService;
import service.admin.IClientService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/admin/client")
public class ClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IClientDAO clientDAO = new ClientDAO();
        IClientService clientService = new ClientService(clientDAO);

        try{
            String clientId = request.getParameter("clientid");
            ClientUserDetail client = clientService.selectClientById(clientId);
            request.setAttribute("client", client);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Client not found");
        }

        request.getRequestDispatcher("/admin/client_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
