package dto;

public class Skill {
    private Long skillId;
    private Long freelancerId;
    private String name;
    private int level;

    public Skill(Long skillId, Long freelancerId, String name, int level) {
        this.skillId = skillId;
        this.freelancerId = freelancerId;
        this.name = name;
        this.level = level;
    }

    public Long getSkillId() { return skillId; }
    public void setSkillId(Long skillId) { this.skillId = skillId; }

    public Long getFreelancerId() { return freelancerId; }
    public void setFreelancerId(Long freelancerId) { this.freelancerId = freelancerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
}
