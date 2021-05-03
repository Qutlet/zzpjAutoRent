package pl.zzpj.autorent.autorent.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.autorent.autorent.model.Car;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private long carID;
    private long ownerID;
    private long clientID;
    private float price;
    private String offerName;
    private String description;
    private Date creationDate = new Date(new java.util.Date().getTime());
    private String phone;
    private String email;
    private boolean rented;

    // TODO: 03.05.2021 add photo field

    public OfferEntity(long carID, long ownerID, float price, String offerName, String description, String phone, String email) {
        this.carID = carID;
        this.ownerID = ownerID;
        this.price = price;
        this.offerName = offerName;
        this.description = description;
        this.phone = phone;
        this.email = email;
        //this.isRented = isRented;
    }

//    public OfferEntity(int carID, int ownerID, float price, String offerName, String description, String phone, String email) {
//        this.carID = carID;
//        this.ownerID = ownerID;
//        this.price = price;
//        this.offerName = offerName;
//        this.description = description;
//        this.phone = phone;
//        this.email = email;
//        this.isRented = false;
//    }


    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }
}
