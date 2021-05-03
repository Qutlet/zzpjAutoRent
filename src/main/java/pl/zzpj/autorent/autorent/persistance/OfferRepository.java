package pl.zzpj.autorent.autorent.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zzpj.autorent.autorent.persistance.entities.OfferEntity;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    // TODO: 03.05.2021 migrate to remote DB
}
