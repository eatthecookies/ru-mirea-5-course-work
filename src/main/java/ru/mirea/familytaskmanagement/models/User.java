package ru.mirea.familytaskmanagement.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private UserRole role;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "family_id")
    private Family family;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    public void addTask(Task task){
        tasks.add(task);
    }
}
