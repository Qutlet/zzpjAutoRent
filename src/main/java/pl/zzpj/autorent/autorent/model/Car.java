package pl.zzpj.autorent.autorent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
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
    // TODO: 03.05.2021 add all need fields
}
