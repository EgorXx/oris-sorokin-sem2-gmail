package ru.kpfu.itis.sorokin.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.sorokin.dto.NoteFormDto;
import ru.kpfu.itis.sorokin.dto.NoteViewDto;
import ru.kpfu.itis.sorokin.model.Note;
import ru.kpfu.itis.sorokin.service.CustomUserDetails;
import ru.kpfu.itis.sorokin.service.NoteService;

import java.util.List;

@Controller
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String getNotes(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long userId = ((CustomUserDetails) userDetails).getId();

        List<NoteViewDto> notes = noteService.getMyNotes(userId);

        model.addAttribute("notes", notes);
        model.addAttribute("user", userDetails);

        return "notes";
    }

    @GetMapping("/notes/public")
    public String getPublicNotes(Model model) {
        List<NoteViewDto> notes = noteService.getPublicNotes();

        model.addAttribute("notes", notes);

        return "public_notes";
    }

    @GetMapping("/notes/create")
    public String createNote(Model model) {
        NoteFormDto note = new NoteFormDto();
        note.setPublicNote(false);
        model.addAttribute("note", note);
        model.addAttribute("formAction", "/notes/create");
        return "note_form";
    }

    @PostMapping("/notes/create")
    public String create(NoteFormDto noteForm,
                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        noteService.create(userDetails.getId(), noteForm);
        return "redirect:/notes";
    }

    @PostMapping("/notes/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       NoteFormDto noteForm,
                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        noteService.update(id, userDetails.getId(), noteForm);
        return "redirect:/notes";
    }

    @GetMapping("/notes/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model,
                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        NoteFormDto note = noteService.getByIdForEdit(id, userDetails.getId());
        model.addAttribute("note", note);
        model.addAttribute("formAction", "/notes/" + id + "/edit");
        return "note_form";
    }

    @PostMapping("/notes/{id}/delete")
    public String deleteNote(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        noteService.deleteById(id, userDetails.getId());
        return "redirect:/notes";
    }
}
