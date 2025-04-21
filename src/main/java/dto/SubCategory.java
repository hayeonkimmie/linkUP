package dto;

public class SubCategory {
    int subCategoryId;
    int categoryId;
    String categoryName;
    String subCategoryName;

    public SubCategory() {
        super();
    }
    public SubCategory( int categoryId, int subCategoryId, String categoryName, String subCategoryName) {
        this.subCategoryId = subCategoryId;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.subCategoryName = subCategoryName;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
