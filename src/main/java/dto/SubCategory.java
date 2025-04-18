package dto;

public class SubCategory {
    int sub_categoryId;
    int categoryId;
    String subCategoryName;

    public SubCategory() {
        super();
    }
    public SubCategory(int sub_categoryId, int categoryId, String subCategoryName) {
        this.sub_categoryId = sub_categoryId;
        this.categoryId = categoryId;
        this.subCategoryName = subCategoryName;
    }

    public int getSub_categoryId() {
        return sub_categoryId;
    }

    public void setSub_categoryId(int sub_categoryId) {
        this.sub_categoryId = sub_categoryId;
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
}
