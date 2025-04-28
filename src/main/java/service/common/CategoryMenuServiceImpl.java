package service.common;

import dao.common.CategoryMenuDAOImpl;
import dao.common.ICategoryMenuDAO;
import dto.Category;

import java.util.List;

public class CategoryMenuServiceImpl implements ICategoryMenuService {
    private final ICategoryMenuDAO categoryDAO = new CategoryMenuDAOImpl();

    @Override
    public List<Category> getAllCategoriesWithSub() {
        return categoryDAO.selectAllCategoriesWithSub();
    }
}
