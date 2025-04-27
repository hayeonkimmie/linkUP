package dto;

public class AdminSettleHistory {
    private Integer slistId;             // 정산 리스트 ID
    private Integer projectId;           // 프로젝트 ID
    private Integer cnt;                    // 정산 회차
    private String projectName;         // 프로젝트명
    private String freelancerName;      // 프리랜서 이름
    private String position;            // 직무
    private Integer amount;                 // 정산 금액
    private String account;             // 계좌번호
    private String startDate;           // 정산 시작일
    private String endDate;             // 정산 종료일
    private String settleDate;          // 실제 정산일
    private String status;              // 정산 상태

    public AdminSettleHistory() {}

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

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AdminSettleHistory{" +
                "slistId=" + slistId +
                ", projectId=" + projectId +
                ", cnt=" + cnt +
                ", projectName='" + projectName + '\'' +
                ", freelancerName='" + freelancerName + '\'' +
                ", position='" + position + '\'' +
                ", amount=" + amount +
                ", account='" + account + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", settleDate='" + settleDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
