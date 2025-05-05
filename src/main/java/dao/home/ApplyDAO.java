package dao.home;

import dto.Apply;
import dto.ClientApply;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.SingleTonSession;

public class ApplyDAO implements IApplyDAO {

    private final SqlSessionFactory sqlSession = SingleTonSession.getInstance();

    @Override
    public Apply makeProjectApply(Apply apply) throws Exception {
        Integer result = 0;
        try(SqlSession session = this.sqlSession.openSession()) {
            result = session.insert("mapper.apply.makeProjectApply", apply);
            session.commit();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("프로젝트 ID로 계약 수를 세는 중 오류 발생", e);
        }
        if(result != 1) {
            throw new Exception("데이터 생성에 실패했습니다.");
        }
        return apply;
    }

    @Override
    public ClientApply setlectApplyByApplyId(int applyId) throws Exception {
        ClientApply apply = null;
        try(SqlSession session = this.sqlSession.openSession()) {
            apply = session.selectOne("mapper.apply.setlectApplyById", applyId);
            session.commit();
            System.out.println(apply);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("프로젝트 ID로 계약 수를 세는 중 오류 발생", e);
        }

        return apply;
    }
}
