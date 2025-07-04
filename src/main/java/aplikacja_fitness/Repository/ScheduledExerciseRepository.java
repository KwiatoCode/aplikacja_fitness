package aplikacja_fitness.Repository;

import aplikacja_fitness.model.ScheduledExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledExerciseRepository extends JpaRepository<ScheduledExercise, Long> {
    List<ScheduledExercise> findByScheduledWorkoutId(Long scheduledWorkoutId);
}