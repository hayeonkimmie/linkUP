package dto;
import java.sql.Date;
import java.util.Arrays;

public class Portfolio {
    int portfolioId;
    String freelancerId;
    String title;
    String thumbnail;
    Date portProjStart;
    Date portProjEnd;
    String teamRole;
    String skillDescription;
    String introduce;
    int projectId;
    String attachment;
    String externalUrl;
    Date created_date;
    boolean isTempSaved;
    boolean isUserDeleted;
    boolean isDeleted;
    int priority;
    private String[] skillList;
    private String projectName;
    private String[] attachmentList;
    private String[] externalUrlList;
    private Date createdDate;

    public Portfolio() {
        super();
    }
//    //작성 및 수정
    public Portfolio(Integer portfolioId, String freelancerId, String title, String thumbnail, String skillDescription, boolean isTempSaved, String introduce, Date createdDate) {
        this.portfolioId = portfolioId;
        this.freelancerId = freelancerId;
        this.title = title;
        this.thumbnail = thumbnail;
        this.skillDescription = skillDescription;
        this.isTempSaved = isTempSaved;
        this.introduce = introduce;
        this.createdDate = createdDate;
    }
//    //포폴 상세
    public Portfolio(Integer portfolioId,String freelancerId, String title, String thumbnail, Date portProjStart, Date portProjEnd, String teamRole, String skillDescription, String[] skillList, String introduce, Integer projectId, String projectName, String[] attachmentList, String[] externalUrlList, Date createdDate, boolean isTempSaved) {
        this.portfolioId = portfolioId;
        this.freelancerId = freelancerId;
        this.title = title;
        this.thumbnail = thumbnail;
        this.portProjStart = portProjStart;
        this.portProjEnd = portProjEnd;
        this.teamRole = teamRole;
        this.skillDescription = skillDescription;
        this.skillList = skillList;
        this.introduce = introduce;
        this.projectId = projectId;
        this.projectName = projectName;
        this.attachmentList = attachmentList;
        this.externalUrlList = externalUrlList;
        this.createdDate = createdDate;
        this.isTempSaved = isTempSaved;
    }
//
//    // 작성, 수정
    public Portfolio(Integer portfolioId, String freelancerId, String title, String thumbnail, Date portProjStart, Date portProjEnd, String teamRole, String skillDescription, String introduce, Integer projectId, String attachment, String externalUrl, Date createdDate, boolean isTempSaved) {
        this.portfolioId = portfolioId;
        this.freelancerId = freelancerId;
        this.title = title;
        this.thumbnail = thumbnail;
        this.portProjStart = portProjStart;
        this.portProjEnd = portProjEnd;
        this.teamRole = teamRole;
        this.skillDescription = skillDescription;
        this.introduce = introduce;
        this.projectId = projectId;
        this.attachment = attachment;
        this.externalUrl = externalUrl;
        this.createdDate = createdDate;
        this.isTempSaved = isTempSaved;
    }
//
//    //구직자 상세페이지용
    public Portfolio(String title, Integer portfolioId, Integer priority) {
        this.title = title;
        this.portfolioId = portfolioId;
        this.priority = priority;
    }


    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getPortProjStart() {
        return portProjStart;
    }

    public void setPortProjStart(Date portProjStart) {
        this.portProjStart = portProjStart;
    }

    public Date getPortProjEnd() {
        return portProjEnd;
    }

    public void setPortProjEnd(Date portProjEnd) {
        this.portProjEnd = portProjEnd;
    }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void getsetCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public boolean getIsTempSaved() {
        return isTempSaved;
    }

    public void setTempSaved(boolean tempSaved) {
        isTempSaved = tempSaved;
    }

    public boolean isUserDeleted() {
        return isUserDeleted;
    }

    public void setUserDeleted(boolean userDeleted) {
        isUserDeleted = userDeleted;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String[] getSkillList() {
        return skillList;
    }

    public void setSkillList(String[] skillList) {
        this.skillList = skillList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String[] getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(String[] attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String[] getExternalUrlList() {
        return externalUrlList;
    }

    public void setExternalUrlList(String[] externalUrlList) {
        this.externalUrlList = externalUrlList;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "portfolioId=" + portfolioId +
                ", freelancerId='" + freelancerId + '\'' +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", portProjStart=" + portProjStart +
                ", portProjEnd=" + portProjEnd +
                ", teamRole='" + teamRole + '\'' +
                ", skillDescription='" + skillDescription + '\'' +
                ", introduce='" + introduce + '\'' +
                ", projectId=" + projectId +
                ", attachment='" + attachment + '\'' +
                ", externalUrl='" + externalUrl + '\'' +
                ", created_date=" + created_date +
                ", isTempSaved=" + isTempSaved +
                ", isUserDeleted=" + isUserDeleted +
                ", isDeleted=" + isDeleted +
                ", priority=" + priority +
                ", skillList=" + Arrays.toString(skillList) +
                ", projectName='" + projectName + '\'' +
                ", attachmentList=" + Arrays.toString(attachmentList) +
                ", externalUrlList=" + Arrays.toString(externalUrlList) +
                ", createdDate=" + createdDate +
                '}';
    }
}
