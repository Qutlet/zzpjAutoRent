package pl.zzpj.autorent.autorent.services;

import com.google.api.services.storage.Storage;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.model.Comment;
import pl.zzpj.autorent.autorent.repositories.CarRepository;
import pl.zzpj.autorent.autorent.repositories.CommentRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
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

    public void updateCar(String id, Car car) {
        car.setId(id);
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
        car.setId(UUID.randomUUID().toString());
        carRepository.save(car);
    }

//    public void addComment(String id, Comment comment){
//        Car car = getCar(id);
//        car.getCommentList().add(comment);
//        carRepository.update(id, car);
//    }

    public void deleteCar(String id) {
        Car car = getCar(id);
        List<Comment> comments = car.getCommentList();
        if (comments != null)
            comments.forEach(comment -> commentRepository.deleteById(comment.getId()));

        if (!car.isRented()) {
            carRepository.deleteById(id);
        }
    }

    public List<Car> getAllCars() {
        return carRepository.retrieveAll();
    }

    public List<Car> getAllNoRentedCars() {
        List<Car> cars = carRepository.retrieveAll();
        cars.removeIf(Car::isRented);
        return cars;
    }

}
