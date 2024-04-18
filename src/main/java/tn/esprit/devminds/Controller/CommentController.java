package tn.esprit.devminds.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Service.IComment;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {
    @Autowired
    IComment iComment;
    @PostMapping("comments/create")
    public ResponseEntity<?> createComment(@RequestParam Long postId,@RequestParam String postedBy,@RequestBody String content){
        try {

                return ResponseEntity.ok(iComment.createComment(postId, postedBy, content));

            }
            catch(Exception e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
            }

}
@GetMapping("comments/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable Long postId){
        try {
            return ResponseEntity.ok(iComment.getCommentByPostId(postId));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
}
    }
