package controller.freelancer;

import dto.JjimProj;
import service.freelancer.IJjimProjService;
import service.freelancer.JjimProjService;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/my-page/project-jjim-list")
public class JJimProjList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public JJimProjList() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String freelancerId = request.getParameter("userId");
            /*if (freelancerId == null) {
                response.sendRedirect("/login");
            };*/
        freelancerId = "free002";
        String pageStr = request.getParameter("page");
        System.out.println("page = " + pageStr);
        Integer page = null;
        if(pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        System.out.println("39 page = " + page);
        PageInfo pageInfo = new PageInfo(page);
        IJjimProjService service = new JjimProjService();
        List<JjimProj> jjimProjList;
        try{
            Integer jjimProjCnt = service.selectJjimProjCnt(freelancerId);
            if (jjimProjCnt > 0) {
                jjimProjList = service.selectJjimProjListForMain(freelancerId);
                System.out.println("JJimProjList 서블릿 47 JJimProjList = " + jjimProjList);
                request.setAttribute("pageInfo", pageInfo);
                request.setAttribute("jjimProjList", jjimProjList);
            } else if (jjimProjCnt == 0){
                request.setAttribute("jjimProjList", null);
            }
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "찜한 프로젝트 조회에 실패했습니다.");
        }
        request.getRequestDispatcher("/freelancer/my_jjim_project.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedValues = request.getParameterValues("jjimId");
        // 찜한 프로젝트 삭제
        IJjimProjService service = new JjimProjService();
        if(selectedValues == null) {
            System.out.println("선택된 값이 없습니다.");
            return;
        } else if(selectedValues.length == 1) {
            Integer selectedJjimId = Integer.parseInt(selectedValues[0]);
            System.out.println("JJimProjList 서블릿 70 selectedJjimId = " + selectedJjimId);
            try {
                service.deleteJjimProjById(selectedJjimId);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("err", "찜한 프로젝트 삭제에 실패했습니다.");
            }
        } else {
            List<Integer> selectedJjimIdList = new ArrayList<>();
            if (selectedValues != null) {
                for (String v : selectedValues) {
                    try {
                        selectedJjimIdList.add(Integer.parseInt(v));
                        System.out.println("JJimProjList 서블릿 70 selectedJjimIdList = " + selectedJjimIdList);
                    } catch (NumberFormatException e) {
                        // 숫자가 아닌 값이 들어온 경우 예외 처리
                        System.err.println("숫자 변환 실패: " + v);
                    }
                }
            }
            for (Integer id : selectedJjimIdList) {
                System.out.println("선택된 찜 ID: " + id);
            }

            try {
                service.deleteJjimProjList(selectedJjimIdList);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("err", "찜한 프로젝트 삭제에 실패했습니다.");
            }
        }
        // 찜한 프로젝트 목록 재조회
        String freelancerId = request.getParameter("userId");
            /*if (freelancerId == null) {
                response.sendRedirect("/login");
            };*/
        try {
            freelancerId = "free002";
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
            List<JjimProj> jjimProjList;
            try{
                Integer jjimProjCnt = service.selectJjimProjCnt(freelancerId);
                if (jjimProjCnt > 0) {
                    jjimProjList = service.selectJjimProjListByPage(pageInfo, freelancerId);
                    System.out.println("JJimProjList 서블릿 123 JJimProjList = " + jjimProjList);
                    request.setAttribute("pageInfo", pageInfo);
                    request.setAttribute("jjimProjList", jjimProjList);
                } else if (jjimProjCnt == 0){
                    request.setAttribute("jjimProjList", null);
                }
            } catch(Exception e) {
                e.printStackTrace();
                request.setAttribute("err", "찜한 프로젝트 조회에 실패했습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/freelancer/my_jjim_project.jsp").forward(request, response);
    }
}