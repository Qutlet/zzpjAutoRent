package pl.zzpj.autorent.autorent.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zzpj.autorent.autorent.model.User;
import pl.zzpj.autorent.autorent.repositories.UserRepository;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * this class is used by spring controller to authenticate and authorize user
     * @param email
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user;
        Optional<User> userFromDatabase = userRepository.findBy("email", email).get(0);
        if(userFromDatabase.isPresent()) {
            user = userFromDatabase.get();
        }
        else {
            throw new UsernameNotFoundException("No user with this email");
        }

        return new CustomUserDetails(user);
    }
}
