package ssafy_study.study.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy_study.study.domain.Board;
import ssafy_study.study.domain.UserInfo;
import ssafy_study.study.repository.BoardRepository;
import ssafy_study.study.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final EntityManager em;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public BoardService(BoardRepository boardRepository, EntityManager em, UserRepository userRepository, UserService userService) {
        this.boardRepository = boardRepository;
        this.em = em;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void saveBoard(String title, String text, UserInfo author) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Board board = new Board();
        board.setTitle(title);
        board.setText(text);
        board.setAuthor(author);
        boardRepository.save(board);
    }

    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
    }

    public void updateBoard(Long id, Board updatedBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        board.setTitle(updatedBoard.getTitle());
        board.setText(updatedBoard.getText());
        boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        Board board = em.find(Board.class, id);
        em.remove(board);
    }

    public Page<Board> getBoards(int page) {
        return boardRepository.findAll(PageRequest.of(page, 5));
    }

    // 제목으로 검색
    public List<Board> searchBoardsByTitle(String keyword) {
        return boardRepository.findByTitleContainingIgnoreCase(keyword);
    }

    // 내용으로 검색
    public List<Board> searchBoardsByText(String keyword) {
        return boardRepository.findByTextContainingIgnoreCase(keyword);
    }
}