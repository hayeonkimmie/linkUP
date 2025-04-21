package dto;

import java.util.List;

public class ClientFavorites {
    private int jjimId;
    private String clientId;
    private String freelancerId;

    // 구인자 (찜한 구인자) 구현을 위해 freelancer 테이블에서 받아와야 할 컬럼명 아래 추가
    private String profileImage;
    private String name;
    private String job;
    private String location;
    private double rating;
    private int projectCount;
    private List<String> tags; //skill split

    // 기본 생성자
    public ClientFavorites() {
        super();
    }
    public ClientFavorites(int jjimId, String clientId, String freelancerId, String profileImage, String name, String job, String location, double rating, int projectCount, List<String> tags) {
        this.jjimId = jjimId;
        this.clientId = clientId;
        this.freelancerId = freelancerId;
        this.profileImage = profileImage;
        this.name = name;
        this.job = job;
        this.location = location;
        this.rating = rating;
        this.projectCount = projectCount;
        this.tags = tags;
    }

    public int getJjimId() {
        return jjimId;
    }

    public void setJjimId(int jjimId) {
        this.jjimId = jjimId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(String freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    // 출력 테스트
    @Override
    public String toString() {
        return "ClientFavorites{" +
                "ClientFavorites=" + jjimId +
                ", clientId='" + clientId + '\'' +
                ", freelancerId='" + freelancerId + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", projectCount=" + projectCount +
                ", tags=" + tags +
                '}';
    }
}
