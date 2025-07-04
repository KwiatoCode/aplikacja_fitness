package aplikacja_fitness.Repository;

import aplikacja_fitness.model.Exercise;
import aplikacja_fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByUser(User user); // <--- bardzo ważne!
}
