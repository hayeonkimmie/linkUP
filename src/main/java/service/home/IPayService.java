package service.home;

import dto.Pay;

import java.util.List;

public interface IPayService {
    void registerPay(Pay pay); // 포지션 1개 등록

    List<Pay> selectPayByProjectId(Integer projectId) throws Exception;
}
