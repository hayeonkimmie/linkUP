package dao.admin;

import dto.AdminProject;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

public class SettlementDAO implements ISettlementDAO {

    ISettlementDAO settlementDAO;

    SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

    @Override
    public List<AdminProject> selectProjectsForSettlement() throws SQLException {

        return sqlSession.selectList("mapper.aproject.selectProjectsForSettlement");
    }
}
