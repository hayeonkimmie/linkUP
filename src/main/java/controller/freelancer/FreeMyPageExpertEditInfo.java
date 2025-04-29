package controller.freelancer;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dao.freelancer.FreelancerDAO;
import dto.*;
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
                response.sendRedirect("/linkup/login");
            };
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
            request.getRequestDispatcher("/freelancer/my_page_free_user_expert_info_edit2.jsp").forward(request, response);

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
        MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
        JSONParser parser = new JSONParser();
        /*// === 1. CareerList 가져오기 ===
        List<Career> careerList = new ArrayList<>();
        int idx = 0;
        while (multi.getParameter("careerList[" + idx + "].companyName") != null) {
            Career career = new Career();
            career.setFreelancerId(freelancerId);
            career.setCompanyName(multi.getParameter("careerList[" + idx + "].companyName"));
            career.setDepartmentName(multi.getParameter("careerList[" + idx + "].departmentName"));
            career.setJoinDate(Date.valueOf(multi.getParameter("careerList[" + idx + "].joinDate")+"01"));
            career.setResignDate(Date.valueOf(multi.getParameter("careerList[" + idx + "].resignDate")+"01"));
            career.setPosition(multi.getParameter("careerList[" + idx + "].position"));
            career.setJobTitle(multi.getParameter("careerList[" + idx + "].jobTitle"));
            career.setSalary(Integer.valueOf(multi.getParameter("careerList[" + idx + "].salary")));
            career.setJobDescription(multi.getParameter("careerList[" + idx + "].jobDescription"));
            careerList.add(career);
            idx++;
        }
        System.out.println("careerList : " + careerList);
// === 2. EducationList 가져오기 ===
        List<Academic> academicList = new ArrayList<>();
        idx = 0;
        while (multi.getParameter("educationList[" + idx + "].academicType") != null) {
            Academic academic = new Academic();
            academic.setAcademicType(multi.getParameter("educationList[" + idx + "].academicType"));
            academic.setAcademicName(multi.getParameter("educationList[" + idx + "].academicName"));
            academic.setGraduateStatus(multi.getParameter("educationList[" + idx + "].graduateStatus"));
            academic.setAcademicMajor(multi.getParameter("educationList[" + idx + "].academicMajor"));
            academic.setEntranceDate(multi.getParameter("educationList[" + idx + "].enterDate"));
            academic.setGraduateDate(multi.getParameter("educationList[" + idx + "].graduateDate"));
            academicList.add(academic);
            idx++;
        }
            freelancer.setAcademicList(academicList);


// === 3. LicenseList 가져오기 ===
        List<License> licenseList = new ArrayList<>();
        idx = 0;
        while (multi.getParameter("licenseList[" + idx + "].name") != null) {
            License license = new License();
            license.setLicenseName(multi.getParameter("licenseList[" + idx + "].name"));
            license.setLicenseGrade(multi.getParameter("licenseList[" + idx + "].licenseGrade"));
            license.setLicenseAgency(multi.getParameter("licenseList[" + idx + "].licenseAgency"));
            license.setLicenseDate(Date.valueOf(multi.getParameter("licenseList[" + idx + "].licenseDate")+"01"));
            licenseList.add(license);
            idx++;
        }
        freelancer.setLicenseList(licenseList);

// === 4. Skill 가져오기 ===
        String skillDescription = multi.getParameter("skillDescriptionHidden"); // "Java^Spring^MySQL"
// === 5. ExternalUrlList 가져오기 ===
        List<String> externalUrlList = new ArrayList<>();
        idx = 0;
        String externalUrl = "";
        while (multi.getParameter("externalUrlList[" + idx + "]") != null) {
            externalUrlList.add(multi.getParameter("externalUrlList[" + idx + "]"));
            externalUrl += multi.getParameter("externalUrlList[" + idx + "]")+"^";
            idx++;
        }
        System.out.println("externalUrl" + externalUrl);
        freelancer.setExternalUrlList(externalUrlList);
        if(externalUrlList.size() > 0){
            freelancer.setExternalUrl(externalUrl);
        }
// === 6. 첨부파일 가져오기 ===
        List<String> attachmentFileNames = new ArrayList<>();
        idx = 0;
        String attachment ="";
        while (true) {
            String fileFieldName = "attachmentList[" + idx + "]";
            String fileName = multi.getFilesystemName(fileFieldName); // 업로드된 실제 파일명
            if (fileName == null) break; // 더 이상 파일 없으면 종료
            attachmentFileNames.add(fileName);
            attachment += fileName+"^";
            idx++;
        }
            freelancer.setAcademicList(academicList);
            if(attachmentFileNames.size() > 0){
                freelancer.setAttachment(attachment);
            }*/
// 1. CareerList
            List<Career> careerList = new ArrayList<>();
            String careerListJson = multi.getParameter("careerListJson");
            if (careerListJson != null && !careerListJson.isEmpty()) {
                JSONArray careerArray = (JSONArray) parser.parse(careerListJson);
                for (Object obj : careerArray) {
                    JSONObject careerObj = (JSONObject) obj;
                    Career career = new Career();
                    career.setFreelancerId(freelancerId);
                    career.setCompanyName((String) careerObj.getOrDefault("companyName", ""));
                    career.setDepartmentName((String) careerObj.getOrDefault("departmentName", ""));
//                    career.setJoinDate(Date.valueOf(((String) careerObj.getOrDefault("joinDate", "")).replace("-", "") + "01"));
//                    career.setResignDate(Date.valueOf(((String) careerObj.getOrDefault("resignDate", "")).replace("-", "") + "01"));

                    // 입사일 세팅 (yyyy-MM → yyyy-MM-01로 변환)
                    String joinDateStr = (String) careerObj.getOrDefault("joinDate", "");
                    if (joinDateStr != null && !joinDateStr.isEmpty()) {
                        joinDateStr = joinDateStr + "-01"; // "2025-05" → "2025-05-01"
                        career.setJoinDate(Date.valueOf(joinDateStr));
                    }

                    // 퇴사일 세팅 (yyyy-MM → yyyy-MM-01로 변환)
                    String resignDateStr = (String) careerObj.getOrDefault("resignDate", "");
                    if (resignDateStr != null && !resignDateStr.isEmpty()) {
                        resignDateStr = resignDateStr + "-01"; // "2025-05" → "2025-05-01"
                        career.setResignDate(Date.valueOf(resignDateStr));
                    }
                    career.setPosition((String) careerObj.getOrDefault("position", ""));
                    career.setJobTitle((String) careerObj.getOrDefault("jobTitle", ""));
                    career.setSalary(Integer.parseInt(careerObj.getOrDefault("salary", "0").toString()));
                    career.setJobDescription((String) careerObj.getOrDefault("jobDescription", ""));
                    careerList.add(career);
                }
            }

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
                    if (enterDateStr != null && !enterDateStr.isEmpty()) {
                        enterDateStr = enterDateStr + "-01"; // "2025-05" → "2025-05-01"
                        academic.setEntranceDate(enterDateStr);
                    }

                    // 졸업일
                    String graduateDateStr = (String) eduObj.getOrDefault("graduateDate", "");
                    if (graduateDateStr != null && !graduateDateStr.isEmpty()) {
                        graduateDateStr = graduateDateStr + "-01";
                        academic.setGraduateDate(graduateDateStr);
                    }
                    educationList.add(academic);
                }
                freelancer.setAcademicList(educationList);
            }

            // 3. LicenseList
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
                    if (licenseDateStr != null && !licenseDateStr.isEmpty()) {
                        licenseDateStr = licenseDateStr + "-01"; // "2025-05" → "2025-05-01"
                        license.setLicenseDate(Date.valueOf(licenseDateStr));
                    }
                    licenseList.add(license);
                }
                freelancer.setLicenseList(licenseList);
            }

            // 4. ExternalUrlList
            List<String> externalUrlList = new ArrayList<>();
            String externalUrlListJson = multi.getParameter("externalUrlListJson");
            if (externalUrlListJson != null && !externalUrlListJson.isEmpty()) {
                JSONArray urlArray = (JSONArray) parser.parse(externalUrlListJson);
                for (Object url : urlArray) {
                    externalUrlList.add((String) url);
                }
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
                freelancer.setAttachment(String.join("^", attachmentList));
            }
            // --- 단일 데이터 수신 ---
// 보유 스킬
            String skillDescription = multi.getParameter("skillDescription"); // "Java^Spring^MySQL"
            System.out.println("skillDescription : "+skillDescription);
// 카테고리 ID (subCategory)
            String subCategoryId = multi.getParameter("subCategory");
            System.out.println("subCategoryId : "+subCategoryId);
// 희망 근무 조건
            String onsitePossible = multi.getParameter("onsitePossible"); // 'Y' or 'N'
            String negotiation = multi.getParameter("negotiation");       // 'Y' 체크 시 존재
            String desiredSalary = multi.getParameter("desiredSalary");   // 숫자
            String desiredLocation = multi.getParameter("desiredLocation"); // 텍스트
            String otherRequest = multi.getParameter("otherRequest");     // 텍스트

// --- freelancer 객체에 세팅 (예시) ---
            freelancer.setSkill(skillDescription);
            freelancer.setSubCategoryId(Integer.parseInt(subCategoryId));

            freelancer.setIsResident("Y".equals(onsitePossible)); // Y이면 true
            freelancer.setNegotiable("Y".equals(negotiation));  // 체크박스는 값이 넘어오면 true
            if (desiredSalary != null && !desiredSalary.isEmpty()) {
                freelancer.setDesiredSalary(Integer.parseInt(desiredSalary));
            }
            freelancer.setDesiredLocation(desiredLocation);
            freelancer.setOtherRequest(otherRequest);

            // 디버그 출력 (옵션)
            System.out.println("서블릿 147 프리렌서: " + freelancer);
            System.out.println("커리어: " + careerList);
             service.updateFreelancer(freelancer);
             service.updateCareer(careerList, freelancer.getFreelancerId());
            // 8. 처리 결과를 응답
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('전문가 정보가 성공적으로 저장되었습니다.');");
            out.println("location.href='/linkup/my-page/edit-expert-info';");
            out.println("</script>");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('프로필 정보 저장 중 오류가 발생했습니다. 다시 시도해주세요.');");
            out.println("history.back();");
            out.println("</script>");
        }
           response.sendRedirect(request.getContextPath() + "/my-page/edit-expert-info");
            //request.getRequestDispatcher("/freelancer/my_page_free_user_expert_info_edit.jsp").forward(request, response);
    }
}
