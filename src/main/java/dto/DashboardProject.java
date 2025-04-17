package dto;

import java.sql.Date;

public class DashboardProject {
    private int projectId;
    private String projectName;
    private Integer totalPay;
    private Date createdDate;
    private String projectStatus;
    private String manager;
    private Date applyDate;
    private boolean isApproved;

    public DashboardProject() {
        super();
    }

    public DashboardProject(int projectId, String projectName, Integer totalPay, Date createdDate, String projectStatus, String manager, Date applyDate, boolean isApproved) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.totalPay = totalPay;
        this.createdDate = createdDate;
        this.projectStatus = projectStatus;
        this.manager = manager;
        this.applyDate = applyDate;
        this.isApproved = isApproved;
    }

    public Integer getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Integer totalPay) {
        this.totalPay = totalPay;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
