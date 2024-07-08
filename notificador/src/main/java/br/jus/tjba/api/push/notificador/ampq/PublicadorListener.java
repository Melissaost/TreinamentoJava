package br.jus.tjba.api.push.notificador.ampq;
import br.jus.tjba.api.push.notificador.dto.UsuariosNotificar;
import br.jus.tjba.api.push.notificador.service.EmailService;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component
public class PublicadorListener {

    private static final Logger log = LoggerFactory.getLogger(PublicadorListener.class);

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "usuarios.notificar")
    public void recebeMensagem(UsuariosNotificar usuariosNotificar){
        String subject = usuariosNotificar.siglaSistema() + " - O processo número " + usuariosNotificar.numeroProcesso() + " foi movimentado";
        log.info("Recebida mensagem para notificar usuário: {}", usuariosNotificar.usuarioSistemaDTO().login());
        emailService.sendEmail(usuariosNotificar.usuarioSistemaDTO().login(), subject, usuariosNotificar.mensagem());
        log.info("Email enviado para: {}", usuariosNotificar.usuarioSistemaDTO().login());
    }
}
