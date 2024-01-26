package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.repo.IPostRepository;
import com.example.demo.repo.IStatusRepository;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    IPostRepository postRepository;
    @Autowired
    IStatusRepository statusRepository;

    @GetMapping
    public ResponseEntity showList() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional = postRepository.findById(id);
        post.setId(postOptional.get().getId());
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        postRepository.deleteById(id);
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }

//    @GetMapping("/newest")
//    public ResponseEntity<List<Post>> getNewestPosts() {
//        List<Post> newestPosts = postRepository.findAllByOrderByCreateAtDesc();
//        return new ResponseEntity<>(newestPosts, HttpStatus.OK);
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String keyword) {
        List<Post> searchResult = postRepository.findAllByTitleContainingOrContentContaining(keyword, keyword);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getSortedPosts() {
        List<Post> posts = postRepository.findAll();
        Collections.sort(posts, Comparator.comparing(Post::getCreateAt).reversed());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/public")
    public ResponseEntity<List<Post>> getPublicPosts() {
        List<Post> publicPosts = postService.getPostsByStatusDescription("public");
        return new ResponseEntity<>(publicPosts, HttpStatus.OK);
    }
    @GetMapping("/only-me")
    public ResponseEntity<List<Post>> getOnlyMePosts() {
        List<Post> onlyMePosts = postService.getPostsByStatusDescription("only me");
        return new ResponseEntity<>(onlyMePosts, HttpStatus.OK);
    }

}
