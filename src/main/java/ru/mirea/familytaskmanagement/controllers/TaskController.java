package ru.mirea.familytaskmanagement.controllers;

import ru.mirea.familytaskmanagement.models.Task;
import ru.mirea.familytaskmanagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Создание новой задачи
    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        taskService.createTask(task);
        return ResponseEntity.ok("Task created successfully!");
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
