package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.repositories.CarRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCar(String id) {
        return carRepository.get(id).orElseThrow();
    }
//
//    // TODO: 03.05.2021 check car ownership
//    // TODO: 03.05.2021 check if car is rented before upgrade
    public void updateCar(String id, Car car) {
        carRepository.update(id,car);
        //return updatedCar;
    }

    public void rentCar(String id) {
        Car car = getCar(id);
        car.setRented(true);
        carRepository.update(id,car);
    }

//    // TODO: 03.05.2021 edit car
//
    public void addCar(Car car) {
        car.setId(UUID.randomUUID().toString());
        carRepository.save(car);
    }

//    // TODO: 03.05.2021 check car ownership
//    // TODO: 03.05.2021 check if car is rented before deleting
    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }

    public List<Car> getAllCars() {
        return carRepository.retrieveAll();
    }
//
//    // TODO: 03.05.2021 add method getAllNoRentedCar
//
}
