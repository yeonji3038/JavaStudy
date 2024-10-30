package javastudy.demo.domain.notice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;


    private String title;  // 게시글 제목
    private String content;  // 게시글 내용
    private String author;  // 작성자

    private LocalDateTime createdAt;  // 작성 날짜

    // 엔티티가 저장되기 전에 자동으로 호출되어 작성 시간을 설정함
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // 비즈니스 로직

    // 게시글 내용 수정
    public void updateContent(String newContent) {
        this.content = newContent;
    }

    // 게시글 제목 수정
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }
}
