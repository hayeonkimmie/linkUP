package dao.admin;

import dto.AdminPrepareSettle;

import java.util.HashMap;

public interface IContractDAO {
    HashMap<String, AdminPrepareSettle> selectInfoForSettle(String id) throws Exception;
    AdminPrepareSettle selectInfoForSettleById(String id) throws Exception;
}
