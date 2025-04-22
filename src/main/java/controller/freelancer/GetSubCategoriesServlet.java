package controller.freelancer;

import dto.SubCategory;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/my-page/getSubCategories")
public class GetSubCategoriesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
        SubCategoryMapper mapper = sqlSession.getMapper(SubCategoryMapper.class);
        List<SubCategory> subCategories = mapper.selectSubCategoriesByCategoryId(categoryId);
        sqlSession.close();

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        for (SubCategory sub : subCategories) {
            out.printf("<option value='%d'>%s</option>%n", sub.getSubCategoryId(), sub.getSubCategoryName());
        }
        out.close();
    }
}
