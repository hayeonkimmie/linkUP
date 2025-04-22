package dto;

public class PrepareSettleJson {
    private String id;
    private String name;
    private String category;
    private int amount;
    private String settleDate;
    private String fid; // freelancer_id


    public PrepareSettleJson() {
    }

    public PrepareSettleJson(String id, String name, String category, int amount, String settleDate, String fid) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.settleDate = settleDate;
        this.fid = fid;
    }

    public String getFid() {
        return fid;
    }
    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    @Override
    public String toString() {
        return "PrepareSettleJson{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", settleDate='" + settleDate + '\'' +
                ", fid='" + fid + '\'' +
                '}';
    }
}
