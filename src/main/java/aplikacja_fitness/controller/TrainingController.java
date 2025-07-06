package aplikacja_fitness.controller;

import aplikacja_fitness.Repository.TrainingRepository;
import aplikacja_fitness.Repository.ExerciseRepository;
import aplikacja_fitness.model.Training;
import aplikacja_fitness.model.TrainingDto;
import aplikacja_fitness.model.Exercise;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {

    private final TrainingRepository trainingRepository;
    private final ExerciseRepository exerciseRepository;

    public TrainingController(TrainingRepository trainingRepository, ExerciseRepository exerciseRepository) {
        this.trainingRepository = trainingRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping
    public List<Training> getAll() {
        return trainingRepository.findAll();
    }

    @PostMapping
    public Map<String, String> createTraining(@RequestBody TrainingDto trainingDto) {
        Training training = new Training();
        training.setName(trainingDto.getName());
        List<Exercise> exercises = trainingDto.getExerciseIds().stream()
                .map(id -> exerciseRepository.findById(id).orElseThrow())
                .collect(Collectors.toList());
        training.setExercises(exercises);
        trainingRepository.save(training);
        return Map.of("message", "Training created");
    }

    @PutMapping("/{id}")
    public Training updateTraining(@PathVariable Long id, @RequestBody TrainingDto dto) {
        Training training = trainingRepository.findById(id).orElseThrow();
        training.setName(dto.getName());
        List<Exercise> exercises = dto.getExerciseIds().stream()
                .map(eid -> exerciseRepository.findById(eid).orElseThrow())
                .collect(Collectors.toList());
        training.setExercises(exercises);
        return trainingRepository.save(training);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trainingRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Training getTraining(@PathVariable Long id) {
        return trainingRepository.findById(id).orElseThrow();
    }
}
