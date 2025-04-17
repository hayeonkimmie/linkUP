package dto;
import java.sql.Date;
public class Portfolio {
    private Integer portfolioId;
    private String freelancerId;
    private String title;
    private String thumbnail;
    private Date portProjStart;
    private Date portProjEnd;
    private String teamRole;
    private String skillDescription;
    private String[] skillList;
    private String introduce;
    private Integer projectId;
    private String attachment;
    private String externalUrl;
    private Date createdDate;
    private boolean isTempSaved;
    private boolean isUserDeleted;
    private boolean isDeleted;
    private Integer priority;

    // Constructor
    public Portfolio() {
        super();
    }
//포폴 목록
    public Portfolio(Integer portfolioId, String freelancerId, String title, String thumbnail, String[] skillList, boolean isTempSaved, String introduce, Date createdDate) {
        this.portfolioId = portfolioId;
        this.freelancerId = freelancerId;
        this.title = title;
        this.thumbnail = thumbnail;
        this.skillList = skillList;
        this.isTempSaved = isTempSaved;
        this.introduce = introduce;
        this.createdDate = createdDate;
    }
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
    //포폴 상세&작성, 수정
    public Portfolio(Integer portfolioId, String freelancerId, String title, String thumbnail, Date portProjStart, Date portProjEnd, String teamRole, String skillDescription, String[] skillList, String introduce, Integer projectId, String attachment, String externalUrl, Date createdDate, boolean isTempSaved, boolean isUserDeleted, boolean isDeleted, Integer priority) {
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
        this.attachment = attachment;
        this.externalUrl = externalUrl;
        this.createdDate = createdDate;
        this.isTempSaved = isTempSaved;
        this.isUserDeleted = isUserDeleted;
        this.isDeleted = isDeleted;
    }

    //구직자 상세페이지용
    public Portfolio(String title, Integer portfolioId, Integer priority) {
        this.title = title;
        this.portfolioId = portfolioId;
        this.priority = priority;
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

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isTempSaved() {
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public String[] getSkillList() {
        return skillList;
    }
    public void setSkillList(String[] skillList) {
        this.skillList = skillList;
    }
}