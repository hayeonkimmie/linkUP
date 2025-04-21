package dao.admin;

import dto.AdminProject;
import dto.AdminProjectDetail;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectDAO implements IProjectDAO {
    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<AdminProject> selectAllOngoingProject() throws Exception {
        return sqlSession.selectList("mapper.aproject.selectAllOngoingProject");
    }

    @Override
    public AdminProjectDetail selectProjectDetail(int projectId) throws Exception {
        return sqlSession.selectOne("mapper.aproject.selectProjectDetail", projectId);
    }

    @Override
    public List<AdminProject> selectPagedProjects(int offset, int limit, String keyword, String settleStatus, String startDate, String endDate) throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("keyword", keyword);
        param.put("settleStatus", settleStatus);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        return sqlSession.selectList("mapper.aproject.selectPagedProjects", param);
    }

    @Override
    public int countAllProjects() throws Exception {
        return sqlSession.selectOne("mapper.aproject.countAllProjects");
    }


}
