package dto;

import java.util.Date;

public class QnA {
    int qnaId;
    String userId;
    String questionTitle;
    String questionContent;
    Date questionDate;
    String answerContent;
    Date answerDate;

    public QnA() {
        super();
    }

    public QnA(int qnaId, String userId, String questionTitle, String questionContent, Date questionDate, String answerContent, Date answerDate) {
        this.qnaId = qnaId;
        this.userId = userId;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.questionDate = questionDate;
        this.answerContent = answerContent;
        this.answerDate = answerDate;
    }

    public int getQnaId() {
        return qnaId;
    }

    public void setQnaId(int qnaId) {
        this.qnaId = qnaId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }


    @Override
    public String toString() {
        return "QnA{" +
                "qnaId=" + qnaId +
                ", userId='" + userId + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", questionDate=" + questionDate +
                ", answerContent='" + answerContent + '\'' +
                ", answerDate=" + answerDate +
                '}';
    }
}

