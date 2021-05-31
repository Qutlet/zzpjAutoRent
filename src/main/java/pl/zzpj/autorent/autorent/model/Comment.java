package pl.zzpj.autorent.autorent.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.zzpj.autorent.autorent.firestore.DocumentId;

@AllArgsConstructor
@NoArgsConstructor

public class Comment {
    @DocumentId
    private String id;
    private String carID;
    private String clientID;
    private Date time;
    private String message;

}
