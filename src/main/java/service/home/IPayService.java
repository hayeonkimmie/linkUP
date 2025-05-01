package service.home;

import dto.Pay;

import java.util.List;

public interface IPayService {
    void registerPay(Pay pay); // 포지션 1개 등록
    List<Pay> getPayByProjectId(int projectId) throws Exception; // 수정 시 프로젝트Id 받아오기
}
