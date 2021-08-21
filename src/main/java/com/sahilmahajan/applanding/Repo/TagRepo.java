package com.sahilmahajan.applanding.Repo;

import javax.swing.text.html.HTML;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TagRepo {

    public boolean insert(int questionID, String[] tags) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        boolean flag;

        try {
            for (int i = 0; i < tags.length; i++) {
                String sql = "insert into questionTags values (?, ?)";
                PreparedStatement statement = conn.getCon().prepareStatement(sql);
                statement.setInt(1, questionID);
                statement.setString(2, tags[i]);
                statement.execute();
            }
            flag = true;
        } catch (SQLException sqlException) {
            flag = false;
        }

        return flag;
    }

    public ArrayList<Integer> GetQuestionID(String[] tags) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        ArrayList<Integer> TagQuestionID = new ArrayList<Integer>();
        ResultSet resultSet = null;
        for (int i = 0; i < tags.length; i++) {
            String sql = "select questionID from questionTags where tagname = ?";
            PreparedStatement statement = conn.getCon().prepareStatement(sql);
            statement.setString(1, tags[i]);
            resultSet = statement.executeQuery();
            resultSet.next();
            TagQuestionID.add(resultSet.getInt(1));
            while (!resultSet.next()) {
                TagQuestionID.add(resultSet.getInt(1));
            }
        }

            return TagQuestionID;
    }
}
