package dto;

public class Amount {
    private Integer amountId;
    private Integer projectId;
    private Integer total;
    private Integer fee;

    public Amount() {
        super();
    }

    public Amount(Integer amountId, Integer projectId, Integer total, Integer fee) {
        this.amountId = amountId;
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

    public Integer getAmountId() {
        return amountId;
    }

    public void setAmountId(Integer amountId) {
        this.amountId = amountId;
    }
}
