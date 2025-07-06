package aplikacja_fitness.Service;

import aplikacja_fitness.Repository.ScheduledWorkoutRepository;
import aplikacja_fitness.Repository.UserRepository;
import aplikacja_fitness.model.ScheduledWorkout;
import aplikacja_fitness.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduledWorkoutService {

    private final ScheduledWorkoutRepository scheduledWorkoutRepository;
    private final UserRepository userRepository;

    public ScheduledWorkoutService(ScheduledWorkoutRepository scheduledWorkoutRepository, UserRepository userRepository) {
        this.scheduledWorkoutRepository = scheduledWorkoutRepository;
        this.userRepository = userRepository;
    }

    // Planuje trening dla danego użytkownika (user pobierany w kontrolerze!)
    public ScheduledWorkout schedule(ScheduledWorkout workout) {
        // zakładamy, że w kontrolerze ustawiono workout.setUser(user)
        return scheduledWorkoutRepository.save(workout);
    }

    // Pobierz wszystkie treningi użytkownika w danym zakresie dat
    public List<ScheduledWorkout> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end) {
        return scheduledWorkoutRepository.findByUserIdAndDateBetween(userId, start, end);
    }

    // Pobierz wszystkie treningi użytkownika w danym zakresie dat (lepsza wersja z User)
    public List<ScheduledWorkout> findByUserAndDateBetween(User user, LocalDate start, LocalDate end) {
        return scheduledWorkoutRepository.findByUserAndDateBetween(user, start, end);
    }

    // Pobierz pojedynczy trening tylko jeśli należy do usera!
    public Optional<ScheduledWorkout> findByIdAndUser(Long id, User user) {
        return scheduledWorkoutRepository.findByIdAndUser(id, user);
    }

    // Usuń trening jeśli należy do usera!
    public void deleteByIdAndUser(Long id, User user) {
        scheduledWorkoutRepository.findByIdAndUser(id, user)
                .ifPresent(scheduledWorkoutRepository::delete);
    }

    // (opcjonalnie) pobierz wszystkie zaplanowane treningi usera
    public List<ScheduledWorkout> findByUser(User user) {
        return scheduledWorkoutRepository.findByUser(user);
    }
    public boolean existsByUserAndDate(User user, LocalDate date) {
        return scheduledWorkoutRepository.existsByUserAndDate(user, date);
    }

}


