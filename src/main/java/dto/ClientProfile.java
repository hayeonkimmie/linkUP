package dto;

public class ClientProfile {
    private ClientUserInfo info;
    private ClientUserDetail detail;

    //기본 생성자
    public ClientProfile(){};

    public ClientUserInfo getInfo() {
        return info;
    }

    public void setInfo(ClientUserInfo info) {
        this.info = info;
    }

    public ClientUserDetail getDetail() {
        return detail;
    }

    public void setDetail(ClientUserDetail detail) {
        this.detail = detail;
    }
}
