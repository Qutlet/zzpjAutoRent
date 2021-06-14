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
    private String ownerId; // id właściciela
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

    /**
     * Car debug constructor
     * @param carName
     * @param carBrandName
     * @param carModelName
     */
    public Car(String carName, String carBrandName, String carModelName) {
        this.carName = carName;
        this.carBrandName = carBrandName;
        this.carModelName = carModelName;
        this.commentList = new ArrayList<>();
    }

    /**
     * Car constructor
     * @param id
     * @param ownerId
     * @param carName
     * @param carBrandName
     * @param carModelName
     * @param isRented
     * @param engine
     * @param enginePower
     * @param color
     * @param gearBox
     * @param fuel
     * @param description
     * @param country
     * @param priceForDay
     * @param photo
     */
    public Car(String id,String ownerId, String carName, String carBrandName, String carModelName, boolean isRented, double engine, double enginePower, String color, String gearBox, String fuel, String description, String country, double priceForDay, String photo) {
        this.id = id;
        this.ownerId = ownerId;
        this.carName = carName;
        this.carBrandName = carBrandName;
        this.carModelName = carModelName;
        this.isRented = isRented;
        this.engine = engine;
        this.enginePower = enginePower;
        this.color = color;
        this.gearBox = gearBox;
        this.fuel = fuel;
        this.description = description;
        this.country = country;
        this.priceForDay = priceForDay;
        this.photo = photo;
        this.commentList = new ArrayList<>();
    }

    /**
     * Car constructor
     * @param id
     * @param carName
     */
    public Car(String id, String carName) {
        this.id = id;
        this.carName = carName;
    }
}
