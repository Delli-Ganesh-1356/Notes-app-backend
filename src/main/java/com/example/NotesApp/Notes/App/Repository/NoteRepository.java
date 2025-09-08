package com.example.NotesApp.Notes.App.Repository;

import com.example.NotesApp.Notes.App.Entity.Note;
import com.example.NotesApp.Notes.App.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
    List<Note> findByUserId(Long userId);
}
