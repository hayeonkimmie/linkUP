package dto;

import java.util.Date;

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

    public Portfolio(int portfolio_id, String freelancer_id, String title, String thumbnail, Date port_proj_start, Date port_proj_end, String team_role, String skill_description, String introduce, int project_id, String attachment, String external_url, Date created_date, boolean is_temp_saved, boolean is_user_deleted, boolean is_deleted, int priority) {
        this.portfolio_id = portfolio_id;
        this.freelancer_id = freelancer_id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.port_proj_start = port_proj_start;
        this.port_proj_end = port_proj_end;
        this.team_role = team_role;
        this.skill_description = skill_description;
        this.introduce = introduce;
        this.project_id = project_id;
        this.attachment = attachment;
        this.external_url = external_url;
        this.created_date = created_date;
        this.is_temp_saved = is_temp_saved;
        this.is_user_deleted = is_user_deleted;
        this.is_deleted = is_deleted;
        this.priority = priority;
    }

    public int getPortfolio_id() {
        return portfolio_id;
    }

    public void setPortfolio_id(int portfolio_id) {
        this.portfolio_id = portfolio_id;
    }

    public String getFreelancer_id() {
        return freelancer_id;
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

    public boolean isIs_temp_saved() {
        return is_temp_saved;
    }

    public void setIs_temp_saved(boolean is_temp_saved) {
        this.is_temp_saved = is_temp_saved;
    }

    public boolean isIs_user_deleted() {
        return is_user_deleted;
    }

    public void setIs_user_deleted(boolean is_user_deleted) {
        this.is_user_deleted = is_user_deleted;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}