package dao.common;

import dto.Category;
import dto.SubCategory;

import java.util.List;

public interface ICategoryMenuDAO {
    List<Category> getAllCategories();
    List<SubCategory> getAllSubCategories();
}
