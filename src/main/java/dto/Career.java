package dto;

import java.sql.Date;

public class Career {
    String freelancer_id;
    String roles; // 업종id
    String company_name; // 회사명
    String department_name; // 부서명
    String position; // 직급/직책
    String job_title; // 직무
    String job_description; // 담당업무
    Integer salary;// 연봉
    Date join_date; // 입사일
    Date resign_date; // 퇴사일

    public Career() {
        super();
    }
}