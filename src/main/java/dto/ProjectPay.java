package dto;

public class ProjectPay {
    private String level; //초급, 중급, 고급
    private int fee; // 1인당 단가
    private int count; // 모집 인원


    public ProjectPay() {} // 기본 생성자

    public ProjectPay(String level, int fee, int count) {
        this.level = level;
        this.fee = fee;
        this.count = count;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
