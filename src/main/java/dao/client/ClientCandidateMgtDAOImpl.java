package dao.client;

import dao.common.IProjectDAO;
import dao.common.ProjectDAO;
import dao.freelancer.FreelancerDAO;
import dao.freelancer.IFreelancerDAO;
import dao.home.ApplyDAO;
import dao.home.IApplyDAO;
import dao.home.IPayDAO;
import dao.home.PayDAO;
import dto.*;
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
    public void insertContract(int projectId, String freelancerId, Integer applyId, String clientId) throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
            Integer contractCount = sqlSession.selectOne("mapper.contract.selectContractCountByProjectId", projectId);
            IApplyDAO applyDAO = new ApplyDAO();
            IPayDAO payDAO = new PayDAO();
            IProjectDAO projectDAO = new ProjectDAO();
            IFreelancerDAO freelancerDAO= new FreelancerDAO();
            ClientApply apply = applyDAO.setlectApplyByApplyId(applyId);
            Pay pay = payDAO.selectPayByProjectPayId(apply.getProjectPayId());
            Project project = projectDAO.selectProjectByProjectId(projectId);
            Freelancer freelancer = freelancerDAO.selectBasicFreelancerById(apply.getFreelancerId());
            Contract cContract = new Contract();
            cContract.setId("ct" + contractCount + projectId + freelancerId);
            cContract.setApplyId(applyId);
            cContract.setProjectPayId(apply.getProjectPayId());
            cContract.setProjectId(apply.getProjectId());
            cContract.setClientId(clientId);
            cContract.setStartDate(project.getStartDate());
            cContract.setEndDate(project.getEndDate());
            cContract.setPname(project.getProjectName());
            cContract.setPay(pay.getProjectFee());
            cContract.setFee((int) (pay.getProjectFee() * 0.03));
            cContract.setPmanager(project.getManager());
            cContract.setTotalPay((int) (pay.getProjectFee() * 1.03));
            cContract.setClientStatus("N");
            cContract.setStatus("계약완료");
            cContract.setFphone(freelancer.getPhoneNum());
            cContract.setAccount(freelancer.getAccountNum());
            cContract.setFreelancerId(freelancerId);
            sqlSession.insert("mapper.candidatemgt.insertContract", cContract);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) sqlSession.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }
}
