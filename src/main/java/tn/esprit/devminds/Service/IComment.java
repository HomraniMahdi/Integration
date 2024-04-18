package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Comment;

import java.util.List;

public interface IComment {
    Comment createComment(Long postId, String postedBy, String content);
    List<Comment> getCommentByPostId(Long postId);
}
