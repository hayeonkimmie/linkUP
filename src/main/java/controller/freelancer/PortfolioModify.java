package controller.freelancer;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dto.Portfolio;
import service.freelancer.IPortfolioService;
import service.freelancer.PortfolioService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

@WebServlet("/my-page/portfolio-modify")
public class PortfolioModify extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PortfolioModify() {
        super();
    }

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            response.sendRedirect("/linkup/login");
            return;
        };
        Integer portfolioId = Integer.parseInt(request.getParameter("id"));
        System.out.println("portfolioId: " + portfolioId);
        IPortfolioService service = new PortfolioService();
        try {
            if (portfolioId == null) {
                request.setAttribute("err", "포트폴리오 ID가 없습니다.");
                request.getRequestDispatcher("/freelancer/my_page_main.jsp").forward(request, response);
            } else if(!service.isPortfolioOwner(freelancerId, portfolioId)) {
                request.setAttribute("err", "본인이 작성하지 않은 포트폴리오를 수정할 수 없습니다.");
                request.getRequestDispatcher("/freelancer/portfolio_list.jsp").forward(request, response);
            } else {
                Map<Integer, String> projectInfoMap = service.projectInfoForPortfolio(freelancerId);
                Portfolio portfolio = service.selectPortfolioById(portfolioId);
                request.setAttribute("projectInfoMap", projectInfoMap);
                portfolio.setProjectInfoMap(projectInfoMap);
                request.setAttribute("projectInfoMap", portfolio.getProjectInfoMap());
                request.setAttribute("portfolio", portfolio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Map<Integer, String> projectInfoMap = service.projectInfoForProtfolio(userId);*/
        request.getRequestDispatcher("/freelancer/portfolio_modify.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer portfolioId = Integer.parseInt(request.getParameter("id"));
        String path = request.getServletContext().getRealPath("upload"); // 업로드 폴더의 물리적 경로 가져오기
        System.out.println("파일 저장경로: = " + path);
        int size = 10 * 1024 * 1024;//10mb
        String freelancerId = (String) request.getSession().getAttribute("userId");
        MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());//파일이 저장되는 시점
        IPortfolioService service = new PortfolioService();
        Portfolio portfolio = new Portfolio();
        try {
            portfolio = service.selectPortfolioById(portfolioId);
            portfolio.setFreelancerId(freelancerId);
            portfolio.setTitle(multi.getParameter("title"));
            portfolio.setIntroduce(multi.getParameter("introduction"));
            portfolio.setTeamRole(multi.getParameter("teamRole"));

            String removedSkillsStr = multi.getParameter("removedSkills");
            String addedSkillsStr = multi.getParameter("addedSkills");
/*            String removedUrlsStr = multi.getParameter("removedUrls");
            String addedUrlsStr = multi.getParameter("addedUrls");*/
            String removedAttachmentsStr = multi.getParameter("removedAttachments");
            String addedAttachmentsStr = multi.getParameter("addedAttachments");
/*            String removedProjectIdsStr = multi.getParameter("removedProjectIds");
            String addedProjectIdsStr = multi.getParameter("addedProjectIds");*/
            System.out.println("removedSkillsStr: " + removedSkillsStr + " addedSkillsStr: "+addedSkillsStr+/*" removedUrlsStr: "+removedUrlsStr*/
                    /*+" addedUrlsStr: "+addedUrlsStr + */" removedAttachmentsStr: "+removedAttachmentsStr + " addedAttachmentsStr : "+addedAttachmentsStr/*+" addedProjectIdsStr "+addedProjectIdsStr*/);
// 스킬 처리
            String originalSkills = portfolio.getSkillDescription();
            System.out.println("87 기존 스킬 originalSkills " +originalSkills);
            List<String> updatedSkillList = PortfolioDataUpdater.updateList(originalSkills, removedSkillsStr, addedSkillsStr);
            portfolio.setSkillDescription(updatedSkillList.isEmpty() ? null : String.join("^", updatedSkillList));
/*// URL 처리
            String originalUrls = portfolio.getExternalUrl();
            System.out.println("93 기존 외부링크 originalUrls " +originalUrls);
            List<String> updatedUrlList = PortfolioDataUpdater.updateList(originalUrls, removedUrlsStr, addedUrlsStr);
            System.out.println("95 새로운 외부링크 updatedUrlList " +updatedUrlList);
            portfolio.setExternalUrl(updatedUrlList.isEmpty() ? null : String.join("^", updatedUrlList));*/
// 첨부파일 처리
            String originalAttachments = portfolio.getAttachment();
            System.out.println("100 기존 첨부파일 originalAttachments " +originalAttachments);
            List<String> updatedAttachmentList = PortfolioDataUpdater.updateList(originalAttachments, removedAttachmentsStr, addedAttachmentsStr);
            System.out.println("102 첨부파일 updatedAttachmentList " +updatedAttachmentList);
            portfolio.setAttachment(updatedAttachmentList.isEmpty() ? null : String.join("^", updatedAttachmentList));
/*// 프로젝트 ID (항상 하나)
            System.out.println("기존 프로젝트 아이디 getProjectId " +portfolio.getProjectId());
            String originalProjectIdStr = (portfolio.getProjectId() != null) ? String.valueOf(portfolio.getProjectId()) : null;
            Integer updatedProjectId = PortfolioDataUpdater.updateSingleProjectId(originalProjectIdStr, removedProjectIdsStr, addedProjectIdsStr);
            portfolio.setProjectId(updatedProjectId);*/

            String monthStr = multi.getParameter("portProjStart");
            String monthEnd = multi.getParameter("portProjEnd");
            Date startDate = null;
            Date endDate = null;
            if (monthEnd != null && !monthEnd.trim().isEmpty() && monthEnd.matches("\\d{4}-\\d{2}")) {
                try {
                    String fullDateStr = monthEnd + "-01";
                    endDate = Date.valueOf(fullDateStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("날짜 형식 오류: " + monthStr);
                    endDate = null;
                    e.printStackTrace();
                }
            }
            portfolio.setPortProjEnd(endDate);

//            String skillDesc = multi.getParameter("skillDescriptionHidden");
//            portfolio.setSkillDescription((skillDesc != null && skillDesc.contains("^")) ? skillDesc : null);
            String newThumbnail = multi.getFilesystemName("thumbnail");
            if (newThumbnail == null || newThumbnail.isEmpty()) {
                Portfolio original = service.selectPortfolioById(portfolioId);
                portfolio.setThumbnail(original.getThumbnail());
            } else {
                portfolio.setThumbnail(newThumbnail);
            }

           // 1) 외부 URL 동적 수집
            Enumeration<String> paramNames = multi.getParameterNames();
            List<String> urls = new ArrayList<>();
            if (urls != null) {
                while (paramNames.hasMoreElements()) {
                    String name = paramNames.nextElement();
                    if (name.startsWith("file-url-")) {
                        String url = multi.getParameter(name);
                        if (url != null && !url.trim().isEmpty()) {
                            urls.add(url);
                        }
                    }
                }
                if (!urls.isEmpty()) {
                    portfolio.setExternalUrl(String.join("^", urls));
                } else {
                    portfolio.setExternalUrl(null);
                }
            } else {
                portfolio.setExternalUrl(null);
            }

/*            // 2) 첨부파일 동적 수집
            List<String> attachment = new ArrayList<>();
            Enumeration<?> fileFields = multi.getFileNames();
            if (fileFields != null) {
                while (fileFields.hasMoreElements()) {
                    String field = (String) fileFields.nextElement();
                    if (field.startsWith("attachment-")) {
                        String saved = multi.getFilesystemName(field);
                        if (saved != null) {
                            attachment.add(saved);
                        }
                    } else {
                        continue;
                    }
                    portfolio.setAttachment(String.join("^", attachment));
                }
                if (!attachment.isEmpty()) {
                    portfolio.setAttachment(String.join("^", attachment));
                } else {
                    portfolio.setAttachment(null);
                }
            } else {
                portfolio.setAttachment(null);
            }
*/
            if (multi.getParameter("projectId") == null) {
                portfolio.setProjectId(null);
            } else {
                System.out.println("185"+Integer.parseInt(multi.getParameter("projectId")));
                portfolio.setProjectId(Integer.parseInt(multi.getParameter("projectId")));
            }
/*            if (multi.getParameter("tempSaved") != null && multi.getParameter("tempSaved").equals("true")) {
                portfolio.setTempSaved(true);
            } else {
                portfolio.setTempSaved(false);
            }*/
            System.out.println(portfolio);
            //service.modifyPortfolio(portfolio);
            service.selectPortfolioById(portfolio.getPortfolioId());
            response.sendRedirect(request.getContextPath() + "/my-page/portfolio-detail?id=" + portfolio.getPortfolioId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}