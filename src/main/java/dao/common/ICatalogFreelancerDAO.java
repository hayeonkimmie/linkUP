package dao.common;

import dto.Freelancer;

import java.util.List;
import java.util.Map;

public interface ICatalogFreelancerDAO {
    List<Freelancer> catalogFreelancersByCategory(int categoryId); // ✅ int로 변경
    List<Freelancer> searchFreelancersByCategoryAndKeyword(Map<String, String> params);
    List<Freelancer> catalogFreelancersBySubCategoryIds(List<Integer> subCategoryIds);
    List<Freelancer> findAllFreelancers();
}
