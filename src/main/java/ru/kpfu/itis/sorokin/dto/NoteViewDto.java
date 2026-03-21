package ru.kpfu.itis.sorokin.dto;

import ru.kpfu.itis.sorokin.model.User;

import java.time.LocalDateTime;

public class NoteViewDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Boolean publicNote;
    private UserViewDto author;

    public NoteViewDto() {}

    public NoteViewDto(Long id, String title, String content, LocalDateTime createdAt, Boolean publicNote, UserViewDto author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.publicNote = publicNote;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getPublicNote() {
        return publicNote;
    }

    public UserViewDto getAuthor() {
        return author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublicNote(Boolean publicNote) {
        this.publicNote = publicNote;
    }

    public void setAuthor(UserViewDto author) {
        this.author = author;
    }
}