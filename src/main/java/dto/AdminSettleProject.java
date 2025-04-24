package dto;

public class AdminSettleProject {
    private String contractId;
    private Integer projectId;
    private String clientId;
    private String companyName;
    private String projectName;
    private String projectDuration;
    private String projectManager;
    private String managerPhone;
    private Integer totalAmount;
    private Integer totalFee;
    private Integer totalSettlement;
    private Integer totalContracts;
    private Integer settledCount;
    private Integer participant;
    private Integer settleDate;
    private String clientStatus;
    private String settleStatus;

    public AdminSettleProject() {
    }

    public AdminSettleProject(String contractId, Integer projectId, String clientId, String companyName, String projectName, String projectDuration, String projectManager, String managerPhone, Integer totalAmount, Integer totalFee, Integer totalSettlement, Integer totalContracts, Integer settledCount, Integer participant, Integer settleDate, String clientStatus, String settleStatus) {
        this.contractId = contractId;
        this.projectId = projectId;
        this.clientId = clientId;
        this.companyName = companyName;
        this.projectName = projectName;
        this.projectDuration = projectDuration;
        this.projectManager = projectManager;
        this.managerPhone = managerPhone;
        this.totalAmount = totalAmount;
        this.totalFee = totalFee;
        this.totalSettlement = totalSettlement;
        this.totalContracts = totalContracts;
        this.settledCount = settledCount;
        this.participant = participant;
        this.settleDate = settleDate;
        this.clientStatus = clientStatus;
        this.settleStatus = settleStatus;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getTotalSettlement() {
        return totalSettlement;
    }

    public void setTotalSettlement(Integer totalSettlement) {
        this.totalSettlement = totalSettlement;
    }

    public Integer getTotalContracts() {
        return totalContracts;
    }

    public void setTotalContracts(Integer totalContracts) {
        this.totalContracts = totalContracts;
    }

    public Integer getSettledCount() {
        return settledCount;
    }

    public void setSettledCount(Integer settledCount) {
        this.settledCount = settledCount;
    }

    public Integer getParticipant() {
        return participant;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public Integer getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Integer settleDate) {
        this.settleDate = settleDate;
    }

    public String getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    @Override
    public String toString() {
        return "AdminSettleProject{" +
                "contractId='" + contractId + '\'' +
                ", projectId=" + projectId +
                ", clientId='" + clientId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectDuration='" + projectDuration + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalFee=" + totalFee +
                ", totalSettlement=" + totalSettlement +
                ", totalContracts=" + totalContracts +
                ", settledCount=" + settledCount +
                ", participant=" + participant +
                ", settleDate=" + settleDate +
                ", clientStatus='" + clientStatus + '\'' +
                ", settleStatus='" + settleStatus + '\'' +
                '}';
    }
}
