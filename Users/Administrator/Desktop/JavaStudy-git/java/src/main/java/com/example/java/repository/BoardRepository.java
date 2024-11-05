package com.example.java.repository;

import com.example.java.domain.board.Board;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// 이 인터페이스는 게시판과 관련된 데이터 접근을 위한 메서드를 정의
@Repository // Spring의 컴포넌트 스캔에 의해 이 인터페이스가 DAO로 인식
public interface BoardRepository {

    // 게시물을 저장하는 메서드
    Board save(Board board);

    // 모든 게시물을 조회하는 메서드
    List<Board> findAll();

    // ID로 게시물을 조회하는 메서드
    Optional<Board> findById(Long id);

    // 게시물을 업데이트하는 메서드
    Board updatePost(Board board);

    // ID로 게시물을 삭제하는 메서드
    void deletePost(Long id);
}
