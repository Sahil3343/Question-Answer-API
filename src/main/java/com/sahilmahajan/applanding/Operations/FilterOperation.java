package com.sahilmahajan.applanding.Operations;

import com.sahilmahajan.applanding.Utils.FilterQuestions;
import com.sahilmahajan.applanding.Utils.FilterQuestionsResults;

import java.sql.SQLException;
import java.util.ArrayList;

public class FilterOperation {

    public ArrayList<FilterQuestionsResults> filterQuestions(FilterQuestions filterQuestions) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        ArrayList<Integer> QuestionID = new ArrayList<Integer>();
        QuestionOperation questionOperation = new QuestionOperation();

        if (filterQuestions.getCompanies() != null) {
            ArrayList<Integer> tempData = new ArrayList<>();
            CompanyOperation companyOperation = new CompanyOperation();
            tempData = companyOperation.GetQuestionID(filterQuestions.getCompanies());
            for (int i = 0; i < tempData.size(); i++) {
                if (!QuestionID.contains(tempData.get(i))) {
                    QuestionID.add(tempData.get(i));
                }
            }
        }

        if (filterQuestions.getSubtopics() != null) {
            ArrayList<Integer> tempData = new ArrayList<>();
            SubtopicOperation subtopicOperation = new SubtopicOperation();
            tempData = subtopicOperation.GetQuestionID(filterQuestions.getSubtopics());
            for (int i = 0; i < tempData.size(); i++) {
                if (!QuestionID.contains(tempData.get(i))) {
                    QuestionID.add(tempData.get(i));
                }
            }
        }

        if (filterQuestions.getQuestionlikes() != 0) {
            ArrayList<Integer> tempData = new ArrayList<>();
            tempData = questionOperation.GetQuestionID(filterQuestions.getQuestionlikes());
            for (int i = 0; i < tempData.size(); i++) {
                if (!QuestionID.contains(tempData.get(i))) {
                    QuestionID.add(tempData.get(i));
                }
            }
        }

        if (filterQuestions.getDate() != null) {
            ArrayList<Integer> tempData = new ArrayList<>();
            tempData = questionOperation.GetDateQuestionID(filterQuestions.getDate());
            for (int i = 0; i < tempData.size(); i++) {
                if (!QuestionID.contains(tempData.get(i))) {
                    QuestionID.add(tempData.get(i));
                }
            }
        }

        if (filterQuestions.getTags() != null) {
            ArrayList<Integer> tempData = new ArrayList<>();
            TagOperation tagOperation = new TagOperation();
            tempData = tagOperation.GetQuestionID(filterQuestions.getTags());
            for (int i = 0; i < tempData.size(); i++) {
                if (!QuestionID.contains(tempData.get(i))) {
                    QuestionID.add(tempData.get(i));
                }
            }
        }

        return questionOperation.GetQuestionsResults(QuestionID);
    }
}
