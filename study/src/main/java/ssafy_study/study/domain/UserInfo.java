package ssafy_study.study.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // -> 이 설정을 해두면 유일한 값만 저장할 수 있음
    private String username;
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

}