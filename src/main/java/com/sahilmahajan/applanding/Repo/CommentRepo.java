package com.sahilmahajan.applanding.Repo;

import com.sahilmahajan.applanding.Utils.Comments;
import com.sahilmahajan.applanding.Utils.Questions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentRepo {

    public int Insert(Comments comments) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        int generatedKey = -1;
        String[] returnID = {"commentID"};

        SQLInstance conn = new SQLInstance();
        String sql = "insert into Comments values (?, ?, ?)";
        PreparedStatement statement = conn.getCon().prepareStatement(sql, returnID);
        statement.setInt(1, comments.getAnswerID());
        statement.setInt(2, comments.getUserID());
        statement.setString(3, comments.getComment());
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
    }
