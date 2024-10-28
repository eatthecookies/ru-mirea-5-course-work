package ru.mirea.familytaskmanagement.controllers;

import ru.mirea.familytaskmanagement.models.Family;
import ru.mirea.familytaskmanagement.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/families")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    // Регистрация семьи
    @PostMapping("/register")
    public ResponseEntity<String> registerFamily(@RequestBody Family family) {
        try {
            familyService.registerFamily(family);
            return ResponseEntity.ok("Family registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    // Получение информации о семье по ID
    @GetMapping("/{id}")
    public ResponseEntity<Family> getFamilyById(@PathVariable Long id) {
        Family family = familyService.getFamilyById(id);
        return family != null ? ResponseEntity.ok(family) : ResponseEntity.notFound().build();
    }

    // Обновление информации о семье
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFamily(@PathVariable Long id, @RequestBody Family updatedFamily) {
        boolean updated = familyService.updateFamily(id, updatedFamily);
        return updated ? ResponseEntity.ok("Family updated successfully!") : ResponseEntity.notFound().build();
    }
}
