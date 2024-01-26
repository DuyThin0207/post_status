package com.example.demo.repo;

import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitleContainingOrContentContaining(String title, String content);

    List<Post> findByStatusDescription(String description);
}
