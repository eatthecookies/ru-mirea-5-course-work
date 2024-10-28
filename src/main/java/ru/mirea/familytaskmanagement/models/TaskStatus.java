package ru.mirea.familytaskmanagement.models;

public enum TaskStatus {
    TODO,        // Задача только добавлена
    IN_PROGRESS, // Задача в процессе выполнения
    DONE,        // Задача завершена
    CANCELLED    // Задача отменена
}
