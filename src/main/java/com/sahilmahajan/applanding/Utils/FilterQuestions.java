package com.sahilmahajan.applanding.Utils;

public class FilterQuestions {

    private String[] Companies;
    private String[] Subtopics;
    private int Questionlikes;
    private String Date;
    private String[] tags;

    public String[] getCompanies() {
        return Companies;
    }

    public void setCompanies(String[] companies) {
        Companies = companies;
    }

    public String[] getSubtopics() {
        return Subtopics;
    }

    public void setSubtopics(String[] subtopics) {
        Subtopics = subtopics;
    }

    public int getQuestionlikes() {
        return Questionlikes;
    }

    public void setQuestionlikes(int questionlikes) {
        Questionlikes = questionlikes;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
