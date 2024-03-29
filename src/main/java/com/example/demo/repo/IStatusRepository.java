package com.example.demo.repo;


import com.example.demo.model.Post;
import com.example.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface IStatusRepository extends JpaRepository<Status,Long> {
}
