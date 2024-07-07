package br.jus.tjba.api.push.notificador.ampq;

import br.jus.tjba.api.push.notificador.dto.UsuarioSistemaDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublicadorListener {

    @RabbitListener(queues = "usuarios.notificar")
    public void recebeMensagem(List<UsuarioSistemaDTO> usuarioSistemaDTOList){
        for (UsuarioSistemaDTO usuarioSistemaDTO : usuarioSistemaDTOList) {
            System.out.println(usuarioSistemaDTO);
        }
        System.out.println("Recebi a mensagem. ");
    }
}
