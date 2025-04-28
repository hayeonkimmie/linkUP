package dto;

public class AdminSettleHistorySummary {
    private Integer slistId;
    private Integer projectId;
    private String projectName;
    private String settleDate;
    private Integer cnt;
    private Integer totalAmount;
    private String status;
    private Integer pay;
    private Integer fee;

    public AdminSettleHistorySummary() {
    }

    public AdminSettleHistorySummary(Integer slistId, Integer projectId, String projectName, String settleDate, Integer cnt, Integer totalAmount, String status, Integer pay, Integer fee) {
        this.slistId = slistId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.settleDate = settleDate;
        this.cnt = cnt;
        this.totalAmount = totalAmount;
        this.status = status;
        this.pay = pay;
        this.fee = fee;
    }

    public Integer getSlistId() {
        return slistId;
    }

    public void setSlistId(Integer slistId) {
        this.slistId = slistId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "AdminSettleHistorySummary{" +
                "slistId=" + slistId +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", settleDate='" + settleDate + '\'' +
                ", cnt=" + cnt +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", pay=" + pay +
                ", fee=" + fee +
                '}';
    }
}
