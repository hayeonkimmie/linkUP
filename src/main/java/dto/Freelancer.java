package dto;

public class Freelancer {
    String freelancer_id;
    String category;
    String skill;
    String address;
    String academic;
    String introduction;
    String license;
    String bank;
    String account_num;
    boolean is_negotiable;
    boolean is_resident;
    int desired_salary;
    String desired_location;
    String other_request;
    String attachment;
    String external_url;

    public Freelancer(String freelancer_id, String category, String skill, String address, String academic, String introduction, String license, String bank, String account_num, boolean is_negotiable, boolean is_resident, int desired_salary, String desired_location, String other_request, String attachment, String external_url) {
        this.freelancer_id = freelancer_id;
        this.category = category;
        this.skill = skill;
        this.address = address;
        this.academic = academic;
        this.introduction = introduction;
        this.license = license;
        this.bank = bank;
        this.account_num = account_num;
        this.is_negotiable = is_negotiable;
        this.is_resident = is_resident;
        this.desired_salary = desired_salary;
        this.desired_location = desired_location;
        this.other_request = other_request;
        this.attachment = attachment;
        this.external_url = external_url;
    }

    public String getFreelancer_id() {
        return freelancer_id;
    }

    public void setFreelancer_id(String freelancer_id) {
        this.freelancer_id = freelancer_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount_num() {
        return account_num;
    }

    public void setAccount_num(String account_num) {
        this.account_num = account_num;
    }

    public boolean isIs_negotiable() {
        return is_negotiable;
    }

    public void setIs_negotiable(boolean is_negotiable) {
        this.is_negotiable = is_negotiable;
    }

    public boolean isIs_resident() {
        return is_resident;
    }

    public void setIs_resident(boolean is_resident) {
        this.is_resident = is_resident;
    }

    public int getDesired_salary() {
        return desired_salary;
    }

    public void setDesired_salary(int desired_salary) {
        this.desired_salary = desired_salary;
    }

    public String getDesired_location() {
        return desired_location;
    }

    public void setDesired_location(String desired_location) {
        this.desired_location = desired_location;
    }

    public String getOther_request() {
        return other_request;
    }

    public void setOther_request(String other_request) {
        this.other_request = other_request;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getExternal_url() {
        return external_url;
    }

    public void setExternal_url(String external_url) {
        this.external_url = external_url;
    }
}
