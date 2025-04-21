package dao.home;

import dto.Client;
import java.util.List;

public interface ISearchCompanyDAO {
    List<Client> searchCompanyByName(String keyword) throws Exception;
}
