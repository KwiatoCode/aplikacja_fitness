package aplikacja_fitness.controller;

import aplikacja_fitness.model.WorkoutEntry;
import aplikacja_fitness.Service.WorkoutEntryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/entries")
public class WorkoutEntryController {
    private final WorkoutEntryService service;
    public WorkoutEntryController(WorkoutEntryService service) { this.service = service; }

    @GetMapping
    public List<WorkoutEntry> getEntries(@PathVariable Long userId,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.findByUserIdAndDateBetween(userId, start, end);
    }

    // Podsumowanie czasu treningu w tygodniu (lub innym zakresie)
    @GetMapping("/summary")
    public Integer getSummary(@PathVariable Long userId,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        Integer sum = service.sumDurationByUserIdAndDateBetween(userId, start, end);
        return sum != null ? sum : 0;
    }

    @PostMapping
    public WorkoutEntry addEntry(@PathVariable Long userId, @RequestBody WorkoutEntry entry) {
        // tu możesz dodać dodatkową walidację userId vs entry.user.id
        return service.save(entry);
    }
}
