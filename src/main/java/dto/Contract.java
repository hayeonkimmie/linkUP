package dto;

import java.util.Date;

public class Contract {
    private String id;
    private int applyId;
    private int projectFeeId;
    private int projectId;
    private String freelancerId;
    private String clientId;
    private Date settleDay;
    private Date startDate;
    private Date endDate;
    private String pname;
    private Integer pay; // 금액 4/18 14:44 추가
    private int fee; // 수수료 금액의 0.3% 14:44 추가
    private String pmanager;
    private int totalPay;
    private String status;
    private String fphone;
    private String account;

    public Contract(String id, int applyId, int projectFeeId, int projectId, String freelancerId, String clientId, Date settleDay, Date startDate, Date endDate, String pname, int fee, String pmanager, int totalPay, String status, String fphone, String account) {
        this.id = id;
        this.applyId = applyId;
        this.projectFeeId = projectFeeId;
        this.projectId = projectId;
        this.freelancerId = freelancerId;
        this.clientId = clientId;
        this.settleDay = settleDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pname = pname;
        this.fee = fee;
        this.pmanager = pmanager;
        this.totalPay = totalPay;
        this.status = status;
        this.fphone = fphone;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getProjectFeeId() {
        return projectFeeId;
    }

    public void setProjectFeeId(int projectFeeId) {
        this.projectFeeId = projectFeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Date getSettleDay() {
        return settleDay;
    }

    public void setSettleDay(Date settleDay) {
        this.settleDay = settleDay;
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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getPmanager() {
        return pmanager;
    }

    public void setPmanager(String pmanager) {
        this.pmanager = pmanager;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
