package ru.mirea.familytaskmanagement.repositories;

import ru.mirea.familytaskmanagement.models.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family, Long> {
    Family findFamilyByName(String familyName);
    // Здесь можно добавить дополнительные методы для поиска семей, если потребуется
}
