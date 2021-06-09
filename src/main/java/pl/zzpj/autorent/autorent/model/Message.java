package pl.zzpj.autorent.autorent.model;

import lombok.Data;

@Data
public class Message {
    private String content;
    private String sender;
    private String receiver;

    public enum MessageType{
        CHAT, LEAVE, JOIN
    }
}
