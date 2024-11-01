package ru.mirea.familytaskmanagement.repositories;

import ru.mirea.familytaskmanagement.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);

    // Здесь можно добавить дополнительные методы для поиска задач
}
