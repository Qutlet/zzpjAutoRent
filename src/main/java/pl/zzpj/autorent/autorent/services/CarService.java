package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.zzpj.autorent.autorent.persistance.CarRepository;
import pl.zzpj.autorent.autorent.persistance.entities.CarEntity;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public CarEntity getCar(long id){
        return carRepository.findById(id).orElseThrow();
    }

    public CarEntity updateCar(CarEntity car){
        CarEntity updatedCar = carRepository.save(car);
        return updatedCar;
    }

    public CarEntity addCar(CarEntity car) {
        CarEntity newCar = carRepository.save(car);
        return newCar;
    }

    public List<CarEntity> getAllMeals() {
        return carRepository.findAll();
    }
}
