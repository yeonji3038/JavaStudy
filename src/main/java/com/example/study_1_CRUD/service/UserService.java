package com.example.study_1_CRUD.service;

import com.example.study_1_CRUD.domain.Users;
import com.example.study_1_CRUD.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Users create(String username, String password) {
        Users user = new Users();
        user.setUsername(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
