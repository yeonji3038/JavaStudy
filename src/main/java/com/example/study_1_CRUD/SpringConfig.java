package com.example.study_1_CRUD;

import com.example.study_1_CRUD.repository.BoardRepository;
import com.example.study_1_CRUD.service.BoardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final BoardRepository boardRepository;

    public SpringConfig(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Bean
    public BoardService boardService(){
        return new BoardService(boardRepository);
    }
}
