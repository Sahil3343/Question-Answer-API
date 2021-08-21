package com.sahilmahajan.applanding.Repo;

import com.sahilmahajan.applanding.Utils.FilterQuestionsResults;
import com.sahilmahajan.applanding.Utils.QuestionDetails;
import com.sahilmahajan.applanding.Utils.QuestionLikes;
import com.sahilmahajan.applanding.Utils.Questions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionRepo {

    public int Insert(Questions data) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        int generatedKey = -1;
        String[] returnID = {"questionID"};

        SQLInstance conn = new SQLInstance();
        String sql = "insert into Question values (?, ?, ?, GETDATE())";
        PreparedStatement statement = conn.getCon().prepareStatement(sql, returnID);
        statement.setString(1, data.getQuestionText());
        statement.setInt(2, data.getUserID());
        statement.setInt(3, 0);
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

    public boolean ValidateQuestion(int ID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        String sql = "select count(questionID) from Question where questionID = ?";
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

    public boolean InsertQuestionLikes(QuestionLikes questionLikes) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        boolean flag;

        try {
            SQLInstance conn = new SQLInstance();
            String sql = "insert into QuestionLikes values (?, ?)";
            PreparedStatement statement = conn.getCon().prepareStatement(sql);
            statement.setInt(1, questionLikes.getQuestionID());
            statement.setInt(2, questionLikes.getUserID());
            statement.execute();
            flag = true;
        } catch (SQLException sqlException) {
            flag = false;
        }

        return flag;
    }

    public boolean UpdateQuestionLikes(int questionID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            SQLInstance conn = new SQLInstance();
            String sql = "select likesCount from Question where questionID = ?";
            PreparedStatement statement = conn.getCon().prepareStatement(sql);
            statement.setInt(1, questionID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int currentLikes = resultSet.getInt(1);
            currentLikes = 1 + currentLikes;

            String sqlupdate = "update Question set likesCount = ? where questionID = ?";
            PreparedStatement statement1 = conn.getCon().prepareStatement(sqlupdate);
            statement1.setInt(1, currentLikes);
            statement1.setInt(2, questionID);
            statement1.execute();

            return true;

        } catch (SQLException sqlException) {
            throw new IllegalArgumentException("Cannot update likes!!");
        }
    }

    public ArrayList<Integer> GetQuestionID(int likes) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        ArrayList<Integer> LikesQuestionID = new ArrayList<Integer>();
        String sql = "select questionID from Question where likesCount = ?";
        PreparedStatement statement = conn.getCon().prepareStatement(sql);
        statement.setInt(1, likes);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        LikesQuestionID.add(resultSet.getInt(1));
        while (!resultSet.next()) {
            LikesQuestionID.add(resultSet.getInt(1));
        }

        return LikesQuestionID;
    }

    public ArrayList<Integer> GetDateQuestionID(String date) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        ArrayList<Integer> DateQuestionID = new ArrayList<Integer>();
        String sql = "select questionID from Question where DateCreated = ?";
        PreparedStatement statement = conn.getCon().prepareStatement(sql);
        statement.setString(1, date);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        DateQuestionID.add(resultSet.getInt(1));
        while (!resultSet.next()) {
            DateQuestionID.add(resultSet.getInt(1));
        }

        return DateQuestionID;
    }

    public ArrayList<FilterQuestionsResults> GetQuestionsResults(ArrayList<Integer> questionID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ArrayList<FilterQuestionsResults> QuestionResults = new ArrayList<>();
        SQLInstance conn = new SQLInstance();
        FilterQuestionsResults filterQuestionsResults = new FilterQuestionsResults();
        for (int i = 0; i < questionID.size(); i++) {
            String sql = "Select Q.questionID, Q.question, Q.likesCount, C.companyName, A.answer, QT.tagname from Question Q " +
                    "join  QuestionCompany QC on QC.questionID = Q.questionID " +
                    "join questionTags QT on QT.questionID = Q.questionID " +
                    "join Company C on C.companyID = QC.companyID " +
                    "join Answers A on A.questionID = Q.questionID " +
                    "where Q.questionID = ?";
            System.out.println(questionID.get(i));
            PreparedStatement statement = conn.getCon().prepareStatement(sql);
            statement.setInt(1, questionID.get(i));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String[] companies;
            String[] tags;
            if (resultSet.getRow() != 1) {
                companies = new String[resultSet.getRow()];
                tags = new String[resultSet.getRow()];

                filterQuestionsResults.setQuestionID(resultSet.getInt(1));
                filterQuestionsResults.setQuestion(resultSet.getString(2));
                filterQuestionsResults.setQuestionLikes(resultSet.getInt(3));
                filterQuestionsResults.setAnswer(resultSet.getString(5));

                for (int j = 0; j < resultSet.getRow(); j++) {
                    companies[j] = resultSet.getString(4);
                    tags[j] = resultSet.getString(6);
                    resultSet.next();
                }

                filterQuestionsResults.setCompany(companies);
                filterQuestionsResults.setTags(tags);

                QuestionResults.add(filterQuestionsResults);
            }
            else {
                companies = new String[1];
                tags = new String[1];
                companies[0] = resultSet.getString(4);
                tags[0] = resultSet.getString(6);
                filterQuestionsResults.setQuestionID(resultSet.getInt(1));
                filterQuestionsResults.setQuestion(resultSet.getString(2));
                filterQuestionsResults.setQuestionLikes(resultSet.getInt(3));
                filterQuestionsResults.setCompany(companies);
                filterQuestionsResults.setAnswer(resultSet.getString(5));
                filterQuestionsResults.setTags(tags);

                QuestionResults.add(filterQuestionsResults);
            }
        }
        return QuestionResults;
    }

    public QuestionDetails GetQuestionDetails(int questionID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        QuestionDetails questionDetails = new QuestionDetails();
        String[] companies;
        String[] tags;
        String sql = "SELECT Q.question, Q.likesCount, QC.companyID, QT.tagname, C.companyName from Question Q \n" +
                "join  QuestionCompany QC on QC.questionID = Q.questionID\n" +
                "join questionTags QT on QT.questionID = Q.questionID\n" +
                "join Company C on C.companyID = QC.companyID\n" +
                "where Q.questionID = ?";
        PreparedStatement statement = conn.getCon().prepareStatement(sql);
        statement.setInt(1, questionID);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        if (resultSet.getRow() != 1) {
            companies = new String[resultSet.getRow()];
            tags = new String[resultSet.getRow()];

            questionDetails.setQuestion(resultSet.getString(1));
            questionDetails.setQuestionLikes(resultSet.getInt(2));

            for (int j = 0; j < resultSet.getRow(); j++) {
                companies[j] = resultSet.getString(5);
                tags[j] = resultSet.getString(4);
                resultSet.next();
            }

            questionDetails.setCompanies(companies);
            questionDetails.setTags(tags);
        }
        else {
            companies = new String[1];
            tags = new String[1];
            companies[0] = resultSet.getString(5);
            tags[0] = resultSet.getString(4);
            questionDetails.setQuestion(resultSet.getString(1));
            questionDetails.setQuestionLikes(resultSet.getInt(2));
            questionDetails.setCompanies(companies);
            questionDetails.setTags(tags);
        }

        return questionDetails;
    }

}
