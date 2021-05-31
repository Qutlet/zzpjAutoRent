package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zzpj.autorent.autorent.exceptions.UserAlreadyExistException;
import pl.zzpj.autorent.autorent.model.User;
import pl.zzpj.autorent.autorent.repositories.UserRepository;
import pl.zzpj.autorent.autorent.security.UserDto;

import java.util.Collections;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRolesNames(Collections.singletonList("USER"));

        return repository.save(user);
    }

    private boolean emailExists(String email) {
        return repository.findBy("email", email) != null;
    }
}
