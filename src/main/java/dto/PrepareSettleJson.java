package dto;

public class PrepareSettleJson {
    private String id;
    private String name;
    private String category;
    private int amount;
    private String start;
    private String end;

    public PrepareSettleJson() {
    }

    public PrepareSettleJson(String id, String name, String category, int amount, String start, String end) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.start = start;
        this.end = end;
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
}
