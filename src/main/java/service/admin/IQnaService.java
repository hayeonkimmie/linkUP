package service.admin;

import dto.QnA;

public interface IQnaService {
    /**
     * 답변 등록
     * @param qnaId
     * @param answerContent
     * @throws Exception
     */
    void updateAnswer(int qnaId, String answerContent) throws Exception;

    QnA selectQnaById(int qnaId) throws Exception;
}
