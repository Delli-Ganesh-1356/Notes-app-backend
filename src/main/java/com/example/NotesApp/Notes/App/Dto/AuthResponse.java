package com.example.NotesApp.Notes.App.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private Long userId;  // better to keep it Long instead of String

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
}
