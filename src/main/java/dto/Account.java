package dto;

public class Account {
    private Long accountId;
    private Long userId;
    private String bankName;
    private String accountNumber;
    private String holderName;

    public Account(Long accountId, Long userId, String bankName, String accountNumber, String holderName) {
        this.accountId = accountId;
        this.userId = userId;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.holderName = holderName;
    }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
}
