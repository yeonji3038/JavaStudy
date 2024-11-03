package com.example.study_1_CRUD.controller;

import com.example.study_1_CRUD.domain.Board;
import com.example.study_1_CRUD.domain.Users;
import com.example.study_1_CRUD.service.BoardService;
import com.example.study_1_CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@RequestMapping("/boards")
@Controller
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    @Autowired
    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @GetMapping("/new")
    public String createForm() {
        return "boards/createBoardForm";
    }

    @PostMapping("/new")
    public String create(BoardForm form, Principal principal) {
        Board board = new Board();
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());

        Users user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        board.setAuthor(user);

        boardService.join(board);
        return "redirect:/boards";
    }


    @GetMapping("")
    public String list(Pageable pageable, Model model) {
        Page<Board> boards = boardService.findAll(pageable);
        model.addAttribute("boards", boards);
        return "boards/boardList";
    }

    @GetMapping("/{id}")
    public String getBoardDetail(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        model.addAttribute("board", board);
        return "boards/boardDetail";
    }

    @GetMapping("/{boardId}/edit")
    public String modifyBoardForm(@PathVariable("boardId") Long boardId, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/user/login";
        }

        Optional<Board> optionalBoard = boardService.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();

            Users author = board.getAuthor();
            if (author == null || !author.getUsername().equals(principal.getName())) {
                return "error/accessDenied";
            }

            BoardForm form = new BoardForm();
            form.setId(board.getId());
            form.setTitle(board.getTitle());
            form.setContent(board.getContent());
            model.addAttribute("form", form);
            return "boards/modifyBoardForm";
        } else {
            return "redirect:/boards";
        }
    }

    @PostMapping("/{boardId}/edit")
    public String modifyBoard(@PathVariable("boardId") Long boardId, @ModelAttribute("form") BoardForm form, Principal principal) {
        Board board = boardService.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));


        Users author = board.getAuthor();
        if (author == null) {
            return "error/accessDenied";
        }

        if (!author.getUsername().equals(principal.getName())) {
            return "error/accessDenied";
        }

        boardService.modifyBoard(boardId, form.getTitle(), form.getContent());
        return "redirect:/boards";
    }



    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }
}
