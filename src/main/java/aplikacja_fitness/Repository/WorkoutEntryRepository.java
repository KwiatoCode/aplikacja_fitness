package aplikacja_fitness.Repository;

import aplikacja_fitness.model.WorkoutEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutEntryRepository extends JpaRepository<WorkoutEntry, Long> {
    List<WorkoutEntry> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);

    @Query("SELECT SUM(w.durationMinutes) FROM WorkoutEntry w WHERE w.user.id = :userId AND w.date BETWEEN :start AND :end")
    Integer sumDurationByUserIdAndDateBetween(@Param("userId") Long userId,
                                              @Param("start") LocalDate start,
                                              @Param("end") LocalDate end);
}
