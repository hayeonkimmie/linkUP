package dto;

public class JjimFree {
    int jjim_id;
    String client_id;
    String freelancer_id;

    public int getJjim_id() {
        return jjim_id;
    }

    public void setJjim_id(int jjim_id) {
        this.jjim_id = jjim_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getFreelancer_id() {
        return freelancer_id;
    }

    public void setFreelancer_id(String freelancer_id) {
        this.freelancer_id = freelancer_id;
    }

    public JjimFree(int jjim_id, String client_id, String freelancer_id) {
        this.jjim_id = jjim_id;
        this.client_id = client_id;
        this.freelancer_id = freelancer_id;


    }
}
