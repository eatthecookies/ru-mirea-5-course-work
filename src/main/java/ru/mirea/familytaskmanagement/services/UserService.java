package ru.mirea.familytaskmanagement.services;

import ru.mirea.familytaskmanagement.models.Family;
import ru.mirea.familytaskmanagement.models.User;
import ru.mirea.familytaskmanagement.models.UserRole;
import ru.mirea.familytaskmanagement.repositories.FamilyRepository;
import ru.mirea.familytaskmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;
import static ru.mirea.familytaskmanagement.utils.FamilyIdentifierGenerator.generateFamilyId;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FamilyRepository familyRepository;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName(); // Возвращает имя пользователя
        }
        return null; // Или обработайте случай, когда пользователь не аутентифицирован
    }

    public User getCurrentUser(){
        String username = getCurrentUsername();
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    public boolean registerUser(String username,
                                String password,
                                UserRole role,
                                String family_name) {
        if (userRepository.existsByUsername(username)) {
            return false; // Имя пользователя уже существует
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        if (!Objects.equals(family_name, "")) {
            Family family = familyRepository.findFamilyByName(family_name);
            user.setFamily(family);
        }else {
            Family new_family = new Family();
            new_family.setName(generateFamilyId());
            familyRepository.save(new_family);
            user.setFamily(new_family);
        }
        userRepository.save(user);
        return true;
    }
}
