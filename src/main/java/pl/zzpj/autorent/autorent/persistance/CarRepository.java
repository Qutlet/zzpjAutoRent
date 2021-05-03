package pl.zzpj.autorent.autorent.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zzpj.autorent.autorent.persistance.entities.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    // TODO: 03.05.2021 migrate to remote DB 
}
