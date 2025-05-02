package dto;

public class ClientSettleTarget {
    private String freelancerName;
    private String settlePeriod; // e.g., "2025-03-10 ~ 2025-04-09"
    private Integer pay;

    public ClientSettleTarget() {
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public String getSettlePeriod() {
        return settlePeriod;
    }

    public void setSettlePeriod(String settlePeriod) {
        this.settlePeriod = settlePeriod;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "ClientSettleTarget{" +
                "freelancerName='" + freelancerName + '\'' +
                ", settlePeriod='" + settlePeriod + '\'' +
                ", pay=" + pay +
                '}';
    }
}
