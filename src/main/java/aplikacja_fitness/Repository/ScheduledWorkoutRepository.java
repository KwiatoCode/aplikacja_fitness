package aplikacja_fitness.Repository;
import aplikacja_fitness.model.User;
import aplikacja_fitness.model.ScheduledWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduledWorkoutRepository extends JpaRepository<ScheduledWorkout, Long> {
    List<ScheduledWorkout> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);

    List<ScheduledWorkout> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);

    Optional<ScheduledWorkout> findByIdAndUser(Long id, User user);

    List<ScheduledWorkout> findByUser(User user);

    boolean existsByUserAndDate(User user, LocalDate date);

}
