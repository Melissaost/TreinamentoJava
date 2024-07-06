package br.jus.tjba.api.push.publicador.service;

import br.jus.tjba.api.push.publicador.dto.UsuarioSistemaDTO;
import br.jus.tjba.api.push.publicador.http.NotificadorClient;
import br.jus.tjba.api.push.publicador.http.UsuarioClient;
import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import br.jus.tjba.api.push.publicador.model.UsuarioSistema;
import br.jus.tjba.api.push.publicador.repository.MensagemPendenteRepository;
import br.jus.tjba.api.push.publicador.repository.UsuarioSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacaoService {

    @Autowired
    private final UsuarioClient usuarioClient;

    @Autowired
    private NotificadorClient notificadorClient;

    @Autowired
    private UsuarioSistemaRepository usuarioSistemaRepository;

    @Autowired
    private MensagemPendenteRepository mensagemPendenteRepository;

    public PublicacaoService(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    public List<UsuarioSistemaDTO> publicarMensagem(String siglaSistema, String numeroProcesso) {
        List<UsuarioSistemaDTO> usuarios = usuarioClient.getUsuarios(siglaSistema, numeroProcesso).getBody();
        return usuarios;
    }

    public void mensagemPendente(String numeroProcesso, Long idUsuarioSistema) {
        UsuarioSistema usuarioSistema = usuarioSistemaRepository.findById(idUsuarioSistema)
                .orElseThrow(() -> new RuntimeException("Usuário sistema não encontrado com ID: " + idUsuarioSistema));
        MensagemPendente mensagemPendente = new MensagemPendente();
        mensagemPendente.setNumeroProcesso(numeroProcesso);
        mensagemPendente.setUsuarioSistema(usuarioSistema);
        mensagemPendente.setMensagem("Email não enviado");
        mensagemPendenteRepository.save(mensagemPendente);
    }

    public String notificar() {
        String notificar = notificadorClient.notificar();
        System.out.println(notificar);
        return notificar;
    }
}
