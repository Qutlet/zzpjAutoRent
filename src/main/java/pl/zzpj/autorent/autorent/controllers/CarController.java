package pl.zzpj.autorent.autorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.services.CarService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(path = "/cars", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCars() {
        final List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @PostMapping(path = "/cars", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addCar(@RequestBody Car car) {
        carService.addCar(car);
        return ResponseEntity.ok().build();
    }

    //
    @PutMapping(path = "/cars/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity rentCar(@PathVariable long id, @RequestBody Car car) {
        carService.updateCar(car);
        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(URI.create("offers/rent/" + updatedCar.getId()));
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/cars/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCar(@PathVariable long id, @RequestBody Car car) {
        carService.deleteCar(id, car);
        return ResponseEntity.accepted().build();
    }
//
//    // TODO: 03.05.2021 return car
//    // TODO: 03.05.2021 edit car


}
