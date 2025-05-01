package service.admin;

import dto.AdminFreelancer;
import dto.Career;
import dto.Freelancer;

import java.util.List;

public interface IFreelancerService {
    /**
     * 대시보드에서 모든 프리랜서 정보 가져오기
     * @return 프리랜서 정보
     */
    List<Freelancer> selectAllFreelancer() throws Exception;

    List<Freelancer> searchFreelancersByKeyword(String keyword) throws Exception;

    AdminFreelancer selectFreelancerById(String freelancerId) throws Exception;

    List<Career> selectCareerListByFreelancerId(String freelancerId) throws Exception;
}
