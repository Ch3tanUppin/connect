package com.app.connect.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.connect.DTOs.PostDTO;
import com.app.connect.DTOs.PostDTOResponse;
import com.app.connect.GlobalException.ApiException;
import com.app.connect.GlobalException.ResourceNotFoundException;
import com.app.connect.model.Post;
import com.app.connect.repository.postRepository;

@Service
public class postServiceImpl implements postService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private postRepository postRepository;

    //creating userpost
    @Override
    public PostDTO createUserPost(PostDTO postDTO) {
        //Post from model and converting it to model mapper
        Post post = modelMapper.map(postDTO, Post.class);
        //below is to create and get the method from repo
        Post postFromDB = postRepository.findByPostName(post.getPostName());
        if(postFromDB != null){ //if the repo is matching with same post it will throw an message
            throw new ApiException("Post with name " + post.getPostName() + " already exists!!!");
        } // if not than post will be saved to repo
        Post savedPost = postRepository.save(post);
        // returing the model in form of modelmapper with savedpost and destination postDto
        return modelMapper.map(savedPost, PostDTO.class);
    }

    //getting post
    @Override
    public PostDTOResponse getAllPost() {
        //getting list of post from repo
        List<Post> userPost = postRepository.findAll();
        //if repo is empty it will throw an message
        if(userPost.isEmpty())
            throw new ApiException("NO post was Created");
            //converting the postdto to modelmapper using stream
            List<PostDTO> postDTOs = userPost.stream().map(post -> modelMapper.map(post, PostDTO.class))
            .toList();
            //creating the new object
            PostDTOResponse postDTOResponse = new PostDTOResponse();
            //setting the content to post dto response
            postDTOResponse.setContent(postDTOs);

            //return the post dto responde after setting the content
            return postDTOResponse;
    }

    //update post
    @Override
    public PostDTO updatepost(PostDTO postDTO, Long postId) { //this method take dto and post id to update
        //below find the post in repo by find by id method if id does not present it will  throw an, message
        Post savedPost = postRepository.findById(postId)
        .orElseThrow(()->  new ResourceNotFoundException("Post", "postId", postId ));
        //after find the "post" post is mapped to modelmapper and model class
        Post post = modelMapper.map(postDTO, Post.class);
        post.setPostId(postId); // and then post is to its id
        savedPost = postRepository.save(post); //and it is saved
       return modelMapper.map(savedPost, PostDTO.class); // at last it return the updated post with modelmapper.
    }

    @Override
    public PostDTO deletePost(Long postId) { //this method take postdto and post id to delete
        List<Post> userPost = postRepository.findAll();//it will find the post in list of post in repo using find all method
        //and then below by converting userPost to stream we match the post against user post id
        Post post = userPost.stream().filter(p -> p.getPostId() == (postId)).findFirst() //if it matches then good orelse it will throw an message
        .orElseThrow(() -> new ResourceNotFoundException("Post","postId" , postId));
        postRepository.delete(post); //after getting the post will delete the post from repo
       return modelMapper.map(post, PostDTO.class); //and then will return.
    }

    
}
