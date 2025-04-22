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
        String userId = (String) request.getAttribute("userId");
        userId = "free002"; // 로그인 구현 이후 빼기
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
        IQnAService service = new QnAService();
        List<QnA> QnAList;
        try {
            Integer QnACnt = service.selectQnACnt(userId);
            if (QnACnt > 0) {
                QnAList = service.selectQnAListByPage(pageInfo, userId);
                System.out.println("MyPageQNAList 43 QnAList = " + QnAList);
                request.setAttribute("pageInfo", pageInfo);
                request.setAttribute("QnAList", QnAList);
            } else if (QnACnt == 0){
                request.setAttribute("QnAList", 0);
            }
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "문의내역 조회에 실패했습니다.");
        }

        request.getRequestDispatcher("/freelancer/qna_list.jsp").forward(request, response);
    }
}