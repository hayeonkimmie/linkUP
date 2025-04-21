package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class License {
    private String licenseName;
    private String licenseAgency;
    private String licenseDate;
    private String licenseGrade;

    public License() {
        super();
    }

    public License(String licenseName, String licenseAgency, String licenseDate, String licenseGrade) {
        super();
        this.licenseName = licenseName;
        this.licenseDate = licenseDate;
        this.licenseGrade = licenseGrade;
        this.licenseAgency = licenseAgency;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(String licenseDate) {
        this.licenseDate = licenseDate;
    }

    public String getLicenseAgency() {
        return licenseAgency;
    }

    public void setLicenseAgency(String licenseAgency) {
        this.licenseAgency = licenseAgency;
    }

    public String getLicenseGrade() {
        return licenseGrade;
    }

    public void setLicenseGrade(String licenseGrade) {
        this.licenseGrade = licenseGrade;
    }
}
