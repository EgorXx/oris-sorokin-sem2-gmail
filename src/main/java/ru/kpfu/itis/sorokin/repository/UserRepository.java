package ru.kpfu.itis.sorokin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.sorokin.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = "select u from User u where u.username = :username")
    Optional<User> getByUsername(String username);

    @Query(value = "select * from users u where u.username = ?1", nativeQuery = true)
    Optional<User> getByUsernameNative(String username);

    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    Optional<User> findByVerificationToken(String token);
}
