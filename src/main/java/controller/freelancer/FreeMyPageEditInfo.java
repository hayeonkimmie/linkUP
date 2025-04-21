//package controller.freelancer;
//
//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//import dto.Career;
//import dto.Freelancer;
//import service.freelancer.FreelancerService;
//import service.freelancer.IFreelancerService;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.WebServlet;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//@WebServlet("/edit-info")
//public class FreeMyPageEditInfo extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    public FreeMyPageEditInfo() {
//        super();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            request.setCharacterEncoding("UTF-8");
//            String freelancerId = request.getParameter("userId");
//            /*if (freelancerId == null) {
//                response.sendRedirect("/login");
//            };*/
//            freelancerId = "free002";
//            IFreelancerService service = new FreelancerService();
//            Freelancer freelancer = service.selectBasicFreelancerById(freelancerId);
//            /*if(freelancer == null) {
//                response.sendRedirect("/join");
//            }*/
//            String type = request.getParameter("type");
//            if(type == null || type.equals("basic")) {
//                // 기본 정보 수정
//                request.setAttribute("freelancer", freelancer);
//                request.getRequestDispatcher("/freelancer/my_page_free_user_basic_info_edit.jsp").forward(request, response);
//            } else if(type.equals("expert")) {
//                // 전문가 정보 수정
//                freelancer = service.selectExpertFreelancerById(freelancerId);
//                Map<Integer, String> portfolioInfoMap = service.selectAllportfolioInfoMap(freelancerId);
//                List<Career> careerList = service.selectCareerListById(freelancerId);
//                request.setAttribute("portfolioInfoMap", portfolioInfoMap);
//                request.setAttribute("careerList", careerList);
//                request.getRequestDispatcher("/freelancer/my_page_free_user_expert_info_edit.jsp").forward(request, response);
//            } else{
//                // 잘못된 요청 처리
//                response.sendRedirect("/error");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//
//        String path = request.getServletContext().getRealPath("upload"); // 업로드 폴더의 물리적 경로 가져오기
//        int size = 10*1024*1024;//10mb
//
//        MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
//
//        try {
//            String type = multi.getParameter("type");
//        System.out.println(type + "일단 여기까지 오기는 옴");
//            String freelancerId = multi.getParameter("userId");
//            freelancerId= "free002";
//        Freelancer freelancer = new Freelancer();
//            if(type == null || type.equals("basic")) {
//                freelancer.setPhoneNum(multi.getParameter("phoneNum"));
//                freelancer.setName(multi.getParameter("name"));
//                freelancer.setNickname(multi.getParameter("nickname"));
//                freelancer.setProfileImg(multi.getParameter("profileImg"));
//                freelancer.setEmail(multi.getParameter("email"));
//                freelancer.setAddress(multi.getParameter("address"));
//                String newPassword = multi.getParameter("newPassword");
//                freelancer.setPassword(newPassword != null ? newPassword : multi.getParameter("password"));
//                freelancer.setPassword("pass456");
//                freelancer.setBank(multi.getParameter("bank"));
//                freelancer.setAccountNum(multi.getParameter("accountNum"));
//                IFreelancerService service = new FreelancerService();
//                freelancer.setFreelancerId(freelancerId);
//                System.out.println(freelancer);
//                service.updateFreelancer(freelancer);
//                request.setAttribute("freelancer", freelancer);
//            // 기본 정보 수정
//                request.getRequestDispatcher("/freelancer/my_page_free_user_basic_info_edit.jsp").forward(request, response);
//            } else{
//                // 전문가 정보 수정
//                request.getRequestDispatcher("/freelancer/my_page_free_user_expert_info_edit.jsp").forward(request, response);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}