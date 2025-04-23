package dto;

import java.util.Date;

public class Review {
    Integer reviewId;
    Integer projectId;
    String wUserId;
    String projectName;
    String rUserId;
    String wUserInfo; // 이름
    String rUserInfo; // 이름
    String wUserProfileImg; // 이름
    String rUserProfileImg; // 이름
    Integer star;
    String comment;
    Date createDate;

    public Review() {
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getProjectId() {
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

    public String getwUserId() {
        return wUserId;
    }

    public void setwUserId(String wUserId) {
        this.wUserId = wUserId;
    }

    public String getrUserId() {
        return rUserId;
    }

    public void setrUserId(String rUserId) {
        this.rUserId = rUserId;
    }

    public String getrUserInfo() {
        return rUserInfo;
    }

    public void setrUserInfo(String rUserInfo) {
        this.rUserInfo = rUserInfo;
    }

    public String getrUserProfileImg() {
        return rUserProfileImg;
    }

    public void setrUserProfileImg(String rUserProfileImg) {
        this.rUserProfileImg = rUserProfileImg;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getwUserInfo() {
        return wUserInfo;
    }

    public void setwUserInfo(String wUserInfo) {
        this.wUserInfo = wUserInfo;
    }
    public String getwUserProfileImg() {
        return wUserProfileImg;
    }
    public void setwUserProfileImg(String wUserProfileImg) {
        this.wUserProfileImg = wUserProfileImg;
    }
    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", projectId=" + projectId +
                ", wUserId='" + wUserId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", rUserId='" + rUserId + '\'' +
                ", wUserInfo='" + wUserInfo + '\'' +
                ", rUserInfo='" + rUserInfo + '\'' +
                ", wUserProfileImg='" + wUserProfileImg + '\'' +
                ", rUserProfileImg='" + rUserProfileImg + '\'' +
                ", star=" + star +
                ", comment='" + comment + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}