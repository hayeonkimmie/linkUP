package dto;


public class ProjectTag {
    private Long projectTagId;
    private Long projectId;
    private Long tagId;

    public ProjectTag(Long projectTagId, Long projectId, Long tagId) {
        this.projectTagId = projectTagId;
        this.projectId = projectId;
        this.tagId = tagId;
    }

    public Long getProjectTagId() { return projectTagId; }
    public void setProjectTagId(Long projectTagId) { this.projectTagId = projectTagId; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public Long getTagId() { return tagId; }
    public void setTagId(Long tagId) { this.tagId = tagId; }
}
