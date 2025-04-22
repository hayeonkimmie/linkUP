package service.home;

import dto.Client;

import java.util.List;

public interface ISearchCompanyService {
    List<Client> searchCompanyByName(String keyword) throws Exception;
}
