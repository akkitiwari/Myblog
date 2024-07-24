package com.myblog12.myblog12.Controller;

import com.myblog12.myblog12.Entity.Comment;
import com.myblog12.myblog12.Payload.CommentDto;
import com.myblog12.myblog12.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

//http://localhost:8080/api/comment?postId=1
    @PostMapping
    public ResponseEntity<CommentDto>createComment
            ( @RequestBody CommentDto commentDto,@RequestParam long postId){

        CommentDto dto = commentService.createPost(commentDto, postId);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


   //http://localhost:8080/api/comment/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
         return new ResponseEntity<>("comment deleted sucessfully!!", HttpStatus.OK);
    }
    //http://localhost:8080/api/comment/2/post/3
    @PutMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto ,@PathVariable Long postId) {
        CommentDto dto = commentService.updateComment(id,commentDto,postId );
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}

