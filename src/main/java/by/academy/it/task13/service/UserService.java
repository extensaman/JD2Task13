package by.academy.it.task13.service;

import by.academy.it.task13.dto.user.UserDto;
import by.academy.it.task13.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<UserDto> findByUsername(String username);

    List<UserDto> findAllActiveUser();

    boolean addUser(UserDto userDto);

    void saveAll(List<User> list);

    boolean activateUser(String code);
}
