package dao.freelancer;

import dto.SubCategory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface SubCategoryMapper {
    List<SubCategory> selectSubCategoriesByCategoryId(int categoryId);
}
