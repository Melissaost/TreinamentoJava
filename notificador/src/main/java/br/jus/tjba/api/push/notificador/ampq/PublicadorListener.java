package br.jus.tjba.api.push.notificador.ampq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PublicadorListener {

    @RabbitListener(queues = "usuarios.notificar")
    public void recebeMensagem(Message message){
        System.out.println("Recebi a mensagem. ");
    }
}
