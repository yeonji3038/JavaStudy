package com.example.java.service;

import com.example.java.domain.board.Board;
import com.example.java.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// 게시판 서비스 클래스, 게시물과 관련된 비즈니스 로직을 처리
@Service // Spring의 컴포넌트 스캔에 의해 이 클래스가 서비스로 인식
public class BoardService {
    private final BoardRepository boardRepository;

    // 생성자 주입을 통해 BoardRepository를 주입
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시물을 저장하고, 저장된 게시물의 ID를 반환
    public Long savePost(Board board) {
        return boardRepository.save(board).getId();
    }

    // 모든 게시물을 조회하여 반환
    public List<Board> findPosts() {
        return boardRepository.findAll();
    }

    // 특정 ID의 게시물을 조회하여 반환
    public Board findOne(Long boardId) {
        return boardRepository.findById(boardId).get(); // Optional에서 값을 추출
    }

    // 게시물을 업데이트하고, 업데이트된 게시물을 반환
    public Board updateOne(Board board) {
        return boardRepository.updatePost(board);
    }

    // 특정 ID의 게시물을 삭제
    public void deleteOne(Long id) {
        boardRepository.deletePost(id);
    }
}
