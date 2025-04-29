package dto;

public class ClientAccountInfo {
    private String clientId; //구인자 ID
    private String bank;     //은행명
    private String accountNo;//계좌번호
    private String holder;   //예금주

    ClientAccountInfo() {}

    public ClientAccountInfo(String clientId, String bank, String accountNo, String holder) {
        this.clientId = clientId;
        this.bank = bank;
        this.accountNo = accountNo;
        this.holder = holder;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
