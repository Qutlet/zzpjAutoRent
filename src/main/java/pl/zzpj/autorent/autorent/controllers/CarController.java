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

    @CrossOrigin
    @GetMapping(path = "/cars", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCars() {
        final List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @CrossOrigin
    @GetMapping(path = "/cars/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCar(@PathVariable String id) {
        final Car cars = carService.getCar(id);
        return ResponseEntity.ok(cars);
    }

    @CrossOrigin
    @PostMapping(path = "/cars", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addCar(@RequestBody Car car, @RequestHeader("image-path") String path) {
        carService.addCar(car,path);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping(path = "/cars/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity editCar(@PathVariable String id, @RequestBody Car car) {
        carService.updateCar(id,car);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping(path = "/cars/rent/{id}")
    public ResponseEntity rentCar(@PathVariable String id) {
        carService.rentCar(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @DeleteMapping(path = "/cars/{id}")
    public ResponseEntity deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
        return ResponseEntity.accepted().build();
    }

    @CrossOrigin
    @GetMapping(path = "/cars/available", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllNoRented() {
        return ResponseEntity.ok(carService.getAllNoRentedCars());
    }

//    @PutMapping(path = "/cars/photo/{id}")
//    public ResponseEntity addPhoto(@PathVariable String photo) {
//
//        return ResponseEntity.ok().build();
//    }
}
