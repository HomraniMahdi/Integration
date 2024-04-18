package tn.esprit.devminds.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.Comment;
import tn.esprit.devminds.Entities.Post;
import tn.esprit.devminds.Repository.CommentRepository;
import tn.esprit.devminds.Repository.PostRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class CommentService implements IComment{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    public Comment createComment(Long postId,String postedBy, String content){
        Optional<Post> optionalPost=postRepository.findById(postId);
        if ((optionalPost.isPresent())){
            Comment comment=new Comment();
            comment.setPost(optionalPost.get());
            comment.setContent(content);
            comment.setPostedBy(postedBy);
            comment.setCreatedAt(new Date());
            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Post Not Found");
    }
    public List<Comment>getCommentByPostId(Long postId){
        return commentRepository.findByPostId(postId);
    }

}
