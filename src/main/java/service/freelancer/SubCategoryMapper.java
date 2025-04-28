package service.freelancer;

import dto.SubCategory;

import java.util.List;

public interface SubCategoryMapper {
    List<SubCategory> selectSubCategoriesByCategoryId(int categoryId);
}
