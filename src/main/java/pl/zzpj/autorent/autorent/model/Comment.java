package pl.zzpj.autorent.autorent.model;



import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.autorent.autorent.firestore.DocumentId;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Comment {
    @DocumentId
    private String id;
    private String carID;
    private String clientID;
    private Timestamp time;
    private String message;

    public void setId(String id) { this.id = id; }

}
