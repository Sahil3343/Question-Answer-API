package com.sahilmahajan.applanding.Operations;

import com.sahilmahajan.applanding.Repo.TagRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class TagOperation {

    public boolean MapQuestionTag(int questionID, String[] tags) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        TagRepo tagRepo = new TagRepo();
        return tagRepo.insert(questionID, tags);
    }

    public ArrayList<Integer> GetQuestionID(String[] tags) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        TagRepo tagRepo = new TagRepo();
        return tagRepo.GetQuestionID(tags);
    }
}
