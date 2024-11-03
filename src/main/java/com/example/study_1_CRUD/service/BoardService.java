package com.example.study_1_CRUD.service;

import com.example.study_1_CRUD.domain.Board;
import com.example.study_1_CRUD.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Long join(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    public void modifyBoard(Long id, String title, String content) {
        Optional<Board> optionalBoard = boardRepository.findById(id); // findOne() -> findById()
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.setTitle(title);
            board.setContent(content);
            boardRepository.save(board);
        }
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}
