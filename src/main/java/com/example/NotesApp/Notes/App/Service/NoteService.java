package com.example.NotesApp.Notes.App.Service;

import com.example.NotesApp.Notes.App.Entity.Note;
import com.example.NotesApp.Notes.App.Entity.User;
import com.example.NotesApp.Notes.App.Repository.NoteRepository;
import com.example.NotesApp.Notes.App.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public NoteService(UserRepository userRepository, NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }

    public Note createNote(Long userId, Note note){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            note.setUser(user.get());
            return noteRepository.save(note);
        }
        throw new RuntimeException("User not found with id: " + userId);
    }
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> getNotesByUser(Long userId){
        return noteRepository.findByUserId(userId);
    }

    public Note updateNotes(Long noteId, Note updatednote){
        Note note = noteRepository.findById(noteId)
                    .orElseThrow(() -> new RuntimeException("Note not found with id: " + noteId));
        note.setTitle(updatednote.getTitle());
        note.setContent(updatednote.getContent());

        return noteRepository.save(note);
    }

    public void deleteNotes(Long noteId){
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found with id: "+noteId));
        noteRepository.delete(note);
    }
}
