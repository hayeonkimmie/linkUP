package dao.client;

import dto.ClientCandidateMgt;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientCandidateMgtDAOImpl implements IClientCandidateMgtDAO {
    private final SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    // 상태에 따른 지원자 목록
    @Override
    public List<ClientCandidateMgt> selectCandidateMgtByStatus(Map<String, Object> param) throws Exception {
        return sqlSession.selectList("mapper.candidatemgt.selectCandidateMgtByStatus", param);
    }

    // 프로젝트 아이디 조회 (프로젝트 상세 띄우기 위함)
    @Override
    public ClientCandidateMgt selectProjectInfoById(int projectId) throws Exception {
        return sqlSession.selectOne("mapper.candidatemgt.selectProjectInfoById", projectId);
    }

    // 지원 상태 업데이트 (수락, 거절)
    @Override
    public void updateApplyStatus(int projectId, String freelancerId, int applyStatus) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("projectId", projectId);
        param.put("freelancerId", freelancerId);
        param.put("applyStatus", applyStatus);
        sqlSession.update("mapper.candidatemgt.updateApplyStatus", param);
    }
}

