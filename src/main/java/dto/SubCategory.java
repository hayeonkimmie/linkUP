package dto;

public class SubCategory {
    int sub_category_id;
    int category_id;
    String sub_category_name;

    public SubCategory(int sub_category_id, int category_id, String sub_category_name) {
        this.sub_category_id = sub_category_id;
        this.category_id = category_id;
        this.sub_category_name = sub_category_name;
    }

    public int getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(int sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public void setSub_category_name(String sub_category_name) {
        this.sub_category_name = sub_category_name;
    }
}
