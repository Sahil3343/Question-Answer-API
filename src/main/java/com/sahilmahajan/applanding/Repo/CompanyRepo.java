package com.sahilmahajan.applanding.Repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyRepo {

    public boolean ValidateCompany(int ID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        String sql = "select count(companyID) from Company where companyID = ?";
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

    public boolean InsertCompany(int questionID, int companyID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            SQLInstance conn = new SQLInstance();
            String sql = "insert into QuestionCompany values (?, ?)";
            PreparedStatement statement = conn.getCon().prepareStatement(sql);
            statement.setInt(1, questionID);
            statement.setInt(2, companyID);
            statement.execute();

            return true;

        } catch (SQLException sqlException) {
            throw new IllegalArgumentException("Unable to insert company!");
        }
    }

    public ArrayList<Integer> GetQuestionID(String[] company) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SQLInstance conn = new SQLInstance();
        ArrayList<Integer> CompanyQuestionID = new ArrayList<Integer>();
        for (int i = 0; i < company.length; i++) {
            String sql = "Select Q.questionID from Question Q join  QuestionCompany QC on QC.questionID = Q.questionID join Company C on C.companyID = QC.companyID where C.companyName = ?";
            PreparedStatement statement = conn.getCon().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE);
            statement.setString(1, company[i]);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            CompanyQuestionID.add(resultSet.getInt(1));
        }

        return CompanyQuestionID;
    }

}
