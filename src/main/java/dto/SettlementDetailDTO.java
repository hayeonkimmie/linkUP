package dto;

import java.sql.Date;

public class SettlementDetailDTO {
    private Integer cnt;
    private Integer settleAmmount;
    private String status;
    private Date settleDate;

    public SettlementDetailDTO() {
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Integer getSettleAmmount() {
        return settleAmmount;
    }

    public void setSettleAmmount(Integer settleAmmount) {
        this.settleAmmount = settleAmmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    @Override
    public String toString() {
        return "SettlementDetailDTO{" +
                "cnt=" + cnt +
                ", settleAmmount=" + settleAmmount +
                ", status='" + status + '\'' +
                ", settleDate=" + settleDate +
                '}';
    }
}
