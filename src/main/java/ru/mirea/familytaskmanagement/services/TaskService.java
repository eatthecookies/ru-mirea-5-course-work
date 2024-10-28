package ru.mirea.familytaskmanagement.services;

import ru.mirea.familytaskmanagement.models.Task;
import ru.mirea.familytaskmanagement.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Создание новой задачи
    public void createTask(Task task) {
        taskRepository.save(task);
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
}
