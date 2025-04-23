package dto;

public class PrepareSettleJson {
    private String id;         // 계약 ID
    private String fid;        // 프리랜서 ID
    private String name;       // 프리랜서 이름
    private String category;   // 구분 (예: 백엔드, 프론트엔드 등)
    private int amount;        // 정산 금액
    private String settleDate;
    private String account; // 정산일
    private String start;      // 정산 시작일
    private String end;        // 정산 종료일

    public PrepareSettleJson() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "PrepareSettleJson{" +
                "id='" + id + '\'' +
                ", fid='" + fid + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", settleDate='" + settleDate + '\'' +
                ", account='" + account + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
