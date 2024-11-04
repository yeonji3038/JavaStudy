package ssafy_study.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy_study.study.domain.UserInfo;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsername(String username);
}
