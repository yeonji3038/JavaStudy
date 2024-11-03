package com.example.study_1_CRUD.repository;

import com.example.study_1_CRUD.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
