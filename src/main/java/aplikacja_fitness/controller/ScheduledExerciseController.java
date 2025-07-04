package aplikacja_fitness.controller;

import aplikacja_fitness.model.ScheduledExercise;
import aplikacja_fitness.Service.ScheduledExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scheduled-exercises")
public class ScheduledExerciseController {
    private final ScheduledExerciseService service;
    public ScheduledExerciseController(ScheduledExerciseService service) { this.service = service; }

    @GetMapping("/workout/{workoutId}")
    public List<ScheduledExercise> getByScheduledWorkout(@PathVariable Long workoutId) {
        return service.findByScheduledWorkoutId(workoutId);
    }
}

