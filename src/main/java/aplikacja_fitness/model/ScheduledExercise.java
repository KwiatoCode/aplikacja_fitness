package aplikacja_fitness.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ScheduledExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference                    // <<< oraz tu
    @ManyToOne(optional = false)
    @JoinColumn(name = "scheduled_workout_id")
    private ScheduledWorkout scheduledWorkout;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private int durationMinutes;
}


