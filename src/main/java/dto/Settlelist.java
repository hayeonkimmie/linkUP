package dto;

import java.util.Date;

public class Settlelist {
    int slistId;
    String contractId;
    int projectPayId;
    String clientId;
    String pname;
    int ammount;
    Date startDate;
    Date endDate;
    int cnt;

    public Settlelist() {
        super();
    }

    public Settlelist(String contractId, int projectPayId, String clientId, String pname, int ammount, Date startDate, Date endDate, int cnt) {
        this.contractId = contractId;
        this.projectPayId = projectPayId;
        this.clientId = clientId;
        this.pname = pname;
        this.ammount = ammount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cnt = cnt;
    }

    public int getSlistId() {
        return slistId;
    }

    public void setSlistId(int slistId) {
        this.slistId = slistId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public int getProjectPayId() {
        return projectPayId;
    }

    public void setProjectPayId(int projectPayId) {
        this.projectPayId = projectPayId;
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

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
