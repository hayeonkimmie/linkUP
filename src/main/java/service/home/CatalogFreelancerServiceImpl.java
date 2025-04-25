package service.home;

import dao.common.CatalogFreelancerDAOImpl;
import dao.common.ICatalogFreelancerDAO;
import dto.Freelancer;

import java.util.List;
import java.util.Map;

public class CatalogFreelancerServiceImpl implements ICatalogFreelancerService {
    private final ICatalogFreelancerDAO freelancerDAO = new CatalogFreelancerDAOImpl();

    @Override
    public List<Freelancer> catalogFreelancersByCategory(int categoryId) {
        return freelancerDAO.catalogFreelancersByCategory(categoryId);
    }

    @Override
    public List<Freelancer> searchFreelancersByCategoryAndKeyword(Map<String, String> params) {
        return freelancerDAO.searchFreelancersByCategoryAndKeyword(params);
    }

    @Override
    public List<Freelancer> catalogFreelancersBySubCategoryIds(List<Integer> subCategoryIds) {
        return freelancerDAO.catalogFreelancersBySubCategoryIds(subCategoryIds);
    }

    @Override
    public List<Freelancer> findAllFreelancers() {
        return freelancerDAO.findAllFreelancers();
    }
}
