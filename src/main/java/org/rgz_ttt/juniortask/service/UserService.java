package org.rgz_ttt.juntask.service;

import lombok.RequiredArgsConstructor;
import org.rgz_ttt.juntask.dto.UserDto;
import org.rgz_ttt.juntask.mapper.UserMapper;
import org.rgz_ttt.juntask.model.User;
import org.rgz_ttt.juntask.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        logger.info("Создание пользователя: {}", userDto);
        User user = userMapper.toEntity(userDto);
        User saveUser = userRepository.save(user);
        logger.debug("Пользователь успешно создан: {}", saveUser);
        return userMapper.toDto(saveUser);
    }

    public Optional<UserDto> getUserById(Long id) {
        logger.info("Получение пользователя по ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            logger.debug("Пользователь найден: {}", user.get());
        } else {
            logger.warn("Пользователь с ID {} не найден", id);
        }
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    public Optional<UserDto> updateUser(Long id, UserDto userDto) {
        logger.info("Обновление пользователя с ID {}: {}", id, userDto);
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDto.username());
                    user.setEmail(userDto.email());
                    User updatedUser = userRepository.save(user);
                    logger.debug("Пользователь успешно обновлен: {}", updatedUser);
                    return userMapper.toDto(updatedUser);
                });
    }

    public void deleteUser(Long id) {
        logger.info("Удаление пользователя с ID: {}", id);
        userRepository.deleteById(id);
        logger.debug("Пользователь с ID {} успешно удален", id);
    }

}
