package by.academy.it.task13.service;

import by.academy.it.task13.dto.user.UserDto;
import by.academy.it.task13.dto.user.UserNameDto;
import by.academy.it.task13.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<UserDto> findUserDtoByUsername(String username);

    Optional<User> findUserByUsername(String username);

    List<UserDto> findAll();

    List<UserNameDto> findAllUserNameDto();

    boolean addUser(UserDto userDto);

    void saveAll(List<User> list);

    boolean activateUser(String code);
}
