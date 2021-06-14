package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zzpj.autorent.autorent.exceptions.UserAlreadyExistException;
import pl.zzpj.autorent.autorent.model.User;
import pl.zzpj.autorent.autorent.repositories.UserRepository;
import pl.zzpj.autorent.autorent.security.UserDto;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    /**
     * Constructor
     * @param repository
     * @param passwordEncoder
     */
    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers new user
     * @param userDto
     * @return
     * @throws UserAlreadyExistException
     */
    public boolean registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }
        //TODO: Add validation
        User user = new User();
        user.setId(repository.generateNewDocumentId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRolesNames(Collections.singletonList("USER"));

        return repository.save(user);
    }

    /**
     * Checks if email currently exist in database
     * @param email
     * @return
     */
    private boolean emailExists(String email) {
        return repository.findBy("email", email) != null;
    }

    /**
     * Gets user by its e-mail
     * @param email
     * @return
     */
    public String getUserIdByEmail(String email) {
        Optional<User> userOptional = repository.findBy("email", email).get(0);
        if(userOptional.isPresent()) {
            return userOptional.get().getId();
        }
        else {
            return null;
        }
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
