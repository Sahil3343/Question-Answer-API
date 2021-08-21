package com.sahilmahajan.applanding.Repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {

    public boolean ValidateUser(int ID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        String sql = "select count(userID) from Users where userID = ?";
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
}
