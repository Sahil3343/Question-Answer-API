package com.sahilmahajan.applanding.Utils;

public class QuestionDetails {

    private String question;
    private int QuestionLikes;
    private AnswerDetails answerDetails;
    private String[] Companies;
    private String[] Tags;
    private String subtopics;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionLikes() {
        return QuestionLikes;
    }

    public void setQuestionLikes(int questionLikes) {
        QuestionLikes = questionLikes;
    }

    public AnswerDetails getAnswerDetails() {
        return answerDetails;
    }

    public void setAnswerDetails(AnswerDetails answerDetails) {
        this.answerDetails = answerDetails;
    }

    public String[] getCompanies() {
        return Companies;
    }

    public void setCompanies(String[] companies) {
        Companies = companies;
    }

    public String[] getTags() {
        return Tags;
    }

    public void setTags(String[] tags) {
        Tags = tags;
    }

    public String getSubtopics() {
        return subtopics;
    }

    public void setSubtopics(String subtopics) {
        this.subtopics = subtopics;
    }
}
