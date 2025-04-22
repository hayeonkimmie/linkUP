/**
 * FreeLancerController.java
 * GET : 프로젝트나 정산등을 통해 프리랜서의 상세 정보를 렌더링
 */
package controller.admin;

import dao.admin.FreelancerDAO;
import dao.admin.IFreelancerDAO;
import dto.AdminFreelancer;
import dto.Freelancer;
import service.admin.FreelancerService;
import service.admin.IFreelancerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/admin/freelancer")
public class FreeLancerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreeLancerController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String freelancerid = request.getParameter("freelancerid");

        IFreelancerDAO freelancerDAO = new FreelancerDAO();
        IFreelancerService freelancerService = new FreelancerService(freelancerDAO);

        AdminFreelancer freelancer = null;
        try {
            freelancer = freelancerService.selectFreelancerById(freelancerid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("freelancer", freelancer);
        request.getRequestDispatcher("/admin/freelancer_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
