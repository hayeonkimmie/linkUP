package dto;
import java.time.LocalDate;
import java.util.Date;


public class Apply {
    private int applyId;
    private String freelancerId;
    private int projectId;
    private Date applyDate;
    private Date cancelDate;
    private boolean isApproved;
    private Date approvalChangeDate;
    private Integer subCategoryId; // 서브 카테고리 ID
    private String subCategoryName; // 서브 카테고리 이름

    public Apply(int applyId, String freelancerId, int projectId, Date applyDate, Date cancelDate, boolean isApproved) {
        this.applyId = applyId;
        this.freelancerId = freelancerId;
        this.projectId = projectId;
        this.applyDate = applyDate;
        this.cancelDate = cancelDate;
        this.isApproved = isApproved;
    }

    public Apply(int applyId, String freelancerId, int projectId, Date applyDate, Date cancelDate, boolean isApproved, Date approvalChangeDate) {
        this.applyId = applyId;
        this.freelancerId = freelancerId;
        this.projectId = projectId;
        this.applyDate = applyDate;
        this.cancelDate = cancelDate;
        this.isApproved = isApproved;
        this.approvalChangeDate = approvalChangeDate;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Date getApprovalChangeDate() {
        return approvalChangeDate;
    }

    public void setApprovalChangeDate(Date approvalChangeDate) {
        this.approvalChangeDate = approvalChangeDate;
    }
}
