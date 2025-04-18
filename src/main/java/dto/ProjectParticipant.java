package dto;

public class ProjectParticipant {
    private String participantName;
    private String participantRole;
    private String participantEmail;
    private String participantPhone;

    public ProjectParticipant() {
    }

    public ProjectParticipant(String participantName, String participantRole, String participantEmail, String participantPhone) {
        this.participantName = participantName;
        this.participantRole = participantRole;
        this.participantEmail = participantEmail;
        this.participantPhone = participantPhone;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getParticipantRole() {
        return participantRole;
    }

    public void setParticipantRole(String participantRole) {
        this.participantRole = participantRole;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public String getParticipantPhone() {
        return participantPhone;
    }

    public void setParticipantPhone(String participantPhone) {
        this.participantPhone = participantPhone;
    }
}
