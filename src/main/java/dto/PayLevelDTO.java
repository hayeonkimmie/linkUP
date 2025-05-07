package dto;

public class PayLevelDTO {
    private Pay pay;
    private Level level;

    public PayLevelDTO(Pay pay, Level level) {
        this.pay = pay;
        this.level = level;
    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
