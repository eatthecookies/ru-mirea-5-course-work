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
        List<Task> familyTasks = taskService.getFamilyTasks();

        User user = userService.getCurrentUser();
        List <User> familyUsers = user.getFamily().getUsers();
        String familyCode = user.getFamily().getName();
        model.addAttribute("role", user.getRole());
        if (user.getRole() == UserRole.Взрослый) {
            model.addAttribute("canEdit", true);
        } else {
            model.addAttribute("canEdit", false);
        }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("tasks", tasks);
        model.addAttribute("familyTasks", familyTasks);
        model.addAttribute("code", familyCode);
        model.addAttribute("familyUsers", familyUsers);
        return "tasks";
    }}
