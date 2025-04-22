package dao.admin;

import dto.*;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettlementDAO implements ISettlementDAO {

    ISettlementDAO settlementDAO;

    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public HashMap<Integer, AdminProject> selectProjectsForSettlement() throws SQLException {
        HashMap<Integer, AdminProject> projects = new HashMap<>();
        List<AdminProject> adminProjects = sqlSession.selectList("mapper.aproject.selectProjectsForSettlement");
        for(AdminProject project : adminProjects) {
            projects.put(project.getProjectId(), project);
        }
        return projects;
    }

    @Override
    public List<AdminProject> selectProjectsForSettlementList() throws SQLException {
        return sqlSession.selectList("mapper.aproject.selectProjectsForSettlement");
    }


    // 정산 회차 구하기

    @Override
    public Integer selectNextSettlementCount(Integer projectId) throws SQLException {
        return sqlSession.selectOne("mapper.settlelist.selectNextSettlementCount", projectId);
    }
    // 정산 대상자 가져오기

    @Override
    public List<AdminSettleTarget> selectFreelancersForSettlement(Integer projectId, Integer cnt) throws SQLException {
        Map<String, Integer> params = new HashMap<>();
        params.put("projectId", projectId);
        params.put("cnt", cnt);
        return sqlSession.selectList("mapper.settlelist.selectFreelancersForSettlement", params);
    }

    // 정산 테이블에 해당 회차 데이터 Insert(Insert into settleList)
    @Override
    public void insertSettlelist(Settlelist settlelist) throws SQLException {
        sqlSession.insert("mapper.settlelist.insertSettlelist", settlelist);
        sqlSession.commit();
    }

    @Override
    public void insertSettlement(Settlement settlement) throws SQLException {
        sqlSession.insert("mapper.settlement.insertSettlement", settlement);
    }
}
