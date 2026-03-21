package ru.kpfu.itis.sorokin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.sorokin.model.Note;
import ru.kpfu.itis.sorokin.model.User;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n WHERE n.author = :author")
    List<Note> findByAuthor(@Param("author") User author);

    @Query(value = "select * from notes n where n.is_public = true", nativeQuery = true)
    List<Note> findByIsPublicTrue();
}
