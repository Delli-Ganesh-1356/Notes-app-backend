package com.example.NotesApp.Notes.App.Controller;

import com.example.NotesApp.Notes.App.Entity.Note;
import com.example.NotesApp.Notes.App.Entity.User;
import com.example.NotesApp.Notes.App.Service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/{userId}")
    public Note createNote(@PathVariable Long userId, @RequestBody Note note){
        return noteService.createNote(userId, note);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllNotesPublic() {
        List<Note> notes = noteService.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Note>> getNotesByUser(@PathVariable Long userId){
        List<Note> notes = noteService.getNotesByUser(userId);
        return ResponseEntity.ok(notes);
    }


    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable Long noteId, @RequestBody Note note){
        return ResponseEntity.ok(noteService.updateNotes(noteId, note));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable Long noteId){
        noteService.deleteNotes(noteId);
        return ResponseEntity.ok("The note deleted successfully");
    }

}
