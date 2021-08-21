package com.sahilmahajan.applanding.Operations;

import com.sahilmahajan.applanding.Repo.CompanyRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyOperation {

    public boolean isValid(int companyID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        CompanyRepo companyRepo = new CompanyRepo();
        return companyRepo.ValidateCompany(companyID);
    }

    public boolean QuestionCompanyMapping(int questionID, int companyID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        CompanyRepo companyRepo = new CompanyRepo();
        return companyRepo.InsertCompany(questionID, companyID);
    }

    public ArrayList<Integer> GetQuestionID(String[] company) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        CompanyRepo companyRepo = new CompanyRepo();
        return companyRepo.GetQuestionID(company);
    }

}
