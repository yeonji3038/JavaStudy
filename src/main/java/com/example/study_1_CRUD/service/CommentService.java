package com.example.study_1_CRUD.service;

import com.example.study_1_CRUD.domain.Comment;
import com.example.study_1_CRUD.repository.CommentRepository; // CommentRepository import
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
