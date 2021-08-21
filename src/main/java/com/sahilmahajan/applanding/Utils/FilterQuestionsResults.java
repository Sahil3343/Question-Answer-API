package com.sahilmahajan.applanding.Utils;

public class FilterQuestionsResults {

    private int QuestionID;
    private String Question;
    private String[] Company;
    private int QuestionLikes;
    private String Answer;
    private String[] Tags;

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String[] getCompany() {
        return Company;
    }

    public void setCompany(String[] company) {
        Company = company;
    }

    public int getQuestionLikes() {
        return QuestionLikes;
    }

    public void setQuestionLikes(int questionLikes) {
        QuestionLikes = questionLikes;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String[] getTags() {
        return Tags;
    }

    public void setTags(String[] tags) {
        Tags = tags;
    }
}
