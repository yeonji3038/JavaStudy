/**
package com.example.study_1_CRUD.repository;

import com.example.study_1_CRUD.domain.Board;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public class JpaBoardRepository implements BoardRepository {

    private final EntityManager em;

    public JpaBoardRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Board save(Board board) {
        if (board.getId() == null) {
            em.persist(board);
            return board;
        } else {
            return em.merge(board);
        }
    }


    @Override
    public Optional<Board> findById(Long id) {
        Board board = em.find(Board.class, id);
        return Optional.ofNullable(board);
    }

    @Override
    public Optional<Board> findByTitle(String title) {
        List<Board> result = em.createQuery("select m from Board m where m.title = :title", Board.class)
                .setParameter("title", title)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Board findOne(Long id){
        return em.find(Board.class, id);
    }

    @Override
    public List<Board> findAll() {
        return em.createQuery("select m from Board m", Board.class)
                .getResultList();
    }

    @Override
    public Page<Board> findAll(Pageable pageable) {
        List<Board> result = em.createQuery("select m from Board m", Board.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        Long total = em.createQuery("select count(m) from Board m", Long.class)
                .getSingleResult();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public void deleteById(Long id) {
        Board board = em.find(Board.class, id);
        if (board != null) {
            em.remove(board);
        }
    }
}
   **/