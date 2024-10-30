package javastudy.demo.domain.controller;

import javastudy.demo.domain.notice.Post;
import javastudy.demo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/notices")
    public String getNotices(Model model) {
        // 모든 게시글을 가져옴
        List<Post> posts = noticeService.findPosts();

        // 모델에 게시글 리스트를 추가
        model.addAttribute("posts", posts);

        // "notices/index"를 반환하면 templates/notices/index.html을 렌더링
        return "notices/index";
    }
}
