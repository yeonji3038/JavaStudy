package javastudy.demo.repository;

import javastudy.demo.domain.notice.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 추가적인 메서드 선언이 필요하면 여기에 작성합니다.
}
