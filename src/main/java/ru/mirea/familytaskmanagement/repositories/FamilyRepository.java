package ru.mirea.familytaskmanagement.repositories;

import ru.mirea.familytaskmanagement.models.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
    // Здесь можно добавить дополнительные методы для поиска семей, если потребуется
}
