package dto;
import java.sql.Date;
import java.util.Arrays;

public class Portfolio {
    int portfolio_id;
    String freelancer_id;
    String title;
    String thumbnail;
    Date port_proj_start;
    Date port_proj_end;
    String team_role;
    String skill_description;
    String introduce;
    int project_id;
    String attachment;
    String external_url;
    Date created_date;
    boolean is_temp_saved;
    boolean is_user_deleted;
    boolean is_deleted;
    int priority;
    private String[] skillList;
    private String introduce;
    private Integer projectId;
    private String projectName;
    private String attachment;
    private String[] attachmentList;
    private String externalUrl;
    private String[] externalUrlList;
    private Date createdDate;
    private boolean isTempSaved;
    private boolean isDeleted;
    private Integer priority;

    public Portfolio() {
        this.portfolio_id = portfolio_id;
        this.freelancer_id = freelancer_id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.port_proj_start = port_proj_start;
        this.port_proj_end = port_proj_end;
        this.team_role = team_role;
        this.skill_description = skill_description;
        this.introduce = introduce;
        this.createdDate = createdDate;
    }
    //작성 및 수정
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
    //포폴 상세
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

    // 작성, 수정
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

    //구직자 상세페이지용
    public Portfolio(String title, Integer portfolioId, Integer priority) {
        this.title = title;
        this.portfolioId = portfolioId;
        this.priority = priority;
    }

    public int getPortfolio_id() {
        return portfolio_id;
    }

    public void setPortfolio_id(int portfolio_id) {
        this.portfolio_id = portfolio_id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancer_id(String freelancer_id) {
        this.freelancer_id = freelancer_id;
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

    public Date getPort_proj_start() {
        return port_proj_start;
    }

    public void setPort_proj_start(Date port_proj_start) {
        this.port_proj_start = port_proj_start;
    }

    public Date getPort_proj_end() {
        return port_proj_end;
    }

    public void setPort_proj_end(Date port_proj_end) {
        this.port_proj_end = port_proj_end;
    }

    public String getTeam_role() {
        return team_role;
    }

    public void setTeam_role(String team_role) {
        this.team_role = team_role;
    }

    public String getSkill_description() {
        return skill_description;
    }

    public void setSkill_description(String skill_description) {
        this.skill_description = skill_description;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getExternal_url() {
        return external_url;
    }

    public void setExternal_url(String external_url) {
        this.external_url = external_url;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public boolean getIsTempSaved() {
        return isTempSaved;
    }

    public void setIs_temp_saved(boolean is_temp_saved) {
        this.is_temp_saved = is_temp_saved;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPortfolioId() {
        return 0;
    }

    public String getSkillDescription() {
        return "";
    }

    public void setSkillList(String[] skillList) {
        this.skillList = skillList;
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

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
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
                ", skillList=" + Arrays.toString(skillList) +
                ", introduce='" + introduce + '\'' +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", attachment='" + attachment + '\'' +
                ", attachmentList=" + Arrays.toString(attachmentList) +
                ", externalUrl='" + externalUrl + '\'' +
                ", externalUrlList=" + Arrays.toString(externalUrlList) +
                ", createdDate=" + createdDate +
                ", isTempSaved=" + isTempSaved +
                ", isDeleted=" + isDeleted +
                ", priority=" + priority +
                '}';
    }

}
