package dto;

public class Ammount {
    private Integer ammountId;
    private Integer projectId;
    private Integer total;
    private Integer fee;

    public Ammount() {
        super();
    }

    public Ammount(Integer ammountId, Integer projectId, Integer total, Integer fee) {
        this.ammountId = ammountId;
        this.projectId = projectId;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getAmmountId() {
        return ammountId;
    }

    public void setAmmountId(Integer ammountId) {
        this.ammountId = ammountId;
    }
}
