package com.sahilmahajan.applanding.Operations;

import com.sahilmahajan.applanding.Repo.AnswerRepo;
import com.sahilmahajan.applanding.Utils.AnswerDetails;
import com.sahilmahajan.applanding.Utils.AnswerLikes;
import com.sahilmahajan.applanding.Utils.Answers;
import com.sahilmahajan.applanding.Utils.QuestionDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerOperation {

    public int CreateAnswer(Answers answers) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        QuestionOperation questionOperation = new QuestionOperation();
        UserOperation userOperation = new UserOperation();

        if (!questionOperation.isValid(answers.getQuestionID())) {
            throw new IllegalAccessException("Question Doesn't exist!");
        }
        else if (!userOperation.isValid(answers.getUserID())) {
            throw new IllegalAccessException("User Doesn't exist!");
        }

        AnswerRepo answerRepo = new AnswerRepo();

        return answerRepo.Insert(answers);
    }

    public boolean isValid(int answerID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        AnswerRepo answerRepo = new AnswerRepo();
        return answerRepo.ValidateAnswer(answerID);
    }

    public boolean AnswerLikes(AnswerLikes answerLikes) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        UserOperation userOperation = new UserOperation();

        if (!isValid(answerLikes.getAnswerID())) {
            throw new IllegalArgumentException("Answer does not exist!");
        }
        else if (!userOperation.isValid(answerLikes.getUserID())) {
            throw new IllegalAccessException("User does not exist!");
        }

        AnswerRepo answerRepo = new AnswerRepo();
        answerRepo.InsertAnswerLikes(answerLikes);

        return answerRepo.UpdateAnswerLikes(answerLikes.getAnswerID());
    }

    public AnswerDetails GetAnswerDetails(int questionID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        AnswerRepo answerRepo = new AnswerRepo();
        return answerRepo.GetAnswerDetails(questionID);
    }
}
