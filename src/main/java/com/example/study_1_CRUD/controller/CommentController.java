package com.example.study_1_CRUD.controller;

import com.example.study_1_CRUD.domain.Board;
import com.example.study_1_CRUD.domain.Comment;
import com.example.study_1_CRUD.service.BoardService;
import com.example.study_1_CRUD.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Long id, @RequestParam(value="content") String content) {
        Board board = boardService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setBoard(board);
        commentService.save(comment);

        return String.format("redirect:/boards/%s", id);
    }
}
