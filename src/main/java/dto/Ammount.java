package dto;

public class Ammount {
    private Integer ammount_id;
    private Integer project_id;
    private Integer total;
    private Integer fee;

    public Ammount() {
        super();
    }

    public Ammount(Integer ammount_id, Integer project_id, Integer total, Integer fee) {
        this.ammount_id = ammount_id;
        this.project_id = project_id;
        this.total = total;
        this.fee = fee;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getAmmount_id() {
        return ammount_id;
    }

    public void setAmmount_id(Integer ammount_id) {
        this.ammount_id = ammount_id;
    }
}
