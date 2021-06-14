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
import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.repositories.CarRepository;
import pl.zzpj.autorent.autorent.services.CarService;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

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

    Car car;

    @Before
    public void setUp() {
        car = new Car("test", "test", "test");
        carService.addCar(car);
    }

    @Test
    public void contextLoads() {
//        assertThat(carController).isNotNull();
    }

    @Test
    public void addCarTest() {
        assertThat(carRepository.get(car.getId()).get().equals(car));
    }

    @Test
    public void updateCarTest() {
        Car car2 = new Car("newName","test","test");
        carService.updateCar(car.getId(),car2);
        assertThat(carRepository.get(car.getId()).get().equals(car2));
    }

    @Test
    public void rentCarTest() {
        carService.rentCar(car.getId());
        assertThat(carRepository.get(car.getId()).get().isRented()).isFalse();
    }

    @Test
    public void deleteCarTest() {
        carService.deleteCar(car.getId());
        assertThat(carRepository.get(car.getId()).get() == null);
    }

    @Test
    public void getAllCarsTest() {
        assertThat(carService.getAllCars().size() != 0);
    }

    @Test
    public void getAllNoRentedCarsTest() throws InterruptedException {
        int size = carService.getAllNoRentedCars().size();
        carService.rentCar(car.getId());
        TimeUnit.SECONDS.sleep(20);
        assertThat(carService.getAllNoRentedCars().size()).isEqualTo(size-1);
    }

}
