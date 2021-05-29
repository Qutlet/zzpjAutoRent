package pl.zzpj.autorent.autorent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import pl.zzpj.autorent.autorent.firestore.DocumentId;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @DocumentId
    private String id;
    private String carID;
    private String ownerID;
    private String clientID;
    private float price;
    private String offerName;
    private String description;
    private Date creationDate = new Date(new java.util.Date().getTime());
    private String phone;
    private String email;
    private boolean rented;
    //zdiecie
    // TODO: 03.05.2021 add photo field

    public Offer(String carID, String ownerID, float price, String offerName, String description, String phone, String email) {
        this.carID = carID;
        this.ownerID = ownerID;
        this.price = price;
        this.offerName = offerName;
        this.description = description;
        this.phone = phone;
        this.email = email;
        //this.isRented = isRented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}
