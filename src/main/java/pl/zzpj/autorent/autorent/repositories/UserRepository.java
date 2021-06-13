package pl.zzpj.autorent.autorent.repositories;

import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;
import pl.zzpj.autorent.autorent.firestore.AbstractFirestoreRepository;
import pl.zzpj.autorent.autorent.model.User;

@Repository
public class UserRepository extends AbstractFirestoreRepository<User> {
    protected UserRepository(Firestore firestore) {
        super(firestore, "users");
    }
}
