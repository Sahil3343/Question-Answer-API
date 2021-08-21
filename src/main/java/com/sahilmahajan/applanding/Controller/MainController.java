package com.sahilmahajan.applanding.Controller;

import com.sahilmahajan.applanding.Operations.AnswerOperation;
import com.sahilmahajan.applanding.Operations.CommentOperation;
import com.sahilmahajan.applanding.Operations.FilterOperation;
import com.sahilmahajan.applanding.Operations.QuestionOperation;
import com.sahilmahajan.applanding.Utils.*;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class MainController {

    @PostMapping("/home")
    public String home() {
        return "This is a test";
    }

    @PostMapping("/Questions")
    public int Questions(@RequestBody Questions questions) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        if (questions.getQuestionText() == null) {
            throw new IllegalArgumentException("Question field is empty!");
        }
        else if (questions.getSubtopicID() == null) {
            throw new IllegalArgumentException("SubtopicID field is empty!");
        }
        else if (questions.getUserID() == 0) {
            throw new IllegalArgumentException("UserID field is empty!");
        }

        QuestionOperation questionOperation = new QuestionOperation();

        return questionOperation.CreateQuestion(questions);
    }

    @PostMapping("/Answers")
    public int Answer(@RequestBody Answers answers) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (answers.getQuestionID() == 0) {
            throw new IllegalArgumentException("Question ID field is empty!");
        }
        else if (answers.getUserID() == 0) {
            throw new IllegalArgumentException("UserID field is empty!");
        }
        else if (answers.getAnswer() == null) {
            throw new IllegalArgumentException("Answer field is empty!");
        }

        AnswerOperation answerOperation = new AnswerOperation();

        return answerOperation.CreateAnswer(answers);
    }

    @PostMapping("/Comments")
    public int Comments(@RequestBody Comments comments) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        if (comments.getAnswerID() == 0) {
            throw new IllegalArgumentException("Answer ID field is empty!");
        }
        else if (comments.getUserID() == 0) {
            throw new IllegalArgumentException("UserID field is empty!");
        }
        else if (comments.getComment() == null) {
            throw new IllegalArgumentException("Comment field is empty!");
        }

        CommentOperation commentOperation = new CommentOperation();
        return commentOperation.CreateComment(comments);
    }

    @PostMapping("/Question_likes")
    public String QuestionLikes(@RequestBody QuestionLikes questionLikes) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (questionLikes.getQuestionID() == 0) {
            throw new IllegalArgumentException("Question ID field is empty!");
        }
        else if (questionLikes.getUserID() == 0) {
            throw new IllegalArgumentException("UserID field is empty!");
        }

        QuestionOperation questionOperation = new QuestionOperation();

        if (questionOperation.QuestionLikes(questionLikes)) {
            return "Success!";
        }
        else {
            return "Fail!";
        }
    }

    @PostMapping("/Answer_likes")
    public String Answerlikes(@RequestBody AnswerLikes answerLikes) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (answerLikes.getAnswerID() == 0) {
            throw new IllegalArgumentException("Answer ID field is empty!");
        }
        else if (answerLikes.getUserID() == 0) {
            throw new IllegalArgumentException("UserID field is empty!");
        }

        AnswerOperation answerOperation = new AnswerOperation();

        if (answerOperation.AnswerLikes(answerLikes)) {
            return "Success!";
        }
        else {
            return "Fail!";
        }
    }

    @GetMapping("/Filter_questions")
    public ArrayList<FilterQuestionsResults> FilterQuestions(@RequestBody FilterQuestions filterQuestions) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        FilterOperation filterOperation = new FilterOperation();
        return filterOperation.filterQuestions(filterQuestions);
    }

    @GetMapping("/Question")
    public QuestionDetails Question(@RequestBody int questionID) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        QuestionOperation questionOperation = new QuestionOperation();
        return questionOperation.QuestionDetails(questionID);
    }
}
