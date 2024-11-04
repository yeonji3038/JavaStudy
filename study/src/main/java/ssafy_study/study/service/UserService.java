package ssafy_study.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ssafy_study.study.domain.UserInfo;
import ssafy_study.study.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import ssafy_study.study.exception.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자 생성
    public UserInfo create(String username, String password, String email) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(passwordEncoder.encode(password));
        userInfo.setEmail(email);
        return userRepository.save(userInfo);
    }

    // 사용자 조회
    public Optional<UserInfo> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 모든 사용자 조회
    public List<UserInfo> findAll() {
        return userRepository.findAll();
    }

    // 사용자 삭제
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // 사용자 업데이트
    public UserInfo updateUser(Long userId, String username, String password, String email) {
        UserInfo userInfo = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userInfo.setUsername(username);
        if (password != null && !password.isEmpty()) {
            userInfo.setPassword(passwordEncoder.encode(password));
        }
        userInfo.setEmail(email);
        return userRepository.save(userInfo);
    }

    public UserInfo getUser(String username) {
        Optional<UserInfo> userInfo = this.userRepository.findByUsername(username);
        if (userInfo.isPresent()) {
            return userInfo.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}