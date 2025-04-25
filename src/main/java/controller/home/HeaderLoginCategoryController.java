package controller.home;

import dto.Category;
import service.common.CategoryMenuServiceImpl;
import service.common.ICategoryMenuService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/headerLogin")
public class HeaderLoginCategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ICategoryMenuService categoryService = new CategoryMenuServiceImpl();
    public HeaderLoginCategoryController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAllCategoriesWithSub();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/home/headerLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
