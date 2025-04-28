package dao.admin;

import dto.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.MybatisSqlSessionFactory;
import util.SingleTonSession;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettlementDAO implements ISettlementDAO {

    ISettlementDAO settlementDAO;

    private final SqlSessionFactory sqlSessionFactory = SingleTonSession.getInstance();

    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public HashMap<Integer, AdminSettleProject> selectProjectsForSettlement() throws SQLException {
        HashMap<Integer, AdminSettleProject> projects = new HashMap<>();
        List<AdminSettleProject> adminProjects = sqlSession.selectList("mapper.aproject.selectProjectsForSettlement");
        for(AdminSettleProject project : adminProjects) {
            projects.put(project.getProjectId(), project);
        }
        return projects;
    }

    @Override
    public List<AdminProject> selectProjectsForSettlementList() throws SQLException {
        return sqlSession.selectList("mapper.aproject.selectProjectsForDashboard");
    }


    // 정산 회차 구하기

    @Override
    public Integer selectNextSettlementCount(Integer projectId) throws SQLException {
        return sqlSession.selectOne("mapper.settlelist.selectNextSettlementCount", projectId);
    }

    // 정산 대상자 가져오기
    @Override
    public List<AdminSettleTarget> selectFreelancersForSettlement(Integer projectId, Date startDate, Date endDate) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
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
    public boolean existsSettlementBySlistIdAndStartEndDate(int slistId, String startDate, String endDate, String freelancerName) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("slistId", slistId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("freelancerName", freelancerName);
        Integer count = sqlSession.selectOne("mapper.settlement.existsSettlementBySlistIdAndStartEndDate", param);
        return count != null && count > 0;
    }


    @Override
    public Settlelist selectAnySettlelistByProjectIdAndDate(int projectId, Date settleDate) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("projectId", projectId);
        param.put("settleDate", settleDate);
        return sqlSession.selectOne("mapper.settlelist.selectAnySettlelistByProjectIdAndDate", param);
    }

    @Override
    public List<AdminSettleHistory> selectHistoryList(Map<String, Object> param) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("mapper.settlelist.selectHistoryList", param);
        }
    }

    @Override
    public Integer countHistory(Map<String, Object> param) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("mapper.settlelist.countHistory", param);
        }
    }

    @Override
    public List<AdminSettleHistorySummary> selectHistorySummaryList(Map<String, Object> param) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("mapper.settlelist.selectHistorySummaryList", param);
        }
    }

    @Override
    public int countHistorySummary(Map<String, Object> param) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("mapper.settlelist.countHistory", param);
        }
    }

    @Override
    public HashMap<Integer ,AdminSettleHistory> selectSettlementHistoryDetail(Integer projectId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            HashMap<Integer ,AdminSettleHistory> historyMap = new HashMap<>();
            List<AdminSettleHistory> list = session.selectList("mapper.settlement.selectSettlementHistoryDetail", projectId);
            for (AdminSettleHistory history : list) {
                historyMap.put(history.getSlistId(), history);
            }
            return historyMap;
        }
    }

    @Override
    public List<SettledInfoDTO> selectSettledFreelancers(Integer slistId) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("mapper.settlement.selectSettledFreelancers", slistId);
        }
    }

    @Override
    public List<SettledInfoDTO> selectWaitingFreelancers(Integer projectId, Integer slistId) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("projectId", projectId);
            param.put("slistId", slistId);
            return session.selectList("mapper.settlement.selectWaitingFreelancers", param);
        }
    }

    public Map<String, Date> selectSettleStartandEnd(Integer projectId) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("mapper.settlement.selectSettleStartandEnd", projectId);
        }
    }

    @Override
    public boolean isAllSettledInCnt(Integer projectId, Integer cnt) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("projectId", projectId);
            param.put("cnt", cnt);
            Integer waitingCount = session.selectOne("mapper.settlement.countWaitingFreelancersInCnt", param);
            return waitingCount == 0;
        }
    }

    @Override
    public List<Map<String, Object>> selectAllSettlementMonthsByProjectId(Integer projectId) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("mapper.settlement.selectAllSettlementMonthsByProjectId", projectId);
        }
    }

    @Override
    public List<SettledInfoDTO> selectSettledFreelancersByMonth(Map<String, Object> param) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("mapper.settlement.selectSettledFreelancersByMonth", param);
        }
    }

    @Override
    public List<SettledInfoDTO> selectWaitingFreelancersByMonth(Map<String, Object> param) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("mapper.settlement.selectWaitingFreelancersByMonth", param);
        }
    }


}
