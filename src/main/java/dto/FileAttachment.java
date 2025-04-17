package dto;

public class FileAttachment {
    private Long fileId;
    private Long relatedId;
    private String fileName;
    private String fileUrl;
    private String uploadedAt;

    public FileAttachment(Long fileId, Long relatedId, String fileName, String fileUrl, String uploadedAt) {
        this.fileId = fileId;
        this.relatedId = relatedId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.uploadedAt = uploadedAt;
    }

    public Long getFileId() { return fileId; }
    public void setFileId(Long fileId) { this.fileId = fileId; }

    public Long getRelatedId() { return relatedId; }
    public void setRelatedId(Long relatedId) { this.relatedId = relatedId; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public String getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(String uploadedAt) { this.uploadedAt = uploadedAt; }
}
