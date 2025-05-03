package dao.client;

import dto.ClientCandidateMgt;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientCandidateMgtDAOImpl implements IClientCandidateMgtDAO {
    // 세션을 메소드마다 새로 생성하는 방식으로 변경

    // 상태에 따른 지원자 목록
    @Override
    public List<ClientCandidateMgt> selectCandidateMgtByStatus(Map<String, Object> param) throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
            return sqlSession.selectList("mapper.candidatemgt.selectCandidateMgtByStatus", param);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    // 프로젝트 아이디 조회 (프로젝트 상세 띄우기 위함)
    @Override
    public ClientCandidateMgt selectProjectInfoById(int projectId) throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
            return sqlSession.selectOne("mapper.candidatemgt.selectProjectInfoById", projectId);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    // 지원 상태 업데이트 (수락, 거절)
    @Override
    public void updateApplyStatus(int projectId, String freelancerId, int applyStatus) throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
            Map<String, Object> param = new HashMap<>();
            param.put("projectId", projectId);
            param.put("freelancerId", freelancerId);
            param.put("applyStatus", applyStatus);

            sqlSession.update("mapper.candidatemgt.updateApplyStatus", param);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) sqlSession.rollback();
            throw e;
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    // 지원상태 => 수락하면 계약테이블에 행 추가
    @Override
    public void insertContract(int projectId, String freelancerId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
            Map<String, Object> map = new HashMap<>();
            map.put("projectId", projectId);
            map.put("freelancerId", freelancerId);

            // 디버깅 로그 추가
            System.out.println("insertContract 맵 파라미터: " + map);


            sqlSession.insert("mapper.candidatemgt.insertContract", map);
            sqlSession.commit();

            // 디버깅 로그 (계약 테이블 행 추가 여부)
            System.out.println("insertContract 성공 - 계약 테이블에 행 추가됨");
        } catch (Exception e) {
            if (sqlSession != null) sqlSession.rollback();
            System.err.println("insertContract 실패: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }
}
