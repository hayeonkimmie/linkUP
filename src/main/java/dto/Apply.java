package dto;
import java.time.LocalDate;
import java.util.Date;


public class Apply {
    int apply_id;
    String freelancer_id;
    String category;
    int project_id;
    Date apply_date;
    Date cancel_date;
    boolean is_approved;
    Date approval_change_date;

    public Apply(int apply_id, String freelancer_id, String category, int project_id, Date apply_date, Date cancel_date, boolean is_approved, Date approval_change_date) {
        this.apply_id = apply_id;
        this.freelancer_id = freelancer_id;
        this.category = category;
        this.project_id = project_id;
        this.apply_date = apply_date;
        this.cancel_date = cancel_date;
        this.is_approved = is_approved;
        this.approval_change_date = approval_change_date;
    }

    public int getApply_id() {
        return apply_id;
    }

    public void setApply_id(int apply_id) {
        this.apply_id = apply_id;
    }

    public String getFreelancer_id() {
        return freelancer_id;
    }

    public void setFreelancer_id(String freelancer_id) {
        this.freelancer_id = freelancer_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public Date getApply_date() {
        return apply_date;
    }

    public void setApply_date(Date apply_date) {
        this.apply_date = apply_date;
    }

    public Date getCancel_date() {
        return cancel_date;
    }

    public void setCancel_date(Date cancel_date) {
        this.cancel_date = cancel_date;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

    public Date getApproval_change_date() {
        return approval_change_date;
    }

    public void setApproval_change_date(Date approval_change_date) {
        this.approval_change_date = approval_change_date;
    }
}
