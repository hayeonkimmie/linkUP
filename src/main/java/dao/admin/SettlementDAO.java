package dao.admin;

import dto.*;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public void createSettlelist(Settlelist settlelist) throws SQLException {
        sqlSession.insert("mapper.settlelist.createSettlelist", settlelist);
        sqlSession.commit();
    }

    @Override
    public void insertSettlement(Settlement settlement) throws SQLException {
        sqlSession.insert("mapper.settlement.insertSettlement", settlement);
        sqlSession.commit();
    }

    @Override
    public Integer getMaxCntByProjectId(Integer projectId) throws SQLException {
        return sqlSession.selectOne("mapper.settlelist.getMaxCntByProjectId", projectId);
    }

    @Override
    public Date selectLatestSettleDateByProjectId(Integer projectId) throws SQLException {
        return sqlSession.selectOne("mapper.settlelist.selectLatestSettleDateByProjectId", projectId);
    }

    @Override
    public Settlelist selectSettlelistByDateAndProject(int projectId, Date settleDate) throws SQLException {
        Map<String, Object> param = new HashMap<>();
        param.put("projectId", projectId);
        param.put("settleDate", settleDate);
        return sqlSession.selectOne("mapper.settlelist.selectSettlelistByDateAndProject", param);
    }


    @Override
    public Settlelist selectSettlelistByProjectIdAndDate(int projectId, Date settleDate) {
        Map<String, Object> param = new HashMap<>();
        param.put("projectId", projectId);
        param.put("settleDate", settleDate);
        return sqlSession.selectOne("mapper.settlelist.selectSettlelistByContractIdAndDate", param);
    }

    @Override
    public boolean existsSettlementBySlistIdAndsettleDate(int slistId, Date settleDate) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("slistId", slistId);
        param.put("settleDate", settleDate);
        Integer count = sqlSession.selectOne("mapper.settlement.existsSettlementBySlistIdAndsettleDate", param);
        return count != null && count > 0;
    }

    @Override
    public Settlelist selectAnySettlelistByProjectIdAndDate(int projectId, Date settleDate) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("projectId", projectId);
        param.put("settleDate", settleDate);
        return sqlSession.selectOne("mapper.settlelist.selectAnySettlelistByProjectIdAndDate", param);
    }


}
