package dto;

public class Timesheet {
    private Long timesheetId;
    private Long contractId;
    private String workDate;
    private int hoursWorked;
    private String note;

    public Timesheet(Long timesheetId, Long contractId, String workDate, int hoursWorked, String note) {
        this.timesheetId = timesheetId;
        this.contractId = contractId;
        this.workDate = workDate;
        this.hoursWorked = hoursWorked;
        this.note = note;
    }

    public Long getTimesheetId() { return timesheetId; }
    public void setTimesheetId(Long timesheetId) { this.timesheetId = timesheetId; }

    public Long getContractId() { return contractId; }
    public void setContractId(Long contractId) { this.contractId = contractId; }

    public String getWorkDate() { return workDate; }
    public void setWorkDate(String workDate) { this.workDate = workDate; }

    public int getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(int hoursWorked) { this.hoursWorked = hoursWorked; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
