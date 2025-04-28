package service.home;

import dao.home.CategoryDAOImpl;
import dao.home.ICategoryDAO;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService{
    private final ICategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Integer> findSubCategoryIdsByCategoryName(String categoryName) {
        return categoryDAO.findSubCategoryIdsByCategoryName(categoryName);
    }
    @Override
    public int findSubCategoryIdByName(String subCategoryName) {
        return categoryDAO.findSubCategoryIdByName(subCategoryName);
    }

}
