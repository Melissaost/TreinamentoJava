package br.jus.tjba.api.push.publicador.job;

import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import br.jus.tjba.api.push.publicador.repository.MensagemPendenteRepository;
import br.jus.tjba.api.push.publicador.service.PublicacaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublicarNotificacaoJob {

    @Autowired
    private MensagemPendenteRepository mensagemPendenteRepository;

    @Autowired
    private PublicacaoService publicacaoService;

    @Scheduled(cron = "0 * * * * *") // Executa a cada minuto
    @Transactional
    public void processarUsuariosPendentes() {
        List<MensagemPendente> mensagemPendentes = mensagemPendenteRepository.findUsuariosPendentes();

        for (MensagemPendente mensagem : mensagemPendentes) {
            publicacaoService.publicarMensagem(mensagem.getUsuarioSistema().getLogin(),
                    mensagem.getNumeroProcesso());
            mensagemPendenteRepository.delete(mensagem);
        }
    }
}
