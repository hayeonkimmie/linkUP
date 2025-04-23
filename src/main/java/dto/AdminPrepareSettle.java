package dto;

public class AdminPrepareSettle {
    private String id;
    private String clientId;
    private String position;
    private String name;

    public AdminPrepareSettle() {
    }

    public AdminPrepareSettle(String id, String clientId, String position, String name) {
        this.id = id;
        this.clientId = clientId;
        this.position = position;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdminPrepareSettle{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", position='" + position + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
