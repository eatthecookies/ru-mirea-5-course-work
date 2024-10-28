package ru.mirea.familytaskmanagement.services;

import ru.mirea.familytaskmanagement.models.User;
import ru.mirea.familytaskmanagement.models.UserRole;
import ru.mirea.familytaskmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(String username, String password, UserRole role) {
        if (userRepository.existsByUsername(username)) {
            return false; // Имя пользователя уже существует
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        userRepository.save(user);
        return true;
    }
}
