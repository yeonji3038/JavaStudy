package com.example.study_1_CRUD.repository;

import com.example.study_1_CRUD.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // Optional<Board> findByTitle(String title);
    /**
    Board save(Board board);
    Optional<Board> findById(Long id);
    Optional<Board> findByTitle(String title);
    Board findOne(Long id);
    List<Board> findAll();
    void deleteById(Long id);
    Page<Board> findAll(Pageable pageable);
     **/
}
