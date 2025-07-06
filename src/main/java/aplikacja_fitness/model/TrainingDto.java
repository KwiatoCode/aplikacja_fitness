package aplikacja_fitness.model;

import lombok.Data;
import java.util.List;

@Data
public class TrainingDto {
    private String name;
    private List<Long> exerciseIds;
}
