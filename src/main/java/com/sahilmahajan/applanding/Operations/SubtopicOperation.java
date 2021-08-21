package com.sahilmahajan.applanding.Operations;

import com.sahilmahajan.applanding.Repo.SubTopicRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubtopicOperation {

    public boolean isValid(int[] subtopicID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SubTopicRepo subTopicRepo = new SubTopicRepo();
        return subTopicRepo.ValidateTopic(subtopicID);
    }

    public void QuestionSubTopicMapping(int questionid, int[] subtopics) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SubTopicRepo subTopicRepo = new SubTopicRepo();
        subTopicRepo.InsertSubTopic(questionid, subtopics);
    }

    public ArrayList<Integer> GetQuestionID(String[] subtopic) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SubTopicRepo subTopicRepo = new SubTopicRepo();
        return subTopicRepo.GetQuestionID(subtopic);
    }

}
