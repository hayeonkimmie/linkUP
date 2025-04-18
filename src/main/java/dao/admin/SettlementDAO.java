package dao.admin;

import dto.AdminProject;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

public class SettlementDAO implements ISettlementDAO {

    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<AdminProject> selectProjectsForSettlement() throws SQLException {

        List<AdminProject> adminProjectList = sqlSession.selectList("mapper.aproject.selectProjectsForSettlement");
        for (AdminProject adminProject : adminProjectList) {
            System.out.println(adminProject);
        }
        return adminProjectList;
    }
}
