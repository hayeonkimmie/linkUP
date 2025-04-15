package dto;


/**
 * * 프로젝트 정보를 담는 DTO 클래스<br>
 *
 */
public class Project {
    private Integer projectId;           // 프로젝트 ID (추가 시 편리)
    private String projectName;          // 프로젝트명
    private String projectDescription;   // 설명
    private String createdDate;          // 프로젝트 등록일
    private String projectEndDate;       // 모집 마감일
    private String projectStatus;        // 진행 상태
    private String projectManager;       // 담당자
    private String managerPhone;         // 담당자 전화번호
    private Integer totalAmount;         // 총 금액
    private Integer totalFee;            // 총 수수료
    private Integer totalSettlement;     // 총 결제 금액
    private Integer participant;         // 참여자 수
    private Integer settleDate;          // 정산일 (ex: 30)
    private String settleStatus;         // 정산 상태

    /**
     * 기본 생성자<br>
     * DTO 객체를 생성할 때 사용
     */
    public Project() {}



    /**
     * /admin/admin_dashboard.jsp 페이지에서 보여줄 프로젝트 정보에 사용되는 생성자<br>
     * @param projectId 프로젝트 ID
     * @param projectName 프로젝트명
     * @param createdDate 등록일
     * @param totalAmount 총 금액
     * @param projectStatus 진행 상태
     * @param settleStatus 정산 상태
     */
    public Project(Integer projectId, String projectName, String createdDate, Integer totalAmount, String projectStatus, String projectManager,String settleStatus) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.createdDate = createdDate;
        this.totalAmount = totalAmount;
        this.projectStatus = projectStatus;
        this.projectManager = projectManager;
        this.settleStatus = settleStatus;
    }

    /**
     * /admin/project_list.jsp 페이지에서 사용되는 생성자<br>
     * @param projectName 프로젝트명
     * @param projectDescription 설명
     * @param projectEndDate 종료일
     * @param projectStatus 진행 상태
     * @param projectManager 담당자
     * @param managerPhone 담당자 전화번호
     * @param totalAmount 총 금액
     * @param totalFee 총 수수료
     * @param totalSettlement 총 결제 금액
     * @param participant 참여자 수
     * @param settleDate 정산일
     * @param settleStatus 정산 상태
     */
    public Project(String projectName, String projectDescription, String createdDate, String projectEndDate,
                   String projectStatus, String projectManager, String managerPhone, Integer totalAmount,
                   Integer totalFee, Integer totalSettlement, Integer participant, Integer settleDate, String settleStatus) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.createdDate = createdDate;
        this.projectEndDate = projectEndDate;
        this.projectStatus = projectStatus;
        this.projectManager = projectManager;
        this.managerPhone = managerPhone;
        this.totalAmount = totalAmount;
        this.totalFee = totalFee;
        this.totalSettlement = totalSettlement;
        this.participant = participant;
        this.settleDate = settleDate;
        this.settleStatus = settleStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getProjectDescription() { return projectDescription; }
    public void setProjectDescription(String projectDescription) { this.projectDescription = projectDescription; }

    public String getProjectEndDate() { return projectEndDate; }
    public void setProjectEndDate(String projectEndDate) { this.projectEndDate = projectEndDate; }

    public String getProjectStatus() { return projectStatus; }
    public void setProjectStatus(String projectStatus) { this.projectStatus = projectStatus; }

    public String getProjectManager() { return projectManager; }
    public void setProjectManager(String projectManager) { this.projectManager = projectManager; }

    public String getManagerPhone() { return managerPhone; }
    public void setManagerPhone(String managerPhone) { this.managerPhone = managerPhone; }

    public Integer getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Integer totalAmount) { this.totalAmount = totalAmount; }

    public Integer getTotalFee() { return totalFee; }
    public void setTotalFee(Integer totalFee) { this.totalFee = totalFee; }

    public Integer getTotalSettlement() { return totalSettlement; }
    public void setTotalSettlement(Integer totalSettlement) { this.totalSettlement = totalSettlement; }

    public Integer getParticipant() { return participant; }
    public void setParticipant(Integer participant) { this.participant = participant; }

    public Integer getSettleDate() { return settleDate; }
    public void setSettleDate(Integer settleDate) { this.settleDate = settleDate; }

    public String getSettleStatus() { return settleStatus; }
    public void setSettleStatus(String settleStatus) { this.settleStatus = settleStatus; }
}
