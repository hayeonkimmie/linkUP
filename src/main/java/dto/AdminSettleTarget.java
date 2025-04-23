package dto;

import java.sql.Date;

public class AdminSettleTarget {
    private String id;       // 계약 ID
    private String freelancerId;     // 프리랜서 ID
    private String freelancerName;   // 프리랜서 이름 (user.name)
    private String categoryName;            // 카테고리명 (pay.category_name)
    private Date startDate;          // 참여 시작일
    private Date endDate;            // 참여 종료일
    private String fphone;           // 전화번호
    private String account;          // 계좌
    private int totalPay;            // 총 지급 금액
    private Integer settleDay;          // 정산일

    public AdminSettleTarget() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }


    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
    }

    public Integer getSettleDay() {
        return settleDay;
    }

    public void setSettleDay(Integer settleDay) {
        this.settleDay = settleDay;
    }

    @Override
    public String toString() {
        return "AdminSettleTarget{" +
                "id='" + id + '\'' +
                ", freelancerId='" + freelancerId + '\'' +
                ", freelancerName='" + freelancerName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fphone='" + fphone + '\'' +
                ", account='" + account + '\'' +
                ", totalPay=" + totalPay +
                ", settleDay=" + settleDay +
                '}';
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
