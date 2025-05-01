package dao.freelancer;

import dto.Career;
import java.util.List;

public interface ICareerDAO {
    public List<Career> selectCareerById(String userId) throws Exception;
    public void insertCareer(Career career) throws Exception;
    public void updateCareer(Career career) throws Exception;
    public void deleteCareer(Integer num) throws Exception;
    List<Career> selectCareerListByFreelancerId(String freelancerId) throws Exception;
}
