package com.example.NotesApp.Notes.App.Controller;

import com.example.NotesApp.Notes.App.Config.JwtUtil;
import com.example.NotesApp.Notes.App.Dto.AuthRequest;
import com.example.NotesApp.Notes.App.Dto.AuthResponse;
import com.example.NotesApp.Notes.App.Entity.User;
import com.example.NotesApp.Notes.App.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://my-notes-app-v1.netlify.app"
})
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            String token = jwtUtil.generateToken(authRequest.getUsername());

            User user = userRepository.findByUsername(authRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return new AuthResponse(token, user.getId());
        }
        catch (AuthenticationException e){
            throw new RuntimeException("Invalid Username or password");
        }
    }
}
