package by.academy.it.task13.service;

import by.academy.it.task13.dto.user.UserDto;
import by.academy.it.task13.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    boolean save(UserDto userDto);

    void saveAll(List<User> list);
}
