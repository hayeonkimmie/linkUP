package controller.home;

import dto.Freelancer;
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
        Freelancer freelancer = null;
        try {
            freelancer = freelancerService.selectBasicFreelancerById(freelancerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("freelancer", freelancer);
        request.getRequestDispatcher("./home/freelancer_detail.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
