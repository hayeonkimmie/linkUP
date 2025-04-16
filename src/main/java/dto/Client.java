package dto;

public class Client {
    String client_id;
    String company_reg_no;
    String company_phone_number;
    String company_address;
    String company_website_url;
    String company_description;
    String company_fax_num;


    public Client(String client_id, String company_reg_no, String company_phone_number, String company_address, String company_website_url, String company_description, String company_fax_num) {
        this.client_id = client_id;
        this.company_reg_no = company_reg_no;
        this.company_phone_number = company_phone_number;
        this.company_address = company_address;
        this.company_website_url = company_website_url;
        this.company_description = company_description;
        this.company_fax_num = company_fax_num;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getCompany_reg_no() {
        return company_reg_no;
    }

    public void setCompany_reg_no(String company_reg_no) {
        this.company_reg_no = company_reg_no;
    }

    public String getCompany_phone_number() {
        return company_phone_number;
    }

    public void setCompany_phone_number(String company_phone_number) {
        this.company_phone_number = company_phone_number;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_website_url() {
        return company_website_url;
    }

    public void setCompany_website_url(String company_website_url) {
        this.company_website_url = company_website_url;
    }

    public String getCompany_description() {
        return company_description;
    }

    public void setCompany_description(String company_description) {
        this.company_description = company_description;
    }

    public String getCompany_fax_num() {
        return company_fax_num;
    }

    public void setCompany_fax_num(String company_fax_num) {
        this.company_fax_num = company_fax_num;
    }
}
