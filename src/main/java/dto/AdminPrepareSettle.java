package dto;

import java.sql.Date;

public class AdminPrepareSettle {
    private String id;
    private String clientId;
    private String position;
    private String name;
    private Date settleDay;
    private Date startDate;
    private Date endDate;

    public AdminPrepareSettle() {
    }

    public AdminPrepareSettle(String id, String clientId, String position, String name, Date settleDay, Date startDate, Date endDate) {
        this.id = id;
        this.clientId = clientId;
        this.position = position;
        this.name = name;
        this.settleDay = settleDay;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdminPrepareSettle{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", position='" + position + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Date getSettleDay() {
        return settleDay;
    }

    public void setSettleDay(Date settleDay) {
        this.settleDay = settleDay;
    }
}
