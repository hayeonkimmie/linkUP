package dto;

import java.util.Date;

public class Project {
    int project_id;
    String client_id;
    String category;
    String advertisement_title;
    String project_name;
    String thumbnail;
    int duration;
    Date created_date;
    Date deadaline_date;
    String project_desciirption;
    String job_details;
    String working_method;
    String working_environment;
    String working_hours;
    String qualification;
    String preferential_conditions;
    Date settle_day;
    String manager;
    String mphone;

    public Project(int project_id, String client_id, String category, String advertisement_title, String project_name, String thumbnail, int duration, Date created_date, Date deadaline_date, String project_desciirption, String job_details, String working_method, String working_environment, String working_hours, String qualification, String preferential_conditions, Date settle_day, String manager, String mphone) {
        this.project_id = project_id;
        this.client_id = client_id;
        this.category = category;
        this.advertisement_title = advertisement_title;
        this.project_name = project_name;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.created_date = created_date;
        this.deadaline_date = deadaline_date;
        this.project_desciirption = project_desciirption;
        this.job_details = job_details;
        this.working_method = working_method;
        this.working_environment = working_environment;
        this.working_hours = working_hours;
        this.qualification = qualification;
        this.preferential_conditions = preferential_conditions;
        this.settle_day = settle_day;
        this.manager = manager;
        this.mphone = mphone;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdvertisement_title() {
        return advertisement_title;
    }

    public void setAdvertisement_title(String advertisement_title) {
        this.advertisement_title = advertisement_title;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getDeadaline_date() {
        return deadaline_date;
    }

    public void setDeadaline_date(Date deadaline_date) {
        this.deadaline_date = deadaline_date;
    }

    public String getProject_desciirption() {
        return project_desciirption;
    }

    public void setProject_desciirption(String project_desciirption) {
        this.project_desciirption = project_desciirption;
    }

    public String getJob_details() {
        return job_details;
    }

    public void setJob_details(String job_details) {
        this.job_details = job_details;
    }

    public String getWorking_method() {
        return working_method;
    }

    public void setWorking_method(String working_method) {
        this.working_method = working_method;
    }

    public String getWorking_environment() {
        return working_environment;
    }

    public void setWorking_environment(String working_environment) {
        this.working_environment = working_environment;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPreferential_conditions() {
        return preferential_conditions;
    }

    public void setPreferential_conditions(String preferential_conditions) {
        this.preferential_conditions = preferential_conditions;
    }

    public Date getSettle_day() {
        return settle_day;
    }

    public void setSettle_day(Date settle_day) {
        this.settle_day = settle_day;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }
}