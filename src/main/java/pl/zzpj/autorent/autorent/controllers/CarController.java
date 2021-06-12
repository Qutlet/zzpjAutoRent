package pl.zzpj.autorent.autorent.controllers;

import com.google.cloud.firestore.DocumentReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.services.CarService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Component
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(path = "/cars", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCars() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
        } else {
            String username = principal.toString();
        }
        final List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }


    @GetMapping(path = "/cars/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCar(@PathVariable String id) {
        final Car cars = carService.getCar(id);
        return ResponseEntity.ok(cars);
    }


    @PostMapping(path = "/cars", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addCar(@RequestBody Car car) {
        carService.addCar(car);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/cars/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity editCar(@PathVariable String id, @RequestBody Car car) {
        carService.updateCar(id,car);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/cars/rent/{id}")
    public ResponseEntity rentCar(@PathVariable String id) {
        carService.rentCar(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/cars/{id}")
    public ResponseEntity deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/cars/available", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllNoRented() {
        return ResponseEntity.ok(carService.getAllNoRentedCars());
    }

    @CrossOrigin
    @GetMapping(path = "/cars/available/{ownerId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllNoRentedFor(@PathVariable String ownerId) {
        return ResponseEntity.ok(carService.getAllNoRentedCarsFor(ownerId));
    }

}
