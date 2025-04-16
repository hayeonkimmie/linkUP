package dto;

public class Admin {
    private Long adminId;
    private String loginId;
    private String password;
    private String name;
    private String role;

    public Admin(Long adminId, String loginId, String password, String name, String role) {
        this.adminId = adminId;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public Long getAdminId() { return adminId; }
    public void setAdminId(Long adminId) { this.adminId = adminId; }

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
