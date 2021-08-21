package com.sahilmahajan.applanding.Utils;

public class Questions <X> {

    String questionText;
    int companyID;
    int[] subtopicID;
    String[] tags;
    int userID;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int[] getSubtopicID() {
        return subtopicID;
    }

    public void setSubtopicID(int[] subtopicID) {
        this.subtopicID = subtopicID;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
