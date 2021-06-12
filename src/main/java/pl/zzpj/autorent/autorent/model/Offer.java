package pl.zzpj.autorent.autorent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.autorent.autorent.firestore.DocumentId;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "offers")
public class Offer {
    @DocumentId
    private String id;
    private String carID;
    private String ownerID;
    private String clientID;
    private double price;
    private String offerName;
    private String description;
    private Date creationDate;// = new Date(new java.util.Date().getTime());
    private String phone;
    private String email;
    private boolean rented;
    //zdiecie
    // TODO: 03.05.2021 add photo field

    public void setRented(boolean rented) {
        this.rented = rented;
    }


    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}
