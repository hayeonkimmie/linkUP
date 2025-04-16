package dto;

import java.sql.Date;

public class QNA {
    Integer qna_id;
    String user_id;
    String qna_title;
    String qna_content;
    Date qna_date;
    boolean is_answered;
    String answer_content;
    Date answer_date;

    public QNA() {
        super();
    }

    public QNA(Integer qna_id, String user_id, String qna_title, String qna_content, Date qna_date, boolean is_answered, String answer_content, Date answer_date) {
        this.qna_id = qna_id;
        this.user_id = user_id;
        this.qna_title = qna_title;
        this.qna_content = qna_content;
        this.qna_date = qna_date;
        this.is_answered = is_answered;
        this.answer_content = answer_content;
        this.answer_date = answer_date;
    }

    public Integer getQna_id() {
        return qna_id;
    }

    public void setQna_id(Integer qna_id) {
        this.qna_id = qna_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQna_title() {
        return qna_title;
    }

    public void setQna_title(String qna_title) {
        this.qna_title = qna_title;
    }

    public String getQna_content() {
        return qna_content;
    }

    public void setQna_content(String qna_content) {
        this.qna_content = qna_content;
    }

    public Date getQna_date() {
        return qna_date;
    }

    public void setQna_date(Date qna_date) {
        this.qna_date = qna_date;
    }

    public boolean isIs_answered() {
        return is_answered;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public Date getAnswer_date() {
        return answer_date;
    }

    public void setAnswer_date(Date answer_date) {
        this.answer_date = answer_date;
    }
}
