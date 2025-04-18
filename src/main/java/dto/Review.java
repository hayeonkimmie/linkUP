package dto;

import java.util.Date;

public class Review {
    int review_id;
    int project_id;
    String w_user_id;
    String r_user_id;
    int star;
    String comment;
    Date create_date;

    public Review(int review_id, int project_id, String w_user_id, String r_user_id, int star, String comment, Date create_date) {
        this.review_id = review_id;
        this.project_id = project_id;
        this.w_user_id = w_user_id;
        this.r_user_id = r_user_id;
        this.star = star;
        this.comment = comment;
        this.create_date = create_date;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getW_user_id() {
        return w_user_id;
    }

    public void setW_user_id(String w_user_id) {
        this.w_user_id = w_user_id;
    }

    public String getR_user_id() {
        return r_user_id;
    }

    public void setR_user_id(String r_user_id) {
        this.r_user_id = r_user_id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}