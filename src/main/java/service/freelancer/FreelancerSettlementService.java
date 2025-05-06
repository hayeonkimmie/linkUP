package service.freelancer;

import dto.SettlementListForF;
import org.apache.ibatis.session.SqlSession;
import util.MybatisSqlSessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreelancerSettlementService {

    public List<SettlementListForF> getSettlementList(String freelancerId, Integer projectId) {
        SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
        List<SettlementListForF> settlementList = null;
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("freelancerId",freelancerId);
            param.put("projectId",projectId);
            System.out.println("FreelancerSettlementService.java 21 param = " + param);
            settlementList = sqlSession.selectList("mapper.freelancer.selectSettlementListForMyPage", param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settlementList;
    }
}
