package controller.home;

import dto.Freelancer;
import service.admin.ClientService;
import service.client.ClientFavoritesServiceImpl;
import service.client.IClientFavoritesService;
import service.freelancer.FreelancerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/freelancer")
public class FreelancerDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreelancerDetailController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String freelancerId = request.getParameter("freelancerid");
        FreelancerService freelancerService = new FreelancerService();
        IClientFavoritesService service = new ClientFavoritesServiceImpl();
        Freelancer freelancer = null;
        String clientId = (String) request.getSession().getAttribute("userId");
        boolean isLiked = false;
        try {
            freelancer = freelancerService.selectBasicFreelancerById(freelancerId);
            if(clientId == null) {
                isLiked = false;
            } else if(clientId != null || !clientId.isEmpty()) {
                isLiked = service.isFreelancerLiked(clientId, freelancerId);
                System.out.println("isLiked : " + isLiked);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("isLiked", isLiked);
        request.setAttribute("freelancer", freelancer);
        request.setAttribute("freelancerId", freelancerId);
        request.getRequestDispatcher("./home/freelancer_detail.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
