package dto;

public class Level {
    private int levelId;
    private String level;
    private int startMonth;
    private int endMonth;

    public Level() {
    }

    public Level(int levelId, String level, int startMonth, int endMonth) {
        this.levelId = levelId;
        this.level = level;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    @Override
    public String toString() {
        return "Level{" +
                "levelId=" + levelId +
                ", level='" + level + '\'' +
                ", startMonth=" + startMonth +
                ", endMonth=" + endMonth +
                '}';
    }
}
