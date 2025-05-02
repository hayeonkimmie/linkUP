package controller.freelancer;

import service.freelancer.FreelancerApplyProjService;
import service.freelancer.IFreelancerApplyProjService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/my-page/apply-cancel")
public class MyPageApplyCancel extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyPageApplyCancel() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String applyIdStr = request.getParameter("applyId");
        System.out.println("MyPageApplyCancel applyIdStr = " + applyIdStr);
        Integer applyId = null;
        if(applyIdStr != null) {
            applyId = Integer.parseInt(applyIdStr);
        }
        System.out.println("applyId = " + applyId);
        IFreelancerApplyProjService service = new FreelancerApplyProjService();
        try {
            service.cancleProjApply(applyId);
            request.setAttribute("msg", "지원내역이 취소되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/my-page/apply-proj-list").forward(request, response);
    }
}