package pl.zzpj.autorent.autorent.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
@EqualsAndHashCode(exclude = "offers")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String carName; //Nazwa samochodu np.: szybka fabia
    private String carBrandName; // Skoda
    private String carModelName; // Fabia
    //Silnik
    //Moc silnika
    //kolor
    //skrzyna biegów
    //paliwo (gaz/benzyna/ropa)
    //Opis techniczny
    //Kraj pochodzenia
    //cena za dzien
    // TODO: 03.05.2021 add all needed fields 

    @ManyToMany(mappedBy = "cars")
    private Set<OfferEntity> offers = new HashSet<>();
}
