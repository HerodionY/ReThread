package com.reTheard.reThreard.service;

import com.reTheard.reThreard.model.Comment;
import com.reTheard.reThreard.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(UUID postId) {
        return commentRepository.findByPostId(postId);
    }

    public void deleteComment(UUID commentId) {
        commentRepository.deleteById(commentId);
    }
}
