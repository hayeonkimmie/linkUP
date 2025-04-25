package dto;
import java.sql.Date;
import java.util.Arrays;
import java.util.Map;

public class Portfolio {
    Integer portfolioId;
    String freelancerId;
    String title;
    String thumbnail;
    Date portProjStart;
    Date portProjEnd;
    String teamRole;
    String skillDescription;
    String introduce;
    Integer projectId;
    String attachment;
    String externalUrl;

    boolean isTempSaved;
    boolean isUserDeleted;
    boolean isDeleted;
    Integer priority;
    private String[] skillList;
    private String projectName;
    private String[] attachmentList;
    private String[] externalUrlList;
    Map<Integer, String> projectInfoMap;
    private Date createdDate;

    public Portfolio() {
        super();
    }

    public Portfolio(Integer portfolioId, String userId, String title, String thumbnail, Date portProjStart, Date portProjEnd, String teamRole, String skillDescription, String introduce, Integer projectId, String attachment, String externalUrl, boolean isTempSaved, boolean isUserDeleted, boolean isDeleted, Integer priority, String[] skillList, String projectName, String[] attachmentList, String[] externalUrlList, Map<Integer, String> projectInfoMap, Date createdDate) {
        this.portfolioId = portfolioId;
        this.freelancerId = userId;
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
        this.isTempSaved = isTempSaved;
        this.isUserDeleted = isUserDeleted;
        this.isDeleted = isDeleted;
        this.priority = priority;
        this.skillList = skillList;
        this.projectName = projectName;
        this.attachmentList = attachmentList;
        this.externalUrlList = externalUrlList;
        this.projectInfoMap = projectInfoMap;
        this.createdDate = createdDate;
    }

    public Integer getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Integer portfolioId) {
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void getsetCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Map<Integer, String> getProjectInfoMap() {
        return projectInfoMap;
    }

    public void setProjectInfoMap(Map<Integer, String> projectInfoMap) {
        this.projectInfoMap = projectInfoMap;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public boolean isTempSaved() {
        return isTempSaved;
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
                ", created_date=" + createdDate +
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
