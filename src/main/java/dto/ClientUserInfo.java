package dto;

import java.sql.Date;

public class ClientUserInfo {
    private String name;
    private String userId;
    private String email;
    private Date registrationDate;
    private String phoneNumber;
    private String userType;

    public ClientUserInfo() {}

    public ClientUserInfo(String name, String userId, String email, Date registrationDate, String phoneNumber, String userType) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }



    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
}
