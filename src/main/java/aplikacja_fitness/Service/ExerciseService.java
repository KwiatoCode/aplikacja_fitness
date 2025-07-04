package aplikacja_fitness.Service;

import aplikacja_fitness.Repository.ExerciseRepository;
import aplikacja_fitness.model.Exercise;
import aplikacja_fitness.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    private final ExerciseRepository repo;

    public ExerciseService(ExerciseRepository repo) {
        this.repo = repo;
    }

    public List<Exercise> findAllForUser(User user) {
        return repo.findByUser(user);
    }

    public Optional<Exercise> findByIdAndUser(Long id, User user) {
        return repo.findById(id).filter(e -> e.getUser().equals(user));
    }

    public Exercise save(Exercise exercise) {
        return repo.save(exercise);
    }

    public void deleteByIdAndUser(Long id, User user) {
        repo.findById(id).filter(e -> e.getUser().equals(user))
                .ifPresent(repo::delete);
    }
}

