package pl.zzpj.autorent.autorent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.autorent.autorent.firestore.DocumentId;

import java.util.ArrayList;
import java.util.List;

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
    private List<Comment> commentList;
    private double engine;       //1.4
    private double enginePower;  //40kM
    private String color;        //czerwony
    private String gearBox;      //manualna/automatyczna
    private String fuel;         //beznyna/diesel/gaz
    private String description;  //fajna szybka furka
    private String country;      //Czechy
    private double priceForDay;  //100 za dzien
    private String photo;

    public Car(String carName, String carBrandName, String carModelName) {
        this.carName = carName;
        this.carBrandName = carBrandName;
        this.carModelName = carModelName;
        this.commentList = new ArrayList<>();
    }

}
