package dto;

import java.util.Date;

public class Contract {
    String id;
    int apply_id;
    int project_fee_id;
    int project_id;
    String client_id;
    Date settle_day;
    Date start_date;
    Date end_date;
    String pname;
    int fee;
    String pmanager;
    int total_pay;
    String status;
    String fphone;
    String account;

    public Contract(String id, int apply_id, int project_fee_id, int project_id, String client_id, Date settle_day, Date start_date, Date end_date, String pname, int fee, String pmanager, int total_pay, String status, String fphone, String account) {
        this.id = id;
        this.apply_id = apply_id;
        this.project_fee_id = project_fee_id;
        this.project_id = project_id;
        this.client_id = client_id;
        this.settle_day = settle_day;
        this.start_date = start_date;
        this.end_date = end_date;
        this.pname = pname;
        this.fee = fee;
        this.pmanager = pmanager;
        this.total_pay = total_pay;
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

    public int getApply_id() {
        return apply_id;
    }

    public void setApply_id(int apply_id) {
        this.apply_id = apply_id;
    }

    public int getProject_fee_id() {
        return project_fee_id;
    }

    public void setProject_fee_id(int project_fee_id) {
        this.project_fee_id = project_fee_id;
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

    public Date getSettle_day() {
        return settle_day;
    }

    public void setSettle_day(Date settle_day) {
        this.settle_day = settle_day;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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

    public int getTotal_pay() {
        return total_pay;
    }

    public void setTotal_pay(int total_pay) {
        this.total_pay = total_pay;
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