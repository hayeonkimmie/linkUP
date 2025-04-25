package dto;

public class Academic {
    private String academicType;
    private String academicName;
    private String academicMajor;
    private String entranceDate;
    private String graduateDate;
    private String graduateStatus;

    public Academic() {
        super();
    }

    public Academic(String academicType, String academicName, String academicMajor, String entranceDate, String graduateDate, String graduateStatus) {
        this.academicType = academicType;
        this.academicName = academicName;
        this.academicMajor = academicMajor;
        this.entranceDate = entranceDate;
        this.graduateDate = graduateDate;
        this.graduateStatus = graduateStatus;
    }

    public String getAcademicType() {
        return academicType;
    }

    public void setAcademicType(String academicType) {
        this.academicType = academicType;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName;
    }

    public String getAcademicMajor() {
        return academicMajor;
    }

    public void setAcademicMajor(String academicMajor) {
        this.academicMajor = academicMajor;
    }

    public String getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(String entranceDate) {
        this.entranceDate = entranceDate;
    }

    public String getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    public String getGraduateStatus() {
        return graduateStatus;
    }

    public void setGraduateStatus(String graduatStatus) {
        this.graduateStatus = graduatStatus;
    }

    @Override
    public String toString() {
        return "Academic{" +
                "academicType='" + academicType + '\'' +
                ", academicName='" + academicName + '\'' +
                ", academicMajor='" + academicMajor + '\'' +
                ", entranceDate='" + entranceDate + '\'' +
                ", graduateDate='" + graduateDate + '\'' +
                ", graduateStatus='" + graduateStatus + '\'' +
                '}';
    }
}
