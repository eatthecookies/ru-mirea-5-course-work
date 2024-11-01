package ru.mirea.familytaskmanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.mirea.familytaskmanagement.models.Task;
import ru.mirea.familytaskmanagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mirea.familytaskmanagement.services.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    private UserService userService;

    // Создание новой задачи
    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        taskService.createTask(task);
        return ResponseEntity.ok("Task created successfully!");
    }

    @GetMapping
    public List<Task> getTasks() {
        return  taskService.getTasks();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Task> getTaskDetails(@PathVariable Long id) {
        Task task = taskService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateTaskField(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            taskService.updateTaskField(id, updates);
            return ResponseEntity.ok("Task updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update task");
        }
    }


    // Обновление задачи по ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        boolean updated = taskService.updateTask(id, updatedTask);
        return updated ? ResponseEntity.ok("Task updated successfully!") : ResponseEntity.notFound().build();
    }

    // Удаление задачи по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        return deleted ? ResponseEntity.ok("Task deleted successfully!") : ResponseEntity.notFound().build();
    }
}
