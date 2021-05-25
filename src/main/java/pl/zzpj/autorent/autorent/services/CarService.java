package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.repositories.CarRepository;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    //
//    public CarEntity getCar(long id) {
//        return carRepository.findById(id).orElseThrow();
//    }
//
//    // TODO: 03.05.2021 check car ownership
//    // TODO: 03.05.2021 check if car is rented before upgrade
    public void updateCar(Car car) {
        carRepository.save(car);
        //return updatedCar;
    }

    //
//    // TODO: 03.05.2021 edit car
//
    public void addCar(Car car) {
        carRepository.save(car);
    }

    //
//    // TODO: 03.05.2021 check car ownership
//    // TODO: 03.05.2021 check if car is rented before deleting
    public void deleteCar(long id, Car car) {
        //carRepository.deleteById(id);
        carRepository.delete(car);
    }

    //
    public List<Car> getAllCars() {
        return carRepository.retrieveAll();
    }
//
//    // TODO: 03.05.2021 add method getAllNoRentedCar
//
}
