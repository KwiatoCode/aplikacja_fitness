package aplikacja_fitness.controller;

import aplikacja_fitness.Repository.UserRepository;
import aplikacja_fitness.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Wyświetlanie wszystkich użytkowników
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Usuwanie użytkownika
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    // Promocja na admina (opcjonalnie)
    @PostMapping("/users/{id}/make-admin")
    public void makeAdmin(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole("ADMIN");
        userRepository.save(user);
    }
}
