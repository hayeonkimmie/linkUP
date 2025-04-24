package service.common;

import dto.Category;

import java.util.List;

public interface ICategoryMenuService {
    List<Category> getAllCategoriesWithSub();
}
