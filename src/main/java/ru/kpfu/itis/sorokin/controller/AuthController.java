package ru.kpfu.itis.sorokin.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.sorokin.dto.UserRegisterDto;
import ru.kpfu.itis.sorokin.service.UserService;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model, HttpServletRequest request) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UserRegisterDto userRegisterDto = new UserRegisterDto(username, password);

        try {
            userService.register(userRegisterDto);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/verification")
    public String verification(@RequestParam String token, Model model) {
        try {
            userService.verify(token);
            model.addAttribute("message", "Почта успешно подтверждена");
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("message", "Подтверждение не удалось");
            model.addAttribute("success", false);
        }

        return "verify-result";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Неверный username или password");
        }
        return "login";
    }
}
