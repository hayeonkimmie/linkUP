package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Academic {
    private String academicType;
    private boolean quit;
    private String academicName;
    private String academicMajor;
    private String entranceDate;
    private String graduateDate;

    public Academic() {
        super();
    }

    public Academic(String academicType, boolean quit, String academicName, String academicMajor, String entranceDate, String graduateDate) {
        this.academicType = academicType;
        this.quit = quit;
        this.academicName = academicName;
        this.academicMajor = academicMajor;
        this.entranceDate = entranceDate;
        this.graduateDate = graduateDate;
    }

    public String getAcademicType() {
        return academicType;
    }

    public void setAcademicType(String academicType) {
        this.academicType = academicType;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
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
}
