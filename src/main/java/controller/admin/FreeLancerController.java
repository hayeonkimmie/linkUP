/**
 * FreeLancerController.java
 * GET : 프로젝트나 정산등을 통해 프리랜서의 상세 정보를 렌더링
 */
package controller.admin;

import dao.admin.FreelancerDAO;
import dao.admin.IFreelancerDAO;
import dto.AdminFreelancer;
import dto.Career;
import dto.Freelancer;
import service.admin.FreelancerService;
import service.admin.IFreelancerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
        List<Career> selectedCarreerList = null;
        try {
            freelancer = freelancerService.selectFreelancerById(freelancerid);
            selectedCarreerList = freelancerService.selectCareerListByFreelancerId(freelancerid);
            for(Career career : selectedCarreerList) {
                System.out.println("Selected Career : "+career);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("name", Objects.requireNonNull(freelancer).getName());
        request.setAttribute("careerList", selectedCarreerList);
        request.setAttribute("freelancer", freelancer);
        request.getRequestDispatcher("/admin/freelancer_detail.jsp").forward(request, response);
    }
}
