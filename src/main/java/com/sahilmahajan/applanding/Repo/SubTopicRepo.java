package com.sahilmahajan.applanding.Repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubTopicRepo {

    public boolean ValidateTopic(int[] ID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        boolean flag = false;
        for (int i = 0; i < ID.length; i++) {
            String sql = "select count(subtopicID) from SubTopics where subtopicID = ?";
            PreparedStatement statement = conn.getCon().prepareStatement(sql);
            statement.setInt(1, ID[i]);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            if (resultSet.getInt(1) != 0) {
                flag = true;
            }
            else {
                break;
            }
        }
        return flag;
    }

    public boolean InsertSubTopic(int questionid, int[] subtopics) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        boolean flag;

        try {
            for (int i = 0; i < subtopics.length; i++) {
                SQLInstance conn = new SQLInstance();
                String sql = "insert into QuestionSubTopics values (?, ?)";
                PreparedStatement statement = conn.getCon().prepareStatement(sql);
                statement.setInt(1, questionid);
                statement.setInt(2, subtopics[i]);
                statement.execute();
            }
            flag = true;
        } catch (SQLException sqlException) {
            flag = false;
        }

        return flag;
    }

    public ArrayList<Integer> GetQuestionID (String[] subtopic) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        ArrayList<Integer> SubtopicQuestionID = new ArrayList<Integer>();
        for (int i = 0; i < subtopic.length; i++) {
            String sql = "select questionID from QuestionSubTopics where subtopic = ?";
            PreparedStatement statement = conn.getCon().prepareStatement(sql);
            statement.setString(1, subtopic[i]);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            SubtopicQuestionID.add(resultSet.getInt(1));
        }

        return SubtopicQuestionID;
    }
}
