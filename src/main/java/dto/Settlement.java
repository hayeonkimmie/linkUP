package dto;

import java.util.Date;

public class Settlement {
    private int settlementId;
    private int slistId;
    private int categoryId;
    private String clientId;
    private String pname;
    private int ammount;
    private Date startDate;
    private Date endDate;
    private int settleday;
    private String position;
    private String name;
    private String status;
    private String account;

    public Settlement() {
    }

    public Settlement( int slistId, int categoryId, String clientId, String pname, int ammount, Date startDate, Date endDate, int settleday, String position, String name, String status, String account) {
        this.slistId = slistId;
        this.categoryId = categoryId;
        this.clientId = clientId;
        this.pname = pname;
        this.ammount = ammount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.settleday = settleday;
        this.position = position;
        this.name = name;
        this.status = status;
        this.account = account;
    }

    public int getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(int settlementId) {
        this.settlementId = settlementId;
    }

    public int getSlistId() {
        return slistId;
    }

    public void setSlistId(int slistId) {
        this.slistId = slistId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
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

    public int getSettleday() {
        return settleday;
    }

    public void setSettleday(int settleday) {
        this.settleday = settleday;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
