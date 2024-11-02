package ru.mirea.familytaskmanagement.models;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class TaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate deadline;
    private Long user;

    // Getters и Setters
}

