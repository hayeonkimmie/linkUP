package dao.freelancer;

import dto.JjimProj;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JjimProjDAO implements IJjimProjDAO {
    private SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public void insertJjimProj(String freelancerId, Integer projectId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("projectId", projectId);
        param.put("freelancerId", freelancerId);
        System.out.println("JjimProjDAO.java 16" + param);
        sqlSession.insert("mapper.jjimProj.insertJjimProj",param);
    }

    @Override
    public void deleteJjimProjById(Integer jjimId) throws Exception {
        try {
            sqlSession.delete("mapper.jjimProj.insertJjimProj",jjimId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteJjimProjList(List<Integer> jjimIdList) throws Exception {
        try {
            sqlSession.delete("mapper.jjimProj.deleteJjimProjects",jjimIdList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<JjimProj> selectJjimProjListByPage(Integer row, String freelancerId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("row", row);
        param.put("freelancerId", freelancerId);
        System.out.println("JjimProjDAO.java 47" + param);
        return sqlSession.selectList("mapper.jjimProj.selectJjimProjectListByUserId",param);
    }

    @Override
    public Integer selectJjimProjCnt(String freelancerId) throws Exception {
        return sqlSession.selectOne("mapper.jjimProj.selectCntJjimProj",freelancerId);
    }
}
