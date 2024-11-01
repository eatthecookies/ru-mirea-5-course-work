package ru.mirea.familytaskmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mirea.familytaskmanagement.models.Task;
import ru.mirea.familytaskmanagement.models.User;
import ru.mirea.familytaskmanagement.models.UserRole;
import ru.mirea.familytaskmanagement.services.TaskService;
import ru.mirea.familytaskmanagement.services.UserService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getTasks();
        User user = userService.getCurrentUser();
        if (user.getRole() == UserRole.CHILD) {
            model.addAttribute("role", "Ребенок");
        } else {
            model.addAttribute("role", "Родитель");
        }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("tasks", tasks);
        return "tasks";
    }}
