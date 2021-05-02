package pl.zzpj.autorent.autorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpj.autorent.autorent.persistance.entities.CarEntity;
import pl.zzpj.autorent.autorent.services.CarService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(path = "/cars", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCars(){
        final List<CarEntity> cars = carService.getAllMeals();
        return ResponseEntity.ok(cars);
    }
}
