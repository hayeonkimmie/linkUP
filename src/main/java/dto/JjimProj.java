package dto;

public class JjimProj {
    private int jjimId;
    private int projectId;
    private String freelancerId;

    public JjimProj(int jjimId, int projectId, String freelancerId) {
        this.jjimId = jjimId;
        this.projectId = projectId;
        this.freelancerId = freelancerId;
    }

    public int getJjimId() {
        return jjimId;
    }

    public void setJjimId(int jjimId) {
        this.jjimId = jjimId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }
}
