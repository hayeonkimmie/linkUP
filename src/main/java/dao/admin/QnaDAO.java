package dao.admin;

import dto.QnA;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.SingleTonSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QnaDAO {
    private final SqlSessionFactory sqlSession = SingleTonSession.getInstance();

    public List<QnA> selectPagedQna(int offset, int limit, String keyword, String category, String answerStatus, String startDate, String endDate) throws Exception {
        try(SqlSession session = this.sqlSession.openSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("offset", offset);
            param.put("limit", limit);
            param.put("keyword", keyword);
            param.put("category", category);
            param.put("answerStatus", answerStatus);
            param.put("startDate", startDate);
            param.put("endDate", endDate);
            return session.selectList("mapper.qna.selectPagedQna", param);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("프로젝트 ID로 계약 수를 세는 중 오류 발생", e);
        }
    }

    public int countQna(String keyword, String category, String answerStatus, String startDate, String endDate) throws Exception{
        Integer cnt = 0;
        try(SqlSession session = this.sqlSession.openSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("keyword", keyword);
            param.put("category", category);
            param.put("answerStatus", answerStatus);
            param.put("startDate", startDate);
            param.put("endDate", endDate);
            cnt = session.selectOne("mapper.qna.countQna", param);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }

    public void deleteQna(int qnaId) {
        try(SqlSession session = this.sqlSession.openSession()) {
            session.delete("mapper.qna.deleteQna", qnaId);
            session.commit();
        }
    }

    public QnA selectQnaById(int qnaId) throws Exception {
        try(SqlSession session = this.sqlSession.openSession()) {
            return session.selectOne("mapper.qna.selectQnaById", qnaId);
        }
    }

    public void updateAnswer(int qnaId, String answerContent) throws Exception{
        try(SqlSession session = this.sqlSession.openSession()) {
            session.update("mapper.qna.updateAnswer", Map.of("qnaId", qnaId, "answerContent", answerContent));
            session.commit();
        }
    }

    // 답변완료된 QnA 총 개수
    public int countAnsweredQna() throws Exception {
        try(SqlSession session = this.sqlSession.openSession()) {
            return session.selectOne("mapper.qna.countAnsweredQna");
        }
    }


}
