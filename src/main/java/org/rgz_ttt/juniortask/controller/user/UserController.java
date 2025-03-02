package org.rgz_ttt.juntask.controller.user;

import lombok.RequiredArgsConstructor;
import org.rgz_ttt.juntask.dto.UserDto;
import org.rgz_ttt.juntask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        logger.info("Получен запрос на создание пользователя: {}", userDto);
        notNull(userDto);
        userService.createUser(userDto);
        logger.debug("Пользователь успешно создан: {}", userDto);
    }

    @GetMapping("/{id}")
    public Optional<UserDto> getUser(@PathVariable long id) {
        logger.info("Получен запрос на получение пользователя с ID: {}", id);
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        logger.info("Получен запрос на обновление пользователя с ID {}: {}", id, userDto);
        notNull(userDto);
        userService.updateUser(id, userDto);
    }

    @DeleteMapping
    public void deleteUser(@PathVariable long id) {
        logger.info("Получен запрос на удаление пользователя с ID: {}", id);
        userService.deleteUser(id);
        logger.debug("Пользователь с ID {} успешно удален", id);
    }

    private void notNull(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("userDto cannot be null");
        }
    }
}
