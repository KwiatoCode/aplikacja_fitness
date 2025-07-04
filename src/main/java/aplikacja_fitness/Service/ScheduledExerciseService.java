package aplikacja_fitness.Service;

import aplikacja_fitness.Repository.ScheduledExerciseRepository;
import aplikacja_fitness.model.ScheduledExercise;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduledExerciseService {
    private final ScheduledExerciseRepository repo;

    public ScheduledExerciseService(ScheduledExerciseRepository repo) {
        this.repo = repo;
    }

    public List<ScheduledExercise> findAll() {
        return repo.findAll();
    }

    public Optional<ScheduledExercise> findById(Long id) {
        return repo.findById(id);
    }

    public List<ScheduledExercise> findByScheduledWorkoutId(Long scheduledWorkoutId) {
        return repo.findByScheduledWorkoutId(scheduledWorkoutId);
    }

    public ScheduledExercise save(ScheduledExercise scheduledExercise) {
        return repo.save(scheduledExercise);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
