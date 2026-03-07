package ru.kpfu.itis.sorokin.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.sorokin.dto.UserCreateDto;
import ru.kpfu.itis.sorokin.dto.UserDto;
import ru.kpfu.itis.sorokin.model.User;
import ru.kpfu.itis.sorokin.repository.UserRepository;
import ru.kpfu.itis.sorokin.repository.UserRepositoryHibernate;

import java.util.List;

@Service
public class UserService {
    private final UserRepositoryHibernate userRepositoryHibernate;
    private final UserRepository userRepository;

    public UserService(UserRepositoryHibernate userRepositoryHibernate, UserRepository userRepository) {
        this.userRepositoryHibernate = userRepositoryHibernate;
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserDto(u.getId(), u.getName()))
                .toList();
    }

    public UserDto create(UserCreateDto user) {
        User user2 = new User();
        user2.setName(user.name());

        User user1 = userRepository.save(user2);
        return new UserDto(user1.getId(), user1.getName());
    }

    public UserDto read(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не существует"));

        return new UserDto(user.getId(), user.getName());
    }

    public UserDto update(UserDto userUpdateDto) {
        if (!userRepository.existsById(userUpdateDto.id())) {
            throw new RuntimeException("Пользователь с таким id не существует");
        }

        User user1 = new User();
        user1.setId(userUpdateDto.id());
        user1.setName(userUpdateDto.name());

        User user = userRepository.save(user1);
        return new UserDto(user.getId(), user.getName());
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Пользователь с таким id не существует");
        }

        userRepository.deleteById(id);
    }

}
