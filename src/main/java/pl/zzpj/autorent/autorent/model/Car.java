package pl.zzpj.autorent.autorent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.autorent.autorent.firestore.DocumentId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @DocumentId
    private String id;
    private String carName; //Nazwa samochodu np.: szybka fabia
    private String carBrandName; // Skoda
    private String carModelName; // Fabia
    private boolean isRented;

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
    }

}
