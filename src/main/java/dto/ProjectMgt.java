package dto;

import java.util.List;

// 구인자 - 내 프로젝트 조회/관리
public class ProjectMgt {
    private int projectId; // 프로젝트 아이디
    private String title; // 구인공고
    private String category; // 카테고리
    private String skills; // 필요 기술
    private String status; //상태
    private String regDate; //프로젝트 등록일
    private String startDate; //프로젝트 시작일
    private String endDate; // 프로젝트 끝나는 날
    private int totalAmount; // 프로젝트 총 단가
    private List<ProjectPay> payList; // ProjectPay DTO 불러오기
}
