package dto;

public class Pay {
    int project_fee_id;
    int project_id;
    int lv_id;
    int project_fee;

    public Pay(int project_fee_id, int project_id, int lv_id, int project_fee) {
        this.project_fee_id = project_fee_id;
        this.project_id = project_id;
        this.lv_id = lv_id;
        this.project_fee = project_fee;
    }

    public int getProject_fee_id() {
        return project_fee_id;
    }

    public void setProject_fee_id(int project_fee_id) {
        this.project_fee_id = project_fee_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getLv_id() {
        return lv_id;
    }

    public void setLv_id(int lv_id) {
        this.lv_id = lv_id;
    }

    public int getProject_fee() {
        return project_fee;
    }

    public void setProject_fee(int project_fee) {
        this.project_fee = project_fee;
    }
}
