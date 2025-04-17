package dto;

public class JjimProj {
    int jjim_id;
    int project_id;
    String freelancer_id;

    public JjimProj(int jjim_id, int project_id, String freelancer_id) {
        this.jjim_id = jjim_id;
        this.project_id = project_id;
        this.freelancer_id = freelancer_id;
    }

    public int getJjim_id() {
        return jjim_id;
    }

    public void setJjim_id(int jjim_id) {
        this.jjim_id = jjim_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getFreelancer_id() {
        return freelancer_id;
    }

    public void setFreelancer_id(String freelancer_id) {
        this.freelancer_id = freelancer_id;
    }
}
