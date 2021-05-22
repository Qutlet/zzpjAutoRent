package pl.zzpj.autorent.autorent.repositories;

import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;
import pl.zzpj.autorent.autorent.firestore.AbstractFirestoreRepository;
import pl.zzpj.autorent.autorent.model.Car;

@Repository
public class CarRepository extends AbstractFirestoreRepository<Car> {
    protected CarRepository(Firestore firestore) {
        super(firestore, "cars");
    }
}
