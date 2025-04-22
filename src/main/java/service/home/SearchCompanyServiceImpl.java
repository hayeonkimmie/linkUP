package service.home;

import dao.home.ISearchCompanyDAO;
import dao.home.SearchCompanyDAOImpl;
import dto.Client;

import java.util.List;

public class SearchCompanyServiceImpl implements ISearchCompanyService {
    private final ISearchCompanyDAO companyDAO = new SearchCompanyDAOImpl();
    
    @Override
    public List<Client> searchCompanyByName(String keyword) throws Exception {
        return companyDAO.searchCompanyByName(keyword);
    }
}
