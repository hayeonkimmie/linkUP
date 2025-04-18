package controller;

import dto.QnA;
import service.client.IQnAService;
import service.client.QnAServiceImpl;
import util.PageInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/inquiry")
public class ClientQnAController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClientQnAController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String pageStr = request.getParameter("page");
        Integer page = null;
        if(pageStr == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }

        PageInfo pageInfo = new PageInfo(page);
        IQnAService service = new QnAServiceImpl();
        try{
            List<QnA> qnaList = service.getQnAList(pageInfo);
            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("qnaList", qnaList);
            System.out.println(qnaList);
            System.out.println(pageInfo);
            request.getRequestDispatcher("./client/clientQnA.jsp").forward(request,response);
//            request.getRequestDispacher("./client/clientQnA.jsp").forward(request,response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
