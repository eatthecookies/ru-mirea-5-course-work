package ru.mirea.familytaskmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/tasks")
    public String index() {
        return "tasks";  // Имя вашего HTML-шаблона для главной страницы
    }
}
