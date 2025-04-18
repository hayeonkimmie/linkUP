package dto;

import java.sql.Date;
import java.util.List;


public class AdminProjectDetail {
    private Integer projectId;
    private String projectName;
    private String projectDescription;
    private Date createdDate;
    private Date deadlineDate;

    private String manager;
    private String memail;
    private String mphone;
    private String clientName;

    private List<ProjectParticipant> participants;

    public AdminProjectDetail() {
    }

    public AdminProjectDetail(Integer projectId, String projectName, String projectDescription, Date createdDate, Date deadlineDate, String manager, String memail, String mphone, String clientName, List<ProjectParticipant> participants) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.createdDate = createdDate;
        this.deadlineDate = deadlineDate;
        this.manager = manager;
        this.memail = memail;
        this.mphone = mphone;
        this.clientName = clientName;
        this.participants = participants;
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

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<ProjectParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ProjectParticipant> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "AdminProjectDetail{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", createdDate=" + createdDate +
                ", deadlineDate=" + deadlineDate +
                ", manager='" + manager + '\'' +
                ", memail='" + memail + '\'' +
                ", mphone='" + mphone + '\'' +
                ", clientName='" + clientName + '\'' +
                ", participants=" + participants +
                '}';
    }
}
