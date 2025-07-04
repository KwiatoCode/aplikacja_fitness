package aplikacja_fitness.controller;

import aplikacja_fitness.Service.ExerciseService;
import aplikacja_fitness.model.Exercise;
import aplikacja_fitness.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ExerciseService service;

    public ExerciseController(ExerciseService service) { this.service = service; }

    // GET wszystkie ćwiczenia tylko zalogowanego usera
    @GetMapping
    public List<Exercise> getAll(@AuthenticationPrincipal User user) {
        return service.findAllForUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return service.findByIdAndUser(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exercise> create(@RequestBody Exercise exercise, @AuthenticationPrincipal User user) {
        exercise.setUser(user); // ustaw właściciela!
        Exercise saved = service.save(exercise);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        service.deleteByIdAndUser(id, user);
        return ResponseEntity.noContent().build();
    }
}
