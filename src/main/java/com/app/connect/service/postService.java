package com.app.connect.service;

import com.app.connect.DTOs.PostDTO;
import com.app.connect.DTOs.PostDTOResponse;

public interface postService{


    PostDTOResponse getAllPost();

    PostDTO createUserPost(PostDTO postDTO);

    PostDTO updatepost(PostDTO postDTO, Long postId);

    PostDTO deletePost(Long postId);

    

       
    
}
