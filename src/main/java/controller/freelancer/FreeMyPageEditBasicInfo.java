package controller.freelancer;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dto.*;
import service.freelancer.FreelancerService;
import service.freelancer.IFreelancerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/my-page/edit-basic-info")
public class FreeMyPageEditBasicInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreeMyPageEditBasicInfo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String freelancerId = (String) request.getSession().getAttribute("userId");
            if (freelancerId == null) {
                response.sendRedirect("/linkup/login");
                return;
            }
            IFreelancerService service = new FreelancerService();
            Freelancer freelancer = new Freelancer();

            freelancer = service.selectBasicFreelancerById(freelancerId);
            request.setAttribute("freelancer", freelancer);

            request.getRequestDispatcher("/freelancer/my_page_free_user_basic_info_edit.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String path = request.getServletContext().getRealPath("upload"); // 업로드 폴더의 물리적 경로 가져오기
        int size = 10 * 1024 * 1024;//10mb
        String freelancerId = (String) request.getSession().getAttribute("userId");
        System.out.println("서블릿 52" + freelancerId);
        IFreelancerService service = new FreelancerService();
        MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
        try {
            Freelancer freelancer = service.selectBasicFreelancerById(freelancerId);
            freelancer.setFreelancerId(freelancerId);
            freelancer.setPhoneNum(multi.getParameter("phoneNum"));
            freelancer.setName(multi.getParameter("name"));
            freelancer.setNickname(multi.getParameter("nickname"));

            // 파일명 처리
            String uploadedFileName = multi.getFilesystemName("profileImg");
            if (uploadedFileName != null) {
                freelancer.setProfileImg(uploadedFileName);
            } else {
                freelancer.setProfileImg("basic_profile_img.png");
            }

            freelancer.setEmail(multi.getParameter("email"));
            freelancer.setAddress(multi.getParameter("address"));

            // 비밀번호 처리 (빈 문자열 방지)
            String newPassword = multi.getParameter("newPassword");
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                freelancer.setPassword(newPassword);
            }
            if(multi.getParameter("bank") != null || !(multi.getParameter("bank").equals(""))) {
                freelancer.setBank(multi.getParameter("bank"));
            }
            if(multi.getParameter("accountNum") != null || !(multi.getParameter("accountNum").equals(""))) {
                freelancer.setAccountNum(multi.getParameter("accountNum"));
            }
            System.out.println("FreeMyPageEditInfo 서블릿 90 : " + freelancer);
            service.updateFreelancer(freelancer);
            // 수정 결과 확인
            freelancer = service.selectBasicFreelancerById(freelancerId);
            System.out.println("FreeMyPageEditInfo 서블릿 93 : " + freelancer);
//                request.setAttribute("freelancer", freelancer);
            // 기본 정보 수정
            response.sendRedirect(request.getContextPath() + "/my-page/edit-basic-info");
            // request.getRequestDispatcher("/WEB-INF/views/my_page_free_user_basic_info_edit.jsp").forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
