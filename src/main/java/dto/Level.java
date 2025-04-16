package dto;

public class Level {
    int level_id;
    String level;
    int start_month;
    int end_month;

    public Level(int level_id, String level, int start_month, int end_month) {
        this.level_id = level_id;
        this.level = level;
        this.start_month = start_month;
        this.end_month = end_month;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getStart_month() {
        return start_month;
    }

    public void setStart_month(int start_month) {
        this.start_month = start_month;
    }

    public int getEnd_month() {
        return end_month;
    }

    public void setEnd_month(int end_month) {
        this.end_month = end_month;
    }
}
