package controller.client;

import dto.ClientProfile;
import dto.ClientUserDetail;
import dto.ClientUserInfo;
import service.client.ClientProfileServiceImpl;
import service.client.IClientProfileService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/clientProfile")
public class ClientMyPageEditInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientMyPageEditInfo() {
        super();
    }

    private final IClientProfileService service = new ClientProfileServiceImpl();

    // 서버에서 UserId가지고 프로필에서 정보 불러와서 화면에 뿌려주기
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // 로그인 정보자 사용자 상태 저장
        String clientId = (String) session.getAttribute("userId"); // 세션에 저장된 로그인 유저ID

        // 테스트용 기본값 (로그인 구현 코드로 교체 필요함)
        if (clientId == null) {
            clientId = "client001";
        }

        try {
            // 서비스 호출하여 데이터 받아오기 (세션으로부터 얻은 clientId로 DB정보 꺼내와서 JSP에 보여주기)
            ClientProfile profile = service.getClientProfile(clientId);

            // JSP에서 사용할 수 있도록 request에 저장
            request.setAttribute("profile", profile);

            // 프로필 JSP 페이지로 이동
            request.getRequestDispatcher("/client/profileSetting.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 버튼 분기
        String action = request.getParameter("action");
        String clientId = request.getParameter("userId");

        try {
            // 프로필 정보 조회 (에러 발생 시에도 재사용)
            ClientProfile profile = service.getClientProfile(clientId);

            if ("changePw".equals(action)) {
                // 비밀번호 변경 처리
                String currentPw = request.getParameter("currentPw");
                String newPw = request.getParameter("newPw");
                String newPwConfirm = request.getParameter("newPwConfirm");

                if (newPw == null || newPw.isEmpty()) {
                    request.setAttribute("error", "새 비밀번호를 입력해주세요.");
                    request.setAttribute("profile", profile);
                    request.getRequestDispatcher("/client/profileSetting.jsp").forward(request, response);
                    return;
                }

                if (!newPw.equals(newPwConfirm)) {
                    request.setAttribute("error", "새 비밀번호가 일치하지 않습니다.");
                    request.setAttribute("profile", profile);
                    request.getRequestDispatcher("/client/profileSetting.jsp").forward(request, response);
                    return;
                }

                if (newPw.length() < 8) {
                    request.setAttribute("error", "비밀번호는 8자 이상이어야 합니다.");
                    request.setAttribute("profile", profile);
                    request.getRequestDispatcher("/client/profileSetting.jsp").forward(request, response);
                    return;
                }

                boolean isPasswordCorrect = service.checkCurrentPassword(clientId, currentPw);
                if (!isPasswordCorrect) {
                    request.setAttribute("error", "현재 비밀번호가 틀렸습니다.");
                    request.setAttribute("profile", profile);
                    request.getRequestDispatcher("/client/profileSetting.jsp").forward(request, response);
                    return;
                }

                // 비밀번호 업데이트 실행
                service.updatePassword(clientId, newPw);

                // 성공 메시지 전달
                request.setAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
                request.setAttribute("profile", profile);
                request.getRequestDispatcher("/client/profileSetting.jsp").forward(request, response);
                return;
            }

            else if ("saveInfo".equals(action)) {
                // 폼에서 넘어온 데이터 수집 (여기서 name은 JSP에서 설정한 입력값에 붙어있는 name)
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String mobile = request.getParameter("mobile");

                String companyName = request.getParameter("companyName");
                String companyWebsite = request.getParameter("companyWebsite");
                String establishAt = request.getParameter("establishAt");
                String regNo = request.getParameter("bizNum");
                String mainPhone = request.getParameter("mainPhone");
                String fax = request.getParameter("fax");
                if (fax == null || fax.trim().isEmpty()) { // 팩스 값 없을 경우, 업데이트 안되서 테스트
                    fax = null;
                }
                String intro = request.getParameter("intro");

                // DTO생성 및 값 넣어주기
                ClientUserInfo info = new ClientUserInfo();
                info.setUserId(clientId);
                info.setName(name);
                info.setEmail(email);
                info.setPhoneNumber(mobile);

                ClientUserDetail detail = new ClientUserDetail();
                detail.setId(clientId); // client_id == user_id
                detail.setName(companyName);
                detail.setCompanyWebsiteUrl(companyWebsite);
                detail.setRegistrationDate(java.sql.Date.valueOf(establishAt)); // yyyy-MM-dd 형식
                detail.setCompanyRegNo(regNo);
                detail.setCompanyPhoneNumber(mainPhone);
                detail.setCompanyFaxNum(fax);
                detail.setCompanyDescription(intro);

                // ClientProfile로 합쳐서 넣어주기
                profile.setInfo(info);
                profile.setDetail(detail);

                // 서비스 호출
                // reusult에 수정하는 몇 행이 담겨있는지 (성공 : 1, 실패 : 0)
                int result = service.updateClientProfile(profile);

                if (result > 0) {
                    //성공하면 doGet()호출해서 최신데이터 새로 불러오기 (새로고침 효과) => 수정완료
                    response.sendRedirect(request.getContextPath() + "/clientProfile");
                } else { // 실패하면, 입력한 값 그대로 유지해서 보여주기
                    // JSP에서 alert 띄우기 위해 에러메시지 전달
                    request.setAttribute("error", "정보 수정에 실패했습니다.");
                    // 사용자가 입력한 내용 다시 화면에 유지시키기 위해 전달
                    request.setAttribute("profile", profile);
                    // 해당 페이지에 머무른 채, 다시 profileSetting.jsp로 돌아가기
                    request.getRequestDispatcher("/client/profileSetting.jsp").forward(request, response);
                }
            }

        } catch (Exception e) { // 에러 처리
            e.printStackTrace();
            request.setAttribute("error", "정보 수정 중 오류 발생: " + e.getMessage());
            try {
                ClientProfile profile = service.getClientProfile(clientId);
                request.setAttribute("profile", profile);
            } catch (Exception ignored) {}
            request.getRequestDispatcher("/client/profileSetting.jsp").forward(request, response);
        }
    }
}
