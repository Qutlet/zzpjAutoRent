package pl.zzpj.autorent.autorent.controllers;

import jdk.jfr.MemoryAddress;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import pl.zzpj.autorent.autorent.model.Message;


@Controller
public class MessageController {
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public Message register(@Payload Message message, SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        simpMessageHeaderAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }
    public Message sendMessage(@Payload Message message){
        return message;
    }
}
