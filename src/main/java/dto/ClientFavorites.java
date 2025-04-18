package dto;

public class ClientFavorites {
    private String jjimId;
    private String clientId;
    private String freelancerId;

    public ClientFavorites() {
        super();
    }

    public ClientFavorites(String jjimId, String clientId, String freelancerId) {
        this.jjimId = jjimId;
        this.clientId = clientId;
        this.freelancerId = freelancerId;
    }

    public String getJjimId() {
        return jjimId;
    }

    public void setJjimId(String jjimId) {
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
}
