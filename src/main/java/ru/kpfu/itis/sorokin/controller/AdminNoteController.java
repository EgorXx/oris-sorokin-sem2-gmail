package ru.kpfu.itis.sorokin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.sorokin.dto.NoteViewDto;
import ru.kpfu.itis.sorokin.service.NoteService;

import java.util.List;

@RestController
public class AdminNoteController {

    private final NoteService noteService;

    public AdminNoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/admin/notes")
    public List<NoteViewDto> getAllNotes() {
        return noteService.getAllNotes();
    }

    @DeleteMapping("/admin/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteByIdAsAdmin(id);
        return ResponseEntity.ok().build();
    }
}