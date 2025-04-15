package dto;

import java.sql.Date;

public class PrepareSettlement {
    String projectName; // 프로젝트 명
    String projectDuration; // 프로젝트 기간
    String projectManager; // 프로젝트 담당자
    String managerPhone; // 담당자 전화번호
    Integer totalAmount; // 총 금액
    Integer totalFee; // 총 수수료
    Integer totalSettlement; // 총 결제 금액
    Integer participant; // 참여자 수
    Integer settleDate; // 정산일
    String settleStatus; // 정산 상태

    public PrepareSettlement() {
    }

    public PrepareSettlement(String projectName, String projectDuration, String projectManager, String managerPhone, Integer totalAmount, Integer totalFee, Integer totalSettlement, Integer participant, Integer settleDate, String settleStatus) {
        this.projectName = projectName;
        this.projectDuration = projectDuration;
        this.projectManager = projectManager;
        this.managerPhone = managerPhone;
        this.totalAmount = totalAmount;
        this.totalFee = totalFee;
        this.totalSettlement = totalSettlement;
        this.participant = participant;
        this.settleDate = settleDate;
        this.settleStatus = settleStatus;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getTotalSettlement() {
        return totalSettlement;
    }

    public void setTotalSettlement(Integer totalSettlement) {
        this.totalSettlement = totalSettlement;
    }

    public Integer getParticipant() {
        return participant;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public Integer getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Integer settleDate) {
        this.settleDate = settleDate;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }
}
