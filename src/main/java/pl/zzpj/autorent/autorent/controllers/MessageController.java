package pl.zzpj.autorent.autorent.controllers;

import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pl.zzpj.autorent.autorent.model.Message;


@Controller
@Component
public class MessageController {
    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.messagingTemplate.setMessageConverter(new SimpleMessageConverter());
    }

    /**
     *
     * @param message
     * @return
     */
    @MessageMapping("/message/{room}")
    @SendTo("/topic/messages/{room}")
    public Message sendMessage(final Message message){
        messagingTemplate.convertAndSend("/topic/messages/"+ message.getRecipient(), message.toString().getBytes());
        return message;
    }
}
