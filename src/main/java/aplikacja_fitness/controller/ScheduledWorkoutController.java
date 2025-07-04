package aplikacja_fitness.controller;

import aplikacja_fitness.Repository.UserRepository;
import aplikacja_fitness.model.User;
import aplikacja_fitness.model.ScheduledWorkout;
import aplikacja_fitness.Service.ScheduledWorkoutService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduledWorkoutController {

    private final ScheduledWorkoutService scheduledWorkoutService;
    private final UserRepository userRepository;

    public ScheduledWorkoutController(ScheduledWorkoutService scheduledWorkoutService, UserRepository userRepository) {
        this.scheduledWorkoutService = scheduledWorkoutService;
        this.userRepository = userRepository;
    }

    // Tworzenie zaplanowanego treningu (user pobierany z tokena)
    @PostMapping
    public ResponseEntity<ScheduledWorkout> schedule(@RequestBody ScheduledWorkout workout, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        workout.setUser(user);
        ScheduledWorkout saved = scheduledWorkoutService.schedule(workout); // zakładam, że przyjmuje tylko workout, nie userId
        return ResponseEntity.ok(saved);
    }

    // Pobierz zaplanowane treningi użytkownika w zakresie dat
    @GetMapping
    public List<ScheduledWorkout> getSchedule(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            Principal principal
    ) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return scheduledWorkoutService.findByUserIdAndDateBetween(user.getId(), start, end);
    }

    // Pobierz pojedynczy zaplanowany trening po jego ID
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduledWorkout> getById(@PathVariable Long scheduleId, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return scheduledWorkoutService.findByIdAndUser(scheduleId, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Usuń zaplanowany trening po jego ID
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> delete(@PathVariable Long scheduleId, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        scheduledWorkoutService.deleteByIdAndUser(scheduleId, user);
        return ResponseEntity.noContent().build();
    }
}

