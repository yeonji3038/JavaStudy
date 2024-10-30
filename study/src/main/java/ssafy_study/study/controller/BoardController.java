package ssafy_study.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ssafy_study.study.domain.UserInfo;
import ssafy_study.study.service.BoardService;
import ssafy_study.study.domain.Board;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 작성 페이지
    @GetMapping("boardWrite")
    public String board(Model model) {
        model.addAttribute("data", "게시글 작성");
        return "boardWrite";
    }

    // 게시글 작성 처리
    @PostMapping("board/save")
    public String saveBoard(Model model, @RequestParam String title, @RequestParam String text, @RequestParam UserInfo author, RedirectAttributes redirectAttributes, Authentication authentication) {

        boardService.saveBoard(title, text, author);
        redirectAttributes.addFlashAttribute("message", "게시글이 등록되었습니다.");
        return "redirect:/boardList";
    }

    // 게시글 목록 페이지
    @GetMapping("/boardList")
    public String boardList(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(required = false) String keyword,
                            @RequestParam(required = false) String searchType,
                            Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            List<Board> searchResults;
            if ("title".equals(searchType)) {
                // 제목으로 검색
                searchResults = boardService.searchBoardsByTitle(keyword);
            } else if ("text".equals(searchType)) {
                // 내용으로 검색
                searchResults = boardService.searchBoardsByText(keyword);
            } else {
                // 검색 타입이 없으면 빈 리스트
                searchResults = List.of();
            }
            model.addAttribute("boards", searchResults);
            model.addAttribute("totalPages", 1); // 검색 결과가 적을 경우 페이지 수 조정 필요
            model.addAttribute("currentPage", 0);
            model.addAttribute("keyword", keyword);
            model.addAttribute("searchType", searchType);
        } else {
            // 기본 게시글 목록
            Page<Board> boardsPage = boardService.getBoards(page);
            model.addAttribute("boards", boardsPage.getContent());
            model.addAttribute("totalPages", boardsPage.getTotalPages());
            model.addAttribute("currentPage", page);
        }
        return "boardList";
    }

    // 게시글 상세 보기 페이지
    @GetMapping("/boards/{id}")
    public String getBoardContent(@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boardContent";
    }

    // 게시글 수정 페이지
    @GetMapping("/boards/edit/{id}")
    public String editBoard(@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boardEdit";
    }

    // 게시글 수정 처리
    @PostMapping("/boards/update/{id}")
    public String updateBoard(@PathVariable Long id, @RequestParam String title, @RequestParam String text) {
        Board updatedBoard = new Board();
        updatedBoard.setTitle(title);
        updatedBoard.setText(text);
        boardService.updateBoard(id, updatedBoard);
        return "redirect:/boardList";
    }

    // 게시글 삭제 처리
    @PostMapping("/boards/delete/{id}")
    public String deleteBoard(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boardService.deleteBoard(id);
        redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
        return "redirect:/boardList";
    }

}