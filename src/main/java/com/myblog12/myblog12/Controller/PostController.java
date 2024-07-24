package com.myblog12.myblog12.Controller;

import com.myblog12.myblog12.Payload.PostDto;
import com.myblog12.myblog12.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {

        this.postService = postService;
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto=postService .createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }
    //http://localhost:8080/api/post/particular?id=1
    @GetMapping("/particular")
    public ResponseEntity<PostDto> getPostById(@RequestParam long id){
        PostDto  dto = postService.getPostById(id);

        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/post?/pageNo=0&pageSize=3&sortBy=title&sortDir
    @GetMapping
    public List<PostDto> getAllPosts(

            @RequestParam(name="pageNo",required = false,defaultValue = "0")int pageNo,
            @RequestParam(name="pageSize",required = false,defaultValue = "3")int pageSize,
            @RequestParam(name="sortBy",required = false,defaultValue = "id")String sortBy,
            @RequestParam(name="sortDir",required = false,defaultValue = "id")String sortDir
    ){
        List<PostDto> postDtos = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);

        return postDtos;

    }
}
