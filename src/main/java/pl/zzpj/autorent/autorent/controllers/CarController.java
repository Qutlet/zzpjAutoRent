package pl.zzpj.autorent.autorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.services.CarService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * Gets all cars from database
     * @return
     */
    @CrossOrigin
    @GetMapping(path = "/cars", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCars() {
        final List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    /**
     * Gets car by id
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping(path = "/cars/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCar(@PathVariable String id) {
        final Car cars = carService.getCar(id);
        return ResponseEntity.ok(cars);
    }

    /**
     * Adds car to database
     * @param car
     * @return
     */
    @CrossOrigin
    @PostMapping(path = "/cars", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addCar(@RequestBody Car car) {
        carService.addCar(car);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates car in database
     * @param id
     * @param car
     * @return
     */
    @CrossOrigin
    @PutMapping(path = "/cars/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity editCar(@PathVariable String id, @RequestBody Car car) {
        carService.updateCar(id,car);
        return ResponseEntity.ok().build();
    }

    /**
     * Rents car by its id
     * @param id
     * @return
     */
    @CrossOrigin
    @PutMapping(path = "/cars/rent/{id}")
    public ResponseEntity rentCar(@PathVariable String id) {
        carService.rentCar(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes car from database
     * @param id
     * @return
     */
    @CrossOrigin
    @DeleteMapping(path = "/cars/{id}")
    public ResponseEntity deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
        return ResponseEntity.accepted().build();
    }

    /**
     * Gets all no rented cars
     * @return
     */
    @CrossOrigin
    @GetMapping(path = "/cars/available", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllNoRented() {
        return ResponseEntity.ok(carService.getAllNoRentedCars());
    }

    /**
     * Gets all no rented cars for specific user
     * @param ownerId
     * @return
     */
    @CrossOrigin
    @GetMapping(path = "/cars/available/{ownerId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllNoRentedFor(@PathVariable String ownerId) {
        return ResponseEntity.ok(carService.getAllNoRentedCarsFor(ownerId));
    }

}
