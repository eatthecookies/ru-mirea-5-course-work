package ru.mirea.familytaskmanagement.services;

import ru.mirea.familytaskmanagement.models.Family;
import ru.mirea.familytaskmanagement.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    // Регистрация новой семьи
    public void registerFamily(Family family) {
        familyRepository.save(family);
    }

    // Получение информации о семье по ID
    public Family getFamilyById(Long id) {
        Optional<Family> family = familyRepository.findById(id);
        return family.orElse(null);
    }

    // Обновление информации о семье
    public boolean updateFamily(Long id, Family updatedFamily) {
        Optional<Family> familyOptional = familyRepository.findById(id);
        if (familyOptional.isPresent()) {
            Family family = familyOptional.get();
            family.setName(updatedFamily.getName());
            // Здесь можно добавить другие поля для обновления
            familyRepository.save(family);
            return true;
        }
        return false;
    }
}
