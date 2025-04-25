package dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class License {
    private String licenseName;
    private String licenseAgency;
    private Date licenseDate;
    private String licenseGrade;

    public License() {
        super();
    }

    public License(String licenseName, String licenseAgency, Date licenseDate, String licenseGrade) {
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

    public Date getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
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

    @Override
    public String toString() {
        return "License{" +
                "licenseName='" + licenseName + '\'' +
                ", licenseAgency='" + licenseAgency + '\'' +
                ", licenseDate=" + licenseDate +
                ", licenseGrade='" + licenseGrade + '\'' +
                '}';
    }
}
