package com.example.java.controoller;


import com.example.java.service.BoardService;
import com.example.java.domain.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Board> postList = boardService.findPosts();
        model.addAttribute("postList", postList);
        return "index";
    }

    @GetMapping("/post")
    public String createPost() {
        return "post";
    }

    @PostMapping("/post")
    public String write(Board board) {
        boardService.savePost(board);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Board findBoard = boardService.findOne(id);
        model.addAttribute("post", findBoard);
        return "detail";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Board findBoard = boardService.findOne(id);
        model.addAttribute("post", findBoard);
        return "edit";


    }
    @PostMapping("/post/update/{id}")
    public String update(Board board) {
        boardService.updateOne(board);
        return "redirect:/";  // 리다이렉트 경로 수정
    }
    @PostMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deleteOne(id);
        return "redirect:/";  // 리다이렉트 경로 수정
    }

}


