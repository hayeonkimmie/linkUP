package dto;

public class ClientProjectSummary {
    private Integer projectId;
    private String projectName;
    private Integer totalAmount;
    private String startDate;
    private String endDate;
    private Integer nextRound;

    public ClientProjectSummary() {}

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

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
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

    public Integer getNextRound() {
        return nextRound;
    }

    public void setNextRound(Integer nextRound) {
        this.nextRound = nextRound;
    }

    @Override
    public String toString() {
        return "ClientProjectSummary{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", totalAmount=" + totalAmount +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", nextRound=" + nextRound +
                '}';
    }
}
