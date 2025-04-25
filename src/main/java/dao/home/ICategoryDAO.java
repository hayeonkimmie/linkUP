package dao.home;

import java.util.List;

public interface ICategoryDAO {
    List<Integer> findSubCategoryIdsByCategoryName(String categoryName);
}
