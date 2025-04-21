package dto;

public class Pay {
    private int projectFeeId;
    private int projectId;
    private int lvId;
    // pay 테이블은 어떤 카테고리의 어떤 포지션이 얼마인지 알아야되는데 목적에 맞지 않게 생성되어있음 컬럼 추가가 필요
    private Integer subCategoryId; // 서브 카테고리 ID 4/18 14:55 추가
    private String categoryName; // 서브 카테고리 이름 4/18 14:55 추가
    private int projectFee;
    private Integer pay; // 금액 4/18 추가
    private int fee; // 수수료 금액의 0.3% 추가


    public Pay() {
        super();
    }

    public Pay(int projectFeeId, int projectId, int lvId, Integer subCategoryId, String categoryName, int projectFee, Integer pay, int fee) {
        this.projectFeeId = projectFeeId;
        this.projectId = projectId;
        this.lvId = lvId;
        this.subCategoryId = subCategoryId;
        this.categoryName = categoryName;
        this.projectFee = projectFee;
        this.pay = pay;
        this.fee = fee;
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

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getProjectFee() {
        return projectFee;
    }

    public void setProjectFee(int projectFee) {
        this.projectFee = projectFee;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
