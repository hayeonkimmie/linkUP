package controller.freelancer;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dao.freelancer.FreelancerDAO;
import dto.*;
import org.json.simple.parser.ParseException;
import service.freelancer.FreelancerService;
import service.freelancer.IFreelancerService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.*;

@WebServlet("/my-page/edit-expert-info")
public class FreeMyPageExpertEditInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FreeMyPageExpertEditInfo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String freelancerId = (String) request.getSession().getAttribute("userId");
            if (freelancerId == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            IFreelancerService service = new FreelancerService();
            String type = request.getParameter("type");
            Freelancer freelancer = new Freelancer();
            // 전문가 정보 수정
            FreelancerDAO freelancerDAO = new FreelancerDAO();
            freelancer = service.selectExpertFreelancerById(freelancerId);
            Map<Integer, String> allPortfolioInfoMap = service.selectAllPortfolioInfoMap(freelancerId);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String path = request.getServletContext().getRealPath("upload"); // 업로드 폴더의 물리적 경로 가져오기
            int size = 10 * 1024 * 1024;//10mb
            String freelancerId = (String) request.getSession().getAttribute("userId");
            System.out.println("doPost 시작 freelancerId : " + freelancerId);
            Freelancer freelancer = new Freelancer();
            IFreelancerService service = new FreelancerService();
            freelancer = service.selectBasicFreelancerById(freelancerId);
            freelancer.setFreelancerId(freelancerId);
            System.out.println("1차 체크 포인트" + freelancer);
            MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
            JSONParser parser = new JSONParser();
            /*Enumeration<String> params = multi.getParameterNames();
            System.out.println("파라미너 이름 체크");
            while (params.hasMoreElements()) {
                String paramName = params.nextElement();
                System.out.println("파라미터 이름: " + paramName);
            }*/
// 1. CareerList
            List<Career> careerList = new ArrayList<>();
            try {
                String careerListJson = multi.getParameter("careerListJson");
                if (careerListJson != null && !careerListJson.isEmpty()) {
                    JSONArray careerArray = (JSONArray) parser.parse(careerListJson);
                    for (Object obj : careerArray) {
                        JSONObject careerObj = (JSONObject) obj;
                        Career career = new Career();
                        career.setFreelancerId(freelancerId);
                        career.setCompanyName((String) careerObj.getOrDefault("companyName", ""));
                        career.setDepartmentName((String) careerObj.getOrDefault("departmentName", ""));

                        // 입사일 세팅 (yyyy-MM → yyyy-MM-01로 변환)
                        // 퇴사일 세팅 (yyyy-MM → yyyy-MM-01로 변환)
                        String joinDateStr = (String) careerObj.getOrDefault("joinDate", "");
                        String resignDateStr = (String) careerObj.getOrDefault("resignDate", "");
                        try {
                            if (joinDateStr != null && !joinDateStr.isEmpty()) {
                                joinDateStr = joinDateStr + "-01";
                                career.setJoinDate(Date.valueOf(joinDateStr));
                            }
                            if (resignDateStr != null && !resignDateStr.isEmpty()) {
                                resignDateStr = resignDateStr + "-01"; // "2025-05" → "2025-05-01"
                                career.setResignDate(Date.valueOf(resignDateStr));
                            }
                        } catch (IllegalArgumentException e) {
                            throw new Exception(e.getMessage());
                        }
                        career.setPosition((String) careerObj.getOrDefault("position", ""));
                        career.setJobTitle((String) careerObj.getOrDefault("jobTitle", ""));
                        Object salaryObj = careerObj.get("salary");
                        String salaryStr = (salaryObj != null) ? salaryObj.toString().trim() : "0";
                        if (salaryStr.isEmpty()) salaryStr = "0";
                        career.setSalary(Integer.parseInt(salaryStr));
                        career.setJobDescription((String) careerObj.getOrDefault("jobDescription", ""));
                        careerList.add(career);
                    }
                }
                System.out.println("110 2차 체크포인트 careerList : " + careerList);
                // 2. EducationList
                List<Academic> educationList = new ArrayList<>();
                String educationListJson = multi.getParameter("educationListJson");

                if (educationListJson != null && !educationListJson.isEmpty()) {
                    JSONArray educationArray = (JSONArray) parser.parse(educationListJson);
                    for (Object obj : educationArray) {
                        JSONObject eduObj = (JSONObject) obj;
                        Academic academic = new Academic();
                        academic.setAcademicType((String) eduObj.getOrDefault("academicType", ""));
                        academic.setAcademicName((String) eduObj.getOrDefault("academicName", ""));
                        academic.setGraduateStatus((String) eduObj.getOrDefault("graduateStatus", ""));
                        academic.setAcademicMajor((String) eduObj.getOrDefault("academicMajor", ""));
                        // 입학일 (yyyy-MM → yyyy-MM-01 변환해서 Date.valueOf 가능하게)
                        String enterDateStr = (String) eduObj.getOrDefault("enterDate", "");
                        // 졸업일
                        String graduateDateStr = (String) eduObj.getOrDefault("graduateDate", "");
                        try {
                            if (enterDateStr != null && !enterDateStr.isEmpty()) {
                                enterDateStr = enterDateStr + "-01"; // "2025-05" → "2025-05-01"
                                academic.setEntranceDate(enterDateStr);
                            }
                            if (graduateDateStr != null && !graduateDateStr.isEmpty()) {
                                graduateDateStr = graduateDateStr + "-01";
                                academic.setGraduateDate(graduateDateStr);
                            }
                        } catch (IllegalArgumentException e) {
                            throw new Exception(e.getMessage());
                        }
                        educationList.add(academic);
                    }
                    System.out.println("3차 체크포인트 educationList :" + educationList);
                    freelancer.setAcademicList(educationList);
                }
                // 3. LicenseList
                if (educationListJson != null && !educationListJson.isEmpty()) {
                    List<License> licenseList = new ArrayList<>();
                    String licenseListJson = multi.getParameter("licenseListJson");
                    if (licenseListJson != null && !licenseListJson.isEmpty()) {
                        JSONArray licenseArray = (JSONArray) parser.parse(licenseListJson);
                        for (Object obj : licenseArray) {
                            JSONObject licenseObj = (JSONObject) obj;
                            License license = new License();
                            license.setLicenseName((String) licenseObj.getOrDefault("name", ""));
                            license.setLicenseGrade((String) licenseObj.getOrDefault("licenseGrade", ""));
                            license.setLicenseAgency((String) licenseObj.getOrDefault("licenseAgency", ""));
                            // 자격증 취득일
                            String licenseDateStr = (String) licenseObj.getOrDefault("licenseDate", "");
                            try {
                                if (licenseDateStr != null && !licenseDateStr.isEmpty()) {
                                    licenseDateStr = licenseDateStr + "-01"; // "2025-05" → "2025-05-01"
                                    license.setLicenseDate((Date.valueOf(licenseDateStr)));
                                }
                            } catch (IllegalArgumentException e) {
                                throw new Exception(e.getMessage());
                            }
                            licenseList.add(license);
                        }
                        freelancer.setLicenseList(licenseList);
                    }

                    System.out.println("3차 체크포인트 educationList :" + educationList);
                    freelancer.setAcademicList(educationList);
                }
                // 4. ExternalUrlList
                List<String> externalUrlList = new ArrayList<>();
                String externalUrlListJson = multi.getParameter("externalUrlListJson");
                if (externalUrlListJson != null && !externalUrlListJson.isEmpty()) {
                    JSONArray urlArray = (JSONArray) parser.parse(externalUrlListJson);
                    for (Object url : urlArray) {
                        externalUrlList.add((String) url);
                    }
                    System.out.println("5차 체크포인트 externalUrlList :" + externalUrlList);
                    freelancer.setExternalUrlList(externalUrlList);
                    freelancer.setExternalUrl(String.join("^", externalUrlList));
                }

                // 5. AttachmentList
                List<String> attachmentList = new ArrayList<>();
                String attachmentListJson = multi.getParameter("attachmentListJson");
                if (attachmentListJson != null && !attachmentListJson.isEmpty()) {
                    JSONArray attachmentArray = (JSONArray) parser.parse(attachmentListJson);
                    for (Object fileName : attachmentArray) {
                        attachmentList.add((String) fileName);
                    }
                    System.out.println("6차 체크포인트 attachmentList :" + attachmentList);
                    freelancer.setAttachment(String.join("^", attachmentList));
                }
            } catch (ParseException e) {
                throw new Exception();
            }
            // --- 단일 데이터 수신 ---
            String introduction = multi.getParameter("introduction"); // "Java^Spring^MySQL"
            freelancer.setIntroduction(introduction);
// 보유 스킬
            String skillDescription = multi.getParameter("skillDescription"); // "Java^Spring^MySQL"
            System.out.println("7차 skillDescription : " + skillDescription);
            freelancer.setSkill(skillDescription);
// 카테고리 ID (subCategory)
            String subCategoryId = multi.getParameter("subCategory");
            System.out.println("8차 체크포인트 subCategoryId  : " + subCategoryId);
            if (subCategoryId == null) {
                freelancer.setSubCategoryId(freelancer.getSubCategoryId());
            } else {
                freelancer.setSubCategoryId(Integer.parseInt(subCategoryId));
            }
// 희망 근무 조건
            String onsitePossible = multi.getParameter("onsitePossible"); // 'Y' or 'N'
            String negotiation = multi.getParameter("negotiation");       // 'Y' 체크 시 존재
            String desiredSalary = multi.getParameter("desiredSalary");   // 숫자
            String desiredLocation = multi.getParameter("desiredLocation"); // 텍스트
            String otherRequest = multi.getParameter("otherRequest");     // 텍스트

            System.out.println("onsitePossible : " + onsitePossible + " negotiation : " + negotiation + " desiredSalary :" + desiredSalary + " desiredLocation : " + desiredLocation
                    + " otherRequest : " + otherRequest);
// --- freelancer 객체에 세팅 (예시) ---
            if (onsitePossible != null) { // Y이면 true
                freelancer.setIsResident("Y".equals(onsitePossible));
            } else {
                freelancer.setIsResident(false);
            }
            if (negotiation != null) {
                freelancer.setNegotiable("Y".equals(negotiation));  // 체크박스는 값이 넘어오면 true
            } else {
                freelancer.setNegotiable(false);
            }
            if (desiredSalary != null && !desiredSalary.isEmpty()) {
                freelancer.setDesiredSalary(Integer.parseInt(desiredSalary));
            } else {
                freelancer.setDesiredSalary(null);
            }
            freelancer.setDesiredLocation(desiredLocation);
            freelancer.setOtherRequest(otherRequest);

            // 디버그 출력 (옵션)
            System.out.println("서블릿 216 프리렌서: " + freelancer);

            service.updateCareer(careerList, freelancer.getFreelancerId());
            service.updateFreelancer(freelancer);
            // 8. 처리 결과를 응답
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('전문가 정보가 성공적으로 저장되었습니다.');");
            out.println("location.href='/linkup/my-page/edit-expert-info';");
            out.println("</script>");

            response.sendRedirect(request.getContextPath() + "/my-page/edit-expert-info");
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('프로필 정보 저장 중 오류가 발생했습니다. 다시 시도해주세요.');");
            out.println("history.back();");
            out.println("</script>");
        }
    }
}