package dto;

import java.util.Date;

public class Settlelist {
    int slistId;
    String contractId;
    int projectPayId;
    Integer projectId;
    String clientId;
    String pname;
    int ammount;
    Date settleDate;
    int cnt;

    public Settlelist() {
        super();
    }

    public Settlelist(String contractId, int projectPayId, Integer projectId, String clientId, String pname, int ammount, Date settleDate, int cnt) {
        this.contractId = contractId;
        this.projectPayId = projectPayId;
        this.projectId = projectId;
        this.clientId = clientId;
        this.pname = pname;
        this.ammount = ammount;
        this.settleDate = settleDate;
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

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Settlelist{" +
                "slistId=" + slistId +
                ", contractId='" + contractId + '\'' +
                ", projectPayId=" + projectPayId +
                ", projectId=" + projectId +
                ", clientId='" + clientId + '\'' +
                ", pname='" + pname + '\'' +
                ", ammount=" + ammount +
                ", settleDate=" + settleDate +
                ", cnt=" + cnt +
                '}';
    }
}
