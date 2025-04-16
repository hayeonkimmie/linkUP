package dto;

public class ChatRoom {
    private Long chatRoomId;
    private Long participantA;
    private Long participantB;
    private String createdAt;

    public ChatRoom(Long chatRoomId, Long participantA, Long participantB, String createdAt) {
        this.chatRoomId = chatRoomId;
        this.participantA = participantA;
        this.participantB = participantB;
        this.createdAt = createdAt;
    }

    public Long getChatRoomId() { return chatRoomId; }
    public void setChatRoomId(Long chatRoomId) { this.chatRoomId = chatRoomId; }

    public Long getParticipantA() { return participantA; }
    public void setParticipantA(Long participantA) { this.participantA = participantA; }

    public Long getParticipantB() { return participantB; }
    public void setParticipantB(Long participantB) { this.participantB = participantB; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
