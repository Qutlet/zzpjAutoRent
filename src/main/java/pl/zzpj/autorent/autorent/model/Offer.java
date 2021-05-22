package pl.zzpj.autorent.autorent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    private int carID;
    private int ownerID;
    private int clientID;
    private float price;
    private String offerName;
    private String description;
    private Date creationDate = new Date(new java.util.Date().getTime());
    private String phone;
    private String email;
    private boolean rented;
    //zdiecie
    // TODO: 03.05.2021 add photo field

    public Offer(int carID, int ownerID, float price, String offerName, String description, String phone, String email, boolean rented) {
        this.carID = carID;
        this.ownerID = ownerID;
        this.price = price;
        this.offerName = offerName;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.rented = rented;
    }
}
