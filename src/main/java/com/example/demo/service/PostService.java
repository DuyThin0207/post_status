package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repo.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private IPostRepository postRepository;

    public List<Post> getPostsByStatusDescription(String description) {
        return postRepository.findByStatusDescription(description);
    }
}
