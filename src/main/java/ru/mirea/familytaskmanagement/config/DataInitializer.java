package ru.mirea.familytaskmanagement.config;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.familytaskmanagement.models.*;
import ru.mirea.familytaskmanagement.repositories.TaskRepository;
import ru.mirea.familytaskmanagement.repositories.UserRepository;
import ru.mirea.familytaskmanagement.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserService userService, UserRepository userRepository, TaskRepository taskRepository) {
        return args -> {

            userService.registerUser("Папа", "password123", UserRole.Взрослый, "");

            Optional<User> optionalParent = userRepository.findByUsername("Папа");
            User parent = optionalParent.orElseThrow(() -> new EntityNotFoundException("User not found"));
            String familyCode = parent.getFamily().getName();

            // Сохраняем задачу через сервис



            userService.registerUser("Вася", "123", UserRole.Ребенок, familyCode);
            Optional<User> optionalVasya = userRepository.findByUsername("Вася");
            User vasya = optionalVasya.orElseThrow(() -> new EntityNotFoundException("User not found"));

            userService.registerUser("Петя", "123", UserRole.Ребенок, familyCode);
            Optional<User> optionalPetya = userRepository.findByUsername("Петя");
            User petya = optionalPetya.orElseThrow(() -> new EntityNotFoundException("User not found"));
            userService.registerUser("Катя", "123", UserRole.Ребенок, familyCode);
            Optional<User> optionalkatya = userRepository.findByUsername("Вася");
            User katya = optionalkatya.orElseThrow(() -> new EntityNotFoundException("User not found"));

            List<User> children = Arrays.asList(petya, katya, vasya);

// Назначаем задачи каждому ребенку
            String[] childTasks = {"Убраться в комнате", "Сделать уроки", "Помыть посуду", "Погулять с собакой"};
            int taskIndex = 0;

            for (User child : children) {
                for (int i = 0; i < 2; i++) {  // Назначаем по 2 задачи каждому ребенку
                    Task task = new Task();
                    task.setTitle(childTasks[taskIndex % childTasks.length]);  // Циклически выбираем задачу из массива
                    task.setDescription("Описание задачи для " + child.getUsername());
                    task.setStatus(TaskStatus.TODO);
                    task.setUser(child);

                    taskRepository.save(task);
                    taskIndex++;
                }
            }

            String[] parentTasks = {"Оплатить счета", "Купить продукты", "Починить кран"};
            for (String title : parentTasks) {
                Task task = new Task();
                task.setTitle(title);
                task.setDescription("Описание задачи для Папы");
                task.setStatus(TaskStatus.TODO);
                task.setUser(parent);

                taskRepository.save(task);
            }
            System.out.println("Sample data initialized");

        };
    }
}

