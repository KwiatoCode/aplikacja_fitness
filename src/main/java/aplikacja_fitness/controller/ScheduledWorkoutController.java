package aplikacja_fitness.controller;

import aplikacja_fitness.Repository.TrainingRepository;
import aplikacja_fitness.Repository.UserRepository;
import aplikacja_fitness.model.Training;
import aplikacja_fitness.model.User;
import aplikacja_fitness.model.ScheduledWorkout;
import aplikacja_fitness.Service.ScheduledWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedule")
public class ScheduledWorkoutController {
    private final ScheduledWorkoutService service;
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    @Autowired
    public ScheduledWorkoutController(
            ScheduledWorkoutService service,
            UserRepository userRepository,
            TrainingRepository trainingRepository
    ) {
        this.service = service;
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    // Dodanie nowego zaplanowanego treningu klasycznego (np. przez edycję)
    @PostMapping
    public ScheduledWorkout schedule(
            @RequestBody ScheduledWorkout workout,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        workout.setUser(user);
        if (workout.getScheduledExercises() != null) {
            workout.getScheduledExercises().forEach(se -> se.setScheduledWorkout(workout));
        }
        return service.schedule(workout);
    }

    // Przypisanie gotowego treningu do dnia
    @PostMapping("/add-training")
    public ResponseEntity<?> addTrainingToDay(
            @RequestBody Map<String, Object> req,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        LocalDate date = LocalDate.parse(req.get("date").toString());
        Long trainingId = Long.valueOf(req.get("trainingId").toString());

        Training training = trainingRepository.findById(trainingId).orElseThrow();

        // Zabezpieczenie: czy na ten dzień już jest zaplanowany trening?
        if (service.existsByUserAndDate(user, date)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Trening na ten dzień już istnieje"));
        }

        ScheduledWorkout workout = new ScheduledWorkout();
        workout.setUser(user);
        workout.setDate(date);
        workout.setTraining(training);

        service.schedule(workout);

        return ResponseEntity.ok(Map.of("message", "Dodano trening do kalendarza"));
    }

    // Pobranie treningów w zadanym przedziale
    @GetMapping
    public List<ScheduledWorkout> getSchedule(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        return service.findByUserAndDateBetween(user, start, end);
    }

    // Usunięcie zaplanowanego treningu
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        service.deleteByIdAndUser(id, user);
    }
}

