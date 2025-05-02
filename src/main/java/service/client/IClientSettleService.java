package service.client;

public interface IClientSettleService {
    int getNextSettleRound(Integer projectId) throws Exception;
}
