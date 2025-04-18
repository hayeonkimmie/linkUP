package dto;

public class Pay {
    private int projectFeeId;
    private int projectId;
    private int lvId;
    private int projectFee;

    public Pay(int projectFeeId, int projectId, int lvId, int projectFee) {
        this.projectFeeId = projectFeeId;
        this.projectId = projectId;
        this.lvId = lvId;
        this.projectFee = projectFee;
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

    public int getLvId() {
        return lvId;
    }

    public void setLvId(int lvId) {
        this.lvId = lvId;
    }

    public int getProjectFee() {
        return projectFee;
    }

    public void setProjectFee(int projectFee) {
        this.projectFee = projectFee;
    }
}
