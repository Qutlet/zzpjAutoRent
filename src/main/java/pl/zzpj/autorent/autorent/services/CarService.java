package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.model.Comment;
import pl.zzpj.autorent.autorent.repositories.CarRepository;
import pl.zzpj.autorent.autorent.repositories.CommentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private CarRepository carRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCar(String id) {
        return carRepository.get(id).orElseThrow();
    }

    // TODO: 03.05.2021 check car ownership
    public void updateCar(String id, Car car) {
        if (!car.isRented()) {
            carRepository.update(id, car);
        }
    }

    public void rentCar(String id) {
        Car car = getCar(id);
        car.setRented(true);
        carRepository.update(id, car);
    }

    public void addCar(Car car) {
        carRepository.save(car);
    }

    // TODO: 03.05.2021 check car ownership
    public void deleteCar(String id) {
        Car car = getCar(id);
        List<Comment> comments = car.getCommentList();
        comments.forEach(comment -> commentRepository.deleteById(comment.getId()));

        if (!car.isRented()) {
            carRepository.deleteById(id);
        }
    }

    public List<Car> getAllCars() {
        return carRepository.retrieveAll();
    }

    // TODO: 03.05.2021 add method getAllNoRentedCar

    public List<Car> getAllNoRentedCars() {
        List<Car> cars = carRepository.retrieveAll();
        cars.removeIf(Car::isRented);
        return cars;
    }

}
