package dto;

public class Pay {
    private int projectFeeId;
    private int projectId;
    private int lvId;
    // pay 테이블은 어떤 카테고리의 어떤 포지션이 얼마인지 알아야되는데 목적에 맞지 않게 생성되어있음 컬럼 추가가 필요
    private Integer subCategoryId; // 서브 카테고리 ID 4/18 14:55 추가
    private String categoryName; // 서브 카테고리 이름 4/18 14:55 추가
    private int projectFee;

    public Pay() {
    }

    public Pay(int projectFeeId, int projectId, int lvId, Integer subCategoryId, String categoryName, int projectFee) {
        this.projectFeeId = projectFeeId;
        this.projectId = projectId;
        this.lvId = lvId;
        this.subCategoryId = subCategoryId;
        this.categoryName = categoryName;
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
}
