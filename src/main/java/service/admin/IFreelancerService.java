package service.admin;

import dto.Freelancer;

import java.util.List;

public interface IFreelancerService {
    /**
     * 대시보드에서 모든 프리랜서 정보 가져오기
     * @return 프리랜서 정보
     */
    public List<Freelancer> selectAllFreelancer() throws Exception;

}
