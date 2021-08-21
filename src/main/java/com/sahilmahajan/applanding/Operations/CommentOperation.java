package com.sahilmahajan.applanding.Operations;

import com.sahilmahajan.applanding.Repo.CommentRepo;
import com.sahilmahajan.applanding.Utils.Comments;

import java.sql.SQLException;

public class CommentOperation {

    public int CreateComment(Comments comments) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        AnswerOperation answerOperation = new AnswerOperation();
        UserOperation userOperation = new UserOperation();

        if (!answerOperation.isValid(comments.getAnswerID())) {
            throw new IllegalArgumentException("Answer is not available!");
        }
        else if (!userOperation.isValid(comments.getUserID())) {
            throw new IllegalAccessException("UserID is invalid!");
        }

        CommentRepo commentRepo = new CommentRepo();
        return commentRepo.Insert(comments);
    }

}
