package controller.freelancer;

import com.google.gson.Gson;
import dto.SubCategory;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getSubCategories")
public class GetSubCategoriesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 파라미터 파싱
        String categoryIdStr = request.getParameter("categoryId");
        int categoryId;

        try {
            categoryId = Integer.parseInt(categoryIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 카테고리 ID");
            return;
        }

        List<SubCategory> subCategories;

        // MyBatis 세션 열기
        try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            // mapper.subCategory.xml 내 select 호출
            subCategories = sqlSession.selectList(
                    "mapper.subCategory.selectSubCategoriesByCategoryId", categoryId
            );
        }

        // 응답 JSON으로 반환
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(subCategories));
        out.close();
    }
}