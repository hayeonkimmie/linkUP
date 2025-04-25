package controller.freelancer;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dao.freelancer.FreelancerDAO;
import dto.*;
import service.freelancer.FreelancerService;
import service.freelancer.IFreelancerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

@WebServlet("/my-page/edit-info")
public class FreeMyPageEditInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreeMyPageEditInfo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String freelancerId = (String) request.getSession().getAttribute("userId");
            /*if (freelancerId == null) {
                response.sendRedirect("/login");
            };*/
            freelancerId = "free002";
            IFreelancerService service = new FreelancerService();
            /*if(freelancer == null) {
                response.sendRedirect("/join");
            }*/
            String type = request.getParameter("type");
            Freelancer freelancer = new Freelancer();
            if(type == null || type.equals("basic")) {
                // 기본 정보 수정
                freelancer = service.selectBasicFreelancerById(freelancerId);
                request.setAttribute("freelancer", freelancer);
                request.getRequestDispatcher("/freelancer/my_page_free_user_basic_info_edit.jsp").forward(request, response);
            } else if(type.equals("expert")) {
                // 전문가 정보 수정
                FreelancerDAO freelancerDAO = new FreelancerDAO();
                freelancer = service.selectExpertFreelancerById(freelancerId);
                Map<Integer,String> allPortfolioInfoMap = service.selectAllPortfolioInfoMap(freelancerId);
                List<Category> selectCategoryList = freelancerDAO.selectCategoryList();
                List<Career> careerList = service.selectCareerListById(freelancerId);

                request.setAttribute("freelancer", freelancer);
                request.setAttribute("selectCategoryList", selectCategoryList);
                request.setAttribute("allPortfolioInfoMap", allPortfolioInfoMap);
                request.setAttribute("careerList", careerList);
                System.out.println("FreeMyPageEditInfo 서블릿 59 : " + careerList);
                System.out.println("allPortfolioInfoMap 서블릿 60 : " + allPortfolioInfoMap);
                System.out.println("FreeMyPageEditInfo 서블릿 61 : " + freelancer);
                request.getRequestDispatcher("/freelancer/my_page_free_user_expert_info_edit.jsp").forward(request, response);
            } else{
                // 잘못된 요청 처리
                response.sendRedirect("/error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String path = request.getServletContext().getRealPath("upload"); // 업로드 폴더의 물리적 경로 가져오기
        int size = 10*1024*1024;//10mb
        String freelancerId = (String) request.getSession().getAttribute("userId");
        freelancerId= "free002";
        Freelancer freelancer = new Freelancer();
        freelancer.setFreelancerId(freelancerId);
        IFreelancerService service = new FreelancerService();
        MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
        String type = multi.getParameter("type");
        System.out.println(type + "일단 여기까지 오기는 옴");
        try {
            System.out.println("서블릿 84" + freelancerId);
            if(type == null || type.equals("basic")) {
                freelancer.setPhoneNum(multi.getParameter("phoneNum"));
                freelancer.setName(multi.getParameter("name"));
                freelancer.setNickname(multi.getParameter("nickname"));
                if(multi.getParameter("profileImg") != null) {
                    freelancer.setProfileImg("프로필 변경 없음.");
                } else {
                    freelancer.setProfileImg(multi.getParameter("profileImg"));
                }
                freelancer.setEmail(multi.getParameter("email"));
                freelancer.setAddress(multi.getParameter("address"));
                String newPassword = multi.getParameter("newPassword");
                freelancer.setPassword(newPassword != null ? newPassword : multi.getParameter("password"));
                freelancer.setPassword("pass456");
                freelancer.setBank(multi.getParameter("bank"));
                freelancer.setAccountNum(multi.getParameter("accountNum"));
                freelancer.setFreelancerId(freelancerId);
                System.out.println("FreeMyPageEditInfo 서블릿 90 : " + freelancer);
                service.updateFreelancer(freelancer);
                // 수정 결과 확인
                freelancer = service.selectBasicFreelancerById(freelancerId);
                System.out.println("FreeMyPageEditInfo 서블릿 93 : " + freelancer);
                request.setAttribute("freelancer", freelancer);
            // 기본 정보 수정
                request.getRequestDispatcher("/freelancer/my_page_free_user_basic_info_edit.jsp").forward(request, response);
            } else {
                // 전문가 정보 수정
                // ----------------------------- 경력 -----------------------------
                List<Career> careerList = new ArrayList<>();
                int i = 0;
                while (true) {
                    String base = "careerList[" + i + "]";
                    String companyName = request.getParameter(base + ".companyName");
                    if (companyName == null) break;

                    Career career = new Career();
                    career.setFreelancerId(freelancerId);
                    career.setCompanyName(companyName);
                    career.setDepartmentName(multi.getParameter(base + ".departmentName"));
                    career.setJoinDate(Date.valueOf(multi.getParameter(base + ".joinDate")+"-01"));
                    career.setResignDate(Date.valueOf(multi.getParameter(base + ".resignDate")+"-01"));
                    career.setPosition(multi.getParameter(base + ".position"));
                    career.setJobTitle(multi.getParameter(base + ".jobTitle"));
                    career.setSalary(Integer.valueOf(multi.getParameter(base + ".salary")));
                    career.setJobDescription(multi.getParameter(base + ".jobDescription"));

                    careerList.add(career);
                    i++;
                }

                // ----------------------------- 학력 -----------------------------
                List<Academic> academicList = new ArrayList<>();
                i = 0;
                while (true) {
                    String base = "educationList[" + i + "]";
                    String academicType = multi.getParameter(base + ".academicType");
                    if (academicType == null) break;

                    Academic edu = new Academic();
                    edu.setAcademicType(academicType);
                    edu.setAcademicName(multi.getParameter(base + ".academicName"));
                    edu.setAcademicMajor(multi.getParameter(base + ".academicMajor"));
                    edu.setEntranceDate(multi.getParameter(base + ".enterDate"));
                    edu.setGraduateDate(multi.getParameter(base + ".graduateDate"));

                    academicList.add(edu);
                    i++;
                }
                freelancer.setAcademicList(academicList);
                // ----------------------------- 자격증 -----------------------------
                List<License> licenseList = new ArrayList<>();
                i = 0;
                while (true) {
                    String base = "licenseList[" + i + "]";
                    String name = multi.getParameter(base + ".name");
                    if (name == null || name.trim().isEmpty()) break;

                    License lic = new License();
                    lic.setLicenseName(name);
                    lic.setLicenseAgency(multi.getParameter(base + ".licenseAgency"));
                    lic.setLicenseDate(Date.valueOf(multi.getParameter(base + ".issueDate") +"-01"));
                    lic.setLicenseGrade(multi.getParameter(base + ".licenseGrade"));

                    licenseList.add(lic);
                    i++;
                }
                freelancer.setLicenseList(licenseList);

    // ----------------------------- 스킬 -----------------------------
                String skillDesc = multi.getParameter("skillDescription");
/*                List<String> skillList = new ArrayList<>();
                if (skillDesc != null && skillDesc.contains(",")) {
                    skillList = Arrays.asList(skillDesc.split(","));
                } else {
                    skillList = new ArrayList<>();
                }*/
                freelancer.setSkillList(skillDesc.split(","));
// ----------------------------- 외부 URL -----------------------------
                Enumeration<String> paramNames = multi.getParameterNames();
                List<String> urls = new ArrayList<>();
                while (paramNames.hasMoreElements()) {
                    String name = paramNames.nextElement();
                    if (name.startsWith("file-url-")) {
                        String url = multi.getParameter(name);
                        if (url != null && !url.trim().isEmpty()) {
                            urls.add(url.trim());
                        }
                    }
                }
                freelancer.setExternalUrlList(urls);
                String externalUrl = String.join("^", urls); // ^로 구분하여 저장
// ----------------------------- 첨부파일 -----------------------------
                List<String> attachmentList = new ArrayList<>();
                Enumeration<?> fileFields = multi.getFileNames();
                while (fileFields.hasMoreElements()) {
                    String field = (String) fileFields.nextElement();
                    if (field.startsWith("attachment-")) {
                        String saved = multi.getFilesystemName(field);
                        if (saved != null) {
                            attachmentList.add(saved);
                        }
                    }
                }
                freelancer.setAttachmentList(attachmentList);
                String attachmentStr = String.join("^", attachmentList); // ^로 구분하여 저장
                freelancer.setAttachment(attachmentStr);
// ----------------------------- 포트폴리오 ID -----------------------------
                List<Portfolio> portfolioIds = new ArrayList<>();
                int index = 0;
                while (true) {
                    String idStr = multi.getParameter("portfolioIds[" + index + "]");
                    if (idStr == null || idStr.trim().isEmpty()) break;
                    Integer id = Integer.valueOf(idStr);
                    Portfolio p = new Portfolio();
                    p.setPortfolioId(id);
                    p.setPriority(index+1);
                    index++;
                    portfolioIds.add(p);
                }
                freelancer.setPortfolioInfoList(portfolioIds);
// -----------------------------  ----------------------------
                freelancer.setIntroduction(multi.getParameter("introduction"));
                String onsitePossible = multi.getParameter("onsitePossible");
                freelancer.setIsResident(onsitePossible.equals("Y"));
                String negotiationable = multi.getParameter("negotiation");
                if (negotiationable != null) {
                    freelancer.setIsNegotiable(negotiationable.equals("Y"));
                } else {
                    freelancer.setIsNegotiable(false);
                }
                freelancer.setDesiredLocation(multi.getParameter("desiredLocation"));
                freelancer.setDesiredSalary(Integer.parseInt(multi.getParameter("desiredSalary")));
                freelancer.setOtherRequest(multi.getParameter("otherRequest"));
                // 디버그 출력 (옵션)
                System.out.println("서블릿 215 프리렌서: " + freelancer);
                System.out.println("커리어: " + careerList);
                request.getRequestDispatcher("/freelancer/my_page_free_user_expert_info_edit.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
