package service.home;

import java.util.List;

public interface ICategoryService {
    List<Integer> findSubCategoryIdsByCategoryName(String categoryName);
    int findSubCategoryIdByName(String subCategoryName);
}
