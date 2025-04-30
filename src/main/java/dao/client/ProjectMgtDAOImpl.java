package dao.client;

import dto.ProjectMgt;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.List;
import java.util.Map;

public class ProjectMgtDAOImpl implements IProjectMgtDAO {

    private final SqlSession sqlSession;

    //기본 생성자
    public ProjectMgtDAOImpl() {
        this.sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
    }

    // 내가 등록한 프로젝트 목록 조회
    @Override
    public List<ProjectMgt> selectProjectMgtByStatus(Map<String, Object> param) throws Exception {
        return sqlSession.selectList("mapper.projectmgt.selectProjectMgtByStatus", param);
    }

    // 구인 중인 프로젝트 '구인확정하기' 기능
    @Override
    public void updateStatusToConfirmed(Map<String, Object> param) throws Exception {
        // try 사용함으로써 commit()후 자동으로 session.close() 호출하기
        try (SqlSession session = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
            int result = session.update("mapper.projectmgt.updateStatusToConfirmed", param);
            System.out.println("DB에 반영된 행 수 = " + result); // 테스트 (1이어야 함)
            session.commit();
        }
    }

    @Override
    public ProjectMgt selectProjectById(int projectId) throws Exception {
        return sqlSession.selectOne("mapper.projectmgt.selectProjectById", projectId);
    }

    // 구인 중인 프로젝트 '삭제하기' 기능

    @Override
    public void deleteProject(int projectId) throws Exception {
        try (SqlSession session = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()){
            session.delete("mapper.projectmgt.deleteProject", projectId);
            session.commit();
        }
    }

    // 프로젝트 진행상태 업데이트 (시작 전 > 진행 중)
    @Override
    public void updateProgressToOngoing(Map<String, Object> param) throws Exception {
        try (SqlSession session = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()){
            session.update("mapper.projectmgt.updateProgressToOngoing", param);
            session.commit();
        }
    }

    // 프로젝트 진행상태 업데이트 (진행 중 > 종료됨)
    @Override
    public void updateProgressToEnd(Map<String, Object> param) throws Exception {
        try (SqlSession session = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()){
            session.update("mapper.projectmgt.updateProgressToEnd", param);
            session.commit();
        }
    }
}

