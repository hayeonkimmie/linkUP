package controller.freelancer;
import dto.Apply;
import service.freelancer.FreelancerApplyProjService;
import service.freelancer.IFreelancerApplyProjService;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-page/apply-proj-list")
public class MyPageApplyList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyPageApplyList(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            response.sendRedirect("/linkup/login");
        };
        String pageStr = request.getParameter("page");
        System.out.println("page = " + pageStr);
        Integer page = null;
        if(pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        System.out.println("page = " + pageStr);
        PageInfo pageInfo = new PageInfo(page);
        IFreelancerApplyProjService service = new FreelancerApplyProjService();

        List<Apply> ApplyProjList = null;
        try {
            Integer applyProjCount = service.getApplyProjCnt(freelancerId);
            if(applyProjCount > 0) {
                ApplyProjList = service.getApplProjyList(freelancerId, pageInfo);
                request.setAttribute("pageInfo", pageInfo);
                request.setAttribute("freelancerId", freelancerId);
                request.setAttribute("applyProjCount", applyProjCount);
                request.setAttribute("page", page);
                System.out.println("43 ApplyProjList = " + ApplyProjList);
                request.setAttribute("ApplyProjList", ApplyProjList);
                request.getRequestDispatcher("/freelancer/apply_proj_list.jsp").forward(request, response);
            } else{
                request.setAttribute("ApplyProjList", null);
            }
        } catch (Exception e) {
            request.setAttribute("err", "지원내역 조회에 실패했습니다");
            e.printStackTrace();
        }
    }
}