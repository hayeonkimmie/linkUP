package dto;

public class SettledInfoDTO {
    private String freelancerName;
    private String position;
    private int duration;
    private int settleAmount;
    private String status;
    private int cnt; // 회차
    private String startDate; // 근무 시작일
    private String endDate;   // 근무 종료일

    public SettledInfoDTO() {
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public void setFreelancerName(String freelancerName) {
        this.freelancerName = freelancerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(int settleAmount) {
        this.settleAmount = settleAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SettledInfoDTO{" +
                "freelancerName='" + freelancerName + '\'' +
                ", position='" + position + '\'' +
                ", duration=" + duration +
                ", settleAmount=" + settleAmount +
                ", status='" + status + '\'' +
                ", cnt=" + cnt +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
