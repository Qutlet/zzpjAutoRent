package pl.zzpj.autorent.autorent.model;

import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private String content;
    private String sender;
    private String recipient;
    @ServerTimestamp
    private Date creationTime;

    /**
     * returns string representation of a message
     * @return
     */
    @Override
    public String toString(){
        return "{\"Message\":{"
                + "                        \"content\":\"" + content + "\""
                + ",                         \"sender\":\"" + sender + "\""
                + ",                         \"recipient\":\"" + recipient+ "\""
                + ",                         \"creattionTime\":" + creationTime
                + "}}";
    }

    public enum MessageType{
        CHAT, LEAVE, JOIN
    }
}
