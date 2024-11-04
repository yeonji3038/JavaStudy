package ssafy_study.study.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ssafy_study.study.domain.UserInfo;
import ssafy_study.study.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> _userInfo = this.userRepository.findByUsername(username);
        if (_userInfo.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        UserInfo userInfo = _userInfo.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return new User(userInfo.getUsername(), userInfo.getPassword(), authorities);
    }

//    public UserInfo getUserByUsername(String username) {
//        return userRepository.findByUsername(username).orElse(null);
//    }
}