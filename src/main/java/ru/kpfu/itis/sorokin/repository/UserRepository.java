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
    Optional<User> findByUsername(String username);

    @Query(value = "select u from User u where u.username = :username")
    Optional<User> getByUsername(String username);

    @Query(value = "select * from users u where u.username = ?1", nativeQuery = true)
    Optional<User> getByUsernameNative(String username);

    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);
}
