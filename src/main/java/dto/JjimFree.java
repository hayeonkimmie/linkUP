package dto;

public class JjimFree {
    private int jjimId;
    private String clientId;
    private String freelancerId;

    public int getJjimId() {
        return jjimId;
    }

    public void setJjimId(int jjimId) {
        this.jjimId = jjimId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public JjimFree(int jjimId, String clientId, String freelancerId) {
        this.jjimId = jjimId;
        this.clientId = clientId;
        this.freelancerId = freelancerId;
    }
}
