package by.academy.it.task13.service.impl;

import by.academy.it.task13.dto.UserDto;
import by.academy.it.task13.entity.User;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.UserRepository;
import by.academy.it.task13.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository repository;
    private final Mapper<User, UserDto> mapper;

    @Override
    public User save(UserDto userDto) {
        LOGGER.info("save");
        return repository.save(mapper.toEntity(userDto));
    }

    @Override
    public void saveAll(List<User> list) {
        LOGGER.info("saveAll");
        repository.saveAll(list);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername");
        User user = repository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User " + username + " not found");
    }
}
