package ru.mirea.familytaskmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mirea.familytaskmanagement.models.Task;
import ru.mirea.familytaskmanagement.services.TaskService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }}
