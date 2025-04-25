package dao.freelancer;

import dto.Apply;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplyProjDAO implements IApplyProjDAO{
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public Integer getApplyProjCnt(String freelancerId) throws Exception {
        Integer applyCount = null;
        try {
            applyCount = sqlSession.selectOne("mapper.apply.selectApplyProjectCnt", freelancerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applyCount;
    }

    @Override
    public void cancleProjApply(Integer applyId) throws Exception {
        System.out.println("ApplyProjDAO.java 27 = " + applyId);
        try {
            sqlSession.update("mapper.apply.cancelApplyProject", applyId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }
    }

    @Override
    public List<Apply> getApplyProjList(Integer row, String freelancerId) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("row", row);
        params.put("freelancerId", freelancerId);
        List<Apply> applyProjList = sqlSession.selectList("mapper.apply.selectApplyProjectList", params);
        System.out.println("ApplyProjDAO.java 38 applyProjList = " + applyProjList);
        return applyProjList;
    }
}
