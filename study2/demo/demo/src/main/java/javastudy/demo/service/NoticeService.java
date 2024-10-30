package javastudy.demo.service;

import javastudy.demo.domain.notice.Post;
import javastudy.demo.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  // 읽기 전용 트랜잭션 (조회만 가능)
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional  // 쓰기 작업에는 트랜잭션을 걸어줌
    public void savePost(Post post) {
        noticeRepository.save(post);  // 게시글 저장
    }

    public List<Post> findPosts() {
        return noticeRepository.findAll();  // 모든 게시글 조회
    }

    public Post findOne(Long postId) {
        return noticeRepository.findOne(postId);  // ID로 게시글 하나 조회
    }

    public List<Post> findByAuthor(String author) {
        return noticeRepository.findByAuthor(author);  // 작성자 이름으로 게시글 조회
    }
}