package dto;

import java.sql.Date;

public class ClientApply {
    private Integer applyId;
    private String freelancerId;
    private Integer projectId;
    private Date applyDate;
    private Date cancelDate;
    private Boolean isApproved;
    private Date approvalChangeDate;
    private Integer projectPayId;
    private String subCategoryName;

    public ClientApply() {
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
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

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Date getApprovalChangeDate() {
        return approvalChangeDate;
    }

    public void setApprovalChangeDate(Date approvalChangeDate) {
        this.approvalChangeDate = approvalChangeDate;
    }

    public Integer getProjectPayId() {
        return projectPayId;
    }

    public void setProjectPayId(Integer projectPayId) {
        this.projectPayId = projectPayId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    @Override
    public String toString() {
        return "ClientApply{" +
                "applyId=" + applyId +
                ", freelancerId='" + freelancerId + '\'' +
                ", projectId=" + projectId +
                ", applyDate=" + applyDate +
                ", cancelDate=" + cancelDate +
                ", isApproved=" + isApproved +
                ", approvalChangeDate=" + approvalChangeDate +
                ", projectPayId=" + projectPayId +
                ", subCategoryName='" + subCategoryName + '\'' +
                '}';
    }
}
