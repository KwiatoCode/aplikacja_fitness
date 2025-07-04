package aplikacja_fitness.controller;

import aplikacja_fitness.Repository.UserRepository;
import aplikacja_fitness.model.User;
import org.springframework.beans.factory.annotation.Value; // <-- DODAJ TO!
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // ---- DODAJ @Value! ----
    @Value("${jwt.secret}")
    private String jwtSecret;

    public AuthController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // DTO do rejestracji
    public static class RegisterRequest {
        public String username;
        public String email;
        public String password;
    }
    public static class LoginRequest {
        public String username;
        public String password;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterRequest req) {
        if (userRepository.findByUsername(req.username).isPresent()) {
            return Map.of("error", "Username taken!");
        }
        User user = new User();
        user.setUsername(req.username);
        user.setEmail(req.email);
        user.setPasswordHash(passwordEncoder.encode(req.password));
        userRepository.save(user);
        return Map.of("message", "Registered!");
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest req) {
        User user = userRepository.findByUsername(req.username).orElseThrow();
        if (!passwordEncoder.matches(req.password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid password!");
        }
        // TU UŻYJ jwtSecret zamiast "sekret"
        String jwt = io.jsonwebtoken.Jwts.builder()
                .setSubject(req.username)
                .setIssuedAt(new java.util.Date())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 3600_000)) // 1h
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, jwtSecret.getBytes()) // <---
                .compact();
        return Map.of("token", jwt);
    }
}
