package br.jus.tjba.api.push.publicador.service;

import br.jus.tjba.api.push.publicador.dto.UsuarioSistemaDTO;
import br.jus.tjba.api.push.publicador.dto.UsuariosNotificar;
import br.jus.tjba.api.push.publicador.http.UsuarioClient;
import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import br.jus.tjba.api.push.publicador.model.UsuarioSistema;
import br.jus.tjba.api.push.publicador.repository.MensagemPendenteRepository;
import br.jus.tjba.api.push.publicador.repository.UsuarioSistemaRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PublicacaoService {

    @Autowired
    private final UsuarioClient usuarioClient;

    @Autowired
    private UsuarioSistemaRepository usuarioSistemaRepository;

    @Autowired
    private MensagemPendenteRepository mensagemPendenteRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public PublicacaoService(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    public void publicarMensagem(String siglaSistema, String numeroProcesso) {
        List<UsuarioSistemaDTO> usuarios = usuarioClient.getUsuarios(siglaSistema, numeroProcesso).getBody();
        for (UsuarioSistemaDTO usuario : Objects.requireNonNull(usuarios)) {
            String mensagem = buildEmailMessage(usuario.nome(), numeroProcesso, siglaSistema);
            rabbitTemplate.convertAndSend("usuarios.notificar", new UsuariosNotificar(usuario, siglaSistema, numeroProcesso, mensagem));
        }
    }

    public void mensagemPendente(String siglaSistema, String numeroProcesso) {
        UsuarioSistema usuarioSistema = usuarioSistemaRepository.findByLoginUsuarioSistema(siglaSistema)
                .orElseThrow(() -> new RuntimeException("Usuário sistema não encontrado com login: " + siglaSistema));
        MensagemPendente mensagemPendente = new MensagemPendente();
        mensagemPendente.setNumeroProcesso(numeroProcesso);
        mensagemPendente.setUsuarioSistema(usuarioSistema);
        mensagemPendente.setMensagem("Email não enviado");
        mensagemPendenteRepository.save(mensagemPendente);
    }

    public static String buildEmailMessage(String nomeUsuario, String numeroProcesso, String siglaSistema) {
        return "Prezado(a) " + nomeUsuario + ",\n\n" +
                "Número do processo: " + numeroProcesso + " foi movimentado recentemente. \n" +
                "Sistema de origem: " + siglaSistema + "\n\n" +
                "Atenciosamente,\n" +
                "Equipe do Sistema Judicial";
    }
}
