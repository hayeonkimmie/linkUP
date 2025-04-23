package service.home;

import dto.Freelancer;

import java.util.List;
import java.util.Map;

public interface ICatalogFreelancerService {
    List<Freelancer> catalogFreelancersByCategory(String category);
    List<Freelancer> searchFreelancersByCategoryAndKeyword(Map<String, String> params);
}
