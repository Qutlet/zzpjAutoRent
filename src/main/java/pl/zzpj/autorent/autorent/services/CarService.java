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

    /**
     * Constructor
     * @param carRepository
     */
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Gets a car by its id
     * @param id
     * @return
     */
    public Car getCar(String id) {
        return carRepository.get(id).orElseThrow();
    }

    /**
     * Updates car in databese
     * @param id
     * @param car
     */
    public void updateCar(String id, Car car) {
        if (!car.isRented()) {
            carRepository.update(id, car);
        }
    }

    /**
     * Rents a car
     * @param id
     */
    public void rentCar(String id) {
        Car car = getCar(id);
        car.setRented(true);
        carRepository.update(id, car);
    }

    /**
     * Adds car
     * @param car
     */
    public void addCar(Car car) {
        carRepository.save(car);
    }

    /**
     * Adds comment to a car by its id
     * @param id
     * @param comment
     */
    public void addComment(String id, Comment comment) {
        Car car = getCar(id);
        car.getCommentList().add(comment);
        carRepository.update(id, car);
    }

    /**
     * Delete comment from car
     * @param id
     * @param comment
     */
    public void deleteComment(String id, Comment comment) {
        Car car = getCar(id);
        car.getCommentList().remove(comment);
        carRepository.update(id, car);
    }

    /**
     * Deletes car by its id
     * @param id
     */
    public void deleteCar(String id) {
        Car car = getCar(id);

        if (!car.isRented()) {
            List<Comment> comments = car.getCommentList();
            comments.forEach(comment -> commentRepository.deleteById(comment.getId()));
            carRepository.deleteById(id);
        }
    }

    /**
     * Gets all cars from database
     * @return
     */
    public List<Car> getAllCars() {
        return carRepository.retrieveAll();
    }

    /**
     * Gets all cars that are not rented from database
     * @return
     */
    public List<Car> getAllNoRentedCars() {
        List<Car> cars = carRepository.retrieveAll();
        cars.removeIf(Car::isRented);
        return cars;
    }

    /**
     * Gets all cars that are not rented and belong to specific user from database
     * @param ownerId
     * @return
     */
    public List<Car> getAllNoRentedCarsFor(String ownerId) {
        List<Car> cars = carRepository.retrieveAll();
        cars.removeIf(c -> (!c.isRented() && c.getOwnerId().equals(ownerId)));
        return cars;
    }

}
