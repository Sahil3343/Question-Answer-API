package com.sahilmahajan.applanding.Operations;

import com.sahilmahajan.applanding.Repo.UserRepo;

import java.sql.SQLException;

public class UserOperation {

    public boolean isValid(int UserID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        UserRepo userRepo = new UserRepo();
        return userRepo.ValidateUser(UserID);
    }
}
