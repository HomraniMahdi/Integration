package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Post;

import java.util.List;

public interface IPost {
    Post savePost(Post post);
    List<Post> getAllPosts();
    Post getPostById(Long postId);
    void likePost(Long postId);
    List<Post> searchByName(String name);
}
