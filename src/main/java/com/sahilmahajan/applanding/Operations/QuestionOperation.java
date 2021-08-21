package com.sahilmahajan.applanding.Operations;

import com.sahilmahajan.applanding.Repo.QuestionRepo;
import com.sahilmahajan.applanding.Repo.UserRepo;
import com.sahilmahajan.applanding.Utils.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionOperation {

    public int CreateQuestion(Questions value) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        UserOperation userOperation = new UserOperation();
        SubtopicOperation subtopicOperation = new SubtopicOperation();
        CompanyOperation companyOperation = new CompanyOperation();

        if (!userOperation.isValid(value.getUserID())) {
            throw new IllegalArgumentException("User ID is invalid!");
        }
        else if (!subtopicOperation.isValid(value.getSubtopicID())) {
            throw new IllegalArgumentException("SubTopic ID is invalid!");
        }
        else if (value.getCompanyID() != 0) {
            if (!companyOperation.isValid(value.getCompanyID())) {
                throw new IllegalAccessException("Company ID is incorrect!");
            }
        }

        QuestionRepo questionRepo = new QuestionRepo();
        int quesID = questionRepo.Insert(value);

        TagOperation tagOperation = new TagOperation();
        tagOperation.MapQuestionTag(quesID, value.getTags());

        subtopicOperation.QuestionSubTopicMapping(quesID, value.getSubtopicID());
        companyOperation.QuestionCompanyMapping(quesID, value.getCompanyID());

        return quesID;
    }

    public boolean isValid(int questionID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        QuestionRepo questionRepo = new QuestionRepo();
        return questionRepo.ValidateQuestion(questionID);
    }

    public boolean QuestionLikes(QuestionLikes questionLikes) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        UserOperation userOperation = new UserOperation();

        if (!isValid(questionLikes.getQuestionID())) {
            throw new IllegalArgumentException("Question Doesn't Exist!");
        }
        else if (!userOperation.isValid(questionLikes.getUserID())) {
            throw new IllegalAccessException("User does not exist!");
        }

        QuestionRepo questionRepo = new QuestionRepo();
        questionRepo.InsertQuestionLikes(questionLikes);

        return questionRepo.UpdateQuestionLikes(questionLikes.getQuestionID());
    }

    public ArrayList<Integer> GetQuestionID(int likes) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        QuestionRepo questionRepo = new QuestionRepo();
        return questionRepo.GetQuestionID(likes);
    }

    public ArrayList<Integer> GetDateQuestionID(String date) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        QuestionRepo questionRepo = new QuestionRepo();
        return questionRepo.GetDateQuestionID(date);
    }

    public ArrayList<FilterQuestionsResults> GetQuestionsResults(ArrayList<Integer> questionID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        QuestionRepo questionRepo = new QuestionRepo();
        return questionRepo.GetQuestionsResults(questionID);
    }

    public QuestionDetails QuestionDetails(int questionID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        QuestionRepo questionRepo = new QuestionRepo();
        QuestionDetails questionDetails = questionRepo.GetQuestionDetails(questionID);

        AnswerOperation answerOperation = new AnswerOperation();
        AnswerDetails answerDetails = answerOperation.GetAnswerDetails(questionID);

        QuestionDetails finalqustiondetails = new QuestionDetails();

        finalqustiondetails.setQuestion(questionDetails.getQuestion());
        finalqustiondetails.setAnswerDetails(answerDetails);
        finalqustiondetails.setQuestionLikes(questionDetails.getQuestionLikes());
        finalqustiondetails.setCompanies(questionDetails.getCompanies());
        finalqustiondetails.setTags(questionDetails.getTags());
        finalqustiondetails.setSubtopics(questionDetails.getSubtopics());

        return finalqustiondetails;
    }
}
