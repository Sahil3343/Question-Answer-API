package com.sahilmahajan.applanding.Repo;

import com.sahilmahajan.applanding.Utils.AnswerDetails;
import com.sahilmahajan.applanding.Utils.AnswerLikes;
import com.sahilmahajan.applanding.Utils.Answers;
import com.sahilmahajan.applanding.Utils.QuestionLikes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerRepo {

    public int Insert(Answers answers) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        int generatedKey = -1;
        String[] returnID = {"answerID"};

        SQLInstance conn = new SQLInstance();
        String sql = "insert into Answers values (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.getCon().prepareStatement(sql, returnID);
        statement.setInt(1, answers.getQuestionID());
        statement.setString(2, answers.getAnswer());
        statement.setInt(3, answers.getUserID());
        statement.setInt(4, 0);
        statement.setInt(5, 0);
        statement.execute();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        try {
            if (generatedKeys.next()) {
                generatedKey = generatedKeys.getInt(1);
            }
        } catch (SQLException sqlException) {
            throw new IllegalArgumentException("Failed to get");
        }

            return generatedKey;
        }

    public boolean ValidateAnswer(int ID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        String sql = "select count(answerID) from Answers where answerID = ?";
        PreparedStatement statement = conn.getCon().prepareStatement(sql);
        statement.setInt(1, ID);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        boolean flag = false;

        if (resultSet.getInt(1) != 0) {
            flag = true;
        }

        return flag;
    }

    public boolean InsertAnswerLikes(AnswerLikes answerLikes) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        boolean flag;

        try {
            SQLInstance conn = new SQLInstance();
            String sql = "insert into AnswerLikes values (?, ?)";
            PreparedStatement statement = conn.getCon().prepareStatement(sql);
            statement.setInt(1, answerLikes.getAnswerID());
            statement.setInt(2, answerLikes.getUserID());
            statement.execute();
            flag = true;
        } catch (SQLException sqlException) {
            throw new IllegalArgumentException("Cannot insert likes!!");
        }

        return flag;
    }

    public boolean UpdateAnswerLikes(int answerID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            SQLInstance conn = new SQLInstance();
            String sql = "select likesCount from Answers where answerID = ?";
            PreparedStatement statement = conn.getCon().prepareStatement(sql);
            statement.setInt(1, answerID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int currentLikes = resultSet.getInt(1);
            currentLikes = 1 + currentLikes;

            String sqlupdate = "update Answers set likesCount = ? where answerID = ?";
            PreparedStatement statement1 = conn.getCon().prepareStatement(sqlupdate);
            statement1.setInt(1, currentLikes);
            statement1.setInt(2, answerID);
            statement1.execute();

            return true;

        } catch (SQLException sqlException) {
            throw new IllegalArgumentException("Cannot update likes!!");
        }
    }

    public AnswerDetails GetAnswerDetails(int questionID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        AnswerDetails answerDetails = new AnswerDetails();
        String[] answer;
        String[] comment;
        String[] commentUser;
        String sql = "select A.answer, A.userID, A.likesCount, C.comment, C.userID from Answers A \n" +
                "join Comments C on C.answerID = A.answerID\n" +
                "where questionID = ?";
        PreparedStatement statement = conn.getCon().prepareStatement(sql);
        statement.setInt(1, questionID);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        if (resultSet.getRow() != 1) {
            comment = new String[resultSet.getRow()];
            commentUser = new String[resultSet.getRow()];
            answer = new String[resultSet.getRow()];

            answerDetails.setUserID(resultSet.getInt(2));
            answerDetails.setAnswerLikes(resultSet.getInt(3));

            for (int j = 0; j < resultSet.getRow(); j++) {
                answer[j] = resultSet.getString(1);
                comment[j] = resultSet.getString(4);
                commentUser[j] = resultSet.getString(5);
                resultSet.next();
            }

            answerDetails.setAnswer(answer);
            answerDetails.setComments(comment);
            answerDetails.setCommentUserID(commentUser);
        }
        else {
            answer = new String[1];
            comment = new String[1];
            commentUser = new String[1];

            answer[0] = resultSet.getString(1);
            comment[0] = resultSet.getString(4);
            commentUser[0] = resultSet.getString(5);
            answerDetails.setUserID(resultSet.getInt(2));
            answerDetails.setAnswerLikes(resultSet.getInt(3));
            answerDetails.setAnswer(answer);
            answerDetails.setComments(comment);
            answerDetails.setCommentUserID(commentUser);
        }

        return answerDetails;
    }

}
