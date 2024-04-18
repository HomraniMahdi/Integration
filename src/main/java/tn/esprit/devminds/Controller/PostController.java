package tn.esprit.devminds.Controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.Post;
import tn.esprit.devminds.Service.IPost;


import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    IPost iPost;
    @PostMapping
  public ResponseEntity<?> createPost(@RequestBody Post post){
      try {
          Post createdPost=iPost.savePost(post);
          return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);

      }catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }

      }
      @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iPost.getAllPosts());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
      }
      @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId){
        try {
            Post post=iPost.getPostById(postId);
            return ResponseEntity.ok(post);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

      }
      @PutMapping("/{postId}/like")
      public ResponseEntity<?> likePost(@PathVariable Long postId){
        try {
            iPost.likePost(postId);
            return ResponseEntity.ok(new String[]{"Post liked successfully."});

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
      }
      @GetMapping("/search/{name}")
    public ResponseEntity<?> searchByName(@PathVariable String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iPost.searchByName(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

          }
      }
  }

