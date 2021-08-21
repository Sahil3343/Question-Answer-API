package com.sahilmahajan.applanding.Utils;

public class AnswerDetails {

    private String[] answer;
    private int userID;
    private int AnswerLikes;
    private String[] Comments;
    private String[] CommentUserID;

    public String[] getCommentUserID() {
        return CommentUserID;
    }

    public void setCommentUserID(String[] commentUserID) {
        CommentUserID = commentUserID;
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAnswerLikes() {
        return AnswerLikes;
    }

    public void setAnswerLikes(int answerLikes) {
        AnswerLikes = answerLikes;
    }

    public String[] getComments() {
        return Comments;
    }

    public void setComments(String[] comments) {
        Comments = comments;
    }
}
