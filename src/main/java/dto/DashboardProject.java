package dto;

import java.sql.Date;

public class DashboardProject {
    private int project_id;
    private String project_name;
    private Date created_date;
    private String project_status;
    private String manager;
    private Date apply_date;
    private boolean is_approved;

    public DashboardProject() {
        super();
    }

    public DashboardProject(int project_id, String project_name, Date created_date,
                          String project_status, String manager, Date apply_date, boolean is_approved) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.created_date = created_date;
        this.project_status = project_status;
        this.manager = manager;
        this.apply_date = apply_date;
        this.is_approved = is_approved;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Date getApply_date() {
        return apply_date;
    }

    public void setApply_date(Date apply_date) {
        this.apply_date = apply_date;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }
}
