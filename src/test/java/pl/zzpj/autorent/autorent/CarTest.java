package pl.zzpj.autorent.autorent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.zzpj.autorent.autorent.controllers.CarController;
import pl.zzpj.autorent.autorent.persistance.CarRepository;
import pl.zzpj.autorent.autorent.persistance.entities.CarEntity;
import pl.zzpj.autorent.autorent.services.CarService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {AutorentApplication.class})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @Before
    public void setUp() {
        carRepository.deleteAllInBatch();

        assertThat(carRepository.count()).isEqualTo(0);
    }

//    @Test
//    void contextLoads() {
//        assertThat(carController).isNotNull();
//    }

    @Test
    public void addCarTest() {
        //CarEntity car = carRepository.save(new CarEntity(1,"test","test","test"));
        CarEntity car = carService.addCar(new CarEntity(1, "test", "test", "test"));
        assertThat(carRepository.count()).isEqualTo(1);
    }
}
