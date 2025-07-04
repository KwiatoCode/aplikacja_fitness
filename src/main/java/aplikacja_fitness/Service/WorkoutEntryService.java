package aplikacja_fitness.Service;

import aplikacja_fitness.Repository.WorkoutEntryRepository;
import aplikacja_fitness.model.WorkoutEntry;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutEntryService {
    private final WorkoutEntryRepository repo;

    public WorkoutEntryService(WorkoutEntryRepository repo) {
        this.repo = repo;
    }

    public List<WorkoutEntry> findAll() {
        return repo.findAll();
    }

    public Optional<WorkoutEntry> findById(Long id) {
        return repo.findById(id);
    }

    public WorkoutEntry save(WorkoutEntry workoutEntry) {
        return repo.save(workoutEntry);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public List<WorkoutEntry> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end) {
        return repo.findByUserIdAndDateBetween(userId, start, end);
    }

    public Integer sumDurationByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end) {
        return repo.sumDurationByUserIdAndDateBetween(userId, start, end);
    }
}
