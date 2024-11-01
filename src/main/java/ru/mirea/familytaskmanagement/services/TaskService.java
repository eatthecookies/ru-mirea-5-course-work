package ru.mirea.familytaskmanagement.services;

import ru.mirea.familytaskmanagement.models.Task;
import ru.mirea.familytaskmanagement.models.TaskStatus;
import ru.mirea.familytaskmanagement.models.User;
import ru.mirea.familytaskmanagement.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.familytaskmanagement.repositories.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    UserService userService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserRepository userRepository;

    // Создание новой задачи
    public void createTask(Task task) {
        User user = userService.getCurrentUser();
        taskRepository.save(task);
        user.addTask(task);
        userRepository.save(user);

    }

    public void updateTaskField(Long id, Map<String, Object> updates) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        updates.forEach((field, value) -> {
            switch (field) {
                case "title": task.setTitle((String) value); break;
                case "description": task.setDescription((String) value); break;
                case "status": task.setStatus(TaskStatus.valueOf((String) value)); break;
            }
        });

        taskRepository.save(task);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    // Обновление задачи по ID
    public boolean updateTask(Long id, Task updatedTask) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    // Удаление задачи по ID
    public boolean deleteTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Task> getTasks() {
        User user = userService.getCurrentUser();
        return user.getTasks();
    }
}
