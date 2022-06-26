package by.academy.it.task13.repo;

import by.academy.it.task13.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
