package dto;

import java.util.Date;

public class Settlement {
    int slist_id;
    String contract_id;
    int category_id;
    String client_id;
    String pname;
    int pay;
    Date start_date;
    Date end_date;
    int cnt;

    public Settlement(int slist_id, String contract_id, int category_id, String client_id, String pname, int pay, Date start_date, Date end_date, int cnt) {
        this.slist_id = slist_id;
        this.contract_id = contract_id;
        this.category_id = category_id;
        this.client_id = client_id;
        this.pname = pname;
        this.pay = pay;
        this.start_date = start_date;
        this.end_date = end_date;
        this.cnt = cnt;
    }

    public int getSlist_id() {
        return slist_id;
    }

    public void setSlist_id(int slist_id) {
        this.slist_id = slist_id;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
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

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
