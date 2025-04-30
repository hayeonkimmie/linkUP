package controller.freelancer;

import dto.QnA;
import service.freelancer.IQnAService;
import service.freelancer.QnAService;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-page/qna-list")
public class MyPageQNAList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyPageQNAList() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String freelancerId = (String) request.getSession().getAttribute("userId");
        if (freelancerId == null) {
            response.sendRedirect("/linkup/login");
        };
        System.out.println("freelancerId = "+freelancerId);
        String pageStr = request.getParameter("page");
        System.out.println("page = " + pageStr);
        Integer page = null;
        if(pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        System.out.println("page = " + page);
        PageInfo pageInfo = new PageInfo(page);
        IQnAService service = new QnAService();
        List<QnA> QnAList;
        try {
            Integer QnACnt = service.selectQnACnt(freelancerId);
            System.out.println("QnACnt = " + QnACnt);
            if (QnACnt > 0) {
                QnAList = service.selectQnAListByPage(pageInfo, freelancerId);
                System.out.println("MyPageQNAList 43 QnAList = " + QnAList);
                request.setAttribute("pageInfo", pageInfo);
                request.setAttribute("QnAList", QnAList);
            } else if (QnACnt == 0){
                request.setAttribute("QnAList", null);
            }
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "문의내역 조회에 실패했습니다.");
        }

        request.getRequestDispatcher("/freelancer/qna_list.jsp").forward(request, response);
    }
}