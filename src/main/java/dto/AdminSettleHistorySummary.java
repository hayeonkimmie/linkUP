package dto;

public class AdminSettleHistorySummary {
    private int projectId;
    private String projectName;
    private String settleDate;
    private int cnt;
    private int totalAmount;
    private String status;

    public AdminSettleHistorySummary() {
    }

    public AdminSettleHistorySummary(int projectId, String projectName, String settleDate, int cnt, int totalAmount, String status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.settleDate = settleDate;
        this.cnt = cnt;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
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

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
