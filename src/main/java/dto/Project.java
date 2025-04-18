package dto;

import java.util.Date;

public class Project {
    int projectId;
    String clientId;
    String category;
    String advertisementTitle;
    String projectName;
    String thumbnail;
    int duration;
    Date createdDate;
    Date deadlineDate;
    String projectDescription;
    String jobDetails;
    String workingMethod;
    String workingEnvironment;
    String workingHours;
    String qualification;
    String preferentialConditions;
    Date settleDay;
    String manager;
    String mphone; // 이건 이미 카멜표기법에 맞음

    public Project() {

    }

    public Project(String mphone, String manager, Date settleDay, String preferentialConditions, String qualification, String workingHours, String workingEnvironment, String workingMethod, String jobDetails, String projectDescription, Date deadlineDate, Date createdDate, int duration, String thumbnail, String projectName, String advertisementTitle, String category, String clientId, int projectId) {
        this.mphone = mphone;
        this.manager = manager;
        this.settleDay = settleDay;
        this.preferentialConditions = preferentialConditions;
        this.qualification = qualification;
        this.workingHours = workingHours;
        this.workingEnvironment = workingEnvironment;
        this.workingMethod = workingMethod;
        this.jobDetails = jobDetails;
        this.projectDescription = projectDescription;
        this.deadlineDate = deadlineDate;
        this.createdDate = createdDate;
        this.duration = duration;
        this.thumbnail = thumbnail;
        this.projectName = projectName;
        this.advertisementTitle = advertisementTitle;
        this.category = category;
        this.clientId = clientId;
        this.projectId = projectId;
    }

    public Project(int projectId, String clientId, String category, String advertisementTitle, String projectName, String thumbnail, int duration, String projectDescription, String jobDetails, String workingMethod, String workingEnvironment, String workingHours, String qualification, String preferentialConditions, Date deadlineDate) {
        this.projectId = projectId;
        this.clientId = clientId;
        this.category = category;
        this.advertisementTitle = advertisementTitle;
        this.projectName = projectName;
        this.thumbnail = thumbnail;
        this.duration = duration;
        this.projectDescription = projectDescription;
        this.jobDetails = jobDetails;
        this.workingMethod = workingMethod;
        this.workingEnvironment = workingEnvironment;
        this.workingHours = workingHours;
        this.qualification = qualification;
        this.preferentialConditions = preferentialConditions;
        this.deadlineDate = deadlineDate;
    }
}