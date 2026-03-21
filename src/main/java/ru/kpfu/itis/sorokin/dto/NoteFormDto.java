package ru.kpfu.itis.sorokin.dto;

public class NoteFormDto {
    private String title;
    private String content;
    private boolean publicNote;

    public NoteFormDto() {}

    public NoteFormDto(String title, String content, boolean isPublic) {
        this.title = title;
        this.content = content;
        this.publicNote = isPublic;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public boolean isPublicNote() { return publicNote; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setPublicNote(boolean isPublic) { this.publicNote = isPublic; }

    @Override
    public String toString() {
        return "NoteFormDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publicNote=" + publicNote +
                '}';
    }
}