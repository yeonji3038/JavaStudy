package javastudy.demo.repository;

import jakarta.persistence.EntityManager;
import javastudy.demo.domain.notice.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeRepository {
    private final EntityManager em;

    // 게시글 저장 (신규 저장과 업데이트를 처리)
    public void save(Post post) {
        if (post.getId() == null) {
            em.persist(post);  // 새 게시글 저장
        } else {
            em.merge(post);  // 기존 게시글 업데이트
        }
    }

    // 게시글 하나 조회 (ID를 기반으로)
    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    // 모든 게시글 조회
    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    // 특정 작성자가 작성한 게시글 조회
    public List<Post> findByAuthor(String author) {
        return em.createQuery("select p from Post p where p.author = :author", Post.class)
                .setParameter("author", author)
                .getResultList();
    }
}