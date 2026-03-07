package ru.kpfu.itis.sorokin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.sorokin.model.User;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    @Query(value = "select u from User u where u.name = :name")
    Optional<User> getByName(String name);

    @Query(value = "select * from users u where u.name = ?1", nativeQuery = true)
    Optional<User> getByNameNative(String name);

    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);
}
