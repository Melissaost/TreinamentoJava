package br.jus.tjba.api.push.notificador.ampq;
import br.jus.tjba.api.push.notificador.dto.UsuariosNotificar;
import br.jus.tjba.api.push.notificador.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublicadorListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "usuarios.notificar")
    public void recebeMensagem(UsuariosNotificar usuariosNotificar){
        String subject = usuariosNotificar.siglaSistema() + " - O processo número " + usuariosNotificar.numeroProcesso() + " foi movimentado";
        emailService.sendEmail(usuariosNotificar.usuarioSistemaDTO().login(), subject, usuariosNotificar.mensagem());
    }
}
