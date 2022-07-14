package by.academy.it.task13.service.impl;

import by.academy.it.task13.AppSetting;
import by.academy.it.task13.dto.certificate.CertificateNameDto;
import by.academy.it.task13.dto.user.UserDto;
import by.academy.it.task13.dto.user.UserNameDto;
import by.academy.it.task13.entity.Certificate;
import by.academy.it.task13.entity.User;
import by.academy.it.task13.mapper.Mapper;
import by.academy.it.task13.repo.UserRepository;
import by.academy.it.task13.service.MailSenderService;
import by.academy.it.task13.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    public static final String MESSAGE_TEMPLATE = "Hi, %s!\nWelcome to Cavalier Horse Club!\nVisit next link to activate your account.\n%s/activate/%s";
    public static final String SUBJECT = "Activation on Cavalier Horse Club";

    private final UserRepository repository;
    private final Mapper<User, UserDto> mapper;
    private final Mapper<User, UserNameDto> mapperName;
    private final MailSenderService mailSenderService;
    private final AppSetting appSetting;

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return Optional.ofNullable(mapper.toDto(repository.findByUsername(username)));
    }

    @Override
    public List<UserDto> findAllActiveUser(){ // TODO Delete this method
        return repository.findAllByActivityIsTrue().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<UserNameDto> findAllUserNameDto(){
        LOGGER.info("findAllUserNameDto");
        List<UserNameDto> userNameDtos = new ArrayList<>();
        for (User user : repository.findAll()) {
            userNameDtos.add(mapperName.toDto(user));
        }
        return userNameDtos;
    }

    @Override
    @Transactional
    public boolean addUser(UserDto userDto) {
        LOGGER.info("save");
        User user = mapper.toEntity(userDto);
        if (repository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setActivationCode(UUID.randomUUID().toString());
        String message = String.format(MESSAGE_TEMPLATE, user.getUsername(),appSetting.getAppUrl(),user.getActivationCode());
        mailSenderService.send(user.getEmail(),
                SUBJECT, message);
        repository.save(user);
        return true;
    }

    @Override
    @Transactional
    public boolean activateUser(String code) {
        User user = repository.findByActivationCode(code);
        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        user.setActivity(true);
        repository.save(user);
        return true;
    }

    @Override
    @Transactional
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
