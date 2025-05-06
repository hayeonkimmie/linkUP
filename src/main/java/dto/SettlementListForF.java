package dto;

public class SettlementListForF {
    Integer settlementId;
    private String status;      // 정산 상태 (0: 대기중, 1: 완료 등)
    private Integer ammount;     // 회차별 금액 (주의: 오타 그대로 반영)
    private String settleDate;   // 정산일
    private Integer projectId;   // 프로젝트 ID
    private String projectName;  // 프로젝트 이름

    public SettlementListForF() {
    }

    public SettlementListForF(Integer projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public Integer getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(Integer settlementId) {
        this.settlementId = settlementId;
    }

    // getter, setter
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getAmmount() {
        return ammount;
    }
    public void setAmmount(Integer ammount) {
        this.ammount = ammount;
    }
    public String getSettleDate() {
        return settleDate;
    }
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
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

    @Override
    public String toString() {
        return "SettlementListForF{" +
                " status='" + status + '\'' +
                ", ammount=" + ammount +
                ", settleDate='" + settleDate + '\'' +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}