package service.home;

import dto.Freelancer;

import java.util.List;
import java.util.Map;

public interface ICatalogFreelancerService {
    List<Freelancer> catalogFreelancersByCategory(int categoryId);
    List<Freelancer> searchFreelancersByCategoryAndKeyword(Map<String, String> params);
    List<Freelancer> catalogFreelancersBySubCategoryIds(List<Integer> subCategoryIds);
    List<Freelancer> findAllFreelancers();
}
