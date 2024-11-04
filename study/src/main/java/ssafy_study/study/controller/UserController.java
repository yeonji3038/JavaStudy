package ssafy_study.study.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ssafy_study.study.DTO.UserCreateForm;
import ssafy_study.study.DTO.UserLoginForm;
import ssafy_study.study.domain.UserInfo;
import ssafy_study.study.User.UserSecurityService;
import ssafy_study.study.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserSecurityService userSecurityService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signupForm";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signupForm";
        }

        userService.create(userCreateForm.getUsername(), userCreateForm.getPassword1(), userCreateForm.getEmail());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        System.out.println("Logged in user: " + user);
        model.addAttribute("user", user);
        return "index";
    }
}