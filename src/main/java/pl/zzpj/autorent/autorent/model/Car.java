package pl.zzpj.autorent.autorent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.zzpj.autorent.autorent.firestore.DocumentId;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "cars")
//@EqualsAndHashCode(exclude = "offers")
public class Car {
    @DocumentId
    private String id;
    private String carName; //Nazwa samochodu np.: szybka fabia
    private String carBrandName; // Skoda
    private String carModelName; // Fabia
    private boolean isRented;
    private List<Comment> commentList;

    //Silnik
    //Moc silnika
    //kolor
    //skrzyna biegów
    //paliwo (gaz/benzyna/ropa)
    //Opis techniczny
    //Kraj pochodzenia
    //cena za dzien
    // TODO: 03.05.2021 add all need fields


    public Car(String carName, String carBrandName, String carModelName) {
        this.carName = carName;
        this.carBrandName = carBrandName;
        this.carModelName = carModelName;
        this.commentList = new ArrayList<>();
    }

}
