package dao.common;

import dto.Freelancer;

import java.util.List;
import java.util.Map;

public interface ICatalogFreelancerDAO {
    List<Freelancer> catalogFreelancersByCategory(String category);
    List<Freelancer> searchFreelancersByCategoryAndKeyword(Map<String, String> params);
}
