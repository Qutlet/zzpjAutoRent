package pl.zzpj.autorent.autorent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assumptions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.zzpj.autorent.autorent.exceptions.UserAlreadyExistException;
import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.model.Offer;
import pl.zzpj.autorent.autorent.model.User;
import pl.zzpj.autorent.autorent.repositories.CarRepository;
import pl.zzpj.autorent.autorent.repositories.OfferRepository;
import pl.zzpj.autorent.autorent.security.UserDto;
import pl.zzpj.autorent.autorent.services.CarService;
import pl.zzpj.autorent.autorent.services.OfferService;
import pl.zzpj.autorent.autorent.services.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.atomicReferenceArray;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {AutorentApplication.class})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserTest {


    @Autowired
    private UserService userService;
    UserDto user;

    @Before
    public void setUp() throws UserAlreadyExistException {
        user = new UserDto( "Jack", "Sparrow", "pirates", "pirates", "jack.sparrow@thecaptain.com");
        //userService.registerNewUserAccount(user);
    }

    @Test
    public void registerNewUserTest() throws UserAlreadyExistException {
        assertThat(userService.registerNewUserAccount(user));

    }

    @Test
    public void getUserIdByEmailTest(){
        assertThat(userService.getUserIdByEmail(user.getEmail()));
    }

    @Test
    public void addRolesToUserTest(){
        List<String> roles = Arrays.asList("TEST", "TEST2", "TEST3");
        assertThat(userService.addRolesToUser(user.getEmail(), roles));
    }

}
