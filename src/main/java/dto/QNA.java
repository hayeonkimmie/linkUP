package dto;

import java.util.Date;

public class Qna {
    int qna_id;
    String user_id;
    String question_title;
    String question_content;
    Date question_date;
    String answer_content;
    Date created_date;

    public Qna(int qna_id, String user_id, String question_title, String question_content, Date question_date, String answer_content, Date created_date) {
        this.qna_id = qna_id;
        this.user_id = user_id;
        this.question_title = question_title;
        this.question_content = question_content;
        this.question_date = question_date;
        this.answer_content = answer_content;
        this.created_date = created_date;
    }

    public int getQna_id() {
        return qna_id;
    }

    public void setQna_id(int qna_id) {
        this.qna_id = qna_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public Date getQuestion_date() {
        return question_date;
    }

    public void setQuestion_date(Date question_date) {
        this.question_date = question_date;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}

