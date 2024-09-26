package com.app.connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.connect.DTOs.PostDTO;
import com.app.connect.DTOs.PostDTOResponse;
import com.app.connect.service.postService;



@RestController
@RequestMapping("/api")
public class postController {

    @Autowired
    private postService postService;

    @PostMapping("/post")
    public ResponseEntity<PostDTO> createUserPost(@RequestBody PostDTO postDTO){
        PostDTO savedPostDTO = postService.createUserPost(postDTO);
        return new ResponseEntity<>(savedPostDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/post")
    public ResponseEntity<PostDTOResponse> getAllPost(){
        PostDTOResponse postDTOResponse = postService.getAllPost();
        return new ResponseEntity<>(postDTOResponse, HttpStatus.OK);
    }
    
    @PutMapping("post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long postId, @RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatepost(postDTO, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("admin/post/{postId}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Long postId){
        PostDTO deletedPost = postService.deletePost(postId);
        return new ResponseEntity<>(deletedPost, HttpStatus.OK);
    }
    
    
}
