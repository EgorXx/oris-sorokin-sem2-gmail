package ru.kpfu.itis.sorokin.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.sorokin.dto.UserDto;
import ru.kpfu.itis.sorokin.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserDto(u.getId(), u.getName()))
                .toList();
    }

}
