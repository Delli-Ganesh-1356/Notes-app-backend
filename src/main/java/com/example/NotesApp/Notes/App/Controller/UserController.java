package com.example.NotesApp.Notes.App.Controller;


import com.example.NotesApp.Notes.App.Entity.Note;
import com.example.NotesApp.Notes.App.Entity.User;
import com.example.NotesApp.Notes.App.Repository.UserRepository;
import com.example.NotesApp.Notes.App.Service.NoteService;
import com.example.NotesApp.Notes.App.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final NoteService noteService;
    public UserController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user){
        System.out.println("register endpoint hit");
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
