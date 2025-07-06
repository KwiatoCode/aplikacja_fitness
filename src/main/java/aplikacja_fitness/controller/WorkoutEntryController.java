package aplikacja_fitness.controller;
import aplikacja_fitness.Repository.UserRepository;
import aplikacja_fitness.model.User;
import aplikacja_fitness.model.WorkoutEntry;
import aplikacja_fitness.Service.WorkoutEntryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class WorkoutEntryController {
    private final WorkoutEntryService service;
    private final UserRepository userRepository;

    public WorkoutEntryController(WorkoutEntryService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<WorkoutEntry> getEntries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        return service.findByUserIdAndDateBetween(user.getId(), start, end);
    }

    @GetMapping("/summary")
    public Integer getSummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Integer sum = service.sumDurationByUserIdAndDateBetween(user.getId(), start, end);
        return sum != null ? sum : 0;
    }

    @PostMapping
    public WorkoutEntry addEntry(@RequestBody WorkoutEntry entry, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        entry.setUser(user);
        return service.save(entry);
    }
}
