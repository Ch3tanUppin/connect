package com.app.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.app.connect.model.Post;

@Repository
public interface postRepository extends JpaRepository<Post, Long>{

    Post findByPostName(String postName);
    
}
