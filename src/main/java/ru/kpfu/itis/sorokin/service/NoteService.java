package ru.kpfu.itis.sorokin.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.sorokin.dto.NoteFormDto;
import ru.kpfu.itis.sorokin.dto.NoteViewDto;
import ru.kpfu.itis.sorokin.dto.UserDto;
import ru.kpfu.itis.sorokin.dto.UserViewDto;
import ru.kpfu.itis.sorokin.model.Note;
import ru.kpfu.itis.sorokin.model.User;
import ru.kpfu.itis.sorokin.repository.NoteRepository;
import ru.kpfu.itis.sorokin.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<NoteViewDto> getMyNotes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found, id: " + userId));

        List<Note> notes = noteRepository.findByAuthor(user);

        UserViewDto userDto = toUserDto(user);

        return notes.stream()
                .map(n -> {
                    return new NoteViewDto(
                            n.getId(),
                            n.getTitle(),
                            n.getContent(),
                            n.getCreatedAt(),
                            n.getPublic(),
                            userDto
                    );
                }).toList();
    }

    @Transactional
    public List<NoteViewDto> getPublicNotes() {
        List<Note> notes = noteRepository.findByIsPublicTrue();

        return notes.stream()
                .map(n -> {
                    return new NoteViewDto(
                            n.getId(),
                            n.getTitle(),
                            n.getContent(),
                            n.getCreatedAt(),
                            n.getPublic(),
                            toUserDto(n.getAuthor())
                    );
                }).toList();
    }

    private UserViewDto toUserDto(User user) {
        return new UserViewDto(
                user.getId(),
                user.getUsername()
        );
    }

    @Transactional
    public void create(Long userId, NoteFormDto noteFormDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found, id: " + userId));

        Note note = new Note();
        note.setTitle(noteFormDto.getTitle());
        note.setContent(noteFormDto.getContent());
        note.setPublic(noteFormDto.isPublicNote());
        note.setAuthor(user);
        note.setCreatedAt(LocalDateTime.now());

        noteRepository.save(note);
    }

    @Transactional
    public void update(Long noteId, Long userId, NoteFormDto noteFormDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found, id: " + userId));

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found, id: " + noteId));

        if (!note.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("User don't have enough rights for edit note");
        }

        note.setTitle(noteFormDto.getTitle());
        note.setContent(noteFormDto.getContent());
        note.setPublic(noteFormDto.isPublicNote());
    }

    @Transactional
    public NoteFormDto getByIdForEdit(Long noteId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found, id: " + userId));

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found, id: " + noteId));

        if (!note.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("User don't have enough rights for edit note");
        }

        return new NoteFormDto(
                note.getTitle(),
                note.getContent(),
                note.getPublic()
        );
    }

    @Transactional
    public void deleteById(Long noteId, Long userId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found, id: " + noteId));

        if (!note.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("User don't have enough rights for delete note");
        }

        noteRepository.deleteById(noteId);
    }

    @Transactional
    public List<NoteViewDto> getAllNotes() {
        return noteRepository.findAll()
                .stream()
                .map(n -> {
                    return new NoteViewDto(
                            n.getId(),
                            n.getTitle(),
                            n.getContent(),
                            n.getCreatedAt(),
                            n.getPublic(),
                            toUserDto(n.getAuthor())
                    );
                })
                .toList();
    }

    @Transactional
    public void deleteByIdAsAdmin(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found: " + id));
        noteRepository.delete(note);
    }
}
