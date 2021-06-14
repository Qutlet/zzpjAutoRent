package pl.zzpj.autorent.autorent.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.autorent.autorent.firestore.DocumentId;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Comment {
    @DocumentId
    private String id;
    private String carID;
    private String clientID;
    @ServerTimestamp
    private Date time;
    private String message;

    /**
     * Gets time of comment
     * @return Date
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets time of comment
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Sets id of comment
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


}
