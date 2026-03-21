package ru.kpfu.itis.sorokin.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.sorokin.dto.UserCreateDto;
import ru.kpfu.itis.sorokin.dto.UserDto;
import ru.kpfu.itis.sorokin.dto.UserRegisterDto;
import ru.kpfu.itis.sorokin.model.Role;
import ru.kpfu.itis.sorokin.model.User;
import ru.kpfu.itis.sorokin.repository.RoleRepository;
import ru.kpfu.itis.sorokin.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserDto(u.getId(), u.getUsername()))
                .toList();
    }

    public void register(UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.username();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        String password = userRegisterDto.password();

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRoles(List.of(role));

        try {
            userRepository.save(user);
        }  catch (Exception e) {
            throw new RuntimeException("Ошибка сохранения пользователя: " + e.getMessage());
        }
    }

    public UserDto create(UserCreateDto user) {
        User user2 = new User();
        user2.setUsername(user.username());

        User user1 = userRepository.save(user2);
        return new UserDto(user1.getId(), user1.getUsername());
    }

    public UserDto read(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не существует"));

        return new UserDto(user.getId(), user.getUsername());
    }

    public UserDto update(UserDto userUpdateDto) {
        if (!userRepository.existsById(userUpdateDto.id())) {
            throw new RuntimeException("Пользователь с таким id не существует");
        }

        User user1 = new User();
        user1.setId(userUpdateDto.id());
        user1.setUsername(userUpdateDto.username());

        User user = userRepository.save(user1);
        return new UserDto(user.getId(), user.getUsername());
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Пользователь с таким id не существует");
        }

        userRepository.deleteById(id);
    }

}
