package ssafy_study.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy_study.study.domain.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContainingIgnoreCase(String title); // 제목 검색
    List<Board> findByTextContainingIgnoreCase(String text); // 내용 검색
}