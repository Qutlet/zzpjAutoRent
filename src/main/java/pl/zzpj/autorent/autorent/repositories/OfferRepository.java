package pl.zzpj.autorent.autorent.repositories;

import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;
import pl.zzpj.autorent.autorent.firestore.AbstractFirestoreRepository;
import pl.zzpj.autorent.autorent.model.Offer;

@Repository
public class OfferRepository extends AbstractFirestoreRepository<Offer> {
    protected OfferRepository(Firestore firestore) {
        super(firestore, "offers");
    }
}
