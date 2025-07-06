package aplikacja_fitness.Repository;

import aplikacja_fitness.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
