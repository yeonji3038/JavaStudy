package board.board_spring.service;

import board.board_spring.domain.Board;
import board.board_spring.dto.BoardDTO;
import board.board_spring.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service @Transactional
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Long savePost(BoardDTO boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    public List<BoardDTO> findPosts() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDTO> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDTO boardDto = BoardDTO.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdAt(board.getCreatedAt())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    public BoardDTO getPost(Long id){
        Board board = boardRepository.findById(id).get();
        BoardDTO boardDto = BoardDTO.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build();
        return boardDto;
    }

    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}