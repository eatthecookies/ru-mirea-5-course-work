package ru.mirea.familytaskmanagement.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import ru.mirea.familytaskmanagement.models.UserRole;
import ru.mirea.familytaskmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Импортируйте Controller
import org.springframework.web.bind.annotation.*;

@Controller // Измените на Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";  // Имя вашего HTML-шаблона для страницы входа
    }
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Возвращает имя HTML-шаблона для регистрации
    }

    @PostMapping("/auth/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam UserRole role, Model model) {

        boolean registered = userService.registerUser(username, password, role);

        if (registered) {
            model.addAttribute("message", "User registered successfully!");
            return "login"; // Перенаправление на страницу входа после успешной регистрации
        } else {
            model.addAttribute("error", "Username already exists!");
            return "register"; // Возврат на страницу регистрации с ошибкой
        }
    }
}
