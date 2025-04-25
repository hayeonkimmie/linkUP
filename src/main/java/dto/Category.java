package dto;

import java.util.List;


public class Category {
    private int categoryId;
    private String categoryName;
    private List<SubCategory> subCategories;

    public Category(int categoryId, String categoryName, List<SubCategory> subCategories) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategories = subCategories;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public Category() {
        super();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }
}
